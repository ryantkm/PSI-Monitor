package com.eventdee.psimonitor.appwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.eventdee.psimonitor.R;

public class ListWidgetFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private int mAppWidgetId;
    private String[] regionArray;
    private String[] psiArray;
    private String[] test;
    private int count;

    public ListWidgetFactory(Context mContext, Intent intent) {
        this.mContext = mContext;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        count = intent.getIntExtra("count",0);
        regionArray = intent.getStringArrayExtra("regionArray");
        psiArray = intent.getStringArrayExtra("psiArray");
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews row=
                new RemoteViews(mContext.getPackageName(), R.layout.list_item_widget);

        row.setTextViewText(android.R.id.text1, regionArray[position]);
        row.setTextViewText(android.R.id.text2, psiArray[position]);

//        Intent i=new Intent();
//        Bundle extras=new Bundle();
//
//        extras.putString(ListWidget.EXTRA_WORD, regionArray[position]);
//        extras.putInt(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
//        i.putExtras(extras);
//        row.setOnClickFillInIntent(android.R.id.text1, i);

        return row;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
