package com.cmpe451.group9.concerterdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.cmpe451.group9.concerterdroid.Adapters.theFragmentPagerAdapter;
import com.cmpe451.group9.concerterdroid.Classes.Concert;
import com.cmpe451.group9.concerterdroid.Fragments.ListConcertsFragment;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements ListConcertsFragment.OnListFragmentInteractionListener  {

    MenuItem prevMenuItem;
    BottomNavigationView navigation;
    ViewPager viewPager;

    public boolean isRegist() {
        return regist;
    }

    //TODO after shared preferences:
    boolean regist = true;

    public void setRegist(boolean regist) {
        this.regist = regist;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Concerter logo on ActionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.concerter_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        //ViewPager
        viewPager = (ViewPager) findViewById(R.id.view_pager_home);                                 // Find the view pager that will allow the user to swipe between fragments
        theFragmentPagerAdapter adapter = new theFragmentPagerAdapter(getSupportFragmentManager()); // Create an adapter that knows which fragment should be shown on each page
        viewPager.setAdapter(adapter);                                                              // Set the adapter onto the view pager
        viewPager.setCurrentItem(1);                                                                //Open home(concertlist) fragment


        //Bottom Navigation View
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(1);//The list of all concerts
                        return true;
                    case R.id.navigation_search:
                        viewPager.setCurrentItem(0);//Advanced search
                        return true;
                    case R.id.navigation_login:
                        viewPager.setCurrentItem(2);//Login/Sign-up
                        return true;
                }
                return false;
            }
        };

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().getItem(0).setChecked(false);                                     //Sync navigationview as home checked at the beginnig
        navigation.getMenu().getItem(1).setChecked(true);                                      //Sync navigationview as others unchecked.
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //Coordination between ViewPager and BottomNavigationView
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else{
                    navigation.getMenu().getItem(1).setChecked(false);
                }
                navigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navigation.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

    }

    public void onListFragmentInteraction(Concert item){

        //When a concert is clicked from the list, open concert page in a new activity called "ConcertPageActivity".
        Intent interno = new Intent(this, ConcertPageActivity.class);
        interno.putExtra("concertInfo", (Serializable) item);
        //TODO delete this extra boolean "registered" after applying Shared Preferences.
        interno.putExtra("registered",  regist);
        startActivity(interno);

    }

}
