package com.qbase.okhttp.params;

/**
 * Created by qay on 2018/3/7.
 */
public class SdParams {

    /**
     * FT--列表 -- 待完成
     */
    public final static String FT_GET_APPROVAL_LIST = "/xzsp/check/task/getTasks?";
    /**
     * FT--列表 -- 已完成
     */
    public final static String FT_GET_APPROVAL_DONE_LIST = "/xzsp/check/cockpit/getAll?";

    /**
     * FT--上传附件
     */
    public final static String FT_POST_ANNEX = "/xzsp/check/file/uploadFile";

    /**
     * FT--上传附件
     */
    public final static String FT_POST_APPROVAL_SUBMIT = "/xzsp/check/record/addOrupdateCheckRecord";


    /**
     * FT--附件下载
     */
    public final static String FT_ANNEX_DOWNLOAD = "/xzsp/check/file/downloadFile?fileId=";

}
