package com.developervishalsehgal.udacityscholarsapp.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.developervishalsehgal.udacityscholarsapp.Adapters.QuizListAdapter;
import com.developervishalsehgal.udacityscholarsapp.Adapters.QuizRecyclerViewAdapter;
import com.developervishalsehgal.udacityscholarsapp.Models.Quizzes;
import com.developervishalsehgal.udacityscholarsapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizzesFragment extends Fragment {

    RecyclerView recyclerView;
    View view;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;

    QuizRecyclerViewAdapter adapter;
    Quizzes quizzes;

    ArrayList<String> serialNumbers = new ArrayList<>();

    List<Quizzes> quizzeslist = new ArrayList<>();

    QuizRecyclerViewAdapter.ShowDeleted showDeleted = new QuizRecyclerViewAdapter.ShowDeleted() {
        @Override
        public void showDeleted(int size) {
            QuizzesFragment.this.onCompleteLeftQuizzes.onCompletes(Integer.toString(size));

        }
    };



    public static interface OnCompleteListener {
        public abstract void onComplete(String totalQuizzes);
    }

    private OnCompleteListener mListener;


    public static interface OnCompleteLeftQuizzes {
        public abstract void onCompletes(String totalQuizzes);
    }

    private OnCompleteLeftQuizzes onCompleteLeftQuizzes;



    public QuizzesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnCompleteListener)activity;
            this.onCompleteLeftQuizzes = (OnCompleteLeftQuizzes)activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_quizzes, container, false);

        quizzes = new Quizzes();
        recyclerView = view.findViewById(R.id.my_home_recycler_view);
        quizzeslist = new ArrayList<Quizzes>();


        adapter = new QuizRecyclerViewAdapter(getContext(),serialNumbers,quizzeslist,showDeleted);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Quizzes");
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                quizzeslist.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    quizzes = dataSnapshot1.getValue(Quizzes.class);
                    String serialNumber = dataSnapshot1.getKey();
                    quizzeslist.add(quizzes);
                    serialNumbers.add(serialNumber);
                    adapter.notifyDataSetChanged();

//                    Toast.makeText(getContext(),quizzes.toString(),Toast.LENGTH_SHORT).show();
                }

                QuizzesFragment.this.mListener.onComplete(Long.toString(quizzeslist.size()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);


        databaseReference.keepSynced(true);
        setUpSwipeRefresh();


        return view;
    }

    SwipeRefreshLayout swipeRefreshLayout;

    private void setUpSwipeRefresh() {
        swipeRefreshLayout = view.findViewById(R.id.refresh_homescreen);
        swipeRefreshLayout.setColorSchemeResources(R.color.bnv_color, R.color.blue_jeans,
                R.color.ufo_green, R.color.vivid_tangelo);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        HomeFragment homeFragment = new HomeFragment();
//                        FragmentManager manager = getSupportFragmentManager();
//                        manager.beginTransaction().replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();

                        if(adapter != null){
                            adapter.notifyDataSetChanged();
                            databaseReference.addValueEventListener(valueEventListener);
                            Snackbar.make(view,"Refreshed.",Snackbar.LENGTH_SHORT).show();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);


            }
        });
    }

}
