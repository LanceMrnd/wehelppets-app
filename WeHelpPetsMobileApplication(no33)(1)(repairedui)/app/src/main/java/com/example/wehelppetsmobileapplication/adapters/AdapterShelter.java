package com.example.wehelppetsmobileapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wehelppetsmobileapplication.R;
import com.example.wehelppetsmobileapplication.ThereProfileActivity;
import com.example.wehelppetsmobileapplication.models.ModelUsers;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterShelter extends RecyclerView.Adapter<AdapterShelter.MyHolder>{

    Context context;
    List<ModelUsers> userList;

    //constructor


    public AdapterShelter(Context context, List<ModelUsers> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_shelter, viewGroup, false);



        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String uid = userList.get(position).getUid();

        String shelter_image = userList.get(position).getImage();
        String shelter_name = userList.get(position).getName();

        holder.sheltername.setText(shelter_name);

        try {

            Picasso.get().load(shelter_image).placeholder(R.drawable.profile_paws).into(holder.shelterimage);

        }

        catch (Exception e){


        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+shelter_name, Toast.LENGTH_SHORT).show();
            }
        });

        holder.shelterimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ThereProfileActivity.class);
                intent.putExtra("uid", uid);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{


        ImageView shelterimage;
        TextView sheltername;


        public MyHolder(@NonNull View itemView) {
            super(itemView);


            shelterimage = itemView.findViewById(R.id.shelter_photo);
            sheltername = itemView.findViewById(R.id.shelter_name);
        }
    }
}
