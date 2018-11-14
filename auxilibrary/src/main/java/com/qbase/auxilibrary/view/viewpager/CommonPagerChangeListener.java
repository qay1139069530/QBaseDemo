package com.qbase.auxilibrary.view.viewpager;

import android.support.v4.view.ViewPager;

public class CommonPagerChangeListener implements ViewPager.OnPageChangeListener {
    private int position = 0;

    public int getPosition() {
        return position;
    }

    public void onPageSelected(int position) {
        this.position = position;
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }
}