package com.codigo.mytour;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.os.ParcelableCompatCreatorCallbacks;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    CardView menu,facility,food,accomodation,gallery,map,volunteer;
    ImageView bg,img_a,img_b,img_c,img_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navigationView=findViewById(R.id.nav_menu);
        drawerLayout=findViewById(R.id.drawer_layout);
        menu = findViewById(R.id.menu);
        accomodation=findViewById(R.id.accomodation);
        gallery=findViewById(R.id.gallery);
        map=findViewById(R.id.map);
        volunteer=findViewById(R.id.volunteer);
        food=findViewById(R.id.food);
        bg=findViewById(R.id.bg);
        img_a=findViewById(R.id.img_a);
                img_b=findViewById(R.id.img_b);
                img_c=findViewById(R.id.img_c);
                img_d=findViewById(R.id.img_d);
                Glide.with(this)
                        .load(R.drawable.mainbg)
                        .into(img_a);
                Glide.with(this)
                .load(R.drawable.mainbg)
                .into(img_b);
                Glide.with(this)
                .load(R.drawable.mainbg)
                .into(img_c);
                Glide.with(this)
                .load(R.drawable.mainbg)
                .into(img_d);
        Glide.with(this)
                .load(R.drawable.mainbgnew)
                .into(bg);

        View header = navigationView.getHeaderView(0);
        ImageView img = header.findViewById(R.id.img);
        Glide.with(this)
                .load(R.drawable.mainbg)
                .into(img);

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,FoodsActivity.class));
            }
        });
        facility=findViewById(R.id.facility);
        facility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,FacilitiesActivity.class));
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        accomodation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,HotelsActivity.class));
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,AttractionsActivity.class));
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Will be introduced soon!",Toast.LENGTH_SHORT).show();
            }
        });
        volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Will be introduced soon!",Toast.LENGTH_SHORT).show();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.attraction: startActivity(new Intent(HomeActivity.this,AttractionsActivity.class));
                    break;
                    case R.id.accomodation: startActivity(new Intent(HomeActivity.this,HotelsActivity.class));
                        break;
                    case R.id.food: startActivity(new Intent(HomeActivity.this,FoodsActivity.class));
                    break;
                    case R.id.logout: finish();
                    break;
                }
                return true;
            }
        });
    }
}