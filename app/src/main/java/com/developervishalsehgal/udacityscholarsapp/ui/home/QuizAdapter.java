package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Adapter class for a {@link Quiz} list that is displayed on home screen
 */
public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private static final String DIFFICULTY_LEVEL_EASY = "easy";
    private static final String DIFFICULTY_LEVEL_MEDIUM = "medium";
    @SuppressWarnings("unused")
    private static final String DIFFICULTY_LEVEL_HARD = "hard";
    @SuppressWarnings("unused")
    private static final String DIFFICULTY_LEVEL_IMPOSSIBLE = "impossible";

    private List<Quiz> mQuizList;
    private QuizItemListener mQuizItemListener;

    /**
     * Parameterized constructor. Takes Quiz iteraction listener as parameter
     *
     * @param quizItemListener a {@link QuizItemListener} for listening to click events on quiz items
     */
    QuizAdapter(@NonNull QuizItemListener quizItemListener) {
        mQuizList = new ArrayList<>();
        this.mQuizItemListener = quizItemListener;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_quizzes, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
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

        // Sort the quizzes
        Collections.sort(mQuizList, (q1, q2) -> {
            if (q1.isBookmarked() != q2.isBookmarked()) {
                return Boolean.compare(q2.isBookmarked(), q1.isBookmarked());
            } else if (q1.isAttempted() != q2.isAttempted()) {
                return Boolean.compare(q1.isAttempted(), q2.isAttempted());
            } else {
                return q1.getTitle().compareTo(q2.getTitle());
            }
        });

        notifyDataSetChanged();
    }

    /**
     * Add new quizzes to current list
     *
     * @param quizList a {@link List} of {@link Quiz}es to be added to current list
     */
    @SuppressWarnings("unused")
    void addQuizzes(@NonNull List<Quiz> quizList) {
        // Removing the quizzes before adding, this ensure no duplication of quizzes
        this.mQuizList.removeAll(quizList);
        this.mQuizList.addAll(quizList);
        notifyDataSetChanged();
    }

    /**
     * {@link android.support.v7.widget.RecyclerView.ViewHolder} object for quiz item.
     */
    class QuizViewHolder extends RecyclerView.ViewHolder {

        TextView tvQuizName;
        TextView tvQuizCreator;
        TextView tvQuizStatus;
        TextView tvDateCreated;
        TextView tvDeadline;
        View easyLevelView;
        View mediumLevelView;
        View hardLevelView;

        ImageView ivBookmark;

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

            ivBookmark = itemView.findViewById(R.id.iv_bookmark);
        }

        void bind(int position) {

            final Context context = itemView.getContext();

            final Quiz currentQuiz = mQuizList.get(position);

            // Binding the data
            tvQuizName.setText(currentQuiz.getTitle());
            tvQuizCreator.setText(currentQuiz.getCreatorName());
            tvDateCreated.setText(currentQuiz.getLastModified());

            if (currentQuiz.getDeadline() == null || currentQuiz.getDeadline().isEmpty()) {
                tvDeadline.setText(context.getString(R.string.txt_deadline_none));
                tvDeadline.setTextColor(ContextCompat.getColor(context, R.color.dark_green));
            } else {
                tvDeadline.setText(currentQuiz.getDeadline());
                tvDeadline.setTextColor(ContextCompat.getColor(context, R.color.dark_red));
            }

            if (currentQuiz.isAttempted()) {
                tvQuizStatus.setText(context.getString(R.string.txt_status_completed));
                tvQuizStatus.setTextColor(ContextCompat.getColor(context, R.color.dark_green));
            } else {
                tvQuizStatus.setText(context.getString(R.string.txt_status_pending));
                tvQuizStatus.setTextColor(ContextCompat.getColor(context, R.color.dark_red));
            }

            if (currentQuiz.isBookmarked()) {
                ivBookmark.setImageResource(R.drawable.ic_bookmark_black_24dp);
            } else {
                ivBookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
            }

            if (DIFFICULTY_LEVEL_HARD.equalsIgnoreCase(currentQuiz.getDifficulty().trim())) {
                hardLevelView.setBackgroundColor(ContextCompat.getColor(context, R.color.color_red_deadline));
                mediumLevelView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_orange_light));
                easyLevelView.setBackgroundColor(ContextCompat.getColor(context, R.color.color_green_deadline));
            } else if (DIFFICULTY_LEVEL_MEDIUM.equalsIgnoreCase(currentQuiz.getDifficulty().trim())) {
                hardLevelView.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
                mediumLevelView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_orange_light));
                easyLevelView.setBackgroundColor(ContextCompat.getColor(context, R.color.color_green_deadline));
            } else {
                hardLevelView.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
                mediumLevelView.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
                easyLevelView.setBackgroundColor(ContextCompat.getColor(context, R.color.color_green_deadline));
            }

            // Attaching click listener to each quiz item
            itemView.setOnClickListener(v -> mQuizItemListener.onQuizClicked(currentQuiz));
            ivBookmark.setOnClickListener(v -> {
                // Toggle bookmark status
                currentQuiz.setBookmarked(!currentQuiz.isBookmarked());
                if (currentQuiz.isBookmarked()) {
                    ivBookmark.setImageResource(R.drawable.ic_bookmark_black_24dp);
                } else {
                    ivBookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                }
                mQuizItemListener.onBookmarkStatusChanged(currentQuiz);
            });
        }
    }

    /**
     * Callback interface for listening to click events on quiz items
     */
    interface QuizItemListener {
        /**
         * Called when quiz is clicked
         *
         * @param quiz the Quiz that was clicked
         */
        void onQuizClicked(Quiz quiz);

        /**
         * Called when quiz is clicked
         *
         * @param quiz the Quiz that was clicked
         */
        void onBookmarkStatusChanged(Quiz quiz);
    }
}
