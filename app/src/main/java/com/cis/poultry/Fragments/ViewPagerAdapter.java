package com.cis.poultry.Fragments;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String[] title;

    public ViewPagerAdapter(FragmentManager manager, List<String> title) {
        super(manager);
        this.title = title.toArray(new String[0]);
    }

    @Override
    public Fragment getItem(int position) {
        Log.e("==========>3",title.length+"");
        return TabFragment.getInstance(position, this.title.length);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    public int gettabssize() {
        return this.title.length;
    }
}