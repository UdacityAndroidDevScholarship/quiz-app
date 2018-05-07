package com.developervishalsehgal.udacityscholarsapp.ui.discussion;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Comment;
import com.developervishalsehgal.udacityscholarsapp.utils.DateUtils;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class QuizDiscussionAdapter extends RecyclerView.Adapter<QuizDiscussionAdapter.QuizDiscussionViewHolder> {

    private List<Comment> mComments;
    private String mLoggedUser;

    /**
     * Parameterized constructor. Takes Logged Username as parameter
     *
     * @param loggedUser a {@link String} for comparing the user who sent message and used to determine left
     *                   or right layout for chat bubble
     */
    QuizDiscussionAdapter(String loggedUser) {
        mComments = new ArrayList<>();
        mLoggedUser = loggedUser;
    }

    /**
     * Inflating left layout if the message is received from other user and right layout if message is sent
     * by the logged user.
     *
     * @param parent   - parent container of view
     * @param viewType - Type of layout according to the user who sent the message
     * @return QuizDiscussionViewHolder - type of view holder
     */
    @NonNull
    @Override
    public QuizDiscussionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discussion_right, parent, false);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discussion_left, parent, false);
                break;
            default:
                throw new IllegalArgumentException("Unsupported ViewType");
        }
        return new QuizDiscussionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizDiscussionViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    /**
     * @param position - current item position
     * @return int - 0 if the logged user sent the message and 1 if message is received from other user
     */
    @Override
    public int getItemViewType(int position) {
        String commentedBy = mComments.get(position).getCommentBy();
        if (mLoggedUser.equals(commentedBy)) {
            return 0;
        }
        return 1;
    }

    /**
     * Clears current comment list and displays the new list
     *
     * @param comments a {@link List} of {@link Comment} to be displayed.
     */
    public void loadCommentHistory(List<Comment> comments) {
        this.mComments.clear();
        this.mComments = comments;
        notifyDataSetChanged();
    }

    /**
     * Add new comment to current list
     *
     * @param comment a {@link Comment} to be added to current list
     */
    public void addNewMessage(Comment comment) {
        mComments.add(comment);
        notifyDataSetChanged();
    }

    /**
     * {@link android.support.v7.widget.RecyclerView.ViewHolder} object for comment item.
     */
    class QuizDiscussionViewHolder extends RecyclerView.ViewHolder {
        private CircularImageView chatProfileImage;
        private TextView senderName;
        private TextView messageDateCreated;
        private TextView message;

        QuizDiscussionViewHolder(View itemView) {
            super(itemView);
            chatProfileImage = itemView.findViewById(R.id.img_chat_profile_picture);
            senderName = itemView.findViewById(R.id.tv_sender_name);
            messageDateCreated = itemView.findViewById(R.id.tv_created_date);
            message = itemView.findViewById(R.id.tv_chat_description);
        }

        void bind(int position) {
            Comment currentComment = mComments.get(position);
            Context context = itemView.getContext();
            if (currentComment.getImage() != null) {
                Glide.with(context).load(currentComment.getImage()).into(chatProfileImage);
            } else {
                chatProfileImage.setImageResource(R.drawable.ic_person);
            }
            senderName.setText(currentComment.getCommentBy());
            messageDateCreated.setText(DateUtils.getFormattedTime(currentComment.getCommentedOn(), System.currentTimeMillis()));
            message.setText(currentComment.getComment());
        }
    }
}
