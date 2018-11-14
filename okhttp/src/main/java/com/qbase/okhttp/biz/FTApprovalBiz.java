package com.qbase.okhttp.biz;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qbase.auxilibrary.app.QBaseApp;
import com.qbase.auxilibrary.base.QBaseBiz;
import com.qbase.auxilibrary.common.IRequestListener;
import com.qbase.auxilibrary.util.UrlUtil;
import com.qbase.okhttp.bean.FTAnnexParams;
import com.qbase.okhttp.bean.FTApprovalData;
import com.qbase.okhttp.bean.FTApprovalEntity;
import com.qbase.okhttp.bean.FTApprovalParams;
import com.qbase.okhttp.params.SdParams;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * FT--Biz
 */

public class FTApprovalBiz extends QBaseBiz<QBaseApp> implements IFTApprovalBiz {


    public FTApprovalBiz(QBaseApp app) {
        super(app);
    }


    @Override
    public void getApprovalListData(@NonNull final String params, @NonNull final IRequestListener listener) {

        //检查版本
        try {
            OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
            //String url = UrlUtil.parseUrl(mApp, UrlUtil.FT_API) + SdParams.FT_GET_APPROVAL_LIST + params;
            String url = "";
            Request request = new Request.Builder().url(url).build();

            mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    listener.onRequestFail(e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        if (response.isSuccessful() && response.body() != null) {

                            String body = response.body().string();

//                            FTApprovalData<FTApprovalEntity> ftdata = new Gson().fromJson(body, new TypeToken<FTApprovalData<FTApprovalEntity>>() {
//                            }.getType());
//                            if (ftdata != null && ftdata.getRet() != null) {
//                                listener.onRequestSuccess(ftdata.getRet().getItems());
//                            } else {
//                                //检查失败
//                                listener.onRequestFail("无数据");
//                            }

                        } else {
                            //检查失败
                            listener.onRequestFail("无数据");
                        }
                    } catch (Exception e) {
                        //检查失败
                        listener.onRequestFail(e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            //检查失败
            listener.onRequestFail(e.getMessage());
        }

    }

    @Override
    public void getApprovalListDoneData(@NonNull final String params, @NonNull final IRequestListener listener) {
//        //检查版本
//        try {
//            OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
//                    .writeTimeout(10, TimeUnit.SECONDS)
//                    .connectTimeout(30, TimeUnit.SECONDS)
//                    .readTimeout(30, TimeUnit.SECONDS)
//                    .build();
//            String url = UrlUtil.parseUrl(mApp, UrlUtil.FT_API) + SdParams.FT_GET_APPROVAL_DONE_LIST + params;
//            Request request = new Request.Builder().url(url).build();
//
//            mOkHttpClient.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    e.printStackTrace();
//                    Resp<String> date = new Resp<String>();
//                    date.setErrorMessage(e.getMessage());
//                    listener.onRequestFail(date);
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    try {
//                        if (response.isSuccessful() && response.body() != null) {
//
//                            String body = response.body().string();
//                            FTApprovalEntity ftdata = new Gson().fromJson(body, new TypeToken<FTApprovalEntity>() {
//                            }.getType());
//                            if (ftdata != null) {
//                                listener.onRequestSuccess(ftdata.getItems());
//                            } else {
//                                //检查失败
//                                Resp<String> date = new Resp<String>();
//                                date.setErrorMessage("无完成审批数据");
//                                listener.onRequestFail(date);
//                            }
//
//                        } else {
//                            //检查失败
//                            Resp<String> date = new Resp<String>();
//                            date.setErrorMessage("无完成审批数据");
//                            listener.onRequestFail(date);
//                        }
//                    } catch (Exception e) {
//                        //检查失败
//                        Resp<String> date = new Resp<String>();
//                        date.setErrorMessage(e.getMessage());
//                        listener.onRequestFail(date);
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            //检查失败
//            Resp<String> date = new Resp<String>();
//            date.setErrorMessage(e.getMessage());
//            listener.onRequestFail(date);
//        }
    }

    @Override
    public void postAnnexData(@NonNull final FTAnnexParams params, @NonNull final IRequestListener listener) {
        //检查版本
//        try {
//            OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
//                    .writeTimeout(10, TimeUnit.SECONDS)
//                    .connectTimeout(30, TimeUnit.SECONDS)
//                    .readTimeout(30, TimeUnit.SECONDS)
//                    .build();
//
//            //一种：参数请求体
////            FormBody paramsBody = new FormBody.Builder()
////                    .add("fileType", String.valueOf(params.getFileType()))
////                    .build();
//
//
//            //文件请求体
//            //RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
//            //图片
//            //MediaType type = MediaType.parse("image/png");//"text/xml;charset=utf-8"
//            MediaType type = MediaType.parse("application/octet-stream");//"text/xml;charset=utf-8"
//            File file = new File(params.getFilePath());
//            RequestBody fileBody = RequestBody.create(type, file);
//
//            //混合参数和文件请求
//            RequestBody multiBody = new MultipartBody.Builder()
//                    .setType(MultipartBody.FORM)
//                    .addFormDataPart("fileType", String.valueOf(params.getFileType()))
//                    .addFormDataPart("multipartFile", params.getFileName(), fileBody)
//                    .build();
//
//            String url = UrlUtil.parseUrl(mApp, UrlUtil.FT_API) + SdParams.FT_POST_ANNEX;
//            Request request = new Request.Builder()
//                    .url(url)
//                    .post(multiBody)//传参数、文件或者混合，改一下就行请求体就行
//                    .build();
//
//            mOkHttpClient.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    e.printStackTrace();
//                    Resp<String> date = new Resp<String>();
//                    date.setErrorMessage(e.getMessage());
//                    listener.onRequestFail(date);
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    try {
//                        if (response.isSuccessful() && response.body() != null) {
//
//                            String body = response.body().string();
//                            FTApprovalData<FTAnnexBean> ftdata = new Gson().fromJson(body, new TypeToken<FTApprovalData<FTAnnexBean>>() {
//                            }.getType());
//                            if (ftdata != null && ftdata.getRet() != null) {
//                                FTAnnexBean annexBean = ftdata.getRet();
//                                annexBean.setLocalPath(params.getFilePath());
//                                listener.onRequestSuccess(annexBean);
//                            } else {
//                                //检查失败
//                                Resp<String> date = new Resp<String>();
//                                date.setErrorMessage("附件上传失败");
//                                listener.onRequestFail(date);
//                            }
//
//                        } else {
//                            //检查失败
//                            Resp<String> date = new Resp<String>();
//                            date.setErrorMessage("附件上传失败");
//                            listener.onRequestFail(date);
//                        }
//                    } catch (Exception e) {
//                        //检查失败
//                        Resp<String> date = new Resp<String>();
//                        date.setErrorMessage(e.getMessage());
//                        listener.onRequestFail(date);
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            //检查失败
//            Resp<String> date = new Resp<String>();
//            date.setErrorMessage(e.getMessage());
//            listener.onRequestFail(date);
//        }
    }


    @Override
    public void postApprovalSubmitData(@NonNull final FTApprovalParams params, @NonNull final IRequestListener listener) {
//        try {
//
//            OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
//                    .writeTimeout(10, TimeUnit.SECONDS)
//                    .connectTimeout(30, TimeUnit.SECONDS)
//                    .readTimeout(30, TimeUnit.SECONDS)
//                    .build();
//
//            //一种：参数请求体
//            Gson gson = new Gson();
////            String checkNames = gson.toJson(params.getCheckNames());
//
////            FormBody paramsBody = new FormBody.Builder()
////                    .add("id", String.valueOf(params.getId()))
////                    .add("checkContent", params.getCheckContent())
////                    .add("checkGist", params.getCheckGist())
////                    .add("checkMatter", params.getCheckMatter())
//////                    .add("checkNames", checkNames)
////                    .add("checkPictureIds", params.getCheckPictureIds())
////                    .add("checkRemark", params.getCheckRemark())
////                    .add("checkResultOpinion", params.getCheckResultOpinion())
////                    .add("checkVideoId", String.valueOf(params.getCheckVideoId()))
////                    .add("overTime", String.valueOf(params.getOverTime()))
////                    .add("returnLatitude", String.valueOf(params.getReturnLatitude()))
////                    .add("returnLongitude", String.valueOf(params.getReturnLongitude()))
////                    .add("taskCode", params.getTaskCode())
////                    .build();
//
//
//            //使用Gson将对象转换为json字符串
//            String json = gson.toJson(params);
//
//            //MediaType  设置Content-Type 标头中包含的媒体类型值
//            RequestBody paramsBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
//
//            String url = UrlUtil.parseUrl(mApp, UrlUtil.FT_API) + SdParams.FT_POST_APPROVAL_SUBMIT;
//            Request request = new Request.Builder()
//                    .url(url)
//                    .post(paramsBody)
//                    .build();
//
//            mOkHttpClient.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    e.printStackTrace();
//                    Resp<String> date = new Resp<String>();
//                    date.setErrorMessage(e.getMessage());
//                    listener.onRequestFail(date);
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    try {
//                        if (response.isSuccessful()) {
//                            listener.onRequestSuccess(null);
//                        } else {
//                            //审批提交失败
//                            Resp<String> date = new Resp<String>();
//                            date.setErrorMessage("审批提交失败");
//                            listener.onRequestFail(date);
//                        }
//                    } catch (Exception e) {
//                        //检查失败
//                        Resp<String> date = new Resp<String>();
//                        date.setErrorMessage(e.getMessage());
//                        listener.onRequestFail(date);
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            //检查失败
//            Resp<String> date = new Resp<String>();
//            date.setErrorMessage(e.getMessage());
//            listener.onRequestFail(date);
//        }
    }
}
