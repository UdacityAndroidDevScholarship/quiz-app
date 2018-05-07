package com.developervishalsehgal.udacityscholarsapp.ui.notification;

import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

/**
 * Notification screen contract. Keeps Notification View and Presenter interfaces in one place.
 *
 * @Author kaushald
 */
public interface NotificationScreenContract {

    /**
     * Notification Screen view
     */
    interface View extends BaseView<Presenter> {

    }

    /**
     * Notification Screen presenter
     */
    interface Presenter extends BasePresenter {

    }

}
