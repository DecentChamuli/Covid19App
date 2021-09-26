package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Vaccines extends AppCompatActivity {

//    ListView listView;

    ListView listCountry;
    ListView listVaccine;
    TextView DateUpdated;
//    ArrayList<Country> completeList = new ArrayList<>();;


    ArrayList<String> countriesArray = new ArrayList<>();
    ArrayList<Integer> vaccinesArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines);
//        listView = findViewById(R.id.CompleteList);
        DateUpdated = findViewById(R.id.dateUpdated);
//        completeList = new ArrayList<>();


        listCountry = findViewById(R.id.CountryList);
        listVaccine = findViewById(R.id.VaccineList);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest JsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://disease.sh/v3/covid-19/vaccine/coverage/countries?lastdays=1",
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0; i<10; i++){
//                    for(int i=0; i<response.length(); i++){

                        JSONObject obj = response.getJSONObject(i);
                        String countryFetch = obj.getString("country");

                        JSONObject timeline = obj.getJSONObject("timeline");
                        int vaccineFetch = timeline.getInt(timeline.names().getString(0));
//                        String vaccineFetch = String.valueOf(timeline.getInt(timeline.names().getString(0)));
                        countriesArray.add(countryFetch);
                        vaccinesArray.add(vaccineFetch);


//                        Country country = new Country(countryFetch, vaccineFetch);

//                        Country country = new Country();
//                        country.setCountryName(countryFetch);
//                        country.setVaccine(vaccineFetch);

//                        completeList.add(country);



                        String dateUpdatedFetch = timeline.names().getString(0);
                        DateUpdated.setText(dateUpdatedFetch);

//                        Log.d("myresponse", "Vaccine Done " + vaccineFetch + " in " + countryFetch);
//                        Log.d("myresponse", "Vaccine Done: " + timeline.names().getString(0) + " of Country " + countryFetch);
                    }
                }
                catch (JSONException e) { e.printStackTrace(); }

//                VaccineAdapter customListAdapter = new VaccineAdapter(getApplicationContext(), R.layout.custom_vaccines_layout, completeList);
//                listView.setAdapter(customListAdapter);


                ArrayAdapter CountryAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, countriesArray);
                ArrayAdapter VaccineAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, vaccinesArray);
                listCountry.setAdapter(CountryAdapter);
                listVaccine.setAdapter(VaccineAdapter);

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
    public void openList(View view){
        Toast.makeText(this, "Opening Countries List", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CountryList.class);
        startActivity(intent);
    }
    public void openHome(View view){
        Toast.makeText(this, "Opening Home Page", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void samePage(View view){
        Toast.makeText(this, "You're on the same Page", Toast.LENGTH_SHORT).show();
    }
}