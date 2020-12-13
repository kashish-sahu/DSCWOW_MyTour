package com.codigo.mytour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AttractionsActivity extends AppCompatActivity {
    List<TourismClass> cards_list;
    RecyclerView cardsView;
    DatabaseReference databaseReference;
    AttractionAdapter adapter;
    ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);
        cards_list=new ArrayList<>();
        cardsView=findViewById(R.id.cardsView);
        loading=findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);
        cardsView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AttractionAdapter(this,cards_list);
        cardsView.setAdapter(adapter);
        getAttractions();
    }
    private void getAttractions(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Places");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cards_list.clear();
            for(DataSnapshot datasnapshot : snapshot.getChildren()){
                TourismClass tourism = datasnapshot.getValue(TourismClass.class);
                cards_list.add(tourism);
            }
            loading.setVisibility(View.INVISIBLE);
            adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}