package com.developervishalsehgal.udacityscholarsapp.ui.settings;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.developervishalsehgal.udacityscholarsapp.R;

/**
 * Created by Akshansh on 08-05-2018.
 */

public class SignOutDialog extends Dialog {

    private Button mCancel, mOk;
    private Context mContext;

    public SignOutDialog(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_signout);

        mCancel = findViewById(R.id.cancel_button_dialog);
        mOk = findViewById(R.id.ok_button_dialog);

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dismiss the Dialog
                dismiss();
            }
        });

        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Adding the code for sigining out of the application.
            }
        });
    }
}
