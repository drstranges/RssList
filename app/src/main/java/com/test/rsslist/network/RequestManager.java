package com.test.rsslist.network;

import android.content.Context;

import com.test.rsslist.Config;

import retrofit.Retrofit;
import retrofit.SimpleXmlConverterFactory;

/**
 * Created on 04.10.2015.
 */
public class RequestManager {

    private static RequestManager INSTANCE;
    private final ApiService mApiService;

    private RequestManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.API_BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        mApiService = retrofit.create(ApiService.class);
    }

    public static RequestManager getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new RequestManager();
        }
        return INSTANCE;
    }


    public ApiService getService(){
        return mApiService;
    }
}
