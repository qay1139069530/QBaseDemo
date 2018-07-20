package com.qbase.auxilibrary.app;

import android.app.Application;

import com.qbase.auxilibrary.util.PathUtil;

/**基础app*/
public class QBaseApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        PathUtil.getInstance().initDirs("qbase", "data", this);
    }
}
