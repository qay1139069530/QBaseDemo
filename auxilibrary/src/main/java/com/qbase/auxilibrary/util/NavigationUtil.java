package com.qbase.auxilibrary.util;

import android.support.annotation.ColorInt;
import android.support.design.internal.NavigationMenuPresenter;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.qbase.auxilibrary.R;

import java.lang.reflect.Field;

public class NavigationUtil {


    /**
     * 设置NavigationView item分隔线的颜色
     */
    public static void setNavigationMenuLineStyle(NavigationView navigationView) {
        if (navigationView == null) {
            return;
        }
        setNavigationMenuLineStyle(navigationView, ContextCompat.getColor(navigationView.getContext(), R.color.line_menu_color), 2);
    }

    /**
     * 设置NavigationView item分隔线的颜色
     *
     * @param color 需要处理的数
     */
    public static void setNavigationMenuLineStyle(NavigationView navigationView, @ColorInt final int color) {
        setNavigationMenuLineStyle(navigationView, color, 2);
    }

    /**
     * 设置NavigationView item分隔线的颜色
     *
     * @param color  线颜色
     * @param height 线高度
     */
    public static void setNavigationMenuLineStyle(NavigationView navigationView, @ColorInt final int color, final int height) {
        try {
            if (navigationView == null) {
                return;
            }
            Field fieldByPressenter = navigationView.getClass().getDeclaredField("mPresenter");
            fieldByPressenter.setAccessible(true);
            NavigationMenuPresenter menuPresenter = (NavigationMenuPresenter) fieldByPressenter.get(navigationView);
            Field fieldByMenuView = menuPresenter.getClass().getDeclaredField("mMenuView");
            fieldByMenuView.setAccessible(true);
            final NavigationMenuView mMenuView = (NavigationMenuView) fieldByMenuView.get(menuPresenter);
            mMenuView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
                @Override
                public void onChildViewAttachedToWindow(View view) {
                    RecyclerView.ViewHolder viewHolder = mMenuView.getChildViewHolder(view);
                    if (viewHolder != null && "SeparatorViewHolder".equals(viewHolder.getClass().getSimpleName()) && viewHolder.itemView != null) {
                        if (viewHolder.itemView instanceof FrameLayout) {
                            FrameLayout frameLayout = (FrameLayout) viewHolder.itemView;
                            View line = frameLayout.getChildAt(0);
                            line.setBackgroundColor(color);
                            line.getLayoutParams().height = height;
                            line.setLayoutParams(line.getLayoutParams());
                        }
                    }
                }

                @Override
                public void onChildViewDetachedFromWindow(View view) {

                }
            });

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
