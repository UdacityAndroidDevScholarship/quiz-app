package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.preference.DialogPreference;
import android.util.AttributeSet;

import com.developervishalsehgal.udacityscholarsapp.R;

public class SignOutDialogPreference extends DialogPreference {

    private boolean loggedIn = true;
    private int mDialogLayoutResId = R.layout.pref_dialog_sign_out;

    public SignOutDialogPreference(Context context){
        super(context, null);
    }

    public SignOutDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPersistent(false);
        setDialogLayoutResource(mDialogLayoutResId);
        setDialogIcon(R.drawable.udacity_toolbar_logo);
        setKey(context.getString(R.string.pref_dialog_key));
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;

        // Saving it to Shared Preferences
        persistBoolean(loggedIn);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getBoolean(index, true);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        setLoggedIn(restorePersistedValue ? getPersistedBoolean(loggedIn)
        : (boolean) defaultValue);
    }

    @Override
    public int getDialogLayoutResource() {
        return mDialogLayoutResId;
    }
}
