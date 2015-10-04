package com.test.rsslist.network;


import com.test.rsslist.BuildConfig;
import com.test.rsslist.Config;

import retrofit.RestAdapter;
import retrofit.converter.SimpleXMLConverter;


/**
 * Created on 04.10.2015.
 */
public class RequestManager {

    private static RequestManager INSTANCE;
    private final ApiService mApiService;

    private RequestManager() {
        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint(Config.API_BASE_URL)
                .setConverter(new SimpleXMLConverter())
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .build();

        mApiService = retrofit.create(ApiService.class);
    }

    public static RequestManager getInstance(){
        if (INSTANCE == null) {
            synchronized(RequestManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RequestManager();
                }
            }
        }
        return INSTANCE;
    }


    public ApiService getService(){
        return mApiService;
    }
}
