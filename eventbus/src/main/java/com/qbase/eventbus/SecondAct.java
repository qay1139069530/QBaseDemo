package com.qbase.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.util.AsyncExecutor;

public class SecondAct extends AppCompatActivity{

    private static final String TAG = "SecondAct";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                AsyncExecutor.create().execute(new AsyncExecutor.RunnableEx() {
//                    @Override
//                    public void run() throws Exception {
//                        EventBus.getDefault().post(new MessageEvent("第二个Activity触发"));
//                    }
//                });

                EventBus.getDefault().postSticky(new MessageEvent("第二个Activity触发"));
                EventBus.getDefault().postSticky(new SomeOtherEvent("第三个个Activity触发"));
                finish();
            }
        });


        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                AsyncExecutor.create().execute(new AsyncExecutor.RunnableEx() {
//                    @Override
//                    public void run() throws Exception {
//                        EventBus.getDefault().post(new MessageEvent("第二个Activity触发"));
//                    }
//                });

                EventBus.getDefault().postSticky(new SomeOtherEvent("第二个Activity SomeOtherEvent触发"));

                finish();
            }
        });

        EventBus.getDefault().register(SecondAct.this);
    }


    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onMessagePosting(MessageEvent event) {
        Log.i(TAG, "onMessagePosting(), message is " + event.getMessage() + "   :  " + Thread.currentThread().getName());
        // 移除粘性事件
        //EventBus.getDefault().removeStickyEvent(event);
    }
}
