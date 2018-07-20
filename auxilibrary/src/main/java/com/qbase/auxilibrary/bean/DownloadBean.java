package com.qbase.auxilibrary.bean;


import java.io.Serializable;

public class DownloadBean implements Serializable {


    /**
     * 链接地址
     */
    private String urlString;
    /**
     * 文件id
     */
    private long attachmentId;
    /**
     * 下载文件名称
     */
    private String fileName;

    /**
     * 下载文件路径
     */
    private String filePath;

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
