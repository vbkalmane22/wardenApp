package com.example.warden;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
       Context context;
    TextView name,usn,reason,date_of_apply,status,parent_contact;
    FirebaseFirestore db;
       ArrayList<User>userArrayList;

    public MyAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
       @Override
       public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
           return new MyViewHolder(v);
       }

       @Override
       public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
          User user=userArrayList.get(position);
          holder.name.setText(user.name);
           holder.usn.setText(user.usn);
           holder.reason.setText(user.reason);
           holder.parent_contact.setText(String.valueOf(user.parent_contact));
           holder.status.setText(user.status);
           holder.date_of_apply.setText(user.date_of_apply);

          

           holder.btnApprove.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   //Toast.makeText(context, "Approved", Toast.LENGTH_SHORT).show();
                    String usn1=holder.usn.getText().toString();
                    String sta="Approved";
                    String val="2";
                   // String Status1="Approved";

                   Map<String,Object> user=new HashMap<>();
                   user.put("status",sta);
                   user.put("value",val);
                   FirebaseFirestore.getInstance().collection("leaves").document(usn1).update(user)
                           .addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void unused) {
                                  Toast.makeText(context, "Approved", Toast.LENGTH_SHORT).show();
                                   holder.btnApprove.setEnabled(false);

                               }
                           }).addOnFailureListener(new OnFailureListener() {
                               @Override
                               public void onFailure(@NonNull Exception e) {
                                   Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
                               }
                           });

               }
           });

           holder.btnReject.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   //Toast.makeText(context, "Rejected", Toast.LENGTH_SHORT).show();
                   String usn1=holder.usn.getText().toString();
                   String sta="Rejected";

                   Map<String,Object> user=new HashMap<>();
                   user.put("status",sta);
                   FirebaseFirestore.getInstance().collection("leaves").document(usn1).update(user)
                           .addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void unused) {
                                   Toast.makeText(context, "Ree", Toast.LENGTH_SHORT).show();
                                   holder.btnReject.setEnabled(false);
                               }
                           }).addOnFailureListener(new OnFailureListener() {
                               @Override
                               public void onFailure(@NonNull Exception e) {
                                   Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
                               }
                           });
               }
           });
       }


    @Override
       public int getItemCount() {
           return userArrayList.size();
       }

       public static class MyViewHolder extends RecyclerView.ViewHolder{
     TextView name,usn,reason,date_of_apply,status,parent_contact;
            Button btnApprove,btnReject;
           public MyViewHolder(@NonNull View itemView) {
               super(itemView);

               name=itemView.findViewById(R.id.nameView);
               usn=itemView.findViewById(R.id.usnView);
               reason=itemView.findViewById(R.id.reasonView);
               date_of_apply=itemView.findViewById(R.id.dateView);
               status=itemView.findViewById(R.id.statusView);
               parent_contact=itemView.findViewById(R.id.contactView);
               btnApprove=itemView.findViewById(R.id.button);
               btnReject=itemView.findViewById(R.id.button1);



           }
       }
   }