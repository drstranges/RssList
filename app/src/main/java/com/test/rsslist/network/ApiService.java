package com.test.rsslist.network;

import com.test.rsslist.data.model.RssItem;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created on 04.10.2015.
 */
public interface ApiService {

    @GET
    Call<List<RssItem>> fetchFeed(@Url String rssUrl);

}
