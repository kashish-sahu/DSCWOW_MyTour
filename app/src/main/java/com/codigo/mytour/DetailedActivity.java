package com.codigo.mytour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class DetailedActivity extends AppCompatActivity {
    ImageView locate,n_image;
    TextView n_name,n_location,n_description;
    String latitude,longitude,image,name,description,location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        locate = findViewById(R.id.locate);
        n_image=findViewById(R.id.mainimg);
        n_name=findViewById(R.id.name);
        n_location=findViewById(R.id.location);
        n_description=findViewById(R.id.description);
        Intent intent = getIntent();
        latitude=intent.getStringExtra("latitude");
        longitude=intent.getStringExtra("longitude");
        image=intent.getStringExtra("image");
        description=intent.getStringExtra("description");
        name=intent.getStringExtra("name");
        location=intent.getStringExtra("location");
        n_description.setText(description);
        n_location.setText(location);
        n_name.setText(name);
        Glide.with(this)
                .load(image)
                .into(n_image);
        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://maps.google.com/maps?daddr="+latitude+","+longitude;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}