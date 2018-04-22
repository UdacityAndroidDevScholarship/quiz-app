package com.developervishalsehgal.udacityscholarsapp.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developervishalsehgal.udacityscholarsapp.R;

/**
 * Created by dell on 4/22/2018.
 */

public class QuizRecyclerAdapter extends RecyclerView.Adapter<QuizRecyclerAdapter.QuizViewHolder> {

    QuizRecyclerAdapter(){

    }

    @Override
    public  QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QuizViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quiz_card_item,parent,false));
    }

    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class QuizViewHolder extends RecyclerView.ViewHolder{

        QuizViewHolder(View itemView){
            super(itemView);
        }
    }
}
