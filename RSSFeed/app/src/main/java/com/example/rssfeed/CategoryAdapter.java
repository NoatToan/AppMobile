package com.example.rssfeed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private List<Category> listCate;
    private LayoutInflater layoutInflater;
    private Context context;

    public CategoryAdapter(Context context, List<Category> listCate) {
        this.context = context;
        this.listCate = listCate;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listCate.size();
    }

    @Override
    public Object getItem(int position) {
        return listCate.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_category, null);
            holder = new Viewholder();
            holder.text = convertView.findViewById(R.id.textCategory);
            convertView.setTag(holder);
        } else {
            holder = (Viewholder) convertView.getTag();
        }
        final Category cate = this.listCate.get(position);
        holder.text.setText(cate.getName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FoodActivity.class);
                i.putExtra("url", cate.getUrlRss());
                i.putExtra("cate",cate.getName());
                context.startActivity(i);
            }
        });
        return convertView;
    }

    static class Viewholder {
        TextView text;
    }
}
