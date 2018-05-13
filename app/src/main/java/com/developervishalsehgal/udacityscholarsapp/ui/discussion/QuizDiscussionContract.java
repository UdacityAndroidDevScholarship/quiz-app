package com.developervishalsehgal.udacityscholarsapp.ui.discussion;


import com.developervishalsehgal.udacityscholarsapp.data.models.Comment;
import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

import java.util.List;

/**
 * Discussion screen contract. Keeps Discussion Screen View and Presenter interfaces in one place.
 *
 * @Author Rahil
 */
public interface QuizDiscussionContract {

    String KEY_QUIZ_ID = "quiz_id";

    /**
     * Discussion View
     */
    interface View extends BaseView<QuizDiscussionContract.Presenter> {
        void loadComments(List<Comment> discussions);

        void onCommentsLoadError();

        void loadComment(Comment comment);

        void showInvalidInput();
    }

    /**
     * Discussion Presenter
     */
    interface Presenter extends BasePresenter {
        void onClickedSendComment(String comment);
    }
}