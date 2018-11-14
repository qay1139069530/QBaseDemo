package com.qbase.rxjava;

import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qbase.auxilibrary.base.QBaseBarAct;
import com.qbase.rxjava.fragment.ContactFragment;
import com.qbase.rxjava.fragment.CreateFragment;
import com.qbase.rxjava.fragment.FlatMapFragment;
import com.qbase.rxjava.fragment.IntervalFragment;
import com.qbase.rxjava.fragment.MapFragment;
import com.qbase.rxjava.fragment.OtherFragment;
import com.qbase.rxjava.fragment.ThreadFragment;
import com.qbase.rxjava.fragment.ZipFragment;

public class RxJavaAct extends QBaseBarAct {

    private FrameLayout mContentView;

    private TextView mTvTitle;

    @Override
    protected void initialize() {
        setContentView(R.layout.rxjava_act);
        mContentView = findViewById(R.id.rxjava_act_content);
        mTvTitle = findViewById(R.id.rxjava_act_title);
        initToolbar("RxJava");
    }


    /**
     * 设置fragment
     */
    private void initFragment(MenuItem item) {
        mTvTitle.setText(item.getTitle());
        mContentView.removeAllViews();
        int indexId = item.getItemId();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (indexId == R.id.rxj_add_create) {
            CreateFragment createFragment = CreateFragment.newInstance();
            fragmentTransaction.replace(R.id.rxjava_act_content, createFragment);
        } else if (indexId == R.id.rxj_add_thread) {
            ThreadFragment threadFragment = ThreadFragment.newInstance();
            fragmentTransaction.replace(R.id.rxjava_act_content, threadFragment);
        } else if (indexId == R.id.rxj_add_map) {
            MapFragment mapFragment = MapFragment.newInstance();
            fragmentTransaction.replace(R.id.rxjava_act_content, mapFragment);
        } else if (indexId == R.id.rxj_add_contact) {
            ContactFragment contactFragment = ContactFragment.newInstance();
            fragmentTransaction.replace(R.id.rxjava_act_content, contactFragment);
        } else if (indexId == R.id.rxj_add_flatmap) {
            FlatMapFragment flatMapFragment = FlatMapFragment.newInstance();
            fragmentTransaction.replace(R.id.rxjava_act_content, flatMapFragment);
        }else if (indexId == R.id.rxj_add_zip) {
            ZipFragment zipFragment = ZipFragment.newInstance();
            fragmentTransaction.replace(R.id.rxjava_act_content, zipFragment);
        }else if (indexId == R.id.rxj_add_interval) {
            IntervalFragment intervalFragment = IntervalFragment.newInstance();
            fragmentTransaction.replace(R.id.rxjava_act_content, intervalFragment);
        }else if (indexId == R.id.rxj_add_other) {
            OtherFragment otherFragment = OtherFragment.newInstance();
            fragmentTransaction.replace(R.id.rxjava_act_content, otherFragment);
        }
        if (!getSupportFragmentManager().isDestroyed()) {
            fragmentTransaction.commitAllowingStateLoss();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rxj_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getGroupId() == R.id.rxj_add_group) {
            initFragment(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
