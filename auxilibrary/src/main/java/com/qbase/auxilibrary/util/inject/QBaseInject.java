package com.qbase.auxilibrary.util.inject;

import com.qbase.auxilibrary.app.QBaseApp;
import com.qbase.auxilibrary.util.inject.biz.QBaseInjector;
import com.qbase.auxilibrary.util.inject.biz.QBaseInjectorImpl;

import java.lang.reflect.Method;

/**
 * 任务控制中心, http, image, db, view注入等接口的入口.
 * 需要在在application的onCreate中初始化: QBaseInject.Ext.init(this);
 */
public final class QBaseInject {

    private QBaseInject() {
    }

    public static QBaseApp app() {
        if (Ext.app == null) {
            try {
                // 在IDE进行布局预览时使用
                Class<?> renderActionClass = Class.forName("com.android.layoutlib.bridge.impl.RenderAction");
                Method method = renderActionClass.getDeclaredMethod("getCurrentContext");
                method.invoke(null);
                Ext.app = new QBaseApp();
            } catch (Throwable ignored) {
                throw new RuntimeException("please invoke QBaseInject.Ext.init(app) on Application#onCreate()"
                        + " and register your Application in manifest.");
            }
        }
        return Ext.app;
    }


    /**初始化*/
    public static QBaseInjector instance() {
        if (Ext.viewInjector == null) {
            QBaseInjectorImpl.registerInstance();
        }
        return Ext.viewInjector;
    }


    public static class Ext {
        private static QBaseApp app;
        private static QBaseInjector viewInjector;

        private Ext() {
        }

        public static void init(QBaseApp app) {
            if (Ext.app == null) {
                Ext.app = app;
            }
        }

        public static void setViewInjector(QBaseInjector viewInjector) {
            Ext.viewInjector = viewInjector;
        }
    }
}
