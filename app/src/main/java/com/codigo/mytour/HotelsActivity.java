package com.codigo.mytour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class HotelsActivity extends AppCompatActivity {
    List<HotelClass> hotelsList;
    RecyclerView hotelsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        hotelsView=findViewById(R.id.hotelsView);
        hotelsList=new ArrayList<>();
        hotelsList.add(new HotelClass("Mysore Hotel","Sayyaji Rao Rd, Agrahara, Chamrajpura, Mysuru",R.drawable.mainbg,300));
        hotelsList.add(new HotelClass("Mysore Hotel","Sayyaji Rao Rd, Agrahara, Chamrajpura, Mysuru",R.drawable.mainbg,300));
        hotelsList.add(new HotelClass("Mysore Hotel","Sayyaji Rao Rd, Agrahara, Chamrajpura, Mysuru",R.drawable.mainbg,300));
        hotelsList.add(new HotelClass("Mysore Hotel","Sayyaji Rao Rd, Agrahara, Chamrajpura, Mysuru",R.drawable.mainbg,300));
        hotelsList.add(new HotelClass("Mysore Hotel","Sayyaji Rao Rd, Agrahara, Chamrajpura, Mysuru",R.drawable.mainbg,300));
        hotelsView.setLayoutManager(new LinearLayoutManager(this));
        HotelsAdapter adapter = new HotelsAdapter(this,hotelsList);
        hotelsView.setAdapter(adapter);
    }
}