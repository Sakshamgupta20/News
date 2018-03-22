package com.example.android.news;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Saksham on 22-01-2018.
 */

public class FragmentPage extends FragmentPagerAdapter {
    private Context mContext;
    public FragmentPage(Context context,FragmentManager fm) {
        super(fm);
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HeadlineFragment();
        }
        else if(position==1) {
            return new SportsFragment();
        }
        else if(position==2) {
            return new TechnologyFragment();
        }
        else if(position==3)
        {
            return new Entertairmentfragment();
        }
        else
        {
            return new SearchFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "HDLINE";
        }
        else if(position==1){
            return "SPORTS";
        }
        else if(position==2) {
            return "TECH";
        }
        else if (position==3)
        {
            return "ENTMT";
        }
        else
        {
            return "Search";
        }
    }
}
