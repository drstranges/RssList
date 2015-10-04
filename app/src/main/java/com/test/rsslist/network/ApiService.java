package com.test.rsslist.network;

import com.test.rsslist.data.model.Rss;

import retrofit.http.GET;

/**
 * Created on 04.10.2015.
 */
public interface ApiService {

    @GET("/")
    Rss fetchFeed();

}
