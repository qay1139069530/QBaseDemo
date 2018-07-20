package com.qbase.auxilibrary.bean;


import java.io.Serializable;

public class WebBean implements Serializable {

    /**类型：1：添加wei头*/
    public static final int WEB_TYPE_1 = 1;
    /**类型：直接加载url*/
    public static final int WEB_TYPE_2 = 2;
    /**id*/
    private int   id;
    /**类型：1：添加wei头，2：直接加载url*/
    private int type;
    /**item 名称*/
    private String url;

    /**名称*/
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
