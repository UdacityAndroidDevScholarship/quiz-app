package com.developervishalsehgal.udacityscholarsapp.ui.create;

import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

/**
 * Create Quiz Contract. Keeps Create Quiz View and Presenter interfaces in one place.
 *
 * @Author kaushald
 */
public interface CreateQuizContract {

    /**
     * Create Quiz view
     */
    interface View extends BaseView<Presenter> {

    }

    /**
     * Create Quiz presenter
     */
    interface Presenter extends BasePresenter {

    }

}
