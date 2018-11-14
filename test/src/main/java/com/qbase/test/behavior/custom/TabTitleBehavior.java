package com.qbase.test.behavior.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;
import android.widget.TextView;

import com.qbase.test.R;
import com.qbase.test.behavior.custom.base.ViewOffsetBehavior;

/**
 * tittle behavior
 */
public class TabTitleBehavior  extends ViewOffsetBehavior<View> {

    private int headerOffset;

    private int titleHeight;


    public TabTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        headerOffset = context.getResources().getDimensionPixelOffset(R.dimen.header_offset);
        titleHeight = context.getResources().getDimensionPixelOffset(R.dimen.title_height);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float positionY = -(1 - dependency.getTranslationY() / headerOffset) * titleHeight;
        child.setY(positionY);
        return true;
    }

}