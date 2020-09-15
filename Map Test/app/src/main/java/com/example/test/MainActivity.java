package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lst;
    PlacesList[] items;
    String[] place;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        prepareData();
        place = new String[items.length];
        for(int i=0;i<items.length;i++){
            place[i] = items[i].getPlaceName();
        }
        adapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,place);
        lst = findViewById(R.id.lstTour);
        lst.setAdapter(adapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = items[position].getPlaceName();
                String link = items[position].getPlaceLink();
                String location = items[position].getPlaceLocation();
                Intent i = new Intent(getApplicationContext(), LinkLocation.class);
                i.putExtra("name",name);
                i.putExtra("link",link);
                i.putExtra("location",location);
                startActivity(i);
            }
        });
    }
    private boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return check == PackageManager.PERMISSION_GRANTED;
    }
    private void prepareData() {
        items = new PlacesList[4];
        items[0] = new PlacesList("Cleveland Tower City", "http://www.google.com",
                R.drawable.ic_launcher_background, "geo:0,0?q=230+W+Huron+Rd+Cleveland+OH+44113+Hoa+Kỳ");
        items[1] = new PlacesList("Browns Football Field", "http://www.google.com",
                R.drawable.ic_launcher_background, "geo:0,0?q=FirstEnergy+Stadium+Alfred+Lerner+Way+Cleveland+OH+Hoa+Kỳ");
        items[2] = new PlacesList("Cleveland State University", "http://www.google.com",
                R.drawable.ic_launcher_background, "geo:0,0?q=Cleveland+State+University+Euclid+Avenue+Cleveland+Ohio+Hoa+Kỳ");
        items[3] = new PlacesList("Playhouse Square", "http://www.google.com",
                R.drawable.ic_launcher_background, "geo:0,0?q=Playhouse+Square+Euclid+Avenue+Cleveland+Ohio+Hoa+Kỳ");
    }
}
