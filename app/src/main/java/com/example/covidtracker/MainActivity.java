package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView totalCases;
    String totalCasesFetch;
    TextView totalDeaths;
    String totalDeathsFetch;
    TextView totalRecoveries;
    String totalRecoveriesFetch;
    TextView totalActive;
    String totalActiveFetch;

    TextView newCases;
    String newCasesFetch;
    TextView newDeaths;
    String newDeathsFetch;

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalCases = findViewById(R.id.totalcasesFetch);
        totalDeaths = findViewById(R.id.totaldeathsFetch);
        totalRecoveries = findViewById(R.id.totalrecoveryFetch);
        totalActive = findViewById(R.id.totalactiveFetch);

        newCases = findViewById(R.id.todaycasesFetch);
        newDeaths = findViewById(R.id.todaydeathsFetch);

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest JsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://disease.sh/v3/covid-19/all",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

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
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(JsonObjectRequest);
        requestQueue.getCache().clear();

        pieChart = findViewById(R.id.pieChart);
        setupPieChart();
        loadPieChartData();

    }

    private void setupPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setCenterText("Covid 19 Cases");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation((Legend.LegendOrientation.VERTICAL));
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void loadPieChartData(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(0.2f, "Travel")); // Green
        entries.add(new PieEntry(0.15f, "Food")); // Yellow
        entries.add(new PieEntry(0.10f, "Shopping")); // Red

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS){
            colors.add(color);
        }
        for (int color: ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Expense");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();
    }

    public void openActivity(View view){
        Toast.makeText(this, "Opening Country Data", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CountryList.class);
        startActivity(intent);
    }
}