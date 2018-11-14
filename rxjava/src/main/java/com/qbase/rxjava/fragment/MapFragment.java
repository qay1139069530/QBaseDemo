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
 * 1）通过 Observable.create() 方法，调用 OkHttp 网络请求；
 * 2）通过 map 操作符集合 gson，将 Response 转换为 bean 类；
 * 3）通过 doOnNext() 方法，解析 bean 中的数据，并进行数据库存储等操作；
 * 4）调度线程，在子线程中进行耗时操作任务，在主线程中更新 UI ；
 * 5）通过 subscribe()，根据请求成功或者失败来更新 UI 。
 */
public class MapFragment extends QBaseFg {

    private TextView mTvText;

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    public MapFragment() {
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
                        Log.e(TAG, "map:转换前:" + response.body());
                        return new Gson().fromJson(body.string(), FTApprovalEntity.class);
                    }
                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<FTApprovalEntity>() {
                    @Override
                    public void accept(FTApprovalEntity ftApprovalEntity) throws Exception {
                        //获取到数据后，先做的动作
                        Log.e(TAG, "doOnNext: 保存成功：" + ftApprovalEntity.toString() + "\n");
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FTApprovalEntity>() {
                    @Override
                    public void accept(FTApprovalEntity ftApprovalEntity) throws Exception {
                        String text = new Gson().toJson(ftApprovalEntity);
                        Log.e(TAG, "成功:" + text + "\n");
                        mTvText.setText(text);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "失败：" + throwable.getMessage() + "\n");
                    }
                });
    }
}
