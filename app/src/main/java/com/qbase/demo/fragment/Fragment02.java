package com.qbase.demo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qbase.auxilibrary.base.QBaseFg;
import com.qbase.auxilibrary.common.struct.FunctionException;
import com.qbase.demo.R;

public class Fragment02 extends QBaseFg {


    public static final String INSTACE = Fragment02.class.getName()+"withResult";

    public static Fragment02 newInstance() {
        Bundle args = new Bundle();
        Fragment02 fragment = new Fragment02();
        fragment.setArguments(args);
        return fragment;
    }
    public Fragment02() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment02,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getView()!=null){
            TextView textView = getView().findViewById(R.id.textview);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = null;
                    try {
                        text = mFunctionManager.invokeFunction(INSTACE,String.class);
                    } catch (FunctionException e) {
                        e.printStackTrace();
                    }
                    Log.e("---","Fragment02 -- with result only :: "+text);
                }
            });
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initialize() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mFunctionManager!=null){
            mFunctionManager.removeFunction(INSTACE);
        }
    }
}
