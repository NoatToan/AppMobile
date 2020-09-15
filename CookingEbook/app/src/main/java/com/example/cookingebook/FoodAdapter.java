package com.example.cookingebook;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class FoodAdapter extends BaseAdapter {
    private List<Food> listFood;
    private LayoutInflater layoutInflater;
    private Context context;

    public FoodAdapter(Context fcontext,List<Food> list)
    {
        this.context=fcontext;
        this.listFood=list;
        layoutInflater=LayoutInflater.from(fcontext);
    }

    @Override
    public int getCount() {
        return listFood.size();
    }

    @Override
    public Object getItem(int position) {
        return listFood.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null)
        {
            convertView=layoutInflater.inflate(R.layout.list_item_layout,null);
            holder = new ViewHolder();
            holder.foodImg= convertView.findViewById(R.id.imageView);
            holder.foodName=convertView.findViewById(R.id.textView_foodName);
            holder.high=convertView.findViewById(R.id.imageHigh);
            holder.r=convertView.findViewById(R.id.r2);
            convertView.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) convertView.getTag();
        }
        final Food food = this.listFood.get(position);
        holder.foodName.setText(food.getName());

        final int resID = context.getResources().getIdentifier(food.getImage(),
                "drawable", context.getPackageName());
        Bitmap b = BitmapFactory.decodeResource(context.getResources(),resID);
        Bitmap scaled = Bitmap.createScaledBitmap(b, 100, 100, true);
        holder.foodImg.setImageBitmap(scaled);
        holder.high.setImageBitmap(scaled);

        holder.r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,ImageHigh.class);
                i.putExtra("idImg",resID);
                context.startActivity(i);
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,Detail.class);
                i.putExtra("name", food.getName());
                i.putExtra("des", food.getDes());
                i.putExtra("prep", String.valueOf(food.getPrep()));
                i.putExtra("cook", String.valueOf(food.getCook()));
                i.putExtra("ingre", food.getIngre());
                context.startActivity(i);
            }
        });
        return convertView;

    }
   /*public Drawable getBitmapResIdByName(String resName)
    {
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(resName, "drawable",
                context.getPackageName());
        return resources.getDrawable(resourceId);
    }*/

    static class ViewHolder{
        ImageView foodImg;
        TextView foodName;
        RelativeLayout r;
        ImageView high;
    }

}
