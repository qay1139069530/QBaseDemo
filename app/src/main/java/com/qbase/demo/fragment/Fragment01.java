package com.qbase.demo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qbase.auxilibrary.base.QBaseFg;
import com.qbase.auxilibrary.common.struct.FunctionException;
import com.qbase.demo.R;

public class Fragment01 extends QBaseFg{


    public static final String INSTACE = Fragment01.class.getName()+"NPNR";

    public static Fragment01 newInstance() {
        Bundle args = new Bundle();
        Fragment01 fragment = new Fragment01();
        fragment.setArguments(args);
        return fragment;
    }
    public Fragment01() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment01,container,false);
    }


    @Override
    protected void initView() {
        if(getView()!=null){
            TextView textView = getView().findViewById(R.id.textview);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        mFunctionManager.invokeFunction(INSTACE);
                    } catch (FunctionException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    protected void initialize() {

    }
}
