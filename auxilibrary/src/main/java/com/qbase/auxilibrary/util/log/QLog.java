package com.qbase.auxilibrary.util.log;

import android.util.Log;

public class QLog {
    private static boolean DEBUG = true;

    /**启用debug模式*/
    public static void setDebug(boolean debug) {
        DEBUG = debug;
    }

    /**是否是Debug模式*/
    public static boolean isDebug() {
        return DEBUG;
    }

    public static void printLog(Object tag, String msg) {
        printLog(QLogLevel.INFO, tag.getClass().getSimpleName(), msg);
    }

    public static void printLog(QLogLevel mLogLevel, String tag, String msg) {
        if (DEBUG) {
            switch (mLogLevel) {
            case DEBUG:
                Log.d(tag, "#" + msg);
                break;
            case WARNING:
                Log.w(tag, "#" + msg);
                break;
            case ERROR:
                Log.e(tag, "#" + msg);
                break;
            case INFO:
                Log.i(tag, "#" + msg);
                break;
            default:
                break;
            }

        }
    }

    public static void d(String tag, String message) {
        printLog(QLogLevel.DEBUG, tag, message);
    }

    public static void i(String tag, String message) {
        printLog(QLogLevel.INFO, tag, message);
    }

    public static void w(String tag, String message) {
        printLog(QLogLevel.WARNING, tag, message);
    }

    public static void e(String tag, String message) {
        printLog(QLogLevel.ERROR, tag, message);
    }

    public static void d(Object obj, String message) {
        d(obj.getClass().getSimpleName(), message);
    }

    public static void i(Object obj, String message) {
        i(obj.getClass().getSimpleName(), message);
    }

    public static void w(Object obj, String message) {
        w(obj.getClass().getSimpleName(), message);
    }

    public static void e(Object obj, String message) {
        e(obj.getClass().getSimpleName(), message);
    }

}
