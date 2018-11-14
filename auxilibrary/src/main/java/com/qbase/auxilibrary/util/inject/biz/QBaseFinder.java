package com.qbase.auxilibrary.util.inject.biz;

import android.app.Activity;
import android.view.View;

/**
 * 注解添加类
 */
final class QBaseFinder {

    private View view;
    private Activity activity;

    public QBaseFinder(View view) {
        this.view = view;
    }

    public QBaseFinder(Activity activity) {
        this.activity = activity;
    }

    public View findViewById(int id) {
        if (view != null) return view.findViewById(id);
        if (activity != null) return activity.findViewById(id);
        return null;
    }

    public View findViewByInfo(QBaseInfo info) {
        return findViewById(info.value, info.parentId);
    }

    public View findViewById(int id, int pid) {
        View pView = null;
        if (pid > 0) {
            pView = this.findViewById(pid);
        }

        View view = null;
        if (pView != null) {
            view = pView.findViewById(id);
        } else {
            view = this.findViewById(id);
        }
        return view;
    }
}
