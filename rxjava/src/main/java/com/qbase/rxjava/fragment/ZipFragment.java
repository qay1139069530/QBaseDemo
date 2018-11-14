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

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
 * 在实际应用中，我们极有可能会在一个页面显示的数据来源于多个接口，这时候我们的 zip 操作符为我们排忧解难。
 * zip 操作符可以将多个 Observable 的数据结合为一个数据源再发射出去。
 */
public class ZipFragment extends QBaseFg {

    private TextView mTvText;
    /**
     * 数据来源
     */
    private boolean isFromNet;

    public static ZipFragment newInstance() {
        return new ZipFragment();
    }

    public ZipFragment() {
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

    @Override
    protected void initialize() {
        Observable.zip(getNewWorkData(), getDetialData(), new BiFunction<List<FTApprovalParams>, FTApprovalEntity, String>() {

            @Override
            public String apply(List<FTApprovalParams> ftApprovalParams, FTApprovalEntity ftApprovalEntity) throws Exception {
                return new Gson().toJson(ftApprovalEntity) + "\n\n\n\n" + new Gson().toJson(ftApprovalEntity);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {
                        mTvText.setText(s);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

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
                        Log.e(TAG, "map:转换前:" + response.body());
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

    private Observable<FTApprovalEntity> getDetialData() {
        Observable<FTApprovalEntity> newwork = Observable.create(new ObservableOnSubscribe<Response>() {

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
        }).subscribeOn(Schedulers.newThread());
        return newwork;
    }
}
