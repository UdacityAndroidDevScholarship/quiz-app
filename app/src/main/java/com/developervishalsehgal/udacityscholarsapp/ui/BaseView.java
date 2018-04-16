package com.developervishalsehgal.udacityscholarsapp.ui;

/**
 * Created by dru on 16/04/18.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showLoading();

    void hideLoading();

}
