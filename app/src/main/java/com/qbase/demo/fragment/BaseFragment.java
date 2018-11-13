package com.qbase.demo.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    protected FunctionManager mFunctionManager;


    public void setFunctionManager(FunctionManager mFunctionManager) {
        this.mFunctionManager = mFunctionManager;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentAct){
            FragmentAct baseActivity = (FragmentAct)context;
            baseActivity.setManager(getTag());
        }
    }
}
