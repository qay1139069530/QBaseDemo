package com.qbase.test.behavior.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * tittle behavior
 */
public class TitleTextBehavior extends CoordinatorLayout.Behavior<TextView> {

    // 界面整体向上滑动，达到列表可滑动的临界点
    private boolean upReach = false;
    // 列表向上滑动后，再向下滑动，达到界面整体可滑动的临界点
    private boolean downReach = false;
    // 列表上一个全部可见的item位置
    private int lastPosition = -1;


    public TitleTextBehavior() {
    }

    public TitleTextBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, TextView child, MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downReach = false;
                upReach = false;
                break;
        }
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        //只上下滑动
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    /**
     * @param dy 上滑为正数，下滑为负数
     */
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        if (target instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) target;
            //获取RecyclerView第一个可见Item位置
            int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
            if (position == 0 && position < lastPosition) {
                //向下滑动
                downReach = true;
            }

            //判断是否可以滑动
            boolean canScroll = canScroll(child, dy);
            if (canScroll && position == 0) {

                float floatY = child.getTranslationY() - dy;
                if (floatY < -child.getHeight()) {
                    floatY = -child.getHeight();
                } else if (floatY > 0) {
                    floatY = 0;
                }
                child.setTranslationY(floatY);
                // 让CoordinatorLayout消费滑动事件
                consumed[1] = dy;
            }
            lastPosition = position;
        }
    }

    /**
     * 判断是否可以滑动
     * child.getTranslationY() child滑动的距离
     */
    private boolean canScroll(View child, float scrollY) {
        if (scrollY > 0 && child.getTranslationY() == -child.getHeight() && !upReach) {
            return false;
        }

        if (downReach) {
            //向下
            return false;
        }

        return true;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
