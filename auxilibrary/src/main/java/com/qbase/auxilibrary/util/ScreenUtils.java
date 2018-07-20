package com.qbase.auxilibrary.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * ScreenUtils
 * <ul>
 * <strong>Convert between dp and sp</strong>
 * </ul>
 */
public class ScreenUtils {

    /**
     * 获得屏幕状态栏的高度
     */
    public static int getWindowStatusBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        if (frame.top == 0) {
            Class<?> c = null;
            Object obj = null;
            Field field = null;
            int x = 0, sbar = 0;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                x = Integer.parseInt(field.get(obj).toString());
                sbar = activity.getResources().getDimensionPixelSize(x);
            } catch (Exception e1) {
                // Log.e("get status bar height fail");
                e1.printStackTrace();
            }
            return sbar;
        } else {
            return frame.top;
        }
    }

    /**
     * 获取屏幕的宽度，单位px
     *
     * @return
     */
    public static int getScreenWidth(Context ctx) {
        DisplayMetrics mDisplayMetrics = ctx.getResources().getDisplayMetrics();
        int width = mDisplayMetrics.widthPixels;
        return width;
    }

    /**
     * ��ȡ��Ļ�Ŀ��
     *
     * @return
     */
    public static int getWindowWPix(Context ctx) {
        Display display = ((WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Log.v("getWindowWPix", "" + width);
        return width;
    }

    /**
     * ��ȡ��Ļ�ĸ߶�
     *
     * @return
     */
    public static int getWindowHPix(Context ctx) {
        Display display = ((WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int height = display.getHeight();
        Log.v("getWindowHPix", "" + height);
        return height;
    }


    public static int getWindowWPixDialog(Dialog ctx) {
        Display display = ((WindowManager) ctx.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Log.v("getWindowWPix", "" + width);
        return width;
    }


    /**
     * 获取状态栏高度
     * 由于窗口view的绘制需要一定的时延，所以在获取状态栏高度的时候在窗口的可视阶段即从oncreate()->onresume()都不能直接使用上面的方法。
     * 可以在界面加载完之后获取
     *
     * @return
     */
    public static int getStatusBarHeight(Activity ctx) {
        Rect frame = new Rect();
        ctx.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        return statusBarHeight;
    }

    /**
     * 获取标题栏高度
     * 由于窗口view的绘制需要一定的时延，所以在获取状态栏高度的时候在窗口的可视阶段即从oncreate()->onresume()都不能直接使用上面的方法。
     * 可以在界面加载完之后获取
     *
     * @return
     */
    public static int getTitleBarHeight(Activity ctx) {
        int contentTop = ctx.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        // statusBarHeight是上面所求的状态栏的高度
        int titleBarHeight = contentTop - getStatusBarHeight(ctx);
        return titleBarHeight;
    }



    /**
     * 获取view 底部位置
     */
    public static int getViewBottomPosition(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return location[1] + view.getHeight();
    }
}
