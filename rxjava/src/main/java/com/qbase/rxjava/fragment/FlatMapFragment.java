package com.qbase.rxjava.fragment;

import android.os.Bundle;
import android.text.TextUtils;
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
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 想必这种情况也在实际情况中比比皆是，例如用户注册成功后需要自动登录，我们只需要先通过注册接口注册用户信息，
 * 注册成功后马上调用登录接口进行自动登录即可。
 * 我们的 flatMap 恰好解决了这种应用场景，flatMap 操作符可以将一个发射数据的 Observable 变换为多个 Observables ，
 * 然后将它们发射的数据合并后放到一个单独的 Observable，利用这个特性，我们很轻松地达到了我们的需求。
 */
public class FlatMapFragment extends QBaseFg {

    private TextView mTvText;
    /**
     * 数据来源
     */
    private boolean isFromNet;

    public static FlatMapFragment newInstance() {
        return new FlatMapFragment();
    }

    public FlatMapFragment() {
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

    StringBuffer sb = new StringBuffer();

    @Override
    protected void initialize() {

//        otherFlatmap();

        netFlatMap();

    }

    private void netFlatMap(){
        Observable.create(new ObservableOnSubscribe<Response>() {

            @Override
            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
                // 第一步：初始化Observabl
                try {
                    AjaxParams ajaxParams = new AjaxParams();
                    String department_name = "水务管理科";
                    ajaxParams.put("checkUnitName", Util.strToUTF8(department_name));
                    ajaxParams.put("pageIndex", "1");
                    ajaxParams.put("pageSize", "10");
                    String url = "http://183.129.170.220:8079/xzsp/check/task/getTasks?" + ajaxParams.toString();
                    Request.Builder builder = new Request.Builder().url(url).get();
                    Request request = builder.build();
                    Call call = new OkHttpClient().newCall(request);
                    Response response = call.execute();
                    emitter.onNext(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).map(new Function<Response, FTApprovalEntity>() {
            @Override
            public FTApprovalEntity apply(Response response) throws Exception {
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        return new Gson().fromJson(body.string(), FTApprovalEntity.class);
                    }
                }
                return null;
            }
        }).subscribeOn(Schedulers.io())        // 在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取列表数据
                .doOnNext(new Consumer<FTApprovalEntity>() {
                    @Override
                    public void accept(FTApprovalEntity ftApprovalEntity) throws Exception {

                    }
                }).subscribeOn(Schedulers.io())//// 回到 io 线程去处理获取食品详情的请求
                .flatMap(new Function<FTApprovalEntity, ObservableSource<List<FTApprovalParams>>>() {
                    @Override
                    public ObservableSource<List<FTApprovalParams>> apply(FTApprovalEntity ftApprovalEntity) throws Exception {

                        Log.e(TAG, "flatMap:");
                        if (ftApprovalEntity != null && ftApprovalEntity.getRet() != null) {
                            return getNewWorkData();
                        }
                        return null;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FTApprovalParams>>() {
                    @Override
                    public void accept(List<FTApprovalParams> ftApprovalParams) throws Exception {
                        String text = new Gson().toJson(ftApprovalParams);
                        Log.e(TAG, "setText:");
                        mTvText.setText(text);
                    }
                });
    }

    private Observable<List<FTApprovalParams>> getNewWorkData() {
        Observable<List<FTApprovalParams>> newwork = Observable.create(new ObservableOnSubscribe<Response>() {

            @Override
            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
                // 第一步：初始化Observabl
                try {
                    AjaxParams ajaxParams = new AjaxParams();
                    String department_name = "水务管理科";
                    ajaxParams.put("checkUnitName", Util.strToUTF8(department_name));
                    ajaxParams.put("pageIndex", "1");
                    ajaxParams.put("pageSize", "10");
                    String url = "http://183.129.170.220:8079/xzsp/check/task/getTasks?" + ajaxParams.toString();
                    Request.Builder builder = new Request.Builder().url(url).get();
                    Request request = builder.build();
                    Call call = new OkHttpClient().newCall(request);
                    Response response = call.execute();
                    emitter.onNext(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).map(new Function<Response, List<FTApprovalParams>>() {
            @Override
            public List<FTApprovalParams> apply(Response response) throws Exception {
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        FTApprovalEntity data = new Gson().fromJson(body.string(), FTApprovalEntity.class);
                        if (data != null && data.getRet() != null) {
                            return data.getRet().getItems();
                        }
                        return null;
                    }
                }
                return null;
            }
        }).subscribeOn(Schedulers.newThread());
        return newwork;
    }


    private void otherFlatmap(){

        //flatMap 不定顺序
        //concatMap 按照顺序

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                Log.e(TAG, "I am value : " + integer + "\n");
                int delayTime = 2000;
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.e(TAG, "flatMap : accept : " + s + "\n");
                        mTvText.setText("flatMap : accept : " + s + "\n");
                    }
                });
    }
}
