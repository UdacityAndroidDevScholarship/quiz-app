package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.view.View;
import android.widget.Toast;

import com.developervishalsehgal.udacityscholarsapp.R;

/**
 * Created by Sudhanshu on 07-05-2018.
 */

public class SignOutPreferenceFragmentCompat extends PreferenceDialogFragmentCompat {

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == DialogInterface.BUTTON_POSITIVE){
            Toast.makeText(getContext(), "Sign out clicked", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Sign out cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {

    }

    @Override
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        Context context = getContext();
        builder.setTitle(context.getString(R.string.pref_dialog_title));
        builder.setPositiveButton(context.getString(R.string.pref_dialog_positive_btn_text), this);
        builder.setNegativeButton(context.getString(R.string.pref_dialog_negative_btn_text), this);
        super.onPrepareDialogBuilder(builder);
    }

    public static SignOutPreferenceFragmentCompat newInstance(
            String key) {
        final SignOutPreferenceFragmentCompat
                fragment = new SignOutPreferenceFragmentCompat();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragment.setArguments(b);

        return fragment;
    }
}
