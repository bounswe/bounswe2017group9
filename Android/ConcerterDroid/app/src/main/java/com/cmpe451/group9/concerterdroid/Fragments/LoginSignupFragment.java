package com.cmpe451.group9.concerterdroid.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cmpe451.group9.concerterdroid.MainActivity;
import com.cmpe451.group9.concerterdroid.R;


public class LoginSignupFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_signup, container, false);
        //TODO signup activity and spotify login.

        final EditText email = (EditText) view.findViewById(R.id.edit_email);
        final EditText password = (EditText) view.findViewById(R.id.edit_password);
        final Button btLogin = (Button) view.findViewById(R.id.bt_login);
        final Button btSignup = (Button) view.findViewById(R.id.bt_signup);
        final Button btSpotify = (Button) view.findViewById(R.id.bt_spotify);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences checkReg = getActivity().getSharedPreferences("concertGoer", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editr = checkReg.edit();

                String emailtxt = email.getText().toString();
                String pswrdtxt = password.getText().toString();

                //TODO get info from api change email password option.
                if(emailtxt.equals("admin")  && pswrdtxt.equals("pass")){
                    editr.putBoolean("login_state", true);
                    editr.commit();
                    ((MainActivity) getActivity()).viewPager.getAdapter().notifyDataSetChanged();
                }else{
                    //TODO implement "did not match" warning
                }
            }
        });



        return view;
    }

}
