package com.cmpe451.group9.concerterdroid.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cmpe451.group9.concerterdroid.MainActivity;
import com.cmpe451.group9.concerterdroid.R;


public class UserAccountsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_accounts, container, false);
        //TODO  Shared preferences: register...
        return view;
    }

}
