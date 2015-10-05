package com.test.rsslist.ui.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.test.rsslist.Config;
import com.test.rsslist.data.model.ApiResponse;
import com.test.rsslist.data.model.Rss;
import com.test.rsslist.network.RequestManager;

import java.io.IOException;

import retrofit.Call;
import retrofit.Response;

/**
 * Created on 05.10.2015.
 */
public class RssLoader extends AsyncTaskLoader<ApiResponse> {

    private static final String LOG_TAG = "RssLoader";

    private final Call<Rss> mCall;

    public RssLoader(Context context) {
        super(context);
        mCall = RequestManager.getInstance()
                .getService().fetchFeed(Config.API_BASE_URL);
    }

    @Override
    protected void onStartLoading() {
        forceLoad(); // Not caching response in the loader, just make new request
    }

    @Override
    public ApiResponse loadInBackground() {
        Call<Rss> call = mCall.clone();
        try {
            Response<Rss> response = call.execute();
            if (response.isSuccess()) {
                final Rss rss = response.body();
                return ApiResponse.successResponse(rss);
            } else {
                String message = response.errorBody().string();
                Log.e(LOG_TAG, "Fetch rss failed: " + message);
                return ApiResponse.errorResponse(message);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Fetch rss failed: " + e.getMessage());
            return ApiResponse.errorResponse(e.getMessage());
        }
    }


}
