package com.test.rsslist.ui.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.test.rsslist.data.model.Rss;
import com.test.rsslist.network.RequestManager;


/**
 * Created on 05.10.2015.
 */
public class RssLoader extends AsyncTaskLoader<Rss> {

    public RssLoader(Context context) {
        super(context);
        forceLoad();
    }

    @Override
    public Rss loadInBackground() {
        return RequestManager.getInstance()
                .getService().fetchFeed();
    }

}
