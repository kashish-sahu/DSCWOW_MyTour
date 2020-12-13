package com.codigo.mytour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelsActivity extends AppCompatActivity {
    List<HotelClass> hotelsList;
    RecyclerView hotelsView;
    RequestQueue mqueue;
    HotelsAdapter adapter;
    ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        hotelsView=findViewById(R.id.hotelsView);
        hotelsList=new ArrayList<>();
        mqueue= Volley.newRequestQueue(this);
        loading=findViewById(R.id.loading);
        hotelsView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HotelsAdapter(this,hotelsList);
        hotelsView.setAdapter(adapter);
        getHotels();
    }

    private void getHotels() {
        StringRequest request = new StringRequest(Request.Method.GET, ConstantVariables.facilitiesfirst+"other_hotels"+ConstantVariables.facilitiessecond, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("RESP:",response);
                try {
                    JSONObject object =  new JSONObject(response);
                    JSONArray array = object.getJSONArray("features");
                    hotelsList.clear();
                    for(int i=0;i<array.length();i++){
                        JSONObject obj = array.getJSONObject(i);
                        JSONObject prop = obj.getJSONObject("properties");
                        if(!prop.getString("name").isEmpty()){
                            String name = prop.getString("name");
                            String kinds=prop.getString("kinds");
                            JSONObject geo = obj.getJSONObject("geometry");
                            JSONArray jarray = geo.getJSONArray("coordinates");
                            String lat = jarray.getString(0);
                            String longi = jarray.getString(1);
                            hotelsList.add(new HotelClass(name,kinds,R.drawable.hotel_img,"300",lat,longi));
                        }
                    }
                    loading.setVisibility(View.INVISIBLE);
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> umap=new HashMap<>();
                umap.put("radius","7000");
                umap.put("lon","76.6552");
                umap.put("lat","7000");
                umap.put("kinds","other_hotels");
                umap.put("apikey",ConstantVariables.API_KEY);
                return umap;
            }
        };
        mqueue.add(request).setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 5000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 5000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                error.printStackTrace();
            }
        });
    }
}