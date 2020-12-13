package com.codigo.mytour;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {
    Activity mContext;
    List<FoodClass> food_list;

    public FoodAdapter(Activity mContext, List<FoodClass> food_list) {
        this.mContext = mContext;
        this.food_list = food_list;
    }
    @NonNull
    @Override
    public FoodAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_card,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.MyViewHolder holder, int position) {
        holder.name.setText(String.valueOf(food_list.get(position).getName()));
        holder.tag.setText(String.valueOf(food_list.get(position).getTag()));
        Glide.with(mContext)
                .load(food_list.get(position).getImage())
                .into(holder.img);
        holder.direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://maps.google.com/maps?daddr="+food_list.get(position).getLongitude()+","+food_list.get(position).getLatitude();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return food_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,tag;
        LinearLayout direction;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            tag=itemView.findViewById(R.id.tag);
            direction=itemView.findViewById(R.id.direction);
            img=itemView.findViewById(R.id.img);
        }
    }
}
