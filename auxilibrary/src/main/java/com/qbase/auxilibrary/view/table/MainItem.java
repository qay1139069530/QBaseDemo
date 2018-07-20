package com.qbase.auxilibrary.view.table;

/**
 * Created by dell on 2016/8/17.
 */
public class MainItem {

    /**
     * id号
     */
    private int id;
    /**
     * 名称
     */
    private String name;
    /**
     * 图片id
     */
    private int res_id;
    /**
     * 数量
     */
    private int count;

    /**
     * 是否选中
     */
    private boolean select;

    public MainItem(String user_Name) {
        this.name = user_Name;
    }

    public MainItem(int id, String user_Name) {
        this.id = id;
        this.name = user_Name;
    }

    public MainItem(int id, String user_Name, int res_id) {
        this.id = id;
        this.name = user_Name;
        this.res_id = res_id;
    }


    public MainItem(int id, String user_Name, int count, int res_id) {
        this.id = id;
        this.name = user_Name;
        this.count = count;
        this.res_id = res_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("name              = ").append(name).append("\n");
        sb.append("id                = ").append(id).append("\n");
        return sb.toString();
    }
}
