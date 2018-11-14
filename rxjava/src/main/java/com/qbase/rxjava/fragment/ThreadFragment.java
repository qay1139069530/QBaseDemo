package com.qbase.rxjava.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qbase.auxilibrary.base.QBaseFg;
import com.qbase.rxjava.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * subscribeOn() 指定的就是发射事件的线程，observerOn 指定的就是订阅者接收事件的线程。
 * 多次指定发射事件的线程只有第一次指定的有效，也就是说多次调用 subscribeOn() 只有第一次的有效，其余的会被忽略。
 * 但多次指定订阅者接收线程是可以的，也就是说每调用一次 observerOn()，下游的线程就会切换一次。
 * Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作；
 * SSchedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作；
 * SSchedulers.newThread() 代表一个常规的新线程；
 * SAndroidSchedulers.mainThread() 代表Android的主线程
 */
public class ThreadFragment extends QBaseFg {


    public static ThreadFragment newInstance() {
        return new ThreadFragment();
    }

    public ThreadFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.create_fg, container, false);
        return mView;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initialize() {
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 第一步：初始化Observabl
                Log.e(TAG, "Observable thread is : " + Thread.currentThread().getName());
                Log.e(TAG, "creat subscribe" + "\n");
                Log.e(TAG, "Observable emit 1" + "\n");
                emitter.onNext(1);
                Log.e(TAG, "Observable emit 2" + "\n");
                emitter.onNext(2);
                Log.e(TAG, "Observable emit 3" + "\n");
                emitter.onNext(3);
                emitter.onComplete();
                Log.e(TAG, "Observable emit 4" + "\n");
                emitter.onNext(4);

            }
        }).subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())//不同线程事件调用，只有第一个有效
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        //获取到数据后，先做的动作
                        Log.e("Current (mainThread) ", Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<Integer>() {
                    // 第二步：初始化Observer
                    @Override
                    public void onSubscribe(Disposable disposable) {
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e("Current (newThread) ", Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

// 第三步：订阅
    }
}
