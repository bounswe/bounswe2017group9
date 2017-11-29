package com.cmpe451.group9.concerterdroid.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmpe451.group9.concerterdroid.R;
import com.cmpe451.group9.concerterdroid.RegisteredUserFragments.UserProfileFragment;


public class RegisterSwitchFragment extends Fragment {


    //get Login state from Shared Preferences.
    boolean registered;

    public RegisterSwitchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    public void onResume() {
        super.onResume();

        manageState();
    }

    public void manageState() {
        SharedPreferences checkReg = getActivity().getSharedPreferences("concertGoer", 0);
        if(checkReg == null)
            registered = false;
        else
            registered = checkReg.getBoolean("login_state", false);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        if(registered){
            UserProfileFragment fragment1 = new UserProfileFragment();
            transaction.replace(R.id.user_frame, fragment1);
        }else{
            LoginSignupFragment fragment2 = new LoginSignupFragment();
            transaction.replace(R.id.user_frame, fragment2);
        }
        transaction.commit();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_switch, container, false);
    }

}
