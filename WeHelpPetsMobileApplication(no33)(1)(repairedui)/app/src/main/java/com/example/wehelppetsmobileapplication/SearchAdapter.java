package com.example.wehelppetsmobileapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder>{

    ArrayList<SearchUser> list;

    public SearchAdapter(ArrayList<SearchUser> list)
    {
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_users, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        holder.uname.setText(list.get(i).getName());
        holder.uemail.setText(list.get(i).getEmail());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView uname, uemail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            uname = itemView.findViewById(R.id.nameTv);
            uemail = itemView.findViewById(R.id.emailTv);


        }
    }

}
