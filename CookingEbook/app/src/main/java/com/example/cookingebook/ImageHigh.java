package com.example.cookingebook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageHigh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_high);
        ImageView img=(ImageView)findViewById(R.id.imageView2);

        if(getIntent().hasExtra("idImg"))
        {
            int idImg=getIntent().getIntExtra("idImg",0);
            Bitmap b = BitmapFactory.decodeResource(getResources(),idImg);
            //Bitmap scaled = Bitmap.createScaledBitmap(b, 1480, 1480, true);
           img.setImageBitmap(b);
        }
    }
}
