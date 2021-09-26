package com.example.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VaccineAdapter extends ArrayAdapter<Country> {
    private Context mContext;
    private int mResource;

    public VaccineAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Country> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String CountryName = getItem(position).getCountryName();
        int CountryVaccine = getItem(position).getVaccine();

        Country country = new Country(CountryName, CountryVaccine);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvCountryName = convertView.findViewById(R.id.countryVaccine);
        TextView tvCountryVaccine = convertView.findViewById(R.id.vaccinesFetch);

        tvCountryName.setText(CountryName);
        tvCountryVaccine.setText(String.valueOf(CountryVaccine));

        return super.getView(position, convertView, parent);
    }
}