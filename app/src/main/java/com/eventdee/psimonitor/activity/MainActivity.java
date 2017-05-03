package com.eventdee.psimonitor.activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eventdee.psimonitor.R;
import com.eventdee.psimonitor.network.ApiClient;
import com.eventdee.psimonitor.network.ApiInterface;
import com.eventdee.psimonitor.pojo.AirQuality;
import com.eventdee.psimonitor.pojo.Item;
import com.eventdee.psimonitor.pojo.RegionMetadatum;
import com.eventdee.psimonitor.ui.OnSwipeTouchListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.eventdee.psimonitor.R.id.date;

public class MainActivity extends AppCompatActivity {

    private int currentPSI;
    private ProgressBar progressBar;
    private TextView tvCurrentPsi;
    private TextView tvCurrentStatus;
    private TextView tvCurrentWarning;
    private TextView tvDate;
    private ImageView ivMainBackground;
    private ImageView rightArrow;
    private ImageView leftArrow;
    private GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
    private GregorianCalendar todayCal = (GregorianCalendar) GregorianCalendar.getInstance();
    private SimpleDateFormat rawFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    private SimpleDateFormat queryFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat finalDateFormat = new SimpleDateFormat("EEEE, dd MMM yyyy");
    private SimpleDateFormat regionFormat = new SimpleDateFormat("dd MMM yyyy, HH:mm");
    private Date today = cal.getTime();
    private String dateString;
    private String regionDate;
    private ArrayList<Item> PSIArray = new ArrayList<Item>();
    private ArrayList<RegionMetadatum> RegionArray = new ArrayList<RegionMetadatum>();
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tvCurrentPsi = (TextView) findViewById(R.id.todaypsi);
        tvCurrentStatus = (TextView) findViewById(R.id.status);
        tvCurrentWarning = (TextView) findViewById(R.id.warning);
        tvDate = (TextView) findViewById(date);
        ivMainBackground = (ImageView) findViewById(R.id.mainBackground);
        rightArrow = (ImageView) findViewById(R.id.rightarrow);
        leftArrow = (ImageView) findViewById(R.id.leftarrow);
        ivMainBackground = (ImageView) findViewById(R.id.mainBackground);

        Picasso.with(this).load(R.drawable.sgskyline).into(ivMainBackground);

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cal.getTime().compareTo(todayCal.getTime()) < 0) {
                    cal.add(Calendar.DAY_OF_YEAR, 1);
                    networkCall();
                } else {
                    Toast.makeText(getApplicationContext(), "latest date", Toast.LENGTH_LONG).show();
                }
            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.add(Calendar.DAY_OF_YEAR, -1);
                networkCall();
            }
        });

        RelativeLayout mainView = (RelativeLayout) findViewById(R.id.content_main);
        mainView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                if (cal.getTime().compareTo(todayCal.getTime()) < 0) {
                    cal.add(Calendar.DAY_OF_YEAR, 1);
                    networkCall();
                } else {
                    Toast.makeText(getApplicationContext(), "latest date", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                cal.add(Calendar.DAY_OF_YEAR, -1);
                networkCall();
            }
        });

        networkCall();

        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putParcelableArrayListExtra("todayPSIArray", PSIArray);
                intent.putExtra("regionName", RegionArray.get(0).getName());
                intent.putExtra("locationImage", R.drawable.national);
                intent.putExtra("timePosition", PSIArray.size() - 1);
                startActivity(intent);
            }
        });
    }

    private void networkCall() {
        if (isOnline()) {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<AirQuality> call = null;
            call = apiService.getPSIByDate(queryFormat.format(cal.getTime()));

            call.enqueue(new Callback<AirQuality>() {
                @Override
                public void onResponse(Call<AirQuality> call, Response<AirQuality> response) {

                    PSIArray = (ArrayList<Item>) response.body().getItems();
                    RegionArray = (ArrayList<RegionMetadatum>) response.body().getRegionMetadata();
                    Collections.swap(RegionArray, 0, 5);
                    Collections.swap(RegionArray, 2, 3);
                    Collections.swap(RegionArray, 4, 5);
                    currentPSI = PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getNational();
                    dateString = PSIArray.get(PSIArray.size() - 1).getTimestamp();

                    try {
                        today = rawFormat.parse(dateString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    dateString = finalDateFormat.format(today);
                    regionDate = regionFormat.format(today);

                    updateContent();
                    updateArray(PSIArray, RegionArray, dateString);

                }

                @Override
                public void onFailure(Call<AirQuality> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Network call failed, server issues!", Toast.LENGTH_LONG).show();
                    Log.e("failure", t.getCause().toString());
                }
            });
        } else {
            Toast.makeText(this, "Network is not available", Toast.LENGTH_LONG).show();
            if (tvCurrentPsi.getText() == null || tvCurrentPsi.getText() == "") {
                tvCurrentPsi.setText("???");
            }
        }

    }

    private void updateArray(ArrayList<Item> psiArray, ArrayList<RegionMetadatum> regionArray, String dateString) {
        this.PSIArray = psiArray;
        this.RegionArray = regionArray;
        this.dateString = dateString;
    }

    private void updateContent() {

        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, currentPSI); // see this max value coming back here, we animale towards that value
        animation.setDuration(3000); //in milliseconds
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
        tvCurrentPsi.setText(String.valueOf(currentPSI));

        tvDate.setText(dateString);

        String todayString = finalDateFormat.format(todayCal.getTime());

        if (dateString.equals(todayString)) {
            rightArrow.setVisibility(View.INVISIBLE);
        } else {
            rightArrow.setVisibility(View.VISIBLE);
        }

        if (currentPSI <= 50) {
            tvCurrentStatus.setText(R.string.status_good);
            tvCurrentWarning.setText(R.string.warning_good);
        } else if (currentPSI >= 51 && currentPSI <= 100) {
            tvCurrentStatus.setText(R.string.status_moderate);
            tvCurrentWarning.setText(R.string.warning_good);
        } else if (currentPSI >= 101 && currentPSI <= 200) {
            tvCurrentStatus.setText(R.string.status_unhealthy);
            tvCurrentWarning.setText(R.string.warning_unhealthy);
        } else if (currentPSI >= 201 && currentPSI <= 300) {
            tvCurrentStatus.setText(R.string.status_very_unhealthy);
            tvCurrentWarning.setText(R.string.warning_very_unhealthy);
        } else {
            tvCurrentStatus.setText(R.string.status_hazardous);
            tvCurrentWarning.setText(R.string.warning_hazardous);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_regions) {
            if (PSIArray.isEmpty()) {
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(this, RegionsActivity.class);
                intent.putExtra("todayPSIArray", PSIArray);
                intent.putParcelableArrayListExtra("regionArray", RegionArray);
                intent.putExtra("dateString", regionDate);
                startActivity(intent);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    //check if there is wifi or internet connectivity
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}
