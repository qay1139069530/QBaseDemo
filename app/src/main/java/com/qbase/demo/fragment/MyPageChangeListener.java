package com.qbase.demo.fragment;

import android.support.v4.view.ViewPager;

public class MyPageChangeListener implements ViewPager.OnPageChangeListener {

    private int position;
    private IPageChangeListener listener;

    public MyPageChangeListener() {
    }

    public MyPageChangeListener(IPageChangeListener listener) {
        this.listener = listener;
    }

    public int getPosition() {
        return this.position;
    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;
        if (listener != null) {
            listener.onPageChangeListener(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }
}
