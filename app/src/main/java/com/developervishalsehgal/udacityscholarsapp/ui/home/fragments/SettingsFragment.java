package com.developervishalsehgal.udacityscholarsapp.ui.home.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.ui.home.SignOutDialogPreference;
import com.developervishalsehgal.udacityscholarsapp.ui.home.SignOutPreferenceFragmentCompat;

/**
 * Created by Sudhanshu on 07-05-2018.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_app);

        PreferenceScreen preference = getPreferenceScreen();
        SharedPreferences sharedPreferences = preference.getSharedPreferences();
        int count = preference.getPreferenceCount();

        for (int i = 0; i < count; i++){
            Preference p = preference.getPreference(i);
            if(p instanceof ListPreference){
                String selectedValue = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSummary(p, selectedValue);
            }
        }
    }

    public void setPreferenceSummary(Preference pref, String value){
        ListPreference listPreference = (ListPreference) pref;
        int selectedIndex = listPreference.findIndexOfValue(value);
        if(selectedIndex >= 0) {
            listPreference.setSummary(listPreference.getEntries()[selectedIndex]);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if(preference != null) {
            if(preference instanceof ListPreference) {
                String selectedValue = sharedPreferences.getString(key, "");
                setPreferenceSummary(preference, selectedValue);
            }
        }
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        // Try if the preference is one of our custom Preferences
        DialogFragment dialogFragment = null;
        if (preference instanceof SignOutDialogPreference) {
            // Create a new instance of SignOutPreferenceFragmentCompat with the key of the related
            // Preference
            dialogFragment = SignOutPreferenceFragmentCompat
                    .newInstance(preference.getKey());
        }

        if (dialogFragment != null) {
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(this.getFragmentManager(),
                    "android.support.v7.preference" +
                            ".PreferenceFragment.DIALOG");
        }
        else {
            super.onDisplayPreferenceDialog(preference);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
