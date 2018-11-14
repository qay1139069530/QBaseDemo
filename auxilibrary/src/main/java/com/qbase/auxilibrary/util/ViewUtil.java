package com.qbase.auxilibrary.util;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.atomic.AtomicInteger;

public class ViewUtil {

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * 以宽为准，等比缩放高
     *
     * @param view      需要缩放的view
     * @param oldWidth  原始宽
     * @param oldHeight 原始高
     * @param newWidth  要缩放的宽
     */
    public static void scaleViewByWidth(@NonNull View view, int oldWidth, int oldHeight, int newWidth) {
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) view.getLayoutParams();
        int newHeight = newWidth * oldHeight / oldWidth;
        params.width = newWidth;
        params.height = newHeight;
        view.setLayoutParams(params);
    }

    @SuppressLint("NewApi")
    public static int generateViewId() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            for (; ; ) {
                final int result = sNextGeneratedId.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF)
                    newValue = 1; // Roll over to 1, not 0.
                if (sNextGeneratedId.compareAndSet(result, newValue))
                    return result;
            }
        } else
            return View.generateViewId();
    }

    /**
     * 设置view背景
     */
    public static void setBackground(View v, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            v.setBackground(drawable);
        else
            v.setBackgroundDrawable(drawable);
    }

}
