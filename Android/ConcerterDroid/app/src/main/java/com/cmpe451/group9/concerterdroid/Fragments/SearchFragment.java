package com.cmpe451.group9.concerterdroid.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cmpe451.group9.concerterdroid.AdvSearchActivity;
import com.cmpe451.group9.concerterdroid.Classes.Concert;
import com.cmpe451.group9.concerterdroid.R;

import java.io.Serializable;
import java.util.ArrayList;


public class SearchFragment extends Fragment {

    EditText name;
    EditText artist;
    EditText location;
    EditText dateTime;
    Button searchButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchButton = (Button) view.findViewById(R.id.button_search);
        name = (EditText) view.findViewById(R.id.s_name);
        artist = (EditText) view.findViewById(R.id.s_artist);
        location = (EditText) view.findViewById(R.id.s_location);
        dateTime = (EditText) view.findViewById(R.id.s_datetime);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

            searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String search[] = new String[4];
                search[0]=name.getText().toString();
                search[1]=artist.getText().toString();
                search[2]=location.getText().toString();
                search[3]=dateTime.getText().toString();

                ArrayList<Concert> concertList = new ArrayList<Concert>();

                //TODO perform search on API and send as extras in the intent.

                Intent searchIntent = new Intent(getActivity(), AdvSearchActivity.class);

                searchIntent.putExtra("listOfConcerts", (Serializable) concertList);

                getActivity().startActivity(searchIntent);
            }
        });


    }

}
