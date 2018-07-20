package com.qbase.auxilibrary.task;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.qbase.auxilibrary.bean.DownloadBean;
import com.qbase.auxilibrary.common.IDownloadListener;
import com.qbase.auxilibrary.params.AuxiliaryParams;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by qay
 * 异步文件下载
 */
public class DownloadTask extends AsyncTask<Void, Void, Boolean> {
    private DownloadBean downloadBean;
    private ProgressDialog pBarLogin;
    private Context _this;

    private IDownloadListener listener;

    public DownloadTask(DownloadBean downloadBean, Context context, IDownloadListener listener) {
        _this = context;
        this.listener = listener;
        this.downloadBean = downloadBean;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (pBarLogin == null && _this != null) {
            // 进度条
            pBarLogin = new ProgressDialog(_this, AlertDialog.THEME_HOLO_LIGHT);
            pBarLogin.setMessage("正在下载,请稍后...");
            pBarLogin.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pBarLogin.setCancelable(false);
        }
        pBarLogin.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        if (downloadBean != null) {
            FileOutputStream fileOutputStream = null;
            InputStream inputStream = null;
            File tempFile = null;
            try {
                URL url = new URL(downloadBean.getUrlString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5 * 1000);
                conn.setRequestMethod("GET");
                inputStream = conn.getInputStream();
                if (inputStream != null) {
                    File rootFile = new File(AuxiliaryParams.SD_DOWNLOAD);
                    System.out.println("文件路径" + rootFile.getAbsolutePath());

                    if (!rootFile.exists() && !rootFile.isDirectory())
                        rootFile.mkdir();

                    tempFile = new File(downloadBean.getFilePath(), downloadBean.getFileName());
                    fileOutputStream = new FileOutputStream(tempFile);
                    byte[] buf = new byte[2048];
                    int ch;
                    while ((ch = inputStream.read(buf)) != -1) {
                        fileOutputStream.write(buf, 0, ch);
                    }
                    fileOutputStream.flush();
                    return true;
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                if (tempFile != null && tempFile.exists())
                    tempFile.delete();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        _this = null;
        if (null != pBarLogin) {
            pBarLogin.dismiss();
        }
        if (result != null && result) {
            if (listener != null) {
                listener.onDownloadSuccess(downloadBean);
            }
        } else {
            if (listener != null) {
                listener.onDownloadFail("下载失败");
            }
        }
    }
}
