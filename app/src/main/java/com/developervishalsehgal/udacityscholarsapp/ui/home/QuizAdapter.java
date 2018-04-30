package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private List<Quiz> mQuizList;
    private QuizItemListener mQuizItemListener;

    QuizAdapter(QuizItemListener quizItemListener) {
        mQuizList = new ArrayList<>();
        this.mQuizItemListener = quizItemListener;
    }

    @Override
    public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_quizzes, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mQuizList.size();
    }

    void addQuizzes(List<Quiz> quizList){
        // Removing the quizzes before adding it. This ensures no duplication
        this.mQuizList.removeAll(quizList);
        this.mQuizList.addAll(quizList);
    }

    class QuizViewHolder extends RecyclerView.ViewHolder {

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

        void bind(int position) {
            Quiz currentQuiz = mQuizList.get(position);

            // Attaching click listener to each quiz item
            itemView.setOnClickListener(v -> mQuizItemListener.onQuizClicked(currentQuiz));

            // TODO: bind the POJO with layout elements here
            showQuizDifficultyView(this, currentQuiz.getDifficulty());
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

    interface QuizItemListener {
        void onQuizClicked(Quiz quiz);
    }
}
