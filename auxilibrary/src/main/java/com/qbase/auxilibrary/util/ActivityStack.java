package com.qbase.auxilibrary.util;


import android.app.Activity;
import android.content.Context;
import android.os.Process;

import com.qbase.auxilibrary.base.QBaseAct;

import java.util.Stack;

/**activity 管理类*/
public class ActivityStack {
    private Stack<QBaseAct> mActivities = new Stack<QBaseAct>();
    private static ActivityStack INSTANCE;

    private ActivityStack() {
        if (CollectionUtil.isEmpty(mActivities)) {
            mActivities = new Stack<QBaseAct>();
        }
    }

    /**
     * 初始化
     */
    public static ActivityStack getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ActivityStack();
        }
        return INSTANCE;
    }

    /**
     * 大小
     */
    public int size() {
        return CollectionUtil.isEmpty(mActivities) ? 0 : mActivities.size();
    }

    /**
     * 添加activity
     */
    public void pushActivity(QBaseAct activity) {
        if (CollectionUtil.isEmpty(mActivities)) {
            mActivities = new Stack<QBaseAct>();
        }
        mActivities.add(activity);
    }

    /**
     * 关闭所有activity
     */
    public void finishAllActivity() {
        if (!CollectionUtil.isEmpty(mActivities)) {
            Activity activity = mActivities.lastElement();
            if (activity != null) {
                activity.finish();
                activity = null;
            }
        }
    }

    /**
     * 关闭当前指定activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            if (!CollectionUtil.isEmpty(mActivities)) {
                if (mActivities.contains(activity)) {
                    mActivities.remove(activity);
                }
            }
            activity = null;
        }
    }

    /**
     * 关闭指定Activity
     */
    public void finishAppointActivity(Class<? extends Context> activityClass) {
        if (CollectionUtil.isEmpty(mActivities)) {
            return;
        }
        for (int i = mActivities.size() - 1; i >= 0; i--) {
            QBaseAct activity = mActivities.elementAt(i);
            if (activity != null && activity.getClass().equals(activityClass)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 关闭顶部指定Activity
     */
    public void finishTopAppointActivity(Activity activity) {
        for (int i = mActivities.size() - 1; i >= 0; i--) {
            QBaseAct base = mActivities.elementAt(i);
            if (activity != null && base.getClass().equals(activity.getClass())) {
                mActivities.remove(i);
                activity.finish();
                break;
            }
        }
    }

    /**
     * 获取当前activity
     */
    public Activity getCurrentActivity() {
        return mActivities.lastElement();
    }

    /**
     * 关闭其它activity
     */
    public void finishOtherActivity(QBaseAct activity) {
        if (activity != null) {
            for (QBaseAct item : mActivities) {
                if (!item.getClass().equals(activity.getClass())) {
                    item.finish();
                    item = null;
                }
            }
            mActivities.removeAllElements();
            mActivities.add(activity);
        }
    }


    /**
     * 关闭程序
     */
    public void finishAllActivity(boolean killProcess) {
        // boolean isForceClose = false;
        if (!CollectionUtil.isEmpty(mActivities)) {
            while (mActivities.size() > 0) {
                finishActivity(getCurrentActivity());
            }
        }
        if (killProcess) {
            Process.killProcess(Process.myPid());
        }
    }

}
