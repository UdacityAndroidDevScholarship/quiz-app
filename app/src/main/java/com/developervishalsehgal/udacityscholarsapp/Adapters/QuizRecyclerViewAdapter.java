package com.developervishalsehgal.udacityscholarsapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.developervishalsehgal.udacityscholarsapp.Fragments.QuizzesFragment;
import com.developervishalsehgal.udacityscholarsapp.Models.Quizzes;
import com.developervishalsehgal.udacityscholarsapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by developervishal on 04/04/18.
 */

public class QuizRecyclerViewAdapter extends RecyclerView.Adapter<QuizRecyclerViewAdapter.SingleItemRowHolder>

{
    boolean once;
    private List<Quizzes> itemsList;
    private Context mContext;
    int boxCount = 0;

    List<CheckBox> items;
    ArrayList<String> serialNumbers;

    SharedPreferences.Editor editor;

    public interface ShowDeleted {

        void showDeleted(int size);

    }

    ShowDeleted showDeleted;

    public QuizRecyclerViewAdapter(Context context,ArrayList<String> serialNumbers, List<Quizzes> itemsList, ShowDeleted showDeleted) {
        this.serialNumbers = serialNumbers;
        this.itemsList = itemsList;
        this.mContext = context;
        this.showDeleted = showDeleted;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.quizzes_list_item, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final SingleItemRowHolder holder, int i) {
        holder.setIsRecyclable(false);
        SharedPreferences sharedPrefs = mContext.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        final Quizzes singleItem = itemsList.get(i);
        holder.quizNames.setText(singleItem.getQuizname());

        holder.moderatorName.setText(singleItem.getModerator());
        holder.expired.setText(singleItem.getExpired());
        holder.serialNumbers.setText(serialNumbers.get(i));

        String deadline = "Deadline: ";
        holder.deadline.setText(mContext.getString(R.string.deadline)+" " + singleItem.getDeadline());
        holder.description.setText(singleItem.getDescription());
        String regexStr = "^[0-9]\\d*(\\.\\d+)?$";

        items = new ArrayList<CheckBox>();
//        if (extractNumber(holder.deadline.getText().toString()).trim().matches(regexStr)) {
//            holder.deadline.setTextColor(mContext.getResources().getColor(R.color.color_green_deadline));
//        } else {
//            holder.deadline.setTextColor(mContext.getResources().getColor(R.color.color_red_deadline));
//
        if(holder.expired.getText().toString().trim().contains("yes")||holder.expired.getText().toString().trim().contains("Yes")){
            holder.deadline.setTextColor(mContext.getResources().getColor(R.color.color_red_deadline));
        }else {
            holder.deadline.setTextColor(mContext.getResources().getColor(R.color.color_green_deadline));
        }

//        Glide.with(mContext).load(mContext.getResources().getString(R.string.thumb_link) + singleItem.getThumbnail()).into(holder.imageView);
        Glide.with(mContext).load(mContext.getResources().getString(R.string.thumb_link) + singleItem.getThumbnail()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                holder.progressBar.setVisibility(View.GONE);
                return false;
            }
        }).apply(new RequestOptions().placeholder(R.drawable.nav_header)).transition(DrawableTransitionOptions.withCrossFade()).into(holder.imageView);


        editor = sharedPrefs.edit();
        holder.checkBox.setChecked(sharedPrefs.getBoolean("CheckValue"+i, false));
        boolean isChecked = ((CheckBox)holder.checkBox).isChecked();
        if(isChecked){
            holder.checkBox.setTypeface(null, Typeface.BOLD);
            holder.checkBox.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        }else {
            holder.checkBox.setTypeface(null, Typeface.NORMAL);
            holder.checkBox.setTextColor(Color.WHITE);
        }



        final int k =i;
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean isChecked = ((CheckBox)compoundButton).isChecked();
                editor.putBoolean("CheckValue"+k , b);
                editor.commit();
                if(isChecked){

//                    showDeleted.showDeleted(items.size());
                    holder.checkBox.setTypeface(null, Typeface.BOLD);
                    holder.checkBox.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                    Snackbar.make(compoundButton,"Completed "+singleItem.getQuizname(),Snackbar.LENGTH_SHORT).show();
                }else {
                    holder.checkBox.setTypeface(null, Typeface.NORMAL);
                    holder.checkBox.setTextColor(Color.WHITE);
                }


            }
        });


//        int countt =0;
//        items.add(holder.checkBox);
//        for (CheckBox item : items){
//            if(item.isChecked()){
//
//                String text= item.getText().toString();
//
//                Toast.makeText(mContext,text,Toast.LENGTH_SHORT).show();
//
////                items.add(holder.checkBox);
//
//            }
//
//        }




//        try {

//        }
//        catch (final ClassCastException e) {
////            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
////        }
//        QuizRecyclerViewAdapter.this.quizListSize = (ShowQuizListSize) mContext;


    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
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


    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView quizNames, moderatorName, deadline, description, expired,serialNumbers;
        protected ImageView imageView;
        protected CheckBox checkBox;
        private final Context context;
        protected Vibrator v;

        public SingleItemRowHolder(View view) {
            super(view);

            context = view.getContext();
            this.quizNames = (TextView) view.findViewById(R.id.quiz_name);
            this.moderatorName = (TextView) view.findViewById(R.id.moderator_name);
            this.deadline = (TextView) view.findViewById(R.id.deadline);
            this.expired = (TextView) view.findViewById(R.id.expired);
            this.serialNumbers = (TextView) view.findViewById(R.id.serial_number);
            this.description = (TextView) view.findViewById(R.id.description);
            this.imageView = view.findViewById(R.id.quiz_thumb);
            this.checkBox = view.findViewById(R.id.done_checkbox);

        }
    }
}
