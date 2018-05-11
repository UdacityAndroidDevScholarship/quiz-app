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

import java.util.List;

public class QuizDiscussionAdapter extends RecyclerView.Adapter<QuizDiscussionAdapter.ViewHolder> {


    private static int sDemoCommentCounter = 3;
    private List<Comment> mComments;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*treating viewType as layout id as returned from getItemViewType(int position)*/
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        //TODO uncomment for actual implementation
       /* Comment comment = mComments.get(position);
        holder.bindComment(comment);*/

    }

    @Override
    public int getItemViewType(int position) {
        /*returning layout id to be inflated in viewType*/
        //TODO replace dummy implementation with actual implementation
        return position % 2 == 0 ? R.layout.item_discussion_others : R.layout.item_discussion_self;
    }

    @Override
    public int getItemCount() {
        //TODO need to uncomment for actual implementation
        //  return mComments == null ? 0 : mComments.size();
        return sDemoCommentCounter;
    }

    public void setComments(List<Comment> comments) {
        this.mComments = comments;
        notifyDataSetChanged();
    }

    public void addComment(Comment comment) {

        //TODO replace dummy implementation with actual implementation
        sDemoCommentCounter++;
        notifyItemInserted(sDemoCommentCounter);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView userImageView;
        TextView userNameTextView;
        TextView commentTimeTextView;
        TextView commentTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            userImageView = itemView.findViewById(R.id.item_discussion_chat_user_image);
            userNameTextView = itemView.findViewById(R.id.item_discussion_user_text_view);
            commentTimeTextView = itemView.findViewById(R.id.item_discussion_time_text_view);
            commentTextView = itemView.findViewById(R.id.item_discussion_comment_text_view);

        }

        public void bindComment(Comment comment) {

            userNameTextView.setText(comment.getCommentBy());
            commentTextView.setText(comment.getComment());
            //TODO need to normalize the date
            //commentTimeTextView.setText(comment.getCommentedOn());

            //TODO need to add circular bitmap transformation for circular image
            Glide.with(userImageView).load(comment.getImage()).into(userImageView);
        }
    }
}
