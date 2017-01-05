package com.eventdee.psimonitor.network;

import com.eventdee.psimonitor.pojo.AirQuality;
import com.eventdee.psimonitor.pojo.Item;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {
    @Headers("api-key: eiTLcBVXhvos6OJKOZnk3mWDOVCSwqdS")
    @GET("psi")
    Call<AirQuality> getPSIByDate(@Query("date") String date);

    @Headers("api-key: eiTLcBVXhvos6OJKOZnk3mWDOVCSwqdS")
    @GET("psi")
    Call<Item> getPSIByTime(@Query("date_time") String dateTime);

}