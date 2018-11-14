package com.qbase.demo.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.qbase.auxilibrary.base.QBaseAct;
import com.qbase.auxilibrary.base.QBaseFg;
import com.qbase.auxilibrary.common.struct.FunctionCallBack;
import com.qbase.auxilibrary.common.struct.FunctionManager;
import com.qbase.auxilibrary.view.viewpager.CommonPagerAdapter;
import com.qbase.auxilibrary.view.viewpager.CommonPagerChangeListener;
import com.qbase.demo.R;

public class FragmentAct extends QBaseAct {

    private TextView mTvText;
    private ViewPager viewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fg_act);
        mTvText = findViewById(R.id.textview);
        mTabLayout = findViewById(R.id.tablelayout);
        viewPager = findViewById(R.id.viewpager);


        Fragment01 fragment01 = Fragment01.newInstance();
        Fragment02 fragment02 = Fragment02.newInstance();
        Fragment03 fragment03 = Fragment03.newInstance();
        Fragment04 fragment04 = Fragment04.newInstance();

        CommonPagerAdapter myPagerAdapter = new CommonPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.add(fragment01);
        myPagerAdapter.add(fragment02);
        myPagerAdapter.add(fragment03);
        myPagerAdapter.add(fragment04);

        viewPager.setAdapter(myPagerAdapter);
        CommonPagerChangeListener mPageChangeListener = new CommonPagerChangeListener();
        viewPager.addOnPageChangeListener(mPageChangeListener);
        mTabLayout.setupWithViewPager(viewPager);


        String first_name = "第一个";
        if (mTabLayout.getTabAt(0) != null) {
            mTabLayout.getTabAt(0).setText(first_name);
        }

        String second_name = "第二个";
        if (mTabLayout.getTabAt(1) != null) {
            mTabLayout.getTabAt(1).setText(second_name);
        }
        String third_name = "第三个";
        if (mTabLayout.getTabAt(2) != null) {
            mTabLayout.getTabAt(2).setText(third_name);
        }
        String four_name = "第四个";
        if (mTabLayout.getTabAt(3) != null) {
            mTabLayout.getTabAt(3).setText(four_name);
        }


        mTvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionManager.getInstance().removeFunction(Fragment01.INSTACE);
            }
        });
    }


    @Override
    public void setFunctionManager(String tag) {
        super.setFunctionManager(tag);
        FragmentManager fragmentManager = getSupportFragmentManager();
        QBaseFg baseFragment = (QBaseFg) fragmentManager.findFragmentByTag(tag);
        FunctionManager functionManager = FunctionManager.getInstance();
        if(baseFragment!=null){
            baseFragment.setFunctionManager(functionManager.addFunction(new FunctionCallBack<String, String>(Fragment01.INSTACE) {
                @Override
                public String functionCallBack(String o) {
                    Log.e("---", " Fragment01.INSTACE " + o);
                    return "Fragment01.INSTACE";
                }
            }).addFunction(new FunctionCallBack<String, String>(Fragment02.INSTACE) {
                @Override
                public String functionCallBack(String o) {
                    Log.e("---", " Fragment02.INSTACE " + o);
                    return "Fragment02.INSTACE";
                }
            }).addFunction(new FunctionCallBack<String, String>(Fragment03.INSTACE) {
                @Override
                public String functionCallBack(String o) {
                    Log.e("---", " Fragment03.INSTACE " + o);
                    return "Fragment03.INSTACE";
                }
            }).addFunction(new FunctionCallBack<String, String>(Fragment04.INSTACE) {
                @Override
                public String functionCallBack(String o) {
                    Log.e("---", " Fragment04.INSTACE " + o);
                    return "Fragment04.INSTACE";
                }
            }));
        }
    }



}
