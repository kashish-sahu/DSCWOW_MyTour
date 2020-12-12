package com.codigo.mytour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacilitiesActivity extends AppCompatActivity {
    RecyclerView facilityView;
    List<FacilityClass> facilityList;
    TextView fuel,atm,rental,bank;
    RequestQueue mqueue;
    FacilitiesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities);
        facilityView = findViewById(R.id.facilityView);
        fuel=findViewById(R.id.fuel);
        atm=findViewById(R.id.atm);
        rental=findViewById(R.id.rentals);
        bank=findViewById(R.id.bank);
        facilityList=new ArrayList<>();
        mqueue= Volley.newRequestQueue(this);
        adapter = new FacilitiesAdapter(this,facilityList);
        facilityView.setLayoutManager(new LinearLayoutManager(this));
        facilityView.setAdapter(adapter);

        fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFacilities("fuel");
            }
        });
        atm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFacilities("atm");
            }
        });
        rental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFacilities("car_rental");
            }
        });
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFacilities("bank");
            }
        });
    }

    private void getFacilities(String tag) {
        StringRequest request = new StringRequest(Request.Method.GET, ConstantVariables.facilitiesfirst+tag+ConstantVariables.facilitiessecond, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("RESP:",response);
                try {
                    JSONObject object =  new JSONObject(response);
                    JSONArray array = object.getJSONArray("features");
                    facilityList.clear();
                    for(int i=0;i<array.length();i++){
                        JSONObject obj = array.getJSONObject(i);
                        JSONObject prop = obj.getJSONObject("properties");
                        if(!prop.getString("name").isEmpty()){
                            String name = prop.getString("name");
                            JSONObject geo = obj.getJSONObject("geometry");
                            JSONArray jarray = geo.getJSONArray("coordinates");
                            String lat = jarray.getString(0);
                            String longi = jarray.getString(1);
                            facilityList.add(new FacilityClass(name.toString(),tag,lat,longi));
                        }
                    }
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
                umap.put("kinds",tag);
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