package com.codigo.mytour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AttractionsActivity extends AppCompatActivity {
    List<AttractionClass> cards_list;
    RecyclerView cardsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);
        cards_list=new ArrayList<>();
        cardsView=findViewById(R.id.cardsView);
        cards_list.add(new AttractionClass("DAINTHLEN FALLS","DAINTHLEN FALLS","","",""));
        cards_list.add(new AttractionClass("MYSORE PALACE","DAINTHLEN FALLS","","",""));
        cards_list.add(new AttractionClass("DAINTHLEN FALLS","DAINTHLEN FALLS","","",""));
        cardsView.setLayoutManager(new LinearLayoutManager(this));
        AttractionAdapter adapter = new AttractionAdapter(this,cards_list);
        cardsView.setAdapter(adapter);
    }
}