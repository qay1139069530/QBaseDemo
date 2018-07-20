package com.qbase.auxilibrary.util;

import android.content.Context;

/**
 * PropertyParse 中 url获取
 */
public class UrlUtil {

    /**
     * 水利巡查-API
     */
    public final static String SLXC_INSPECT_API = "inspect_apiUrl";
    /**
     * 水利巡查-WEB
     */
    public final static String SLXC_INSPECT_WEB = "inspect_webUrl";

    /**
     * 水利执法-API
     */
    public final static String SLXC_LAW_API = "law_apiUrl";
    /**
     * 水利执法-WEB
     */
    public final static String SLXC_LAW_WEB = "law_webUrl";

    /**
     * 水利执法-API
     */
    public final static String SLXC_STANDARD_API = "apiUrl";
    /**
     * 水利执法-WEB
     */
    public final static String SLXC_STANDARD_WEB = "webUrl";


    /**
     * 水利工程-API
     */
    public final static String SLXC_PROJECT_API = "project_apiUrl";
    /**
     * 水利工程-WEB
     */
    public final static String SLXC_PROJECT_WEB = "project_webUrl";

    /**
     * PropertyParse url
     */
    public static String parseUrl(Context context, String key) {
        try {
            return PropertyParse.parse(context, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
