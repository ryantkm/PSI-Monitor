package com.eventdee.psimonitor.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.eventdee.psimonitor.R;
import com.eventdee.psimonitor.network.ApiClient;
import com.eventdee.psimonitor.network.ApiInterface;
import com.eventdee.psimonitor.pojo.AirQuality;
import com.eventdee.psimonitor.pojo.Item;
import com.eventdee.psimonitor.pojo.RegionMetadatum;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private Button datePicker;
    private Button timePicker;
    private static Button regionPicker;
    private Button btnSubmit;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private String dateString;
    private ArrayList<Item> PSIArray = new ArrayList<>();
    ArrayList<RegionMetadatum> RegionArray = new ArrayList<RegionMetadatum>();
    private Calendar c = Calendar.getInstance();
    private SimpleDateFormat queryFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static int position;
    private int timePosition;
    private static int imageLocation;
    private int timeSelectedInt = 2400;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        datePicker = (Button) findViewById(R.id.datePicker);
        timePicker = (Button) findViewById(R.id.timePicker);
        regionPicker = (Button) findViewById(R.id.regionPicker);
        btnSubmit = (Button) findViewById(R.id.search);

        datePicker.setOnClickListener(this);
        timePicker.setOnClickListener(this);
        regionPicker.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        // Get Current Date

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onClick(View v) {

        if (v == datePicker) {

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // adding "0" for single digit day
                            String daySelectedString;
                            if (dayOfMonth < 10) {
                                daySelectedString = "0" + dayOfMonth;
                            } else {
                                daySelectedString = String.valueOf(dayOfMonth);
                            }
                            // adding "0" for single digit month
                            String monthSelectedString;
                            if ((monthOfYear + 1) < 10) {
                                monthSelectedString = "0" + (monthOfYear + 1);
                            } else {
                                monthSelectedString = String.valueOf(monthOfYear + 1);
                            }

                            int intSelectedDate = Integer.valueOf(year + monthSelectedString + daySelectedString);

                            String dayTodayString;
                            if (mDay < 10) {
                                dayTodayString = "0" + mDay;
                            } else {
                                dayTodayString = String.valueOf(mDay);
                            }
                            // adding "0" for single digit month
                            String monthTodayString;
                            if ((monthOfYear + 1) < 10) {
                                monthTodayString = "0" + (mMonth + 1);
                            } else {
                                monthTodayString = String.valueOf(mMonth + 1);
                            }
                            int intTodayDate = Integer.valueOf(mYear + monthTodayString + dayTodayString);

                            //  check if date selected is later than today
                            if (intSelectedDate <= intTodayDate) {
                                datePicker.setText(daySelectedString + "-" + monthSelectedString + "-" + year);
                                datePicker.setBackgroundColor(getResources().getColor(R.color.primary_light));
                                dateString = year + "-" + monthSelectedString + "-" + daySelectedString;
                            } else {
                                Toast.makeText(getApplicationContext(), "Select an earlier date", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, mYear, mMonth, mDay);

            datePickerDialog.show();
        }
        if (v == timePicker) {

            // Get Current Time
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            String minuteSelectedString;
                            // adding "0" for single digit minute
                            if (minute < 10) {
                                minuteSelectedString = "0" + minute;
                            } else {
                                minuteSelectedString = String.valueOf(minute);
                            }
                            timePicker.setText(hourOfDay + ":" + minuteSelectedString);
                            timePicker.setBackgroundColor(getResources().getColor(R.color.primary_light));
                            timeSelectedInt = Integer.valueOf(String.valueOf(hourOfDay) + minuteSelectedString);
                            if (timeSelectedInt < 100) {
                                timePosition = 0;
                            } else if (timeSelectedInt >= 100 && timeSelectedInt < 200) {
                                timePosition = 1;
                            } else if (timeSelectedInt >= 200 && timeSelectedInt < 300) {
                                timePosition = 2;
                            } else if (timeSelectedInt >= 300 && timeSelectedInt < 400) {
                                timePosition = 3;
                            } else if (timeSelectedInt >= 400 && timeSelectedInt < 500) {
                                timePosition = 4;
                            } else if (timeSelectedInt >= 500 && timeSelectedInt < 600) {
                                timePosition = 5;
                            } else if (timeSelectedInt >= 600 && timeSelectedInt < 700) {
                                timePosition = 6;
                            } else if (timeSelectedInt >= 700 && timeSelectedInt < 800) {
                                timePosition = 7;
                            } else if (timeSelectedInt >= 800 && timeSelectedInt < 900) {
                                timePosition = 8;
                            } else if (timeSelectedInt >= 900 && timeSelectedInt < 1000) {
                                timePosition = 9;
                            } else if (timeSelectedInt >= 1000 && timeSelectedInt < 1100) {
                                timePosition = 10;
                            } else if (timeSelectedInt >= 1100 && timeSelectedInt < 1200) {
                                timePosition = 11;
                            } else if (timeSelectedInt >= 1200 && timeSelectedInt < 1300) {
                                timePosition = 12;
                            } else if (timeSelectedInt >= 1300 && timeSelectedInt < 1400) {
                                timePosition = 13;
                            } else if (timeSelectedInt >= 1400 && timeSelectedInt < 1500) {
                                timePosition = 14;
                            } else if (timeSelectedInt >= 1500 && timeSelectedInt < 1600) {
                                timePosition = 15;
                            } else if (timeSelectedInt >= 1600 && timeSelectedInt < 1700) {
                                timePosition = 16;
                            } else if (timeSelectedInt >= 1700 && timeSelectedInt < 1800) {
                                timePosition = 17;
                            } else if (timeSelectedInt >= 1800 && timeSelectedInt < 1900) {
                                timePosition = 18;
                            } else if (timeSelectedInt >= 1900 && timeSelectedInt < 2000) {
                                timePosition = 19;
                            } else if (timeSelectedInt >= 2000 && timeSelectedInt < 2100) {
                                timePosition = 20;
                            } else if (timeSelectedInt >= 2100 && timeSelectedInt < 2200) {
                                timePosition = 21;
                            } else if (timeSelectedInt >= 2200 && timeSelectedInt < 2300) {
                                timePosition = 22;
                            } else if (timeSelectedInt >= 2300) {
                                timePosition = 23;
                            } else {
                                timePosition = 23;
                            }
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if (v == regionPicker) {
            FragmentManager fm = getFragmentManager();
            RegionDialog dialogFragment = new RegionDialog();
            dialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
            dialogFragment.show(fm, "region dialog");
        }

        if (v == btnSubmit) {

            if (dateString == null || dateString.isEmpty()) {
                dateString = queryFormat.format(c.getTime());
            }

            if (regionPicker.getText() == null || regionPicker.getText() == "") {
                position = 0;
                imageLocation = R.drawable.national;
            }

            networkCall();
        }
    }

    public static class RegionDialog extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Choose a region")
                    .setItems(R.array.regions, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    regionPicker.setText("national");
                                    position = 0;
                                    imageLocation = R.drawable.national;
                                    break;
                                case 1:
                                    regionPicker.setText("central");
                                    position = 1;
                                    imageLocation = R.drawable.central;
                                    break;
                                case 2:
                                    regionPicker.setText("north");
                                    position = 2;
                                    imageLocation = R.drawable.north;
                                    break;
                                case 3:
                                    regionPicker.setText("south");
                                    position = 3;
                                    imageLocation = R.drawable.south;
                                    break;
                                case 4:
                                    regionPicker.setText("east");
                                    position = 4;
                                    imageLocation = R.drawable.east;
                                    break;
                                case 5:
                                    regionPicker.setText("west");
                                    position = 5;
                                    imageLocation = R.drawable.west;
                                    break;
                                default:
                                    regionPicker.setText("national");
                                    position = 0;
                                    imageLocation = R.drawable.national;
                                    break;
                            }
                            regionPicker.setBackgroundColor(getResources().getColor(R.color.primary_light));
                            // The 'which' argument contains the index position
                            // of the selected item
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

    private void networkCall() {
        if (isOnline()) {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<AirQuality> call = null;
            call = apiService.getPSIByDate(dateString);

            call.enqueue(new Callback<AirQuality>() {
                @Override
                public void onResponse(Call<AirQuality> call, Response<AirQuality> response) {

                    PSIArray = (ArrayList<Item>) response.body().getItems();
                    RegionArray = (ArrayList<RegionMetadatum>) response.body().getRegionMetadata();
                    Collections.swap(RegionArray, 0, 5);
                    Collections.swap(RegionArray, 2, 3);
                    Collections.swap(RegionArray, 4, 5);

                    // if no time is selected, current time will be selected
                    if (timeSelectedInt == 2400) {
                        timePosition = PSIArray.size()-1;
                    }

                    Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                    intent.putParcelableArrayListExtra("todayPSIArray", PSIArray);
                    intent.putParcelableArrayListExtra("regionArray", RegionArray);
                    intent.putExtra("regionName", RegionArray.get(position).getName());
                    intent.putExtra("locationImage", imageLocation);
                    // indicate which time slot
                    intent.putExtra("timePosition", timePosition);
                    // indicate which region
                    intent.putExtra("position", position);
                    startActivity(intent);

                    finish();
                }

                @Override
                public void onFailure(Call<AirQuality> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Network call failed, server issues!", Toast.LENGTH_LONG).show();
                    Log.e("failure", t.getCause().toString());
                }
            });
        } else {
            Toast.makeText(this, "Network is not available", Toast.LENGTH_LONG).show();
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cancel) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
