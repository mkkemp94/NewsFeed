package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by kempm on 1/9/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private static final String LOG_TAG = NewsLoader.class.getName();

    // Query URL
    private String mUrl;

    // Constructor
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;

    }

    // Load
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    // Fetch news list on background thread
    @Override
    public List<News> loadInBackground() {

        // Make sure url exists
        if (mUrl == null) {
            return null;
        }

        // Perform network request, parse the response, and extract a list of news
        List<News> news = QueryUtils.fetchNewsData(mUrl);
        return news;
    }
}
