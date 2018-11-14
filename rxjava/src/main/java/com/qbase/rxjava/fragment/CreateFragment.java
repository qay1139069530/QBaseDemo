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
import io.reactivex.disposables.Disposable;

public class CreateFragment extends QBaseFg{


    public static CreateFragment newInstance() {
        return new CreateFragment();
    }

    public CreateFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.create_fg, container,false);
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
                Log.e(TAG, "creat subscribe" + "\n");
                Log.e(TAG, "Observable emit 1" + "\n");
                emitter.onNext(1);
                Log.e(TAG, "Observable emit 2" + "\n");
                emitter.onNext(2);
                Log.e(TAG, "Observable emit 3" + "\n");
                emitter.onNext(3);
                emitter.onComplete();
                Log.e(TAG, "Observable emit 4" + "\n" );
                emitter.onNext(4);

            }
        }).subscribe(new Observer<Integer>() {
            // 第二步：初始化Observer

            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable disposable) {
                Log.e(TAG, "onSubscribe ");
                mDisposable = disposable;
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext ");
//                i++;
//                if (i == 2) {
//                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
//                    mDisposable.dispose();
//                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete ");
            }
        });

// 第三步：订阅
    }
}
