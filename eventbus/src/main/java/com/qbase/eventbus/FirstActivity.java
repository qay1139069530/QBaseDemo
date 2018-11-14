package com.qbase.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.util.AsyncExecutor;
import org.greenrobot.eventbus.util.ThrowableFailureEvent;

public class FirstActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView mTvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        mTvText = findViewById(R.id.text);

        findViewById(R.id.posting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessageEvent("传递参数"));
                startActivity(new Intent(FirstActivity.this,SecondAct.class));
//                EventBus.getDefault().post(new MessageEvent("测试"));
            }
        });

        findViewById(R.id.main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("测试"));
            }
        });

        findViewById(R.id.stick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessageEvent("测试"));
            }
        });


        findViewById(R.id.async).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncExecutor.create().execute(
                        new AsyncExecutor.RunnableEx() {
                            @Override
                            public void run() {
                                EventBus.getDefault().postSticky(new MessageEvent("测试"));
                            }
                        }
                );
            }
        });


        findViewById(R.id.background).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("测试"));
            }
        });
        findViewById(R.id.other).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventBus.getDefault().post(new SomeOtherEvent("测试"));
                EventBus.getDefault().register(FirstActivity.this);
            }
        });


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMain(MessageEvent messageEvent) {
        Log.i(TAG, "onMain(), message is " + messageEvent.getMessage() + "   :  " + Thread.currentThread().getName());
    }


    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onMessagePosting(MessageEvent event) {
        Log.i(TAG, "onMessagePosting(), message is " + event.getMessage() + "   :  " + Thread.currentThread().getName());
        // 移除粘性事件
        //EventBus.getDefault().removeStickyEvent(event);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onMessageAsync(MessageEvent event) {
        Log.i(TAG, "onMessageAsync(), message is " + event.getMessage() + "   :  " + Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageBackound(MessageEvent event) {
        Log.i(TAG, "onMessageBackound(), message is " + event.getMessage() + "   :  " + Thread.currentThread().getName());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleSomethingElse(SomeOtherEvent event) {
        Log.i(TAG, "handleSomethingElse(), message is " + event.getMessage() + "   :  " + Thread.currentThread().getName());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageThrowable(ThrowableFailureEvent event) {
        Log.i(TAG, "onMessageThrowable(), message is " + event.getThrowable().getMessage());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
