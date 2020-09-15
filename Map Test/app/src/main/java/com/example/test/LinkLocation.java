package com.example.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LinkLocation extends AppCompatActivity {

    Button btnLink,btnLocation;
    TextView txtName;
    String name,link,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link_location);

        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        link = extras.getString("link");
        location = extras.getString("location");
        txtName = findViewById(R.id.txtName);
        txtName.setText(name);

        btnLink = (Button)findViewById(R.id.btnLink);
        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    startActivity(intent);
            }
        });
        btnLocation = (Button)findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(location));
                startActivity(intent);
            }
        });
    }
}


