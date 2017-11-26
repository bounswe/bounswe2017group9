package com.cmpe451.group9.concerterdroid.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cmpe451.group9.concerterdroid.Fragments.ListConcertsFragment;
import com.cmpe451.group9.concerterdroid.Fragments.LoginSwitchFragment;
import com.cmpe451.group9.concerterdroid.Fragments.SearchFragment;
import com.cmpe451.group9.concerterdroid.Fragments.UserAccountsFragment;

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
            return new ListConcertsFragment();//The list of concerts

        }else {
            return new LoginSwitchFragment();//Login/Sign-up
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
