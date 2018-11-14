package com.qbase.eventbus;

import android.app.Application;


public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // 配置EventBus
//        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
    }
}
