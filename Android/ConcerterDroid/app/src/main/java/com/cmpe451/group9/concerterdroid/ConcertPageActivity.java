package com.cmpe451.group9.concerterdroid;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmpe451.group9.concerterdroid.Classes.Concert;
import com.cmpe451.group9.concerterdroid.Fragments.ListCommentFragment;
import com.cmpe451.group9.concerterdroid.RegisteredUserFragments.AddCommentFragment;
import com.cmpe451.group9.concerterdroid.RegisteredUserFragments.AttendingFragment;

public class ConcertPageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_page);

        //Concerter logo on ActionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.concerter_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Gets related concert object to display.
        Intent i = getIntent();
        Concert concert = (Concert) i.getSerializableExtra("concertInfo");

        boolean registered;
        //TODO get the value of registered (boolean) from Shared Preferences
        registered= (i.getBooleanExtra("registered", false) );

        //Layout objects:
        TextView tvName;
        //ImageView ivImage;
        TextView tvArtist;
        TextView tvLocation;
        TextView tvPrice;
        TextView tvDate;
        TextView tvTime;
        TextView tvAttending;
        TextView tvRate;


        tvName = (TextView) findViewById(R.id.ctv_name);
        tvArtist = (TextView) findViewById(R.id.ctv_artist);
        tvLocation = (TextView) findViewById(R.id.ctv_location);
        tvPrice = (TextView) findViewById(R.id.ctv_price);
        tvDate = (TextView) findViewById(R.id.ctv_date);
        tvTime = (TextView) findViewById(R.id.ctv_time);
        tvRate = (TextView) findViewById(R.id.ctv_rate);
        tvAttending = (TextView) findViewById(R.id.ctv_attending);

        //TODO rate and attendee number is not implemented. After implementing them fix this part.
        tvName.setText(concert.getName());
        tvArtist.setText(concert.getArtist().getName());
        tvLocation.setText(concert.getLocation().getAddress());
        tvPrice.setText("" + concert.getPriceRange());
        String[] words = concert.getDateTime().split(" ");
        tvDate.setText(words[0] + " " + words[1] + " " + words[2]);
        tvTime.setText(words[3].substring(0,words[3].length()-3) + " " + words[4]);//holder.concert.getDateTime());// Temporary
        tvRate.setText("4.5 / 5");// Temporary
        tvAttending.setText("35");// Temporary

        if(registered){

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            AttendingFragment attend_fragment = new AttendingFragment();
            //TODO get the value of attended (boolean) from API data.
            attend_fragment.setAttended(  i.getBooleanExtra("attended", false)  );
            transaction.replace(R.id.attend_bt_frame, attend_fragment);


            AddCommentFragment addCommentFragment = new AddCommentFragment();
            transaction.replace(R.id.addcomment_frame, addCommentFragment);
            transaction.commit();

        }else{
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.remove(attend_fragment);
//            transaction.commit();
//
//            transaction.remove(addCommentFragment);
//            transaction.commit();
        }


        //Place comments
        ListCommentFragment fragment = new ListCommentFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.comment_frame, fragment);
        transaction.commit();


    }
}
