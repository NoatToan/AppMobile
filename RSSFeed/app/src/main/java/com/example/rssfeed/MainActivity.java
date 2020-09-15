package com.example.rssfeed;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.Window;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView) findViewById(R.id.listViewCate);
        listView.setAdapter(new CategoryAdapter(this,getData()));
    }

    private ArrayList<Category> getData() {
        ArrayList<Category> listCate= new ArrayList<Category>();
        Category c1 = new Category("Delicious Healthy Recipes","https://www.skinnytaste.com/feed/");
        Category c2 = new Category("Paleo Recipes","https://nomnompaleo.com/feed");
        Category c3 = new Category("Simple recipes","https://addapinch.com/feed/");
        Category c4 = new Category("Celebrating Delicious and Easy Recipes","https://www.gimmesomeoven.com/feed/");
        Category c5 = new Category("Vegan Recipes","https://ohsheglows.com/feed/");
        Category c6 = new Category("Delicious + easy recipes","https://www.melskitchencafe.com/feed/");
        Category c7 = new Category("Recipes for a Wholesome Life","https://www.wellplated.com/feed/");
        Category c8 = new Category("A food blog with easy, decadent recipes","https://www.browneyedbaker.com/feed/");
        Category c9 = new Category("Paris based chef recipes","https://www.davidlebovitz.com/feed/");
        Category c10= new Category("Naturally food recipes","https://naturallyella.com/feed/");

        listCate.add(c1);
        listCate.add(c2);
        listCate.add(c3);
        listCate.add(c4);
        listCate.add(c5);
        listCate.add(c6);
        listCate.add(c7);
        listCate.add(c8);
        listCate.add(c9);
        listCate.add(c10);

        return listCate;
    }


}

