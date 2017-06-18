package com.jkd_apps.stepsahead;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.volley.Request.Method.GET;

/**
 * Created by Jack on 18-Jun-17.
 */

public class ShopDetailsActivity extends AppCompatActivity {

    //items needed to populate your gridview.I'm assuming you need to display an image with its desciption
    public static final String image = "small";
    public static final String desc = "title";
    Bundle bundle;
    String url;
    //GridView Object
    private GridView gridView;

    private ArrayList<String> item_image;
    private ArrayList<String> item_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);  //change it to your gridview layout
        bundle = getIntent().getExtras();
        int catid = bundle.getInt("catID");
        url = "http://www.stepsahead.in/shop/index.php/apidata/product/"+(catid+1);
        gridView = (GridView) findViewById(R.id.gridView);
        item_image = new ArrayList<>();
        item_desc = new ArrayList<>();
        //getting the json data from api
        parseJSON();

       // Intent intent = getIntent();
      //  String id = intent.getStringExtra("catid");

    }

    private void parseJSON(){

        JsonObjectRequest req = new JsonObjectRequest(GET,
                url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Parsing json object response
                    // response will be a json object
                    JSONArray res = response.getJSONArray("category");
                    for(int i=0; i<res.length(); i++){
                        JSONObject jsonObject = (JSONObject) res.get(i);
                        item_image.add(jsonObject.getString(image));
                        item_desc.add(jsonObject.getString(desc));
                    }

                    Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                    displayItem();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(req);
    }

    //Method to get the item info...

    private void displayItem(){
        Toast.makeText(this,"inside display",Toast.LENGTH_SHORT).show();
        //Creating GridViewAdapter Object
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this,item_image,item_desc);
        //Adding adapter to gridview
        gridView.setAdapter(gridViewAdapter);
    }

}
