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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FacilitiesAdapter extends RecyclerView.Adapter<FacilitiesAdapter.MyViewHolder> {
    Activity mContext;
    List<FacilityClass> facility_list;

    public FacilitiesAdapter(Activity mContext, List<FacilityClass> facility_list) {
        this.mContext = mContext;
        this.facility_list = facility_list;
    }
    @NonNull
    @Override
    public FacilitiesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.facility_card,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FacilitiesAdapter.MyViewHolder holder, int position) {
        holder.count.setText(String.valueOf(position+1));
        holder.name.setText(String.valueOf(facility_list.get(position).getName()));
        holder.tag.setText(String.valueOf(facility_list.get(position).getTag()));
        holder.direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://maps.google.com/maps?daddr="+facility_list.get(position).getLongitude()+","+facility_list.get(position).getLatitude();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return facility_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,tag,count;
        LinearLayout direction;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            tag=itemView.findViewById(R.id.tag);
            count = itemView.findViewById(R.id.count);
            direction=itemView.findViewById(R.id.direction);
        }
    }
}
