package com.codigo.mytour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodsActivity extends AppCompatActivity {
    RecyclerView foodsView;
    List<FoodClass> foodList;
    RequestQueue mqueue;
    FoodAdapter adapter;
    ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);
        foodList=new ArrayList<>();
        mqueue= Volley.newRequestQueue(this);
        foodsView=findViewById(R.id.foodsView);
        loading=findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);
        foodsView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodAdapter(this,foodList);
        foodsView.setAdapter(adapter);
        getFood();
    }

    private void getFood() {
        StringRequest request = new StringRequest(Request.Method.GET, ConstantVariables.facilitiesfirst+"foods"+ConstantVariables.facilitiessecond, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("RESP:",response);
                try {
                    JSONObject object =  new JSONObject(response);
                    JSONArray array = object.getJSONArray("features");
                    foodList.clear();
                    for(int i=0;i<array.length();i++){
                        JSONObject obj = array.getJSONObject(i);
                        JSONObject prop = obj.getJSONObject("properties");
                        if(!prop.getString("name").isEmpty()){
                            String name = prop.getString("name");
                            JSONObject geo = obj.getJSONObject("geometry");
                            JSONArray jarray = geo.getJSONArray("coordinates");
                            String lat = jarray.getString(0);
                            String longi = jarray.getString(1);
                            if(name.toLowerCase().contains("kfc")){
                                foodList.add(new FoodClass(name,R.drawable.kfc,"kfc",lat,longi));
                            }else if(name.toLowerCase().contains("dominos")){
                                foodList.add(new FoodClass(name,R.drawable.download,"dominos",lat,longi));
                            }else if(name.toLowerCase().contains("cafe")){
                                foodList.add(new FoodClass(name,R.drawable.cafe,"cafe",lat,longi));
                            }else{
                                foodList.add(new FoodClass(name,R.drawable.hotel,"hotel",lat,longi));
                            }
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
                umap.put("kinds","food");
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