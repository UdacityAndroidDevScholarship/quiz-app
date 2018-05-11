package com.developervishalsehgal.udacityscholarsapp.ui.settings;

import android.os.Bundle;
import android.support.v7.preference.PreferenceDialogFragmentCompat;

public class SignOutPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {



    /**
     * Creates a new Instance of the SignOutPreferenceDialogFragmentCompat and stores the key of the
     * related Preference
     *
     * @param key The key of the related Preference
     * @return A new Instance of the SignOutPreferenceDialogFragmentCompat
     */
    public static SignOutPreferenceDialogFragmentCompat newInstance(String key) {
        final SignOutPreferenceDialogFragmentCompat
                fragment = new SignOutPreferenceDialogFragmentCompat();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragment.setArguments(b);

        return fragment;
    }



    /**
     * Called when the Dialog is closed.
     *
     * @param positiveResult Whether the Dialog was accepted or canceled.
     */
    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {

        }
    }
}

