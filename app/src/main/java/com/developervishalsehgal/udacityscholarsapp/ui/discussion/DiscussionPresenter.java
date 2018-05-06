package com.developervishalsehgal.udacityscholarsapp.ui.discussion;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.developervishalsehgal.udacityscholarsapp.data.DataHandler;
import com.developervishalsehgal.udacityscholarsapp.data.DataHandlerProvider;
import com.developervishalsehgal.udacityscholarsapp.data.models.Discussion;

import java.util.List;

public class DiscussionPresenter implements DiscussionContract.Presenter {

    private DiscussionContract.View mView;
    private DataHandler mDataHandler;

    public DiscussionPresenter(DiscussionContract.View view) {
        this.mView = view;
        this.mDataHandler = DataHandlerProvider.provide();

        this.mView.setPresenter(this);
    }

    @Override
    public void start(@Nullable Bundle extras) {
        mView.showLoading();

        mDataHandler.fetchDiscussionHistory(0, new DataHandler.Callback<List<Discussion>>() {
            @Override
            public void onResponse(List<Discussion> result) {
                mView.loadDiscussionHistory(result);
                mView.hideLoading();
            }

            @Override
            public void onError() {
                mView.onDiscussionLoadError();
                mView.hideLoading();
            }
        });
    }

    @Override
    public void destroy() {
        this.mView = null;
        this.mDataHandler = null;
    }

    @Override
    public void onSendMessageClicked(Discussion discussio) {
        mView.showLoading();
        mDataHandler.sendDiscussionMessage(discussio, new DataHandler.Callback<Void>() {
            @Override
            public void onResponse(Void result) {
                mView.onSendMessageSuccess();
                mView.hideLoading();
            }

            @Override
            public void onError() {
                mView.onSendMessageSuccess();
                mView.hideLoading();
            }
        });
    }
}
