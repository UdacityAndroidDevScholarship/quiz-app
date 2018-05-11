package com.developervishalsehgal.udacityscholarsapp.ui.notification;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Notification;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Common Recycler view Adapter for <b>New Quiz</b> and <b>Deadline</b> tabs Notification screen.
 */
public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder> {

    @Retention(SOURCE)
    @IntDef({TYPE_NEW_QUIZ, TYPE_DEADLINE, TYPE_RESOURCES})
    @interface QuizType {
    }

    static final int TYPE_NEW_QUIZ = 1;
    static final int TYPE_DEADLINE = 2;
    static final int TYPE_RESOURCES = 3;

    private ItemClickListener mListener;

    private List<Notification> mNotifications;

    /**
     * should store either TYPE_NEW_QUIZ or TYPE_DEADLINE. We are not using enum here as
     * that will take up a lot more space than simple int constants
     */
    private int mType;

    /**
     * @param type     type of tab this is used for. TYPE_NEW_QUIZ or TYPE_DEADLINE
     * @param listener
     */
    NotificationsAdapter(@QuizType int type, ItemClickListener listener) {
        this.mType = type;
        this.mListener = listener;
        this.mNotifications = new ArrayList<>();
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item_notifications, parent, false);
        return new NotificationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.NotificationViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNotifications.size();
    }

    /**
     * Clears the list and adds new items
     *
     * @param notificationList the new items to be added
     */
    public void addItems(List<Notification> notificationList) {
        // TODO clear the notification list, add the items in new list passed above
        // update the adapter.
        mNotifications.clear();
        mNotifications.addAll(notificationList);
        notifyDataSetChanged();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {
        ImageView mImgIcon;
        TextView mTVNotificationTitle;
        TextView mTvNotificationDescription;
        TextView mTvDeadline;

        NotificationViewHolder(View itemView) {
            super(itemView);
            // TODO(1) bind the items from layout/list_item_notifications.xml here
            mTVNotificationTitle = itemView.findViewById(R.id.tv_notification_name);
            mImgIcon = itemView.findViewById(R.id.iv_notification_icon);
            mTvDeadline = itemView.findViewById(R.id.tv_notification_date);
            mTvNotificationDescription = itemView.findViewById(R.id.tv_notification_description);
        }

        void bind(int position) {
            Notification notificationItem = mNotifications.get(position);
            // TODO(2) bind notificationItem with UI elements (the textviews) here
            mTVNotificationTitle.setText(notificationItem.getTitle());
            mTvNotificationDescription.setText(notificationItem.getDescription());

            mTvDeadline.setText("not available");


            // TODO(3): set onclick listener in itemView here (no need to create a separate variable)
            // TODO(3): using lambda expression would be good, whatever suggested by android studio.
            // TODO(3): call mListener's onClick here and pass notificationItem
            itemView.setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onClick(notificationItem);
                }
            });

            // TODO(4): based on mType set drawable resource for on mImgIcon
            // quiz - R.drawable.ic_notification_quiz
            // deadline - R.drawable.ic_notification_deadline
            // resource - R.drawable.ic_notification_resource
            String quizDeadline = notificationItem.getExtra2();
            switch (mType) {
                case NotificationsAdapter.TYPE_NEW_QUIZ:
                    mImgIcon.setImageResource(R.drawable.ic_notification_quiz);

                    if (quizDeadline != null && !quizDeadline.trim().isEmpty()) {
                        mTvDeadline.setVisibility(View.VISIBLE);
                        mTvDeadline.setText(quizDeadline);
                        mTvDeadline.setTextColor(ContextCompat.getColor(itemView.getContext(),
                                R.color.white));
                    } else {
                        mTvDeadline.setVisibility(View.GONE);
                    }
                    break;
                case NotificationsAdapter.TYPE_DEADLINE:
                    mImgIcon.setImageResource(R.drawable.ic_notification_deadline);

                    if (quizDeadline != null && !quizDeadline.trim().isEmpty()) {
                        mTvDeadline.setVisibility(View.VISIBLE);
                        mTvDeadline.setText(quizDeadline);
                        mTvDeadline.setTextColor(ContextCompat.getColor(itemView.getContext(),
                                R.color.color_red_deadline));
                    } else {
                        mTvDeadline.setVisibility(View.GONE);
                    }
                    break;
                case NotificationsAdapter.TYPE_RESOURCES:
                    mImgIcon.setImageResource(R.drawable.ic_notification_resource);
                    break;
                default:
                    break;
            }
        }
    }

    public interface ItemClickListener {
        void onClick(Notification notification);
    }
}
