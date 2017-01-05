package com.eventdee.psimonitor.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.eventdee.psimonitor.R;
import com.eventdee.psimonitor.activity.MainActivity;
import com.eventdee.psimonitor.network.ApiClient;
import com.eventdee.psimonitor.network.ApiInterface;
import com.eventdee.psimonitor.pojo.AirQuality;
import com.eventdee.psimonitor.pojo.Item;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgressWidget extends AppWidgetProvider {

    private Calendar todayCal;
    private SimpleDateFormat queryFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String today;

    void updateAppWidget(final Context context, final AppWidgetManager appWidgetManager,
                                final int appWidgetId) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AirQuality> call = null;
        call = apiService.getPSIByDate(today);

        call.enqueue(new Callback<AirQuality>() {
            @Override
            public void onResponse(Call<AirQuality> call, Response<AirQuality> response) {
                ArrayList<Item> PSIArray = new ArrayList<Item>();
                PSIArray = (ArrayList<Item>) response.body().getItems();
                int currentPSI = PSIArray.get(PSIArray.size() - 1).getReadings().getPsiTwentyFourHourly().getNational();


                // Perform this loop procedure for each Today widget
                int layoutId = R.layout.progress_widget;
                RemoteViews views = new RemoteViews(context.getPackageName(), layoutId);

                // Add the data to the RemoteViews
                views.setTextViewText(R.id.appwidget_text, String.valueOf(currentPSI));
                views.setProgressBar(R.id.progressBar2, 500, currentPSI, false);

                // Create an Intent to launch MainActivity
                Intent launchIntent = new Intent(context, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, 0);
                views.setOnClickPendingIntent(R.id.progress_widget, pendingIntent);

                // Tell the AppWidgetManager to perform an update on the current app widget
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

