package com.cmpe451.group9.concerterdroid.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmpe451.group9.concerterdroid.R;
import com.cmpe451.group9.concerterdroid.RegisteredUserFragments.UserProfileFragment;


public class LoginSwitchFragment extends Fragment {

    //TODO Shared Preferences: register
    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    boolean registered = true;

    public LoginSwitchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        //TODO Shared preferences, fix accordingly.
        if(registered){
            UserProfileFragment fragment1 = new UserProfileFragment();
            transaction.replace(R.id.user_frame, fragment1);
        }else{
            UserAccountsFragment fragment2 = new UserAccountsFragment();
            transaction.replace(R.id.user_frame, fragment2);
        }
        transaction.commit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_switch, container, false);
    }

}
