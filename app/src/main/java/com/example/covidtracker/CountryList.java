package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.data.PieEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountryList extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Country> countries;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        recyclerView = findViewById(R.id.countryList);

        countries = new ArrayList<>();
        fetchCountry();
    }

    private void fetchCountry(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest JsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://disease.sh/v3/covid-19/countries",
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                    try {
                        for (int i = 0; i < 10; i++) {
//                        for(int i=0; i<response.length(); i++){

                            JSONObject obj = response.getJSONObject(i);
                            String countryFetch = obj.getString("country");
                            Country country = new Country();
                            country.setCountryName(countryFetch);

                            countries.add(country);

//                        JSONObject countryInfo = obj.getJSONObject("countryInfo");
//                        String CountryNameLink = countryInfo.getString("iso2");
                            Log.d("myresponse", i + 1 + ". Country: " + obj.getString("country") + ", Array: " + countries);
                        }
                    }
                    catch (JSONException e) { e.printStackTrace(); }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(), countries);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myresponse", "Something went wrong");
                Toast.makeText(CountryList.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(JsonArrayRequest);
        requestQueue.getCache().clear();
    }
}