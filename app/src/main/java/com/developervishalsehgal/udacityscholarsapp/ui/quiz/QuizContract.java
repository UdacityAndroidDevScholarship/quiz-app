package com.developervishalsehgal.udacityscholarsapp.ui.quiz;

import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

/**
 * Quiz screen contract. Keeps Quiz View and Presenter interfaces in one place.
 *
 * @Author kaushald
 */
public interface QuizContract {

    /**
     * Quiz View
     */
    interface View extends BaseView<Presenter> {

    }

    /**
     * Quiz Presenter
     */
    interface Presenter extends BasePresenter {

    }

}
