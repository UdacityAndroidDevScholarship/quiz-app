package com.developervishalsehgal.udacityscholarsapp.ui.home;

import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

/**
 * Home screen contract. Keeps Home Screen View and Presenter interfaces in one place.
 *
 * @Author kaushald
 */
public interface HomeContract {

    /**
     * Home View
     */
    interface View extends BaseView<Presenter> {

    }

    /**
     * Home Presenter
     */
    interface Presenter extends BasePresenter {

    }

}
