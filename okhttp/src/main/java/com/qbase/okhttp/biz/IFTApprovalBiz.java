package com.qbase.okhttp.biz;

import android.support.annotation.NonNull;

import com.qbase.auxilibrary.common.IRequestListener;
import com.qbase.okhttp.bean.FTAnnexParams;
import com.qbase.okhttp.bean.FTApprovalParams;

/**
 * Created by Qay on 2017/11/29.
 */
public interface IFTApprovalBiz {

    /**
     * 获取列表
     */
    void getApprovalListData(@NonNull String params, @NonNull IRequestListener listener);

    /**
     * 获取列表--已完成
     */
    void getApprovalListDoneData(@NonNull String params, @NonNull IRequestListener listener);

    /**
     * 提交附件
     */
    void postAnnexData(@NonNull FTAnnexParams params, @NonNull IRequestListener listener);


    /**
     * 提交更改结果
     */
    void postApprovalSubmitData(@NonNull FTApprovalParams params, @NonNull IRequestListener listener);
}
