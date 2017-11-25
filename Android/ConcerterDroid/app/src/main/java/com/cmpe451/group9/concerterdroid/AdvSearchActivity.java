package com.cmpe451.group9.concerterdroid;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cmpe451.group9.concerterdroid.Classes.Concert;
import com.cmpe451.group9.concerterdroid.Fragments.ListConcertsFragment;

import java.io.Serializable;

public class AdvSearchActivity extends AppCompatActivity implements ListConcertsFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv_search);

        //Concerter logo on ActionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.concerter_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        Intent i = getIntent();
//        Concert concert = (Concert) i.getSerializableExtra("concertInfo");


        //Place comments
        ListConcertsFragment fragment = new ListConcertsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.search_result, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

    public void onListFragmentInteraction(Concert item){

        //Yeni activity aç consert page koy

        Intent interno = new Intent(this, ConcertPageActivity.class);

        interno.putExtra("concertInfo", (Serializable) item);

        startActivity(interno);

//         ListCommentFragment
//        a2Fragment.setConcert(item);
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//

//
//        // Store the Fragment in stack
//        transaction.addToBackStack(null);
//        transaction.replace(R.id.home_frame, a2Fragment);
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        transaction.commit();
    }
}
