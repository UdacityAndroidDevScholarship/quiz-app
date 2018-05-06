package com.developervishalsehgal.udacityscholarsapp.ui.discussion;

import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

/**
 * Create Quiz Contract. Keeps Create Quiz View and Presenter interfaces in one place.
 * @Author intkhabahmed
 */
public interface QuizDiscussionContract {

    /**
     * Quiz Discussion View
     */
    interface View extends BaseView<Presenter> {

    }

    /**
     * Quiz Discussion Presenter
     */
    interface Presenter extends BasePresenter {

    }
}
