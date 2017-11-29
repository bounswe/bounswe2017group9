package com.cmpe451.group9.concerterdroid.RegisteredUserFragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cmpe451.group9.concerterdroid.Fragments.RegisterSwitchFragment;
import com.cmpe451.group9.concerterdroid.MainActivity;
import com.cmpe451.group9.concerterdroid.R;


public class UserProfileFragment extends Fragment {

    public UserProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user_profile, container, false);

        Button btLogout = (Button) view.findViewById(R.id.logout);

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences checkReg = getActivity().getSharedPreferences("concertGoer", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editr = checkReg.edit();
                editr.putBoolean("login_state", false);
                editr.commit();
                ((MainActivity) getActivity()).viewPager.getAdapter().notifyDataSetChanged();

                (  (RegisterSwitchFragment)  getParentFragment()).manageState();
            }
        });

        return view;
    }

}
