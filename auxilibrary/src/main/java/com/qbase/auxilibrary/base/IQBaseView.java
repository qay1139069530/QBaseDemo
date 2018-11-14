package com.qbase.auxilibrary.base;

/**
 * base view interface
 */
public interface IQBaseView {
    /**
     * 加载错误调用
     */
    void afterRequestError(String text);

    /**
     * 加载才成功调用
     */
    void afterRequestSuccess();

}
