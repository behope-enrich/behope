package com.enrich.behope;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

//import com.enrich.behope.ui.login.model;
//import com.enrich.behope.ui.login.myadapter;
import com.enrich.behope.ui.login.dmodel;
import com.enrich.behope.ui.login.dmyadapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    RecyclerView donorRecyclerView;

    //myadapter adapter2;
    dmyadapter adapter;

    Button btncall;

    //ProgressBar homeProgress;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate( R.layout.fragment_home, container, false );

        donorRecyclerView = v.findViewById( R.id.donorRecyclerView );
        donorRecyclerView.setLayoutManager( new LinearLayoutManager( v.getContext() ) );

        //homeProgress = v.findViewById( R.id.progressHome );
        //homeProgress.setVisibility( View.VISIBLE );

        btncall = v.findViewById( R.id.btncall );

        FirebaseRecyclerOptions<dmodel> options =
                new FirebaseRecyclerOptions.Builder<dmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("blood_donors"), dmodel.class)
                        .build();



        adapter = new dmyadapter( options );
        donorRecyclerView.setAdapter( adapter );

        //homeProgress.setVisibility( View.GONE );

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();

    }
}

