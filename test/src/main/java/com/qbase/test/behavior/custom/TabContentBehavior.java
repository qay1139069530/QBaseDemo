package com.qbase.test.behavior.custom;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.qbase.test.R;
import com.qbase.test.behavior.custom.base.HeaderScrollingViewBehavior;

import java.util.List;

/**
 * tittle behavior
 */
public class TabContentBehavior extends HeaderScrollingViewBehavior {

    private int headerOffset;

    private int titleHeight;
    private int tabHeight;

    public TabContentBehavior() {
    }

    public TabContentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        headerOffset = context.getResources().getDimensionPixelOffset(R.dimen.header_offset);
        titleHeight = context.getResources().getDimensionPixelOffset(R.dimen.title_height);
        tabHeight = context.getResources().getDimensionPixelOffset(R.dimen.tab_height);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float contentScrollY = dependency.getTranslationY() / headerOffset * (dependency.getHeight() - tabHeight - titleHeight);
        float y = dependency.getHeight() - contentScrollY;
        child.setY(y);
        return true;
    }

    @Override
    public View findFirstDependency(List<View> views) {
        int size = views == null ? 0 : views.size();
        for (int i = 0; i < size; i++) {
            View view = views.get(i);
            if (view != null && view instanceof TextView)
                return view;
        }
        return null;
    }

    @Override
    protected int getScrollRange(View v) {
        if (v instanceof TextView) {
            return Math.max(0, v.getMeasuredHeight() - tabHeight - titleHeight);
        }
        return super.getScrollRange(v);
    }


}
