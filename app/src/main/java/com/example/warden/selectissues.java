package com.example.warden;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class selectissues extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<User2> userArrayList2;
    MyAdapter2 myAdapter2;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectissues);

        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching the data...");
        progressDialog.show();
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        userArrayList2 = new ArrayList<User2>();
        myAdapter2 = new MyAdapter2(selectissues.this, userArrayList2);

        recyclerView.setAdapter(myAdapter2);


        EventChangeListener();
    }
    private void EventChangeListener() {
        db.collection("issues")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error!=null){

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc: value.getDocumentChanges()){
                            if(dc.getType()==DocumentChange.Type.ADDED){
                                userArrayList2.add(dc.getDocument().toObject(User2.class));
                            }
                            myAdapter2.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    }
                });
}
}