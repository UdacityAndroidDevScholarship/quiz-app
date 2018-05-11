package com.developervishalsehgal.udacityscholarsapp.ui.settings;

/**
 * Created by tavishjain on 08-05-2018.
 */

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.developervishalsehgal.udacityscholarsapp.R;

public class FragmentSettings extends PreferenceFragmentCompat {

    public FragmentSettings(){    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference_screen);
    }
}