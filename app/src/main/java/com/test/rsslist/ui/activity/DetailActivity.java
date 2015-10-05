package com.test.rsslist.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.test.rsslist.R;
import com.test.rsslist.ui.fragment.DetailFragment;

public class DetailActivity extends AppCompatActivity {

    private static final String ARG_LINK = "link";
    private static final String ARG_TITLE = "title";

    private String mLink;
    private String mTitle;

    public static void prepareExtra(Intent intent, String title, String link) {
        intent.putExtra(ARG_TITLE, title);
        intent.putExtra(ARG_LINK, link);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sendLinkToWebViewFragment();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        sendLinkToWebViewFragment();
    }

    private void sendLinkToWebViewFragment() {
        mTitle = getIntent().getStringExtra(ARG_TITLE);
        setTitle(mTitle);
        mLink = getIntent().getStringExtra(ARG_LINK);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_web_view);
        if (fragment != null && fragment instanceof DetailFragment) {
            ((DetailFragment) fragment).loadLink(mLink);
        }
    }
}
