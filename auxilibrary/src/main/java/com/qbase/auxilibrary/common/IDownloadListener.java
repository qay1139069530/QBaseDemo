package com.qbase.auxilibrary.common;

import com.qbase.auxilibrary.bean.DownloadBean;

/**
 * 下载回调
 */
public interface IDownloadListener {

    /**
     * 下载成功
     */
    void onDownloadSuccess(DownloadBean data);

    /**
     * 下载失败
     */
    void onDownloadFail(String data);
}
