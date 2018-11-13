package com.qbase.demo.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.qbase.demo.R;

public class FragmentAct extends AppCompatActivity {

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

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.add(fragment01);

        viewPager.setAdapter(myPagerAdapter);
        MyPageChangeListener mPageChangeListener = new MyPageChangeListener();
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
    }





    public void setManager(String tag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        BaseFragment baseFragment = (BaseFragment)fragmentManager.findFragmentByTag(tag);
        FunctionManager functionManager = FunctionManager.getInstance();
        baseFragment.setFunctionManager(functionManager.addFunction(new FunctionNoParamsNoResult(Fragment01.INSTACE) {
            @Override
            public void function() {
                Toast.makeText(FragmentAct.this,"成功调用无参数！！", Toast.LENGTH_SHORT).show();
            }
        }));

        baseFragment.setFunctionManager(functionManager.addFunction(new FunctionWithParamsAndResult<String,String>(Fragment01.INSTACE) {
            @Override
            public String function(String o) {
                return null;
            }
        }));
    }
}
