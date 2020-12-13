package com.codigo.mytour;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.MyViewHolder> {
    Activity mContext;
    List<HotelClass> hotels_list;

    public HotelsAdapter(Activity mContext, List<HotelClass> hotels_list) {
        this.mContext = mContext;
        this.hotels_list = hotels_list;
    }
    @NonNull
    @Override
    public HotelsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_card,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HotelsAdapter.MyViewHolder holder, int position) {
        holder.name.setText(hotels_list.get(position).getName());
        holder.address.setText(hotels_list.get(position).getAddress());
        Glide.with(mContext)
                .load(R.drawable.hotel_img)
                .into(holder.img);
        holder.cost.setText(String.valueOf(hotels_list.get(position).getPrice()));
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://maps.google.com/maps?daddr="+hotels_list.get(position).getLongitude()+","+hotels_list.get(position).getLatitude();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotels_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        CardView card;
        TextView name,address,cost;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            address=itemView.findViewById(R.id.address);
            cost=itemView.findViewById(R.id.cost);
            card=itemView.findViewById(R.id.card);
        }
    }
}
