package com.example.android.newsapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public static class NewsPreferenceFragment extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener{

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Use our settings resource
            addPreferencesFromResource(R.xml.settings_main);

            Preference searchTerm = findPreference(getString(R.string.settings_search_term_key));
            bindPreferenceSummaryToValue(searchTerm);

            Preference pageSize = findPreference(getString(R.string.settings_page_size_key));
            bindPreferenceSummaryToValue(pageSize);

            Preference orderBy = findPreference(getString(R.string.settings_order_by_key));
            bindPreferenceSummaryToValue(orderBy);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {

            // Take what we filled in for the preference and update the settings
            String stringValue = o.toString();

            if (preference instanceof ListPreference) {

                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(stringValue);
                if (prefIndex >= 0) {

                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[prefIndex]);
                }
            } else {

                preference.setSummary(stringValue);
            }

            return true;
        }

        private void bindPreferenceSummaryToValue(Preference preference) {

            // Listen for preference changes
            preference.setOnPreferenceChangeListener(this);

            // Get the preferences
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());

            // See what's currently there
            String preferenceString = preferences.getString(preference.getKey(), "");

            // Change to what's there
            onPreferenceChange(preference, preferenceString);
        }
    }
}
