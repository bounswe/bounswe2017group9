package com.cmpe451.group9.concerterdroid.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cmpe451.group9.concerterdroid.Fragments.HomeFragment;
import com.cmpe451.group9.concerterdroid.Fragments.ListConcertsFragment;
import com.cmpe451.group9.concerterdroid.Fragments.RegisterSwitchFragment;
import com.cmpe451.group9.concerterdroid.Fragments.SearchFragment;

/**
 * Created by Necip on 22.11.2017.
 */

public class theFragmentPagerAdapter extends FragmentPagerAdapter {

    public theFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new SearchFragment();//Advanced search

        }else if (position == 1) {
            return new HomeFragment();//The list of concerts

        }else {
            return new RegisterSwitchFragment();//Login/Sign-up
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public int getItemPosition(Object object) {
        // POSITION_NONE makes it possible to reload the PagerAdapter
        return POSITION_NONE;
    }
}

