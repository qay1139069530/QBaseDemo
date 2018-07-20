package com.qbase.auxilibrary.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Qay
 */

public class NetworkUtil {


    /**
     * 判断是否有网络连接
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 判断是否有网络连接
     */
    public static boolean isNetworkAvailable(Context context) {
        boolean isWifiAvailable = isWifiAvailable(context);
        if (isWifiAvailable) {
            return true;
        }
        boolean is4GAvailable = is4GAvailable(context);
        if (is4GAvailable) {
            return true;
        }
        return false;
    }

    /**
     * 判断WIFI网络是否可用
     */
    public static boolean isWifiAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI);
    }

    /**
     * 判断当前网络是否是4G网络
     *
     * @param
     * @return boolean
     */
    public static boolean is4GAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int networkType = telephonyManager.getNetworkType();
            /** Current network is LTE */
            if (networkType == 13) {
                /**此时的网络是4G的*/
                return true;
            }
        }
        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 获取当前网络连接的类型信息
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }


    /**
     * 检查互联网地址是否可以访问
     *
     * @param callback 检查结果回调（是否可以ping通地址）{@see java.lang.Comparable<T>}
     */
    public static void isNetWorkAvailable(final Comparable<Boolean> callback) {

        final Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (callback != null) {
                    callback.compareTo(msg.arg1 == 0);
                }
            }

        };
        new Thread(new Runnable() {

            @Override
            public void run() {
                Runtime runtime = Runtime.getRuntime();
                Message msg = new Message();
                String address = PropertyParse.parse("pingUrl");
                try {
                    Process pingProcess = runtime.exec("/system/bin/ping -c 1 " + address);
                    InputStreamReader isr = new InputStreamReader(pingProcess.getInputStream());
                    BufferedReader buf = new BufferedReader(isr);
                    if (buf.readLine() == null) {
                        msg.arg1 = -1;
                    } else {
                        msg.arg1 = 0;
                    }
                    buf.close();
                    isr.close();
                } catch (Exception e) {
                    msg.arg1 = -1;
                    e.printStackTrace();
                } finally {
                    runtime.gc();
                    if (handler != null) {
                        handler.sendMessage(msg);
                    }
                }
            }

        }).start();
    }

    /**
     * 检查互联网地址是否可以访问
     * 此方法需在线程中使用
     */
    public static boolean isNetWorkAvailable() {
        boolean isConnect = false;
        Runtime runtime = Runtime.getRuntime();
        Message msg = new Message();
        String address = PropertyParse.parse("pingUrl");
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            Process pingProcess = runtime.exec("/system/bin/ping -c 1 " + address);
            inputStreamReader = new InputStreamReader(pingProcess.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            if (bufferedReader.readLine() == null) {
                msg.arg1 = -1;
            } else {
                msg.arg1 = 0;
                isConnect = true;
            }
            bufferedReader.close();
            bufferedReader.close();
        } catch (Exception e) {
            msg.arg1 = -1;
            e.printStackTrace();
        } finally {
            runtime.gc();
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                    inputStreamReader = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    bufferedReader = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return isConnect;
    }
}
