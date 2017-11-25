package com.cmpe451.group9.concerterdroid;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmpe451.group9.concerterdroid.Classes.Concert;
import com.cmpe451.group9.concerterdroid.Fragments.ListCommentFragment;

public class ConcertPageActivity extends AppCompatActivity {

    //TODO implement attended / will attend (also in layout too)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_page);

        //Concerter logo on ActionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.concerter_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Gets realted concert object to display.
        Intent i = getIntent();
        Concert concert = (Concert) i.getSerializableExtra("concertInfo");

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

        //Place comments
        ListCommentFragment fragment = new ListCommentFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.comment_frame, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();


    }
}
