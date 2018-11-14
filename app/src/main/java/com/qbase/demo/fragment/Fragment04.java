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

public class Fragment04 extends QBaseFg {


    public static final String INSTACE = Fragment04.class.getName()+"Result and Params";

    public static Fragment04 newInstance() {
        Bundle args = new Bundle();
        Fragment04 fragment = new Fragment04();
        fragment.setArguments(args);
        return fragment;
    }
    public Fragment04() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment04,container,false);
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
                        String text = mFunctionManager.invokeFunction(INSTACE,"Fragment04 带参数的有结果 调用",String.class);
                        Log.e("---","Fragment04 -- with params and result :: "+text);
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
