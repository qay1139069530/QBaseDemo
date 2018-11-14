package com.qbase.rxjava.fragment;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 其它操作符
 */
public class OtherFragment extends QBaseFg {

    private TextView mTvText;

    public static OtherFragment newInstance() {
        return new OtherFragment();
    }

    public OtherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.map_fg, container, false);
        return mView;
    }

    @Override
    protected void initView() {
        mTvText = mView.findViewById(R.id.map_fg_tv);
    }

    @Override
    protected void initialize() {

        distinct();

    }

    /**
     * distinct
     * 操作符去重
     */
    private void distinct() {

        //just 数据不能太多
        Observable.just(1, 1, 2, 3, 2, 1, 4, 5, 6, 8).distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "distinct : " + integer + "\n");
            }
        });
    }

    /**
     * filter
     * 过滤器。可以接受一个参数，让其过滤掉不符合我们条件的值
     */
    private void filter() {
        //just 数据不能太多
        Observable.just(1, 20, 65, -5, 7, 19)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer >= 10;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.e(TAG, "filter : " + integer + "\n");
            }
        });
    }

    /**
     * buffer
     * 将 Observable 中的数据按 skip (步长) 分成最大不超过 count 的 buffer ，然后生成一个  Observable 。
     * 也许你还不太理解，我们可以通过我们的示例图和示例代码来进一步深化它。
     */
    private void buffer() {
        //输出结构   123，345，5   隔两个字段，输出长度为3个的数据
        Observable.just(1, 2, 3, 4, 5)
                .buffer(3, 2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(@NonNull List<Integer> integers) throws Exception {
                        Log.e(TAG, "buffer size : " + integers.size() + "\n");
                        Log.e(TAG, "buffer value : ");
                        for (Integer i : integers) {
                            Log.e(TAG, i + "");
                        }
                        Log.e(TAG, "\n");
                    }
                });
    }

    /**
     * skip
     * 接受一个 long 型参数 count ，代表跳过 count 个数目开始接收
     */
    private void skip() {
        Observable.just(1, 2, 3, 4, 5)
                .skip(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "skip : " + integer + "\n");
                    }
                });
    }

    /**
     * take
     * 接受一个 long 型参数 count ，代表至多接收 count 个数据。
     */
    private void take() {
        Observable.just(1, 2, 3, 4, 5)
                .take(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "take : " + integer + "\n");
                    }
                });
    }

    /**
     * Single
     * 只会接收一个参数，而 SingleObserver 只会调用 onError() 或者 onSuccess()。
     */
    private void single() {
        Single.just(new Random().nextInt())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "single : onSubscribe : ");
                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        Log.e(TAG, "single : onSuccess : " + integer + "\n");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "single : onError : " + e.getMessage() + "\n");
                    }
                });

    }

    /**
     * debounce
     * 去除发送频率过快的项，看起来好像没啥用处，但你信我，后面绝对有地方很有用武之地。
     * 输出 2，4，5
     */
    private void debounce() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                // send events with simulated time wait
                emitter.onNext(1); // skip
                Thread.sleep(400);
                emitter.onNext(2); // deliver
                Thread.sleep(505);
                emitter.onNext(3); // skip
                Thread.sleep(100);
                emitter.onNext(4); // deliver
                Thread.sleep(605);
                emitter.onNext(5); // deliver
                Thread.sleep(510);
                emitter.onComplete();
            }
        }).debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "debounce :" + integer + "\n");
                    }
                });

    }

    /**
     * defer
     * 简单地时候就是每次订阅都会创建一个新的 Observable，并且如果没有被订阅，就不会产生新的 Observable。
     */
    private void defer() {
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(1, 2, 3);
            }
        });


        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.e(TAG, "defer : " + integer + "\n");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "defer : onError : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "defer : onComplete\n");
            }
        });

    }

    /**
     * last
     * 操作符仅取出可观察到的最后一个值，或者是满足某些条件的最后一项。
     * 输出 3
     */
    private void last() {
        Observable.just(1, 2, 3)
                .last(4)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "last : " + integer + "\n");
                    }
                });

    }

    /**
     * merge
     * merge 顾名思义，熟悉版本控制工具的你一定不会不知道 merge 命令，
     * 而在 Rx 操作符中，merge 的作用是把多个 Observable 结合起来，接受可变参数，
     * 也支持迭代器集合。注意它和 concat 的区别在于，
     * 不用等到 发射器 A 发送完所有的事件再进行发射器 B 的发送。
     * 输出  1，2，3，4，5
     */
    private void merge() {
        Observable.merge(Observable.just(1, 2), Observable.just(3, 4, 5))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.e(TAG, "accept: merge :" + integer + "\n");
                    }
                });
    }

    /**
     * reduce
     * reduce 操作符每次用一个方法处理一个值，可以有一个 seed 作为初始值。
     * 输出  6
     */
    private void reduce() {
        Observable.just(1, 2, 3)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.e(TAG, "accept: reduce : " + integer + "\n");
            }
        });
    }


    /**
     * scan
     * scan 操作符作用和上面的 reduce 一致，唯一区别是 reduce 是个只追求结果，
     * 而  scan 会始终如一地把每一个步骤都输出。
     * 输出  1,3,6
     */
    private void scan() {
        Observable.just(1, 2, 3)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.e(TAG, "accept: scan " + integer + "\n");
            }
        });
    }

    /**
     * window
     * 按照实际划分窗口，将数据发送给不同的 Observable
     */
    private void window() {
        Observable.interval(1, TimeUnit.SECONDS) // 间隔一秒发一次
                .take(15) // 最多接收15个
                .window(3, TimeUnit.SECONDS)// 每隔 3s 查看一次日志
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Observable<Long>>() {
                    @Override
                    public void accept(@NonNull Observable<Long> longObservable) throws Exception {
                        Log.e(TAG, "Sub Divide begin...\n");
                        longObservable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(@NonNull Long aLong) throws Exception {
                                        Log.e(TAG, "Next:" + aLong + "\n");
                                    }
                                });
                    }
                });
    }
}
