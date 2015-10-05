package com.test.rsslist.network;

import com.test.rsslist.data.model.Rss;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created on 04.10.2015.
 */
public interface ApiService {

    @GET
    Call<Rss> fetchFeed(@Url String feedLink);

}
