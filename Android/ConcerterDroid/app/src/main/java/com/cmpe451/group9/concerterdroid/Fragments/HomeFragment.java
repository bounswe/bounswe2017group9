package com.cmpe451.group9.concerterdroid.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cmpe451.group9.concerterdroid.R;


public class HomeFragment extends Fragment {

    FloatingActionButton btAddConcert;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onResume() {
        super.onResume();

        SharedPreferences checkReg = getActivity().getSharedPreferences("concertGoer", 0);
        boolean registered;
        if(checkReg == null)
            registered = false;
        else
            registered = checkReg.getBoolean("login_state", false);

        if(registered){
            btAddConcert.setVisibility(View.VISIBLE);
            btAddConcert.setClickable(true);
            btAddConcert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }else{
            btAddConcert.setVisibility(View.GONE);
        }

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ListConcertsFragment fragment = new ListConcertsFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.list_concerts_frame, fragment);
        transaction.commit();

        btAddConcert =(FloatingActionButton) view.findViewById(R.id.bt_addconcert);

        return view;
    }

}
