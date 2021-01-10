package com.example.myportfolio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> titleList = new LinkedList<>();
    private final LinkedList<String> imageList = new LinkedList<>();
    private final LinkedList<String> bodyList = new LinkedList<>();
    private final LinkedList<String> urlList = new LinkedList<>();
    private PortfolioListAdapter adapter;
    String jsonUrl ="https://thibautherremans.be/api/portfolio";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView portfolioRecycler = findViewById(R.id.recycler);
        adapter = new PortfolioListAdapter(titleList, imageList, bodyList, urlList, this);
        portfolioRecycler.setAdapter(adapter);
        portfolioRecycler.setLayoutManager(new LinearLayoutManager(this));

        Context context = getApplicationContext();
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, jsonUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject itemDetails = response.getJSONObject(i);
                        String title = itemDetails.getString("title");
                        titleList.add(title);
                        String image = itemDetails.getString("image");
                        imageList.add(image);
                        String body = itemDetails.getString("body");
                        bodyList.add(body);
                        String url = itemDetails.getString("url");
                        urlList.add(url);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("json", "Failed to connect to Json");
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}