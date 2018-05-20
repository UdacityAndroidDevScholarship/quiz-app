package com.developervishalsehgal.udacityscholarsapp.ui.discussion;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Comment;
import com.developervishalsehgal.udacityscholarsapp.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizDiscussionAdapter extends RecyclerView.Adapter<QuizDiscussionAdapter.ViewHolder> {

    private List<Comment> mComments;

    QuizDiscussionAdapter() {
        mComments = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /* *
         * treating viewType as layout id as returned from getItemViewType(int position)
         * */
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = mComments.get(position);
        holder.bindComment(comment);

    }

    @Override
    public int getItemViewType(int position) {
        Comment comment = mComments.get(position);
        return comment.isMyComment() ? R.layout.item_discussion_self : R.layout.item_discussion_others;
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public void setComments(List<Comment> comments) {
        this.mComments.clear();
        this.mComments.addAll(comments);

        Collections.sort(mComments, (o1, o2) -> (int) (o1.getCommentedOn() - o2.getCommentedOn()));

        notifyDataSetChanged();
    }

    public void addComment(@NonNull Comment comment) {
        mComments.add(comment);

        Collections.sort(mComments, (o1, o2) -> (int) (o1.getCommentedOn() - o2.getCommentedOn()));

        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView userImageView;
        TextView userNameTextView;
        TextView commentTimeTextView;
        TextView commentTextView;

        ViewHolder(View itemView) {
            super(itemView);
            userImageView = itemView.findViewById(R.id.item_discussion_chat_user_image);
            userNameTextView = itemView.findViewById(R.id.item_discussion_user_text_view);
            commentTimeTextView = itemView.findViewById(R.id.item_discussion_time_text_view);
            commentTextView = itemView.findViewById(R.id.item_discussion_comment_text_view);

        }

        void bindComment(Comment comment) {

            userNameTextView.setText(comment.getCommentBy());
            commentTextView.setText(comment.getComment());
            //TODO need to normalize the date
            commentTimeTextView.setText(Utils.getDisplayDate(comment.getCommentedOn()));

            //TODO need to add circular bitmap transformation for circular image
            Glide.with(userImageView).load(comment.getImage()).into(userImageView);
        }
    }
}
