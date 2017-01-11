package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<News>> {

    public static final String LOG_TAG = NewsActivity.class.getName();

    // Nintendo Switch URL
    //public static final String NINTENDO_URL = "https://content.guardianapis.com/search?api-key=677d2b25-9c8d-4958-8194-951345eb1425&page-size=20&q=nintendo%20AND%20switch";
    public static final String NINTENDO_URL = "https://content.guardianapis.com/search";

    // Id for news loader
    private static final int NEWS_LOADER_ID = 1;

    // Adapter for populating the list view
    private NewsAdapter newsAdapter;

    // Text view is displayed when list is empty
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Log.e(LOG_TAG, "TEST : In onCreate()");

        // List view to display news
        ListView listView = (ListView) findViewById(R.id.list);

        // Text view only shows when list is empty
        mEmptyStateTextView = (TextView) findViewById(R.id.tv_no_news_found);
        listView.setEmptyView(mEmptyStateTextView);

        // Create NEW adapter with empty list of news stories as imput
        newsAdapter = new NewsAdapter(this, new ArrayList<News>());

        // Assign adapter to list view
        listView.setAdapter(newsAdapter);

        // When a news item is tapped
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get the url for this news story
                News thisStory = newsAdapter.getItem(position);
                String thisUrl = thisStory.getWebUrl();

                // Go to website
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(thisUrl));
                startActivity(intent);
            }
        });

        // Get a reference to the connectivity manager to check the state of the network connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // If there is a network connection
        if (networkInfo != null && networkInfo.isConnected()) {

            // Initialize a loader which will run in the background
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {

            // Hide loading indicator
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Display error in text view
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {

        // Get search term from preferences
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String searchTerm = sharedPrefs.getString(
                getString(R.string.settings_search_term_key),
                getString(R.string.settings_search_term_default));
        String pageSize = sharedPrefs.getString(
                getString(R.string.settings_page_size_key),
                getString(R.string.settings_page_size_default));
        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default));

        // Allow for editing of the uri/url
        Uri baseUri = Uri.parse(NINTENDO_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("api-key", "677d2b25-9c8d-4958-8194-951345eb1425");
        uriBuilder.appendQueryParameter("order-by", orderBy);
        uriBuilder.appendQueryParameter("page-size", pageSize);
        uriBuilder.appendQueryParameter("q", searchTerm);

        // Create a new loader for the given uri
        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newsList) {

        // Hide loading indicator
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Clear the adapter
        newsAdapter.clear();

        if (newsList != null && !newsList.isEmpty()) {

            // Add loaded list to adapter's data set and update
            newsAdapter.addAll(newsList);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

        // Loader reset, so clear adapter of existing data
        newsAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
