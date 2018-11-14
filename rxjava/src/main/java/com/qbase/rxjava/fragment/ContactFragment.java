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

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
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
 * concat 可以做到不交错的发射两个甚至多个 Observable 的发射事件，
 * 并且只有前一个 Observable 终止(onComplete) 后才会订阅下一个 Observable。
 * eg:采用 concat 操作符先读取缓存再通过网络请求获取数据
 */
public class ContactFragment extends QBaseFg {

    private TextView mTvText;
    /**
     * 数据来源
     */
    private boolean isFromNet;

    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    public ContactFragment() {
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

        //缓存数据处理
        Observable<String> cache = Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                // 第一步：初始化Observabl
                try {
                    //首先读取本地网络数据
                    Log.e(TAG, "create当前线程:" + Thread.currentThread().getName());
                    String data = mTvText.getText().toString();
                    // 在操作符 concat 中，只有调用 onComplete 之后才会执行下一个 Observable
                    if (!TextUtils.isEmpty(data)) {
                        // 如果缓存数据不为空，则直接读取缓存数据，而不读取网络数据
                        isFromNet = false;
                        Log.e(TAG, "\nsubscribe: 读取缓存数据:");
                        sb.append("\nsubscribe: 读取缓存数据:\n");

                        emitter.onNext(data);
                    } else {
                        isFromNet = true;
                        sb.append("\nsubscribe: 读取网络数据:\n");
                        Log.e(TAG, "\nsubscribe: 读取网络数据:");
                        emitter.onComplete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        //网络数据处理
        AjaxParams ajaxParams = new AjaxParams();
        String department_name = "水务管理科";
        ajaxParams.put("checkUnitName", Util.strToUTF8(department_name));
        ajaxParams.put("pageIndex", "1");
        ajaxParams.put("pageSize", "10");
        String url = "http://183.129.170.220:8079/xzsp/check/task/getTasks?" + ajaxParams.toString();

        Observable<String> network = getNewWorkData();


        //合并处理
        // 两个 Observable 的泛型应当保持一致

        Observable.concat(cache, network)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String data) throws Exception {
                        Log.e(TAG, "subscribe 成功:" + Thread.currentThread().getName());
                        if (isFromNet) {
                            Log.e(TAG, "accept : 网络获取数据设置缓存: \n" + data.toString());
                            //保存数据到本地

                            sb.append("accept: 网路数据:" + data + "\n");
                        } else {
                            sb.append("本地数据:" + data + "\n");
                        }

                        mTvText.setText(sb.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe 失败:" + Thread.currentThread().getName());
                        Log.e(TAG, "accept: 读取数据失败：" + throwable.getMessage());
                    }
                });


    }


    private Observable<String> getNewWorkData() {
        Observable<String> newwork = Observable.create(new ObservableOnSubscribe<Response>() {

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
        }).map(new Function<Response, String>() {
            @Override
            public String apply(Response response) throws Exception {
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        Log.e(TAG, "map:转换前:" + response.body());
                        return body.string();
                    }
                }
                return null;
            }
        });
        return newwork;
    }

}
