package com.eventdee.psimonitor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.eventdee.psimonitor.R;
import com.eventdee.psimonitor.pojo.Item;
import com.eventdee.psimonitor.pojo.RegionMetadatum;
import com.eventdee.psimonitor.ui.RecyclerItemClickListener;
import com.eventdee.psimonitor.ui.RegionAdapter;

import java.util.ArrayList;

import static com.eventdee.psimonitor.R.string.PM10;

public class RegionsActivity extends AppCompatActivity {

    private ArrayList<RegionMetadatum> locations = new ArrayList<>();
    private ArrayList<Item> PSIArray = new ArrayList<Item>();
    private String dateString;
    private String pollutantSelector = "psi";
    private String pollutantLabel;
    private MenuItem pollutantName;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pollutantLabel = getString(R.string.psi);

        Intent intent = getIntent();
        PSIArray = intent.getParcelableArrayListExtra("todayPSIArray");
        locations = intent.getParcelableArrayListExtra("regionArray");
        dateString = intent.getStringExtra("dateString");

        setTitle(dateString);

        locations.get(0).setImageLocation(R.drawable.national);
        locations.get(1).setImageLocation(R.drawable.central);
        locations.get(2).setImageLocation(R.drawable.north);
        locations.get(3).setImageLocation(R.drawable.south);
        locations.get(4).setImageLocation(R.drawable.east);
        locations.get(5).setImageLocation(R.drawable.west);

        locations.get(0).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getNational());
        locations.get(1).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getCentral());
        locations.get(2).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getNorth());
        locations.get(3).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getSouth());
        locations.get(4).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getEast());
        locations.get(5).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getWest());

        updateUI();
    }

    private void updateUI() {

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mLayoutManager = new GridLayoutManager(this, getResources().getInteger(R.integer.grid_number_cols));
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new RegionAdapter(locations, pollutantLabel);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(RegionsActivity.this, DetailActivity.class);
                        intent.putParcelableArrayListExtra("todayPSIArray", PSIArray);
                        intent.putExtra("regionName", locations.get(position).getName());
                        intent.putExtra("locationImage", locations.get(position).getImageLocation());
                        intent.putExtra("position", position);
                        intent.putExtra("timePosition", PSIArray.size() - 1);
                        startActivity(intent);
                    }
                })
        );
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_region, menu);

        pollutantName = menu.findItem(R.id.action_pollutant);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.psi:
                if (pollutantSelector != "psi") {
                    pollutantSelector = "psi";
                    item.setChecked(true);
                    locations.get(0).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getNational());
                    locations.get(1).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getCentral());
                    locations.get(2).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getNorth());
                    locations.get(3).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getSouth());
                    locations.get(4).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getEast());
                    locations.get(5).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getWest());
                    pollutantLabel = getString(R.string.psi);
                    pollutantName.setTitle("PSI");
                    updateUI();
                }
                break;
            case R.id.so2:
                if (pollutantSelector != "so2") {
                    pollutantSelector = "so2";
                    item.setChecked(true);
                    locations.get(0).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getSo2TwentyFourHourly().getNational());
                    locations.get(1).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getSo2TwentyFourHourly().getCentral());
                    locations.get(2).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getSo2TwentyFourHourly().getNorth());
                    locations.get(3).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getSo2TwentyFourHourly().getSouth());
                    locations.get(4).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getSo2TwentyFourHourly().getEast());
                    locations.get(5).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getSo2TwentyFourHourly().getWest());
                    pollutantLabel = getString(R.string.SO2);
                    pollutantName.setTitle("SO2");
                    updateUI();
                }
                break;
            case R.id.pm10:
                if (pollutantSelector != "pm10") {
                    pollutantSelector = "pm10";
                    item.setChecked(true);
                    locations.get(0).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPm10TwentyFourHourly().getNational());
                    locations.get(1).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPm10TwentyFourHourly().getCentral());
                    locations.get(2).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPm10TwentyFourHourly().getNorth());
                    locations.get(3).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPm10TwentyFourHourly().getSouth());
                    locations.get(4).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPm10TwentyFourHourly().getEast());
                    locations.get(5).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPm10TwentyFourHourly().getWest());
                    pollutantLabel = getString(PM10);
                    pollutantName.setTitle("PM10");
                    updateUI();
                }
                break;
            case R.id.no2:
                if (pollutantSelector != "no2") {
                    pollutantSelector = "no2";
                    item.setChecked(true);
                    locations.get(0).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getNo2OneHourMax().getNational());
                    locations.get(1).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getNo2OneHourMax().getCentral());
                    locations.get(2).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getNo2OneHourMax().getNorth());
                    locations.get(3).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getNo2OneHourMax().getSouth());
                    locations.get(4).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getNo2OneHourMax().getEast());
                    locations.get(5).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getNo2OneHourMax().getWest());
                    pollutantLabel = getString(R.string.NO2);
                    pollutantName.setTitle("NO2");
                    updateUI();
                }
                break;
            case R.id.o3:
                if (pollutantSelector != "o3") {
                    pollutantSelector = "o3";
                    item.setChecked(true);
                    locations.get(0).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getO3EightHourMax().getNational());
                    locations.get(1).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getO3EightHourMax().getCentral());
                    locations.get(2).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getO3EightHourMax().getNorth());
                    locations.get(3).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getO3EightHourMax().getSouth());
                    locations.get(4).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getO3EightHourMax().getEast());
                    locations.get(5).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getO3EightHourMax().getWest());
                    pollutantLabel = getString(R.string.O3);
                    pollutantName.setTitle("O3");
                    updateUI();
                }
                break;
            case R.id.co:
                if (pollutantSelector != "co") {
                    pollutantSelector = "co";
                    item.setChecked(true);
                    locations.get(0).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getCoEightHourMax().getNational());
                    locations.get(1).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getCoEightHourMax().getCentral());
                    locations.get(2).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getCoEightHourMax().getNorth());
                    locations.get(3).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getCoEightHourMax().getSouth());
                    locations.get(4).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getCoEightHourMax().getEast());
                    locations.get(5).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getCoEightHourMax().getWest());
                    pollutantLabel = getString(R.string.CO);
                    pollutantName.setTitle("CO");
                    updateUI();
                }
                break;
            case R.id.pm25:
                if (pollutantSelector != "pm25") {
                    pollutantSelector = "pm25";
                    item.setChecked(true);
                    locations.get(0).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPm25TwentyFourHourly().getNational());
                    locations.get(1).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPm25TwentyFourHourly().getCentral());
                    locations.get(2).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPm25TwentyFourHourly().getNorth());
                    locations.get(3).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPm25TwentyFourHourly().getSouth());
                    locations.get(4).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPm25TwentyFourHourly().getEast());
                    locations.get(5).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPm25TwentyFourHourly().getWest());
                    pollutantLabel = getString(R.string.PM25);
                    pollutantName.setTitle("PM2.5");
                    updateUI();
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
