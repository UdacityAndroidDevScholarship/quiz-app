package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developervishalsehgal.udacityscholarsapp.R;

public class QuizRecyclerAdapter extends RecyclerView.Adapter<QuizRecyclerAdapter.QuizViewHolder> {

    public QuizRecyclerAdapter() {

    }

    @Override
    public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QuizViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_quiz, parent, false));
    }

    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {

        public QuizViewHolder(View itemView) {
            super(itemView);
        }
    }
}