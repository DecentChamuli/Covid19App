package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Vaccines extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines);

        fetchVaccine();
    }

    private void fetchVaccine(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest JsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://disease.sh/v3/covid-19/vaccine/coverage/countries?lastdays=1",
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0; i<5; i++){
//                    for(int i=0; i<response.length(); i++){

                        JSONObject obj = response.getJSONObject(i);
                        String countryFetch = obj.getString("country");
                        Country country = new Country();
                        country.setCountryName(countryFetch);

                        JSONObject timeline = obj.getJSONObject("timeline");
                        int OnDateVaccines = timeline.getInt("9/20/21");

                        Log.d("myresponse", "Vaccine Done " + OnDateVaccines + " of Country " + countryFetch);
                    }
                }
                catch (JSONException e) { e.printStackTrace(); }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myresponse", "Something went wrong");
                Toast.makeText(Vaccines.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(JsonArrayRequest);
        requestQueue.getCache().clear();
    }
}