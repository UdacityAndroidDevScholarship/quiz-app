package com.developervishalsehgal.udacityscholarsapp.ui.notification;

import com.developervishalsehgal.udacityscholarsapp.data.models.Notification;
import com.developervishalsehgal.udacityscholarsapp.ui.BasePresenter;
import com.developervishalsehgal.udacityscholarsapp.ui.BaseView;

import java.util.List;

/**
 * Notification screen contract. Keeps Notification View and Presenter interfaces in one place.
 *
 * @Author kaushald
 */
public interface NotificationContract {

    /**
     * Notification Screen view
     */
    interface View extends BaseView<Presenter> {
        void loadNewQuizNotifications(List<Notification> newQuizNotifications);

        void loadDeadlineNotifications(List<Notification> deadlineNotifications);

        void loadResourceNotifications(List<Notification> resourceNotifications);

        void showError();

        void showResourcesTab();
    }

    /**
     * Notification Screen presenter
     */
    interface Presenter extends BasePresenter {
        void onTabSwitched(int tabId);
        void fetchNewQuizNotifications();
        void fetchDeadlineNotifications();
        void fetchResources();
    }

}
