package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developervishalsehgal.udacityscholarsapp.R;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private Context mContext;

    QuizAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_quizzes, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position) {
        holder.quizNameTextView.setText(mContext.getString(R.string.dummy_quiz_name));
        holder.quizCreatorTextView.setText(mContext.getString(R.string.dummy_creator_name));
        holder.dateCreatedTextView.setText(mContext.getString(R.string.dummy_date_created));
        holder.deadlineTextView.setText(mContext.getString(R.string.dummy_deadline));
        holder.quizStatusTextView.setText(mContext.getString(R.string.dummy_status));
        showQuizDifficultyView(holder, mContext.getString(R.string.dummy_quiz_level));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class QuizViewHolder extends RecyclerView.ViewHolder{
        TextView quizNameTextView;
        TextView quizCreatorTextView;
        TextView quizStatusTextView;
        TextView dateCreatedTextView;
        TextView deadlineTextView;
        View easyLevelView;
        View mediumLevelView;
        View hardLevelView;
        QuizViewHolder(View itemView) {
            super(itemView);
            quizNameTextView = itemView.findViewById(R.id.tv_quiz_name);
            quizCreatorTextView = itemView.findViewById(R.id.tv_creator_name);
            quizStatusTextView = itemView.findViewById(R.id.tv_completion_status);
            dateCreatedTextView = itemView.findViewById(R.id.tv_date_created);
            deadlineTextView = itemView.findViewById(R.id.tv_deadline);
            easyLevelView = itemView.findViewById(R.id.difficulty_easy);
            mediumLevelView = itemView.findViewById(R.id.difficulty_medium);
            hardLevelView = itemView.findViewById(R.id.difficulty_hard);
        }
    }

    private void showQuizDifficultyView(QuizViewHolder holder, String difficultyLevel) {
        switch (difficultyLevel) {
            case "Easy":
                holder.easyLevelView.setVisibility(View.VISIBLE);
                holder.mediumLevelView.setVisibility(View.INVISIBLE);
                holder.hardLevelView.setVisibility(View.INVISIBLE);
                return;
            case "Medium":
                holder.easyLevelView.setVisibility(View.VISIBLE);
                holder.mediumLevelView.setVisibility(View.VISIBLE);
                holder.hardLevelView.setVisibility(View.INVISIBLE);
                return;
            case "Hard":
                holder.easyLevelView.setVisibility(View.VISIBLE);
                holder.mediumLevelView.setVisibility(View.VISIBLE);
                holder.hardLevelView.setVisibility(View.VISIBLE);
        }
    }
}
