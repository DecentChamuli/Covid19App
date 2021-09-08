package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CountryList extends AppCompatActivity {

    TextView totalCases;
    String totalCasesFetch;
    TextView totalDeaths;
    String totalDeathsFetch;
    TextView totalRecoveries;
    String totalRecoveriesFetch;
    TextView totalActive;
    String totalActiveFetch;

    TextView countryName;
    String countryNameFetch;

    TextView newCases;
    String newCasesFetch;
    TextView newDeaths;
    String newDeathsFetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        totalCases = findViewById(R.id.totalcasesFetch);
        totalDeaths = findViewById(R.id.totaldeathsFetch);
        totalRecoveries = findViewById(R.id.totalrecoveryFetch);
        totalActive = findViewById(R.id.totalactiveFetch);

        newCases = findViewById(R.id.todaycasesFetch);
        newDeaths = findViewById(R.id.todaydeathsFetch);

        countryName= findViewById(R.id.location);

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        String country = "pk";

        JsonObjectRequest JsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://disease.sh/v3/covid-19/countries/"+country,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    countryNameFetch = response.getString("country");
                    countryName.setText(countryNameFetch);

                    totalCasesFetch = String.valueOf(response.getInt("cases"));
                    totalCases.setText(totalCasesFetch);
                    totalDeathsFetch = String.valueOf(response.getInt("deaths"));
                    totalDeaths.setText(totalDeathsFetch);
                    totalRecoveriesFetch = String.valueOf(response.getInt("recovered"));
                    totalRecoveries.setText(totalRecoveriesFetch);
                    totalActiveFetch = String.valueOf(response.getInt("active"));
                    totalActive.setText(totalActiveFetch);

                    newCasesFetch = String.valueOf(response.getInt("todayCases"));
                    newCases.setText(newCasesFetch);
                    newDeathsFetch = String.valueOf(response.getInt("todayDeaths"));
                    newDeaths.setText(newDeathsFetch);

                    Log.d("myresponse", "Today Deaths are: " + response.getInt("todayDeaths") + " Today Cases are: " + response.getInt("todayCases"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myresponse", "Something went wrong");
                Toast.makeText(CountryList.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(JsonObjectRequest);
        requestQueue.getCache().clear();
    }
}