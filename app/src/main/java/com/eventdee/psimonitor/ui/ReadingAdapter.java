package com.eventdee.psimonitor.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eventdee.psimonitor.R;
import com.eventdee.psimonitor.pojo.Pollutant;

import java.util.ArrayList;

public class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.ReadingViewHolder> {

    public static class ReadingViewHolder extends RecyclerView.ViewHolder {

        TextView reading;
        TextView label;
        TextView unit;


        ReadingViewHolder(View itemView) {
            super(itemView);
            unit = (TextView)itemView.findViewById(R.id.unit);
            label = (TextView)itemView.findViewById(R.id.label);
            reading = (TextView)itemView.findViewById(R.id.reading);
        }
    }

    ArrayList<Pollutant> readings;

    public ReadingAdapter(ArrayList<Pollutant> readings){
        this.readings = readings;
    }

    @Override
    public ReadingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_reading_details, viewGroup, false);
        ReadingViewHolder pvh = new ReadingViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ReadingViewHolder regionViewHolder, int i) {
        regionViewHolder.unit.setText(readings.get(i).getUnit());
        regionViewHolder.label.setText(readings.get(i).getLabel());
        if (i==4){
            regionViewHolder.reading.setText(String.valueOf(String.format("%.1f",readings.get(i).getValue())));
        } else {
            regionViewHolder.reading.setText(String.valueOf((int) readings.get(i).getValue()));
        }

    }

    @Override
    public int getItemCount() {
        return readings.size();
    }
}