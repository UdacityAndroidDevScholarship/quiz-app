package com.developervishalsehgal.udacityscholarsapp.ui.notification;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developervishalsehgal.udacityscholarsapp.R;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private Context mContext;
    private String mTabName;
    private NotificationItemClickListener mListener;
    private String mName;
    private String mDate;
    private String mDescription;

    //Interface for itemClickListener
    public interface NotificationItemClickListener {
        public void onItemClick(View v, int position);
    }

    //Passing Context, Active Tab, onClickListener to the Adapter
    NotificationAdapter(Context mContext, String mTabName, NotificationItemClickListener mListener){
        this.mContext = mContext;
        this.mTabName = mTabName;
        this.mListener = mListener;
    }

    @Override
    public NotificationAdapter.NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView;
        final NotificationViewHolder mViewHolder;

        switch (mTabName){
            // Inflating Layout and Setting onClickListener on items in TAB1 and sending item details to Notification Detail Page
            case "TAB1" :
                mView = LayoutInflater.from(mContext).inflate(R.layout.list_item_new_quiz,parent,false);
                mViewHolder = new NotificationViewHolder(mView);
                mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(parent.getContext(),NotificationDetail.class);
                        i.putExtra("name",mName);
                        i.putExtra("date",mDate);
                        i.putExtra("description",mDescription);
                        i.putExtra("title",v.getResources().getString(R.string.notification_detail_title_new_quiz));
                        mContext.startActivity(i);
                    }
                });
                return mViewHolder;

            // Inflating Layout and Setting onClickListener on items in TAB2 and sending item details to Notification Detail Page
            case "TAB2" :
                mView = LayoutInflater.from(mContext).inflate(R.layout.list_item_deadlines,parent,false);
                mViewHolder = new NotificationViewHolder(mView);
                mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(parent.getContext(),NotificationDetail.class);
                        i.putExtra("name",mName);
                        i.putExtra("date",mDate);
                        i.putExtra("description",mDescription);
                        i.putExtra("title",v.getResources().getString(R.string.notification_detail_title_deadline));
                        mContext.startActivity(i);
                    }
                });
                return mViewHolder;

            // Inflating Layout and Setting onClickListener on items in TAB3 and sending item details to Notification Detail Page
            case "TAB3" :
                mView = LayoutInflater.from(mContext).inflate(R.layout.list_item_resources,parent,false);
                mViewHolder = new NotificationViewHolder(mView);
                mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(parent.getContext(),NotificationDetail.class);
                        i.putExtra("name",mName);
                        i.putExtra("date",mDate);
                        i.putExtra("description",mDescription);
                        i.putExtra("title",v.getResources().getString(R.string.notification_detail_title_resource));
                        mContext.startActivity(i);
                    }
                });
                return mViewHolder;
        }
        return null;
    }

    //Binding Data for Notification Detail Page
    @Override
    public void onBindViewHolder(NotificationAdapter.NotificationViewHolder holder, int position) {
        mName = holder.notificationName.getText().toString();
        mDate = holder.notificationDate.getText().toString();
        mDescription = holder.notificationDescription.getText().toString();
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder{
        TextView notificationName;
        TextView notificationDate;
        TextView notificationDescription;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            //Selecting appropriate viewHolder based on focussed TAB
            switch (mTabName){
                case "TAB1" :
                    newQuizViewHolder(itemView);
                    break;
                case "TAB2" :
                    deadlinesViewHolder(itemView);
                    break;
                case "TAB3" :
                    resourcesViewHolder(itemView);
                    break;
            }
        }
        //Getting list item Views from TAB1(New Quiz)
        private void newQuizViewHolder(View itemView){
            notificationName = itemView.findViewById(R.id.tv_quiz_name);
            notificationDate = itemView.findViewById(R.id.tv_quiz_date);
            notificationDescription = itemView.findViewById(R.id.tv_quiz_description);
        }

        //Getting list item Views from TAB2(Deadline)
        private void deadlinesViewHolder(View itemView){
            notificationName = itemView.findViewById(R.id.tv_deadline_name);
            notificationDate = itemView.findViewById(R.id.tv_deadline_date);
            notificationDescription = itemView.findViewById(R.id.tv_deadline_description);
        }

        //Getting list item Views from TAB3(Resources)
        private void resourcesViewHolder(View itemView){
            notificationName = itemView.findViewById(R.id.tv_resource_name);
            notificationDate = itemView.findViewById(R.id.tv_resource_date);
            notificationDescription = itemView.findViewById(R.id.tv_resource_description);
        }
    }
}
