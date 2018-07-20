package com.qbase.auxilibrary.base;

import android.os.Message;

import com.qbase.auxilibrary.common.CommonHandler;
import com.qbase.auxilibrary.common.IHandlerListener;

/**
 * Created qay
 */
public class QBasePresenter<T extends IQBaseView> implements IHandlerListener {

    protected T mView;

    protected CommonHandler mHandler = new CommonHandler(this);

    public QBasePresenter(T view) {
        this.mView = view;
    }


    /**
     * 显示弹出框
     */
    protected void afterRequestError(final String text) {
        if (mHandler != null && mView != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    //是否要显示错误信息
                    mView.afterRequestError(text);
                }
            });
        }
    }


    /**
     * Persenter 生命周期 onDestroy
     */
    public void onDestroy() {
        if (mHandler != null) {
            mHandler = null;
        }
    }

    @Override
    public void onHandlerMessage(Message msg) {
        //消息处理
    }

}
