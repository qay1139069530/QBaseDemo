package com.qbase.test.behavior.custom;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * RecyclerView behavior
 */
public class RecyclerBehavior extends CoordinatorLayout.Behavior<RecyclerView> {

    public RecyclerBehavior() {
    }

    public RecyclerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
        //RecyclerView初始化时候，RecyclerView所在位置
        if(dependency instanceof TextView){
            TextView textView = (TextView)dependency;
            Log.e("--dependency--",textView.getText().toString());
        }
        float positionY = dependency.getHeight()+dependency.getTranslationY();
        if(positionY<0){
            positionY = 0;
        }
        child.setY(positionY);
        return true;
    }
}
