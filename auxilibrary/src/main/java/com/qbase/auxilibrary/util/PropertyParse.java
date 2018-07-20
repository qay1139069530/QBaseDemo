package com.qbase.auxilibrary.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.content.Context;


public class PropertyParse {

    /**
     * 获取assets文件价键值对
     */
    public static String parse(String key) {
        try {
            Properties pro = new Properties();
            pro.load(PropertyParse.class.getResourceAsStream("/assets/config.properties"));
            return pro.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取assets文件价键值对
     */
    public static String parse(Context context, String key) {
        Properties pro = new Properties();
        InputStream is;
        try {
            is = context.getAssets().open("config.properties");
            pro.load(is);
            return pro.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
