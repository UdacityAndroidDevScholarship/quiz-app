package com.developervishalsehgal.udacityscholarsapp.ui.settings;

import android.content.Context;
import android.support.v7.preference.DialogPreference;
import android.util.AttributeSet;

import com.developervishalsehgal.udacityscholarsapp.R;

public class SignOutDialogPreference extends DialogPreference{
    /**
     * Resource of the dialog layout
     */
    private int mDialogLayoutResId = R.layout.dialog_preference_signout;

    public SignOutDialogPreference(Context context) {
        // Delegate to other constructor
        this(context, null);
    }

    public SignOutDialogPreference(Context context, AttributeSet attrs) {
        // Delegate to other constructor
        // Use the preferenceStyle as the default style
        this(context, attrs, R.attr.preferenceStyle);
    }

    public SignOutDialogPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        // Delegate to other constructor
        this(context, attrs, defStyleAttr, defStyleAttr);
    }

    public SignOutDialogPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        // Du custom stuff here
        // ...
        // read attributes etc.
    }

    /**
     * Returns the layout resource that is used as the content View for the dialog
     */
    @Override
    public int getDialogLayoutResource() {
        return mDialogLayoutResId;
    }
}
