package com.eventdee.psimonitor.appwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.eventdee.psimonitor.R;
import com.eventdee.psimonitor.network.ApiClient;
import com.eventdee.psimonitor.network.ApiInterface;
import com.eventdee.psimonitor.pojo.AirQuality;
import com.eventdee.psimonitor.pojo.Item;
import com.eventdee.psimonitor.pojo.RegionMetadatum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of App Widget functionality.
 */
public class ListWidget extends AppWidgetProvider {

    public static String EXTRA_WORD=
            "com.commonsware.android.appwidget.lorem.WORD";
    private ArrayList<Item> PSIArray = new ArrayList<Item>();
    private SimpleDateFormat queryFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Calendar todayCal;
    private String today;
    private String[] regionList;
    private String[] psiList;
    private String[] test;

    private ArrayList<RegionMetadatum> RegionArray = new ArrayList<RegionMetadatum>();

    void updateAppWidget(final Context context, final AppWidgetManager appWidgetManager,
                         final int appWidgetId) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AirQuality> call = null;
        call = apiService.getPSIByDate(today);

        call.enqueue(new Callback<AirQuality>() {
            @Override
            public void onResponse(Call<AirQuality> call, Response<AirQuality> response) {
                PSIArray = (ArrayList<Item>) response.body().getItems();
                RegionArray = (ArrayList<RegionMetadatum>) response.body().getRegionMetadata();

                Collections.swap(RegionArray, 1, 4);
                Collections.swap(RegionArray, 3, 4);

                RegionArray.get(0).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getNational());
                RegionArray.get(1).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getCentral());
                RegionArray.get(2).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getNorth());
                RegionArray.get(3).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getSouth());
                RegionArray.get(4).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getEast());
                RegionArray.get(5).setpollutantValue(PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getWest());

                regionList = new String[RegionArray.size()];
                psiList = new String[RegionArray.size()];
                for (int i=0; i< RegionArray.size(); i++){
                    regionList[i] = RegionArray.get(i).getName();
                    psiList[i] = RegionArray.get(i).getPollutantValue();
                }
                Intent svcIntent=new Intent(context, ListWidgetService.class);

                svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
                svcIntent.putExtra("regionArray", regionList);
                svcIntent.putExtra("psiArray", psiList);
                svcIntent.putExtra("count", RegionArray.size());

                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.list_widget);

                views.setRemoteAdapter(R.id.region_list, svcIntent);

                // Instruct the widget manager to update the widget
                appWidgetManager.updateAppWidget(appWidgetId, views);
            }

            @Override
            public void onFailure(Call<AirQuality> call, Throwable t) {
                Toast.makeText(context, "Network call failed, server issues!", Toast.LENGTH_LONG).show();
                Log.e("failure", t.getCause().toString());
            }
        });
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        todayCal = Calendar.getInstance();
        today = queryFormat.format(todayCal.getTime());
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

