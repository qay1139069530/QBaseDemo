package com.qbase.test.behavior.custom;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * tittle behavior
 */
public class TitleBehavior extends CoordinatorLayout.Behavior {

    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;

    public TitleBehavior() {
    }

    public TitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 确定使用Behavior的View要依赖的View的类型
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof RecyclerView;
    }


    /**
     * 当被依赖的View状态改变时回调
     * dependency 依赖childView的关联
     * child 使用Behavior的childView
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
// 位移
//      if (deltaY == 0) {
//            deltaY = dependency.getY() - child.getY();
//        }
//        //title显示的位置
//        float dy = dependency.getY() - child.getHeight();
//        dy = dy < 0 ? 0 : dy;
//        float y = -(dy / deltaY) * child.getHeight();
//        child.setTranslationY(y);


        //渐变
        if (deltaY == 0) {
            deltaY = dependency.getY() - child.getHeight();
        }

        float dy = dependency.getY() - child.getHeight();
        dy = dy < 0 ? 0 : dy;
        float alpha = 1 - (dy / deltaY);
        child.setAlpha(alpha);
        return true;
    }
}
