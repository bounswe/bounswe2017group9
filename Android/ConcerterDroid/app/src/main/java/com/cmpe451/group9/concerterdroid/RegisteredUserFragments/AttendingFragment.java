package com.cmpe451.group9.concerterdroid.RegisteredUserFragments;

import android.content.Context;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cmpe451.group9.concerterdroid.R;


public class AttendingFragment extends Fragment {

    Button btAttend ;
    boolean attended;

    public AttendingFragment() {
        attended = false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

        if(attended){
            btAttend.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.spotifyGreen)));
            btAttend.setText("Attended");
        }else{
            btAttend.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.light_Grey)));
            btAttend.setText("Click to Attend");
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        btAttend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(attended){
                    //If attended, then change it to not attended.
                    //TODO implement attending a concert using API features.
                    attended = false;
                    btAttend.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.light_Grey)));
                    btAttend.setText("Click to Attend");
                }else{
                    //If not attended, then change it to attended.
                    attended = true;
                    btAttend.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.spotifyGreen)));
                    btAttend.setText("Attended");
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attending, container, false);

        btAttend = (Button) view.findViewById(R.id.button_attend);

        return view;
    }


    public void setAttended(boolean attended) {
        this.attended = attended;
    }

}
