package com.vishwaraj.kamgarchowk.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.vishwaraj.kamgarchowk.R;
import com.vishwaraj.kamgarchowk.model.HomeResponse;

import java.util.List;


public class HomeCategoryGridAdapter extends BaseAdapter {

    Activity context;
    List<HomeResponse.Featuredlist> list;

    public HomeCategoryGridAdapter(Activity context, List<HomeResponse.Featuredlist> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final View myView;
        if (convertView == null) {  // if it's not recycled, initialize some attribute
            //Inflate the layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myView = inflater.inflate(R.layout.item_home_grid, null);
        } else {
            myView = (View) convertView;
        }
        HomeResponse.Featuredlist featuredlist = list.get(position);

        TextView textView = (TextView) myView.findViewById(R.id.textView);
        ImageView imageGrid=(ImageView) myView.findViewById(R.id.imageGrid);
        textView.setText(featuredlist.getName());
        //myImage.setImageDrawable(featuredlist.getCategoryicon());
        Picasso.with(context)
                .load(featuredlist.getCategoryicon())
                .placeholder(R.drawable.weldericon)
                .into(imageGrid);


        return myView;
    }


}