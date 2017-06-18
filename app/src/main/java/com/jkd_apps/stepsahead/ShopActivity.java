package com.jkd_apps.stepsahead;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

public class ShopActivity extends AppCompatActivity {

    public final String url = "http://www.stepsahead.in/shop/index.php/apidata/category";
    public static final String catid = "id";
    public static final String title = "name";
    private ArrayList<String> category;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        category = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listView);
        parseJson();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),""+category.get(position),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShopActivity.this,ShopDetailsActivity.class);
                intent.putExtra("catID",position);
                startActivity(intent);
            }
        });
    }

    private void parseJson(){

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
                        category.add(i,jsonObject.getString(title));
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

    private void displayItem(){
        //Toast.makeText(this,"inside display",Toast.LENGTH_SHORT).show();
        ListViewAdapter listViewAdapter = new ListViewAdapter(this,category);
        listView.setAdapter(listViewAdapter);
    }

}
