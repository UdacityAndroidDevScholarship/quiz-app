package com.developervishalsehgal.udacityscholarsapp.settings;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.util.Log;

import com.developervishalsehgal.udacityscholarsapp.R;

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    SharedPreferences preferences;
    PreferenceScreen preferenceScreen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Register the sharedPreferencesListener to
            trigger events when shared preferences are changed
         */
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_app);

        preferences = getPreferenceScreen().getSharedPreferences();
        preferenceScreen = getPreferenceScreen();

        int categoryCnt = preferenceScreen.getPreferenceCount();
        int prefCount;

        // Getting the preference category count
        for (int i = 0; i < categoryCnt; i++) {
            Preference p = preferenceScreen.getPreference(i);
            if (p instanceof PreferenceCategory) {
                PreferenceCategory pc = (PreferenceCategory) p;
                prefCount = pc.getPreferenceCount();
                Log.d("Preference Count", String.valueOf(prefCount));

                /* Calling setupPreferences() method
                    to set preference summary after getting the
                    preference count from preference category */
                setupPreferences(prefCount, pc);
            }
        }
    }

    private void setupPreferences(int prefCount, PreferenceCategory category) {
        for (int i = 0; i < prefCount; i++) {
            Preference p = category.getPreference(i);
            if (!(p instanceof SwitchPreference)) {
                String value = preferences.getString(p.getKey(), "");
                Log.d("Value", value);
                setPreferenceSummary(p, value);
            }
        }

        Preference signOut = findPreference(getResources().getString(R.string.sign_out_key));
        signOut.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                showSignOutAlert();
                return true;
            }
        });
    }

    private void showSignOutAlert() {
        new AlertDialog.Builder(getContext())
                .setTitle(getResources().getString(R.string.sign_out_title))
                .setMessage(getResources().getString(R.string.sign_out_message))
                .setPositiveButton(getResources().getString(R.string.sign_out_ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO Implement Sign out sequence
                            }
                        })
                .setNegativeButton(getResources().getString(R.string.sign_out_cancel),
                        null)
                .create().show();
    }

    //Function to set the preferenceSummary
    private void setPreferenceSummary(Preference preference, String value) {
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int index = listPreference.findIndexOfValue(value);
            Log.d("Index", String.valueOf(index));
            if (index >= 0) {
                listPreference.setSummary(listPreference.getEntries()[index]);
            }
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //Update the summary of List preference on change
        Preference preference = findPreference(key);
        if (preference != null) {
            if (!(preference instanceof SwitchPreference)) {
                String value = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceSummary(preference, value);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //Unregister the sharedPreferences listener to prevent memory leaks
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
