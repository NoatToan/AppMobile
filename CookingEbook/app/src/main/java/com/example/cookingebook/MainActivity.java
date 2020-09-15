package com.example.cookingebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView=(ListView) findViewById(R.id.my_lit_view);
        List<Food>  foods=getListData();
        listView.setAdapter(new FoodAdapter(this,foods));


    }

    private List<Food> getListData() {
        List<Food> foods= new ArrayList<Food>();

        Food nodle=new Food("ROSEMARY CHICKEN NOODLE SOUP","nodle",8,22);
        nodle.setDes(getApplication().getResources().getString(R.string.description1));
        nodle.setIngre(getApplication().getResources().getString(R.string.ingredient1));

        Food salad=new Food("SUNSHINE CITRUS AVOCADO SALAD","salad",20,0);
        salad.setIngre(getApplication().getResources().getString(R.string.ingredient2));
        salad.setDes(getApplication().getResources().getString(R.string.description2));

        Food chicken=new Food("BEST CHICKEN ENCHILADAS EVER!","chicken",15,45);
        chicken.setIngre(getApplication().getResources().getString(R.string.ingredient3));
        chicken.setDes(getApplication().getResources().getString(R.string.description3));

        foods.add(nodle);
        foods.add(salad);
        foods.add(chicken);

        return foods;
    }
}
