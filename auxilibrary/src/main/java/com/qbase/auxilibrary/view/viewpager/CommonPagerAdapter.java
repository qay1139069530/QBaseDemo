package com.qbase.auxilibrary.view.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CommonPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {

    private List<T> mFragments = new ArrayList<>();

    public CommonPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void add(T fm) {
        mFragments.add(fm);
    }

    @Override
    public T getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}