package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void loadQuizzes(List<Quiz> quizzes) {

    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
