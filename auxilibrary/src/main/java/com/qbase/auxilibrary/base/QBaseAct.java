package com.qbase.auxilibrary.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.qbase.auxilibrary.app.QBaseApp;
import com.qbase.auxilibrary.common.CommonHandler;
import com.qbase.auxilibrary.common.IHandlerListener;
import com.qbase.auxilibrary.util.ActivityStack;

/**base activity*/
public class QBaseAct extends AppCompatActivity implements IHandlerListener {

    protected QBaseApp mApp;

    protected QBaseAct _this;
    /**
     * base handler
     */
    protected CommonHandler mHandler = new CommonHandler(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = (QBaseApp) getApplication();
        _this = this;
        ActivityStack.getInstance().pushActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.getInstance().finishActivity(this);
        if (mHandler != null) {
            mHandler = null;
        }
    }


    @Override
    public void onHandlerMessage(Message msg) {
        //消息处理
    }

}
