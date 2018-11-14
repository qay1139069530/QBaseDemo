package com.qbase.test.behavior.custom;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.qbase.test.R;

/**
 * tittle behavior
 */
public class TabBehavior extends CoordinatorLayout.Behavior<View> {


    private int headerOffset;

    private int titleHeight;


    public TabBehavior(Context context, AttributeSet attrs) {
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
        float tabScrollY =dependency.getTranslationY() / headerOffset * (dependency.getHeight()-titleHeight);
        float positionY = dependency.getHeight() - tabScrollY;
        child.setY(positionY);
        return true;
    }

}
