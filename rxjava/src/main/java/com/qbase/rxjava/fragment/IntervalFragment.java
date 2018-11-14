package com.qbase.rxjava.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qbase.auxilibrary.base.QBaseFg;
import com.qbase.auxilibrary.util.AjaxParams;
import com.qbase.auxilibrary.util.Util;
import com.qbase.rxjava.R;
import com.qbase.rxjava.bean.FTApprovalEntity;
import com.qbase.rxjava.bean.FTApprovalParams;

import org.reactivestreams.Publisher;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 采用 interval 操作符实现心跳间隔任务
 * 想必即时通讯等需要轮训的任务在如今的 APP 中已是很常见，
 * 而 RxJava 2.x 的 interval 操作符可谓完美地解决了我们的疑惑。
 */
public class IntervalFragment extends QBaseFg {

    private TextView mTvText;

    public static IntervalFragment newInstance() {
        return new IntervalFragment();
    }

    public IntervalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.zip_fg, container, false);
        return mView;
    }

    @Override
    protected void initView() {
        mTvText = mView.findViewById(R.id.zip_fg_tv);
    }


    private Disposable mDisposable;

    @Override
    protected void initialize() {

        mDisposable = Flowable.interval(1, TimeUnit.SECONDS).doOnNext(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                //aLong 从0开始，每 period 事件 调用一次
                Log.e(TAG, "accept: doOnNext : " + aLong);

            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e(TAG, "accept: 设置文本 ：" + aLong);

                        mTvText.setText("循环：" + aLong);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.isDisposed();
        }
    }
}
