package com.andytenholder.comradetrump;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Andy Tenholder on 4/12/2017.
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

    }

    public static class SettingsPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            //Create a preference object and get the value loacted in the string
            Preference startDate = findPreference(getString(R.string.start_date_key));
            bindPreferenceSummaryToValue(startDate);

            //Create a preference object and get the value loacted in the string
            Preference endDate = findPreference(getString(R.string.end_date_key));
            bindPreferenceSummaryToValue(endDate);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object value){
            String stringValue = value.toString();
            preference.setSummary(stringValue);
            return true;
        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = preferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceString);
        }

    }


}