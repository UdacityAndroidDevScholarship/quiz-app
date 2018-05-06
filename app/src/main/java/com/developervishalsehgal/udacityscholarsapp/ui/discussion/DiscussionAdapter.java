package com.developervishalsehgal.udacityscholarsapp.ui.discussion;

import android.content.Context;
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
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class for a {@link Quiz} list that is displayed on home screen
 */
public class DiscussionAdapter extends RecyclerView.Adapter<DiscussionAdapter.DiscussionViewHolder> {

    private List<Comment> mCommentList;

    DiscussionAdapter(List<Comment> mCommentList){
        if(mCommentList == null){
            this.mCommentList = new ArrayList<>();
        }else{
            this.mCommentList = mCommentList;
        }
    }

    @NonNull
    @Override
    public DiscussionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_discussion, parent, false);
        return new DiscussionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscussionViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    public void addMessage(Comment comment){
        mCommentList.add(comment);
        notifyDataSetChanged();
    }

    /**
     * {@link android.support.v7.widget.RecyclerView.ViewHolder} object for discussion item.
     */
    class DiscussionViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserName;
        TextView tvMessage;
        TextView tvTimeStamp;
        ImageView imgUserProfile;

        DiscussionViewHolder(View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tv_username);
            tvMessage = itemView.findViewById(R.id.tv_message);
            tvTimeStamp = itemView.findViewById(R.id.tv_time_stamp);
            imgUserProfile = itemView.findViewById(R.id.img_discussion_user_image);
        }

        void bind(int position) {

            Context context = itemView.getContext();

            Comment comment = mCommentList.get(position);

            tvUserName.setText(comment.getCommentBy());
            tvMessage.setText(comment.getComment());
            tvTimeStamp.setText(comment.getImage());

            if(comment.getImage()!=null){
                Glide.with(context).load(comment.getImage()).into(imgUserProfile);
            }else
            {
                imgUserProfile.setImageResource(R.drawable.ic_person);
            }

        }
    }

}
