package com.developervishalsehgal.udacityscholarsapp.ui.intro;

import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

/**
 * Introduction screen contract. Keeps Introduction View and Presenter interfaces in one place.
 *
 * @Author kaushald
 */
public interface IntroductionContract {

    /**
     * Introduction view
     */
    interface View extends BaseView<Presenter> {

    }

    /**
     * Introduction presenter
     */
    interface Presenter extends BasePresenter {

    }

}
