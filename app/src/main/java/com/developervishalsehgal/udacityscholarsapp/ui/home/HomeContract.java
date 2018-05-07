package com.developervishalsehgal.udacityscholarsapp.ui.home;

import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;
import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

import java.util.List;

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
        void loadQuizzes(List<Quiz> quizzes);
    }

    /**
     * Home Presenter
     */
    interface Presenter extends BasePresenter {
        void onQuizClicked(Quiz quiz);
    }

}
