package me.hugomedina.themovielister.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import me.hugomedina.themovielister.fragments.MovieListFragment;
import me.hugomedina.themovielister.fragments.StatsFragment;

/**
 * Created by Hugo on 2/28/2016.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private int NUM_PAGES = 2;

    public MainPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new MovieListFragment();
            case 1:
                return new StatsFragment();

            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "My List";
            case 1:
                return "Statistics";
        }
        return "";
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
