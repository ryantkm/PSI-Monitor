package com.eventdee.psimonitor.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eventdee.psimonitor.R;
import com.eventdee.psimonitor.pojo.RegionMetadatum;

import java.util.ArrayList;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.RegionViewHolder> {

    public static class RegionViewHolder extends RecyclerView.ViewHolder {

        TextView regionName;
        ImageView regionImage;
        TextView regionPSI;
        TextView pollutantLabel;

        RegionViewHolder(View itemView) {
            super(itemView);
            regionName = (TextView)itemView.findViewById(R.id.regionName);
            regionImage = (ImageView) itemView.findViewById(R.id.regionImage);
            regionPSI = (TextView)itemView.findViewById(R.id.regionPSI);
            pollutantLabel = (TextView) itemView.findViewById(R.id.pollutantLabel);
        }
    }

    ArrayList<RegionMetadatum> regions;
    String pollutantLabel;

    public RegionAdapter(ArrayList<RegionMetadatum> regions, String pollutantLabel){
        this.regions = regions;
        this.pollutantLabel = pollutantLabel;
    }

    @Override
    public RegionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_region_details, viewGroup, false);
        RegionViewHolder pvh = new RegionViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(RegionViewHolder regionViewHolder, int i) {
        regionViewHolder.regionName.setText(regions.get(i).getName());
        regionViewHolder.regionImage.setImageResource(regions.get(i).getImageLocation());
        regionViewHolder.regionPSI.setText(String.valueOf(regions.get(i).getPollutantValue()));
        regionViewHolder.pollutantLabel.setText(pollutantLabel);
    }

    @Override
    public int getItemCount() {
        return regions.size();
    }
}