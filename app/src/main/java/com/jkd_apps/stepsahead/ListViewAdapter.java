package com.jkd_apps.stepsahead;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jack on 18-Jun-17.
 */

public class ListViewAdapter extends BaseAdapter {

    //Context
    private Context context;

    //Array List that would contain the urls and the titles for the images
    private ArrayList<String> category;

    public ListViewAdapter(Context context, ArrayList<String> category){
        //Getting all the values
        this.context = context;
        this.category = category;
    }

    @Override
    public int getCount() {
        return category.size();
    }

    @Override
    public Object getItem(int position) {
        return category.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Creating a linear layout
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        //Creating a textview to show the category
        TextView textView = new TextView(context);
        textView.setText(category.get(position));
        //Adding views to the layout
        linearLayout.addView(textView);
        //Returnint the layout
        return linearLayout;
    }
}
