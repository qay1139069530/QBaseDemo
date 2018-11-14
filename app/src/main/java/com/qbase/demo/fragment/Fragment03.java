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

public class Fragment03 extends QBaseFg {


    public static final String INSTACE = Fragment03.class.getName()+"withParams";

    public static Fragment03 newInstance() {
        Bundle args = new Bundle();
        Fragment03 fragment = new Fragment03();
        fragment.setArguments(args);
        return fragment;
    }
    public Fragment03() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment03,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getView()!=null){
            TextView textView = getView().findViewById(R.id.textview);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        mFunctionManager.invokeFunction(INSTACE,"Fragment03 带参数的调用");
                    } catch (FunctionException e) {
                        e.printStackTrace();
                    }
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
}
