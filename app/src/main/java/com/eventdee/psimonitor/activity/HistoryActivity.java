package com.eventdee.psimonitor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.eventdee.psimonitor.R;
import com.eventdee.psimonitor.ui.HistoryAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Integer> history;
    private ArrayList<Double> historyCO;
    private ArrayList<String> times;
    private ArrayList<Entry> entries = new ArrayList<Entry>();
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        history = intent.getIntegerArrayListExtra("history");
        times = intent.getStringArrayListExtra("time");
        String pollutantName = intent.getStringExtra("pollutantName");

        getSupportActionBar().setTitle(pollutantName);

        mRecyclerView = (RecyclerView) findViewById(R.id.history_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new HistoryAdapter(history, times);
        mRecyclerView.setAdapter(mAdapter);

        LineChart mChart = (LineChart) findViewById(R.id.linegraph);

        for (int i = 0; i < times.size(); i++) {
            if (pollutantName.equals(getString(R.string.CO))) {
                historyCO = (ArrayList<Double>) intent.getSerializableExtra("history");
                entries.add(new Entry(i, historyCO.get(i).floatValue()));
            } else {
                entries.add(new Entry(i, history.get(i)));
            }
        }

        LineDataSet set1;

        // create a dataset and give it a type
        set1 = new LineDataSet(entries, "DataSet 1");
        set1.setFillAlpha(110);
        set1.setLineWidth(1f);
        set1.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(dataSets);

        // set data
        mChart.setData(data);

        Description description = mChart.getDescription();
        description.setEnabled(false);

        Legend legend = mChart.getLegend();
        legend.setEnabled(false);

        mChart.animateX(1000);
    }
}
