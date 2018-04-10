package com.developervishalsehgal.udacityscholarsapp.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developervishalsehgal.udacityscholarsapp.Models.Quizzes;
import com.developervishalsehgal.udacityscholarsapp.R;

import java.util.List;

/**
 * Created by developervishal on 02/04/18.
 */

public class QuizListAdapter extends BaseAdapter {

    List<Quizzes> list;
    Context mContext;

    ViewHolder holder;
    private LayoutInflater layoutInflater;

    SharedPreferences.Editor editor;


    public QuizListAdapter(Context context, List<Quizzes> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        SharedPreferences sharedPrefs = mContext.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        final Quizzes quizzes = list.get(position);

        if (convertView == null) {

            convertView = layoutInflater.from(mContext).inflate(R.layout.quizzes_list_item, parent, false);
            holder = new ViewHolder();
            holder.quizNames = (TextView) convertView.findViewById(R.id.quiz_name);
            holder.moderatorName = (TextView) convertView.findViewById(R.id.moderator_name);
            holder.deadline = (TextView) convertView.findViewById(R.id.deadline);
            holder.description = convertView.findViewById(R.id.description);
            holder.imageView = convertView.findViewById(R.id.quiz_thumb);
            holder.doneBox = convertView.findViewById(R.id.done_checkbox);
        }



        holder.quizNames.setText(quizzes.getQuizname());

        holder.moderatorName.setText(quizzes.getModerator());

        holder.deadline.setText(mContext.getResources().getString(R.string.deadline) + quizzes.getDeadline());
        holder.description.setText(quizzes.getDescription());
        String regexStr = "^[1-9]\\d*(\\.\\d+)?$";

        editor = sharedPrefs.edit();

       holder.doneBox.setChecked(sharedPrefs.getBoolean("CheckValue"+position, false));

        if (extractNumber(holder.deadline.getText().toString()).trim().matches(regexStr)) {
            holder.deadline.setTextColor(Color.GREEN);
        } else {
            holder.deadline.setTextColor(Color.RED);
        }

        holder.doneBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("CheckValue"+position, b);
                editor.commit();
            }
        });

//        setDoneCheckbox(false, position);

        Glide.with(mContext).load(mContext.getResources().getString(R.string.thumb_link) + quizzes.getThumbnail()).into(holder.imageView);


        return convertView;
    }

    public void setDoneCheckbox(boolean b, int position) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("DoneCheckbox", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("done"+position, b);
        editor.commit();
        editor.apply();
    }

    private boolean getDoneCheckbox(int position) {
        SharedPreferences ss = PreferenceManager.getDefaultSharedPreferences(mContext);
        boolean isCheck = ss.getBoolean("done"+position, true);
        return isCheck;
    }

    public static String extractNumber(final String str) {

        if (str == null || str.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
                found = true;
            } else if (found) {
                // If we already found a digit before and this char is not a digit, stop looping
                break;
            }
        }

        return sb.toString();
    }


    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolder {
        TextView quizNames;
        TextView moderatorName;
        TextView deadline;
        TextView description;
        ImageView imageView;
        CheckBox doneBox;
    }
}
