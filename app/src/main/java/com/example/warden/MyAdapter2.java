package com.example.warden;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {

    Context context2;
    ArrayList<User2> userArrayList2;

    public MyAdapter2(Context context2, ArrayList<User2> userArrayList2) {
        this.context2 = context2;
        this.userArrayList2 = userArrayList2;
    }

    @NonNull
    @Override
    public MyAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context2).inflate(R.layout.item2, parent, false);
        return new MyAdapter2.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.MyViewHolder holder, int position) {
        User2 user2 = userArrayList2.get(position);
        holder.name.setText(user2.name);
        holder.usn.setText(user2.usn);
        holder.room_no.setText((user2.room));
        holder.description.setText(user2.description);
    }

    @Override
    public int getItemCount() {
        return userArrayList2.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, usn, description, room_no;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name2View);
            usn = itemView.findViewById(R.id.usn2View);
            description = itemView.findViewById(R.id.descriptionView);
            room_no = itemView.findViewById(R.id.roomView);

        }
    }
}
