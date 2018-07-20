package com.qbase.auxilibrary.util;

import android.content.Context;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 工具类
 */
public class Util {

    /**
     * 判断 是不是这个类: 是 : true
     */
    public static boolean judgeContextToActivity(Context context, Class<? extends Context> activityClass) {
        if (context != null) {
            Class<? extends Context> ctxClass = context.getClass();
            if (ctxClass.hashCode() == activityClass.hashCode()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断 是不是这个类: 是 : true
     */
    public static boolean judgeClass(Class a, Class b) {
        if (a != null) {
            if (a.getClass().hashCode() == b.hashCode()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是有效的图片path
     *
     * @param path
     * @return
     */
    public static String realImgPath(String path) {
        if (TextUtils.isEmpty(path)) {
            return path;
        }
        if (!path.contains(".")) {
            return path;
        }

        String[] suffixs = new String[]{".jpg", ".jpeg", ".png", ".bmp", ".gif"};
        String img_path = path.substring(0, path.lastIndexOf("."));
        String suffix = path.substring(path.lastIndexOf("."));
        for (int i = 0; i < suffixs.length; i++) {
            if (suffixs[i].equalsIgnoreCase(suffix) || suffix.contains(suffixs[i])) {
                img_path = img_path + suffixs[i];
                break;
            }
        }
        return img_path;
    }

    /**
     * 获取图片名称
     *
     * @param path
     * @return
     */
    public static String getImgPathName(String path) {
        try {
            if (TextUtils.isEmpty(path)) {
                return path;
            }
            String name = "";
            if (path.lastIndexOf("/") != -1) {
                name = path.substring(path.lastIndexOf("/"), path.length()).replaceFirst("/", "");
            }
            return name;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 对象toString()
     *
     * @return
     */
    public static String toString(Object obj) {

        if (obj == null)
            return "null";

        StringBuffer sb = new StringBuffer();

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        sb.append(clazz.getName() + "{");
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                sb.append("\n  " + field.getName() + ":" + field.get(obj));
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        sb.append("\n}");
        return sb.toString();
    }

    /**
     * 将str转成utf-8
     *
     * @param s
     * @return
     */
    public static String strToUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return s;
        }
    }

    public static String formatUTC(long l, String strPattern) {
        if (TextUtils.isEmpty(strPattern)) {
            strPattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(strPattern, Locale.CHINA);
        return sdf == null ? "NULL" : sdf.format(l);
    }
}