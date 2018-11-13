package com.qbase.demo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment01 extends BaseFragment{


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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getView()!=null){
            TextView textView = getView().findViewById(R.id.textview);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFunctionManager.invokeFunction(INSTACE);
                }
            });
        }
    }
}
