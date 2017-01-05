package com.eventdee.psimonitor.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eventdee.psimonitor.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    SimpleDateFormat rawFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView historyValue;
        TextView historyTime;

        HistoryViewHolder(View itemView) {
            super(itemView);
            historyValue = (TextView)itemView.findViewById(R.id.history);
            historyTime = (TextView)itemView.findViewById(R.id.time);
        }
    }

    ArrayList<Integer> history;
    ArrayList<String> time;

    public HistoryAdapter(ArrayList<Integer> history, ArrayList<String> time){
        this.history = history;
        this.time = time;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_history_details, viewGroup, false);
        HistoryViewHolder pvh = new HistoryViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder historyViewHolder, int i) {
        historyViewHolder.historyValue.setText(String.valueOf(history.get(i)));

        try {
            Date currentTime = rawFormat.parse(time.get(i));
            historyViewHolder.historyTime.setText(timeFormat.format(currentTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return history.size();
    }
}