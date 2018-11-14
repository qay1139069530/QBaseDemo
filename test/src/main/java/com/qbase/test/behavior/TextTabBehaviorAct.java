package com.qbase.test.behavior;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.qbase.auxilibrary.base.QBaseBarAct;
import com.qbase.auxilibrary.base.QBaseFg;
import com.qbase.auxilibrary.view.viewpager.CommonPagerAdapter;
import com.qbase.auxilibrary.view.viewpager.CommonPagerChangeListener;
import com.qbase.test.R;
import com.qbase.test.fragment.Test01Fragmnet;
import com.qbase.test.fragment.Test02Fragmnet;
import com.qbase.test.fragment.Test03Fragmnet;

public class TextTabBehaviorAct extends QBaseBarAct {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private CommonPagerAdapter<QBaseFg> mPagerAdapter;
    private CommonPagerChangeListener mPageChangeListener;

    @Override
    protected void initialize() {
        setContentView(R.layout.texttab_behavior_act);
        initToolbar("Text Tab Behavior");
        initView();
        initFragment();
    }

    private void initView() {
        mTabLayout= findViewById(R.id.texttab_behavior_act_tablayout);
        mViewPager = findViewById(R.id.texttab_behavior_act_viewpager);
    }

    private void initFragment(){
        mPagerAdapter = new CommonPagerAdapter<>(getSupportFragmentManager());
        mPagerAdapter.add(Test01Fragmnet.newInstance());
        mPagerAdapter.add(Test02Fragmnet.newInstance());
        mPagerAdapter.add(Test03Fragmnet.newInstance());
        mViewPager.setAdapter(mPagerAdapter);
        mPageChangeListener = new CommonPagerChangeListener();
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText("Tab-1");
        mTabLayout.getTabAt(1).setText("Tab-2");
        mTabLayout.getTabAt(2).setText("Tab-3");
    }
}
