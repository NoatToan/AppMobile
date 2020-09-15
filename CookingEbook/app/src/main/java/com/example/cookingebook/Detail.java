package com.example.cookingebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Detail extends AppCompatActivity {
    TextView name,des,prep,cook,total,ingre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = this.getIntent();
        name= (TextView) findViewById(R.id.textName);
        name.setText(i.getStringExtra("name"));
        des= (TextView) findViewById(R.id.textDes);
        des.setText(i.getStringExtra("des"));

        prep= (TextView) findViewById(R.id.textPrep);
        prep.setText(i.getStringExtra("prep"));
        cook= (TextView) findViewById(R.id.textCook);
        cook.setText(i.getStringExtra("cook"));
        int number=Integer.valueOf(i.getStringExtra("prep"))+Integer.valueOf(i.getStringExtra("cook"));
        total= (TextView) findViewById(R.id.textTotal);
        total.setText(String.valueOf(number));
        ingre= (TextView) findViewById(R.id.textING);
        ingre.setText(i.getStringExtra("ingre"));
    }
}
