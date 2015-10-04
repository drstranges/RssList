package com.test.rsslist.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.rsslist.R;
import com.test.rsslist.data.model.Rss;
import com.test.rsslist.data.model.RssItem;
import com.test.rsslist.ui.adapter.RssAdapter;
import com.test.rsslist.ui.loader.RssLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 04.10.2015.
 */
public class RssListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        LoaderManager.LoaderCallbacks<Rss> {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<RssItem> mRssItems = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View mEmptyMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rss_list, container, false);

        mEmptyMessage = rootView.findViewById(android.R.id.empty);

        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new RssAdapter(mRssItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        getLoaderManager().initLoader(R.id.loader_rss, Bundle.EMPTY, this);

        return rootView;
    }

    @Override
    public void onRefresh() {
        fetchFeed();
    }

    private void fetchFeed() {
        getLoaderManager().getLoader(R.id.loader_rss).forceLoad();
    }

    @Override
    public Loader<Rss> onCreateLoader(int id, Bundle args) {
        return new RssLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Rss> loader, Rss rss) {
        mRssItems.clear();
        if (rss != null && rss.getChannel() != null) {
            mRssItems.addAll(rss.getChannel().getItemList());
        }

        notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
        if (mRssItems == null || mRssItems.isEmpty()) {
            setEmptyMessageVisibility(true);
        } else {
            setEmptyMessageVisibility(false);
        }
    }

    private void setEmptyMessageVisibility(boolean visible) {
        mEmptyMessage.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<Rss> loader) {
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
