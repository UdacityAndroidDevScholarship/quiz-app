package com.developervishalsehgal.udacityscholarsapp.ui.discussion;

import com.developervishalsehgal.udacityscholarsapp.data.models.Discussion;
import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

import java.util.List;

/**
 * Home screen contract. Keeps Home Screen View and Presenter interfaces in one place.
 *
 * @Author kaushald
 */
public interface DiscussionContract {

    /**
     * Discussion view
     */
    interface View extends BaseView<DiscussionContract.Presenter> {
        void onSendMessageSuccess();

        void onSendMessageError(String message);

        void onMessageReceivedFromCommunity(String message);

        void loadDiscussionHistory(List<Discussion>lDiscussion);

        void onDiscussionLoadError();
    }

    /**
     * Discussion Presenter
     */
    interface Presenter extends BasePresenter {

        void onSendMessageClicked(Discussion discussion);
    }



}
