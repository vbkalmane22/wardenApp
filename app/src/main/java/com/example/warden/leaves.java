package com.example.warden;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class leaves extends AppCompatActivity {
RecyclerView recyclerView;

TextView usnView;
ArrayList<User>userArrayList;
MyAdapter myAdapter;
FirebaseFirestore db;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaves);
        // Button approvebtn=findViewById(R.id.button);
       // Button rejectbtn=findViewById(R.id.button1);
        //TextView usnView = findViewById(R.id.usnView);
   progressDialog=new ProgressDialog(this);
      progressDialog.setCancelable(false);
      progressDialog.setMessage("Fetching the data...");
      progressDialog.show();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<User>();
        myAdapter = new MyAdapter(leaves.this, userArrayList);

        recyclerView.setAdapter(myAdapter);


        EventChangeListener();

    }

    private void EventChangeListener() {
        db.collection("leaves").orderBy("value", Query.Direction.ASCENDING)
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
                                  userArrayList.add(dc.getDocument().toObject(User.class));
                              }
                              myAdapter.notifyDataSetChanged();
                              if(progressDialog.isShowing())
                                  progressDialog.dismiss();
                          }
                    }
                });
    }
}