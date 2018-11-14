package com.qbase.auxilibrary.common;

/**
 * Created by jhy on 2016/8/17.
 */
public interface IRequestListener<T> {

    /**
     * 获取数据成功
     */
    void onRequestSuccess(T data);

    /**
     * 获取数据失败
     */
    void onRequestFail(String data);

}
