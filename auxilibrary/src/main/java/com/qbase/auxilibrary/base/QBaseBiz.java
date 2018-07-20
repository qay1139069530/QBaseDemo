package com.qbase.auxilibrary.base;


import com.qbase.auxilibrary.app.QBaseApp;

/**
 * Created by qay
 */
public class QBaseBiz<T extends QBaseApp> {

    protected T mApp;

    public QBaseBiz(T app) {
        mApp = app;
    }
}
