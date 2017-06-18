package com.jkd_apps.stepsahead;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

/**
 * Created by Jack on 18-Jun-17.
 */

public class GridViewAdapter extends BaseAdapter {

    private ImageLoader imageLoader;

    //Context
    private Context context;

    //Array List that would contain the urls and the titles for the images
    private ArrayList<String> item_image;
    private ArrayList<String> item_descp;

    public GridViewAdapter (Context context, ArrayList<String> item_image, ArrayList<String> item_descp){
        //Getting all the values
        this.context = context;
        this.item_image = item_image;
        this.item_descp = item_descp;
    }

    @Override
    public int getCount() {
        return item_image.size();
    }

    @Override
    public Object getItem(int position) {
        return item_image.get(position);
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

        //NetworkImageView
        NetworkImageView networkImageView = new NetworkImageView(context);

        //Initializing ImageLoader
        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(item_image.get(position), ImageLoader.getImageListener(networkImageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        //Setting the image url to load
        networkImageView.setImageUrl(item_image.get(position),imageLoader);

        //Creating a textview to show the title
        TextView textView = new TextView(context);
        textView.setText(item_descp.get(position));

        //Scaling the imageview
        networkImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        networkImageView.setLayoutParams(new GridView.LayoutParams(200,200));

        //Adding views to the layout
        linearLayout.addView(textView);
        linearLayout.addView(networkImageView);

        //Returnint the layout
        return linearLayout;
    }
}
