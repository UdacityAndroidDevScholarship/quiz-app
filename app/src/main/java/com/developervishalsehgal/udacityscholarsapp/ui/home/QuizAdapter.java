package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.support.annotation.NonNull;
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

    QuizAdapter(@NonNull QuizItemListener quizItemListener) {
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

    /**
     * Clears current quiz list and displays the new list
     *
     * @param quizList a {@link List} of {@link Quiz}es to be displayed.
     */
    void loadQuizzes(@NonNull List<Quiz> quizList) {
        this.mQuizList.clear();
        this.mQuizList.addAll(quizList);
        notifyDataSetChanged();
    }

    /**
     * Add new quizzes to current list
     *
     * @param quizList a {@link List} of {@link Quiz}es to be added to current list
     */
    void addQuizzes(@NonNull List<Quiz> quizList) {
        // Removing the quizzes before adding, this ensure no duplication of quizzes
        this.mQuizList.removeAll(quizList);
        this.mQuizList.addAll(quizList);
        notifyDataSetChanged();
    }

    class QuizViewHolder extends RecyclerView.ViewHolder {

        TextView tvQuizName;
        TextView tvQuizCreator;
        TextView tvQuizStatus;
        TextView tvDateCreated;
        TextView tvDeadline;
        View easyLevelView;
        View mediumLevelView;
        View hardLevelView;

        QuizViewHolder(View itemView) {
            super(itemView);
            tvQuizName = itemView.findViewById(R.id.tv_quiz_name);
            tvQuizCreator = itemView.findViewById(R.id.tv_creator_name);
            tvQuizStatus = itemView.findViewById(R.id.tv_completion_status);
            tvDateCreated = itemView.findViewById(R.id.tv_date_created);
            tvDeadline = itemView.findViewById(R.id.tv_deadline);
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
