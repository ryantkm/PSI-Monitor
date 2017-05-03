package com.eventdee.psimonitor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eventdee.psimonitor.R;
import com.eventdee.psimonitor.pojo.Item;
import com.eventdee.psimonitor.pojo.Pollutant;
import com.eventdee.psimonitor.ui.ReadingAdapter;
import com.eventdee.psimonitor.ui.RecyclerItemClickListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    private ArrayList<Item> PSIArray = new ArrayList<Item>();
    private int position;
    private int timePosition;
    private ArrayList<Pollutant> PSIReadings = new ArrayList<Pollutant>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int currentPSI = 123;
    private ArrayList<String> historyTime = new ArrayList<String>();
    private ArrayList<Integer> historySO2 = new ArrayList<Integer>();
    private ArrayList<Integer> historyPM10 = new ArrayList<Integer>();
    private ArrayList<Integer> historyNO2 = new ArrayList<Integer>();
    private ArrayList<Integer> historyO3 = new ArrayList<Integer>();
    private ArrayList<Double> historyCO = new ArrayList<Double>();
    private ArrayList<Integer> historyPM25 = new ArrayList<Integer>();
    private ArrayList<Integer> historyPSI = new ArrayList<Integer>();
    private SimpleDateFormat rawFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
    private SimpleDateFormat dayNameFormat = new SimpleDateFormat("EEEE");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private String locationName;
    private String dateStr;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
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
        PSIArray = intent.getParcelableArrayListExtra("todayPSIArray");
        position = intent.getIntExtra("position", 0);
        timePosition = intent.getIntExtra("timePosition", 0);

        for (int i = 0; i < PSIArray.size(); i++) {
            String timestamp = PSIArray.get(i).getTimestamp();
            historyTime.add(timestamp);
        }

        // check if the time chosen from search is in the future
        if (timePosition > PSIArray.size() - 1) {
            timePosition = PSIArray.size() - 1;
            Toast.makeText(this, "Time adjusted to the latest data available", Toast.LENGTH_LONG).show();
        }

        //getting the values for the different regions
        switch (position) {
            case 0:
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getSo2TwentyFourHourly().getNational(), "µg/m3", getString(R.string.SO2)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getPm10TwentyFourHourly().getNational(), "µg/m3", getString(R.string.PM10)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getNo2OneHourMax().getNational(), "µg/m3", getString(R.string.NO2)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getO3EightHourMax().getNational(), "µg/m3", getString(R.string.O3)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getCoEightHourMax().getNational(), "mg/m3", getString(R.string.CO)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getPm25TwentyFourHourly().getNational(), "µg/m3", getString(R.string.PM25)));
                currentPSI = PSIArray.get(timePosition).getReadings().getPsiTwentyFourHourly().getNational();
                for (int i = 0; i < PSIArray.size(); i++) {
                    int valueSO2 = PSIArray.get(i).getReadings().getSo2TwentyFourHourly().getNational();
                    historySO2.add(valueSO2);
                    int valuePM10 = PSIArray.get(i).getReadings().getPm10TwentyFourHourly().getNational();
                    historyPM10.add(valuePM10);
                    int valueNO2 = PSIArray.get(i).getReadings().getNo2OneHourMax().getNational();
                    historyNO2.add(valueNO2);
                    int valueO3 = PSIArray.get(i).getReadings().getO3EightHourMax().getNational();
                    historyO3.add(valueO3);
                    double valueCO = PSIArray.get(i).getReadings().getCoEightHourMax().getNational();
                    historyCO.add(valueCO);
                    int valuePM25 = PSIArray.get(i).getReadings().getPm25TwentyFourHourly().getNational();
                    historyPM25.add(valuePM25);
                    int valuePSI = PSIArray.get(i).getReadings().getPsiTwentyFourHourly().getNational();
                    historyPSI.add(valuePSI);
                }
                break;
            case 1:
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getSo2TwentyFourHourly().getCentral(), "µg/m3", getString(R.string.SO2)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getPm10TwentyFourHourly().getCentral(), "µg/m3", getString(R.string.PM10)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getNo2OneHourMax().getCentral(), "µg/m3", getString(R.string.NO2)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getO3EightHourMax().getCentral(), "µg/m3", getString(R.string.O3)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getCoEightHourMax().getCentral(), "mg/m3", getString(R.string.CO)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getPm25TwentyFourHourly().getCentral(), "µg/m3", getString(R.string.PM25)));
                currentPSI = PSIArray.get(timePosition).getReadings().getPsiTwentyFourHourly().getCentral();
                for (int i = 0; i < PSIArray.size(); i++) {
                    int valueSO2 = PSIArray.get(i).getReadings().getSo2TwentyFourHourly().getCentral();
                    historySO2.add(valueSO2);
                    int valuePM10 = PSIArray.get(i).getReadings().getPm10TwentyFourHourly().getCentral();
                    historyPM10.add(valuePM10);
                    int valueNO2 = PSIArray.get(i).getReadings().getNo2OneHourMax().getCentral();
                    historyNO2.add(valueNO2);
                    int valueO3 = PSIArray.get(i).getReadings().getO3EightHourMax().getCentral();
                    historyO3.add(valueO3);
                    double valueCO = PSIArray.get(i).getReadings().getCoEightHourMax().getCentral();
                    historyCO.add(valueCO);
                    int valuePM25 = PSIArray.get(i).getReadings().getPm25TwentyFourHourly().getCentral();
                    historyPM25.add(valuePM25);
                    int valuePSI = PSIArray.get(i).getReadings().getPsiTwentyFourHourly().getCentral();
                    historyPSI.add(valuePSI);
                }
                break;
            case 2:
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getSo2TwentyFourHourly().getNorth(), "µg/m3", getString(R.string.SO2)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getPm10TwentyFourHourly().getNorth(), "µg/m3", getString(R.string.PM10)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getNo2OneHourMax().getNorth(), "µg/m3", getString(R.string.NO2)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getO3EightHourMax().getNorth(), "µg/m3", getString(R.string.O3)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getCoEightHourMax().getNorth(), "mg/m3", getString(R.string.CO)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getPm25TwentyFourHourly().getNorth(), "µg/m3", getString(R.string.PM25)));
                currentPSI = PSIArray.get(timePosition).getReadings().getPsiTwentyFourHourly().getNorth();
                for (int i = 0; i < PSIArray.size(); i++) {
                    int valueSO2 = PSIArray.get(i).getReadings().getSo2TwentyFourHourly().getNorth();
                    historySO2.add(valueSO2);
                    int valuePM10 = PSIArray.get(i).getReadings().getPm10TwentyFourHourly().getNorth();
                    historyPM10.add(valuePM10);
                    int valueNO2 = PSIArray.get(i).getReadings().getNo2OneHourMax().getNorth();
                    historyNO2.add(valueNO2);
                    int valueO3 = PSIArray.get(i).getReadings().getO3EightHourMax().getNorth();
                    historyO3.add(valueO3);
                    double valueCO = PSIArray.get(i).getReadings().getCoEightHourMax().getNorth();
                    historyCO.add(valueCO);
                    int valuePM25 = PSIArray.get(i).getReadings().getPm25TwentyFourHourly().getNorth();
                    historyPM25.add(valuePM25);
                    int valuePSI = PSIArray.get(i).getReadings().getPsiTwentyFourHourly().getNorth();
                    historyPSI.add(valuePSI);
                }
                break;
            case 3:
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getSo2TwentyFourHourly().getSouth(), "µg/m3", getString(R.string.SO2)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getPm10TwentyFourHourly().getSouth(), "µg/m3", getString(R.string.PM10)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getNo2OneHourMax().getSouth(), "µg/m3", getString(R.string.NO2)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getO3EightHourMax().getSouth(), "µg/m3", getString(R.string.O3)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getCoEightHourMax().getSouth(), "mg/m3", getString(R.string.CO)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getPm25TwentyFourHourly().getSouth(), "µg/m3", getString(R.string.PM25)));
                currentPSI = PSIArray.get(timePosition).getReadings().getPsiTwentyFourHourly().getSouth();
                for (int i = 0; i < PSIArray.size(); i++) {
                    int valueSO2 = PSIArray.get(i).getReadings().getSo2TwentyFourHourly().getSouth();
                    historySO2.add(valueSO2);
                    int valuePM10 = PSIArray.get(i).getReadings().getPm10TwentyFourHourly().getSouth();
                    historyPM10.add(valuePM10);
                    int valueNO2 = PSIArray.get(i).getReadings().getNo2OneHourMax().getSouth();
                    historyNO2.add(valueNO2);
                    int valueO3 = PSIArray.get(i).getReadings().getO3EightHourMax().getSouth();
                    historyO3.add(valueO3);
                    double valueCO = PSIArray.get(i).getReadings().getCoEightHourMax().getSouth();
                    historyCO.add(valueCO);
                    int valuePM25 = PSIArray.get(i).getReadings().getPm25TwentyFourHourly().getSouth();
                    historyPM25.add(valuePM25);
                    int valuePSI = PSIArray.get(i).getReadings().getPsiTwentyFourHourly().getSouth();
                    historyPSI.add(valuePSI);
                }
                break;
            case 4:
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getSo2TwentyFourHourly().getEast(), "µg/m3", getString(R.string.SO2)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getPm10TwentyFourHourly().getEast(), "µg/m3", getString(R.string.PM10)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getNo2OneHourMax().getEast(), "µg/m3", getString(R.string.NO2)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getO3EightHourMax().getEast(), "µg/m3", getString(R.string.O3)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getCoEightHourMax().getEast(), "mg/m3", getString(R.string.CO)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getPm25TwentyFourHourly().getEast(), "µg/m3", getString(R.string.PM25)));
                currentPSI = PSIArray.get(timePosition).getReadings().getPsiTwentyFourHourly().getEast();
                for (int i = 0; i < PSIArray.size(); i++) {
                    int valueSO2 = PSIArray.get(i).getReadings().getSo2TwentyFourHourly().getEast();
                    historySO2.add(valueSO2);
                    int valuePM10 = PSIArray.get(i).getReadings().getPm10TwentyFourHourly().getEast();
                    historyPM10.add(valuePM10);
                    int valueNO2 = PSIArray.get(i).getReadings().getNo2OneHourMax().getEast();
                    historyNO2.add(valueNO2);
                    int valueO3 = PSIArray.get(i).getReadings().getO3EightHourMax().getEast();
                    historyO3.add(valueO3);
                    double valueCO = PSIArray.get(i).getReadings().getCoEightHourMax().getEast();
                    historyCO.add(valueCO);
                    int valuePM25 = PSIArray.get(i).getReadings().getPm25TwentyFourHourly().getEast();
                    historyPM25.add(valuePM25);
                    int valuePSI = PSIArray.get(i).getReadings().getPsiTwentyFourHourly().getEast();
                    historyPSI.add(valuePSI);
                }
                break;
            case 5:
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getSo2TwentyFourHourly().getWest(), "µg/m3", getString(R.string.SO2)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getPm10TwentyFourHourly().getWest(), "µg/m3", getString(R.string.PM10)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getNo2OneHourMax().getWest(), "µg/m3", getString(R.string.NO2)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getO3EightHourMax().getWest(), "µg/m3", getString(R.string.O3)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getCoEightHourMax().getWest(), "mg/m3", getString(R.string.CO)));
                PSIReadings.add(new Pollutant(PSIArray.get(timePosition).getReadings().getPm25TwentyFourHourly().getWest(), "µg/m3", getString(R.string.PM25)));
                currentPSI = PSIArray.get(timePosition).getReadings().getPsiTwentyFourHourly().getWest();
                for (int i = 0; i < PSIArray.size(); i++) {
                    int valueSO2 = PSIArray.get(i).getReadings().getSo2TwentyFourHourly().getWest();
                    historySO2.add(valueSO2);
                    int valuePM10 = PSIArray.get(i).getReadings().getPm10TwentyFourHourly().getWest();
                    historyPM10.add(valuePM10);
                    int valueNO2 = PSIArray.get(i).getReadings().getNo2OneHourMax().getWest();
                    historyNO2.add(valueNO2);
                    int valueO3 = PSIArray.get(i).getReadings().getO3EightHourMax().getWest();
                    historyO3.add(valueO3);
                    double valueCO = PSIArray.get(i).getReadings().getCoEightHourMax().getWest();
                    historyCO.add(valueCO);
                    int valuePM25 = PSIArray.get(i).getReadings().getPm25TwentyFourHourly().getWest();
                    historyPM25.add(valuePM25);
                    int valuePSI = PSIArray.get(i).getReadings().getPsiTwentyFourHourly().getWest();
                    historyPSI.add(valuePSI);
                }
                break;
            default:
                break;
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.reading_recycler_view);

        mLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new ReadingAdapter(PSIReadings);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(DetailActivity.this, HistoryActivity.class);
                        intent.putExtra("time", historyTime);
                        switch (position) {
                            case 0:
                                intent.putIntegerArrayListExtra("history", historySO2);
                                intent.putExtra("pollutantName", getString(R.string.SO2));
                                break;
                            case 1:
                                intent.putIntegerArrayListExtra("history", historyPM10);
                                intent.putExtra("pollutantName", getString(R.string.PM10));
                                break;
                            case 2:
                                intent.putIntegerArrayListExtra("history", historyNO2);
                                intent.putExtra("pollutantName", getString(R.string.NO2));
                                break;
                            case 3:
                                intent.putIntegerArrayListExtra("history", historyO3);
                                intent.putExtra("pollutantName", getString(R.string.O3));
                                break;
                            case 4:
                                intent.putExtra("history", historyCO);
                                intent.putExtra("pollutantName", getString(R.string.CO));
                                break;
                            case 5:
                                intent.putIntegerArrayListExtra("history", historyPM25);
                                intent.putExtra("pollutantName", getString(R.string.PM25));
                                break;
                        }

                        intent.putExtra("position", position);
                        startActivity(intent);
                    }
                })
        );

        locationName = intent.getStringExtra("regionName");

        int image = intent.getIntExtra("locationImage", 0);

        ImageView locationIV = (ImageView) findViewById(R.id.locationiv);
        TextView psiTV = (TextView) findViewById(R.id.detailPSI);
        TextView statusTV = (TextView) findViewById(R.id.status);

        Picasso.with(this).load(image).into(locationIV);

//        locationIV.setImageResource(image);
        psiTV.setText(String.valueOf(currentPSI));

        // set the color of PSI background depending on PSI reading
        LinearLayout PSIBackground = (LinearLayout) findViewById(R.id.PSIBackground);
        if (currentPSI <= 50) {
            PSIBackground.setBackgroundColor(getResources().getColor(R.color.good));
            statusTV.setText(R.string.status_good);
        } else if (currentPSI >= 51 && currentPSI <= 100) {
            PSIBackground.setBackgroundColor(getResources().getColor(R.color.moderate));
            statusTV.setText(R.string.status_moderate);
        } else if (currentPSI >= 101 && currentPSI <= 200) {
            PSIBackground.setBackgroundColor(getResources().getColor(R.color.unhealthy));
            statusTV.setText(R.string.status_unhealthy);
        } else if (currentPSI >= 201 && currentPSI <= 300) {
            PSIBackground.setBackgroundColor(getResources().getColor(R.color.very_unhealthy));
            statusTV.setText(R.string.status_very_unhealthy);
        } else {
            PSIBackground.setBackgroundColor(getResources().getColor(R.color.hazardous));
            statusTV.setText(R.string.status_hazardous);
        }

        psiTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, HistoryActivity.class);
                intent.putExtra("time", historyTime);
                intent.putIntegerArrayListExtra("history", historyPSI);
                intent.putExtra("pollutantName", getString(R.string.psi));
                startActivity(intent);
            }
        });

        // setting the dates related textviews
        String selectedDate = PSIArray.get(timePosition).getTimestamp();
        Date convertedDate = new Date();
        try {
            convertedDate = rawFormat.parse(selectedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String dayNameStr = dayNameFormat.format(convertedDate);
        dateStr = dateFormat.format(convertedDate);
        String timeStr = timeFormat.format(convertedDate);

        TextView dayName = (TextView) findViewById(R.id.dayName);
        TextView date = (TextView) findViewById(R.id.date);
        TextView time = (TextView) findViewById(R.id.time);

        dayName.setText(dayNameStr);
        date.setText(dateStr);
        time.setText(timeStr);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(locationName);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider myShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        Intent myShareIntent = new Intent(Intent.ACTION_SEND);
        myShareIntent.setType("text/plain");
        myShareIntent.putExtra(Intent.EXTRA_SUBJECT, "24-Hr PSI (" + locationName +") on " + dateStr);
        myShareIntent.putExtra(Intent.EXTRA_TEXT, "24-Hr PSI (" + locationName +") on " + dateStr + " is " + currentPSI + ".");
        myShareActionProvider.setShareIntent(myShareIntent);

        return true;
    }
}
