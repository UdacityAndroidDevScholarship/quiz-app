package com.developervishalsehgal.udacityscholarsapp.ui.discussion;

import com.developervishalsehgal.udacityscholarsapp.data.models.Comment;
import com.developervishalsehgal.udacityscholarsapp.data.models.Discussion;
import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

/**
 * Create Quiz Contract. Keeps Create Quiz View and Presenter interfaces in one place.
 *
 * @author intkhabahmed
 */
public interface QuizDiscussionContract {

    /**
     * Quiz Discussion View
     */
    interface View extends BaseView<Presenter> {

        void loadDiscussion(Discussion discussion);

        void onDiscussionLoadError();

        void onSendMessageSuccess();

        void onSendMessageFailed();

        void showReceivedMessage(Comment comment);
    }

    /**
     * Quiz Discussion Presenter
     */
    interface Presenter extends BasePresenter {
        void onSendMessageClicked(Comment comment);

        void onMessageReceived(Comment comment);
    }
}
