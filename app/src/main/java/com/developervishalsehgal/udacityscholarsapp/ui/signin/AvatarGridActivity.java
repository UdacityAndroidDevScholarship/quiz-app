package com.developervishalsehgal.udacityscholarsapp.ui.signin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.developervishalsehgal.udacityscholarsapp.R;

public class AvatarGridActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_avatar_grid);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",position+"");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

    }

}
