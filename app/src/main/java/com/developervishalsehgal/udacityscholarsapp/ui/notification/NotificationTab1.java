package com.developervishalsehgal.udacityscholarsapp.ui.notification;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.developervishalsehgal.udacityscholarsapp.R;
import com.developervishalsehgal.udacityscholarsapp.data.models.Notification;
import com.developervishalsehgal.udacityscholarsapp.ui.home.QuizAdapter;


public class NotificationTab1 extends Fragment {
    //Layout code for TAB1 content
    private RecyclerView mRecyclerView;
    private NotificationAdapter mNotificationAdapter;
    private final String TAB_NAME = "TAB1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Recycler View section for TAB1(New Quiz)
        View rootView = inflater.inflate(R.layout.tab1_new_quiz, container, false);
        mRecyclerView = rootView.findViewById(R.id.rv_new_quiz);
        Context context=rootView.getContext();
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //Initialising Notification Adapter to pass context, active tab , itemClickListener
        mNotificationAdapter = new NotificationAdapter(context,TAB_NAME,new NotificationAdapter.NotificationItemClickListener(){
            @Override
            public void onItemClick(View v, int position) {}
        });
        mRecyclerView.setAdapter(mNotificationAdapter);

        //Code to Delete an item when swiped Right/Left
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //implements swipe functionality
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //Can implement remove item function on database here
                mNotificationAdapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(mRecyclerView);

        return rootView;
    }
}
