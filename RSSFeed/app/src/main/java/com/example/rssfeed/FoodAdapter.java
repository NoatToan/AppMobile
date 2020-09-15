package com.example.rssfeed;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends BaseAdapter {
    private List<Food> listFood;
    private LayoutInflater layoutInflater;
    private Context context;
    private String cate;

    public FoodAdapter(Context context, List<Food> listFood,String cate) {
        this.context = context;
        this.listFood = listFood;
        this.cate=cate;
        layoutInflater = LayoutInflater.from(context);
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
        Viewholder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_food, null);
            holder = new Viewholder();
            holder.text = convertView.findViewById(R.id.textFood);
            holder.img = convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (Viewholder) convertView.getTag();
        }
        final Food food = this.listFood.get(position);
        holder.text.setText(food.getName());

        Glide.with(context).load(food.getImg()).into(holder.img);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.customdialog);

                TextView txtBig=(TextView) dialog.findViewById(R.id.txtTitleBig);
                txtBig.setText(cate);

                TextView txtDesc=(TextView) dialog.findViewById(R.id.txtDesc);
                txtDesc.setText(food.getDes());

                ImageView img=(ImageView) dialog.findViewById(R.id.imgFood);
                Glide.with(context).load(food.getImg()).into(img);

                TextView txtTitle=(TextView) dialog.findViewById(R.id.txtTitle);
                txtTitle.setText(food.getName());
                TextView txtpubDate=dialog.findViewById(R.id.txtpubDate);
                txtpubDate.setText(food.getPubDate());

                Button btnMore =(Button) dialog.findViewById(R.id.btnMore);
                Button btnClose=(Button) dialog.findViewById(R.id.btnClose);

                btnMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context,webViewFood.class);
                        i.putExtra("link",food.getLink());
                        context.startActivity(i);

                    }
                });
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        return convertView;
    }

    static class Viewholder {
        ImageView img;
        TextView text;
    }

}
