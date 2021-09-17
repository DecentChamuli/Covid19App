package com.example.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Country> country;

    public Adapter(Context context, List<Country> country){
        this.inflater = LayoutInflater.from(context);
        this.country = country;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_layout,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.countryNameFetch.setText(country.get(position).getCountryName());
    }

    @Override
    public int getItemCount() {
        return country.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView countryNameFetch;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            countryNameFetch = itemView.findViewById(R.id.countrynameFetch);
        }
    }
}
