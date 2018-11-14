package com.qbase.okhttp;

import com.qbase.auxilibrary.base.QBaseBarAct;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

public class OkHttpAct extends QBaseBarAct {

    @Override
    protected void initialize() {
        initToolbar("OK Http");
    }


    private void okhttptest(){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return null;
            }
        }).build();

    }
}

