package com.developervishalsehgal.udacityscholarsapp.ui.notification;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Notification;
import com.developervishalsehgal.udacityscholarsapp.ui.quizattempt.AttemptQuizActivity;
import com.developervishalsehgal.udacityscholarsapp.ui.quizattempt.AttemptQuizContract;

import java.util.List;


public class NotificationTabFragment extends Fragment implements NotificationsAdapter.ItemClickListener {

    private static final String KEY_TAB_TYPE = "tab_type";

    private NotificationsAdapter mNotificationAdapter;

    private int mTabType;

    RecyclerView mNotificationList;
    private TextView mTvEmptyMessage;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_notification_screen, container, false);
        mNotificationList = rootView.findViewById(R.id.rv_notification_item);
        mNotificationList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mNotificationList.setLayoutManager(linearLayoutManager);

        //Initialising Notification Adapter to pass context, active tab , itemClickListener
        mNotificationAdapter = new NotificationsAdapter(mTabType, this);
        mNotificationList.setAdapter(mNotificationAdapter);

        mTvEmptyMessage = rootView.findViewById(R.id.tv_notification_tab_empty);

        return rootView;
    }

    /**
     * Always use this method to create an instance of {@link NotificationTabFragment}
     *
     * @param tabType type of tab being added. See {@link NotificationsAdapter} for available
     *                options
     * @return A new instance of this fragment by setting the passed parameters
     */
    public static NotificationTabFragment getInstance(@NotificationsAdapter.QuizType int tabType) {
        NotificationTabFragment notificationTabFragment = new NotificationTabFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_TAB_TYPE, tabType);
        notificationTabFragment.setArguments(args);
        return notificationTabFragment;
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        if (args == null || !args.containsKey(KEY_TAB_TYPE)) {
            throw new IllegalArgumentException("Tab type is missing");
        }
        mTabType = args.getInt(KEY_TAB_TYPE);
    }

    /**
     * Clears the list and adds new items
     *
     * @param notificationList the new items to be added
     */
    public void addItems(@NonNull List<Notification> notificationList) {
        mNotificationAdapter.addItems(notificationList);
        if (!notificationList.isEmpty()) {
            mTvEmptyMessage.setVisibility(View.GONE);
            mNotificationList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(Notification notification) {
        switch (mTabType) {
            case NotificationsAdapter.TYPE_NEW_QUIZ:
                // TODO: launch quiz description activity by extracting
                // TODO: information from notification object. (action field contains quiz id)
                Intent newQuizIntent = new Intent(getActivity(), AttemptQuizActivity.class);
                newQuizIntent.putExtra(AttemptQuizContract.KEY_QUIZ_ID, notification.getAction());
                startActivity(newQuizIntent);
                break;
            case NotificationsAdapter.TYPE_DEADLINE:
                // TODO: launch quiz description activity by extracting (Same action as New Quiz).
                // TODO: information from notification object. (action field contains quiz id)
                Intent attemptQuizIntent = new Intent(getActivity(), AttemptQuizActivity.class);
                attemptQuizIntent.putExtra(AttemptQuizContract.KEY_QUIZ_ID, notification.getAction());
                startActivity(attemptQuizIntent);
                break;
            case NotificationsAdapter.TYPE_RESOURCES:
                // TODO: launch the resource URL. ACTION_VIEW
                // TODO: (action field contains resource URL)
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(notification.getAction()));
                startActivity(browserIntent);
                break;
            default:
                break;
        }
    }
}
