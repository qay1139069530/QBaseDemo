package com.qbase.auxilibrary.view.process;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.qbase.auxilibrary.R;

/**
 * 进度条对话框
 */
public class MyProgressDialog extends Dialog {

    private MyProgressDialog myProgressDialog;
    private Context context;
    private ProgressView mProgressView;

    public MyProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public MyProgressDialog(Context context) {
        super(context);
        this.context = context;
    }

    public synchronized MyProgressDialog creatDialog(String message) {
        return creatDialog(message, true);
    }

    /**
     * @param message
     * @return
     */
    public synchronized MyProgressDialog creatDialog(String message, boolean cancelable) {
        if (myProgressDialog == null) {
            myProgressDialog = new MyProgressDialog(context, R.style.CustomProgressDialog);
            //myProgressDialog = new MyProgressDialog(context);
            myProgressDialog.setContentView(R.layout.view_progressdialog);
            myProgressDialog.setCancelable(cancelable);
            myProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
            mProgressView = (ProgressView) myProgressDialog.findViewById(R.id.view_progressdialog_progress);
            mProgressView.start();
            // 加载提示信息
            TextView tv_message = (TextView) myProgressDialog.findViewById(R.id.view_progressdialog_message);
            if (TextUtils.isEmpty(message))
                tv_message.setVisibility(View.GONE);
            else
                tv_message.setText(message);
            myProgressDialog.setCanceledOnTouchOutside(false);
            myProgressDialog.setCancelable(false);
        }
        return myProgressDialog;
    }

    @Override
    public void cancel() {
        super.cancel();
        if (myProgressDialog != null) {
            if (mProgressView != null) {
                mProgressView.stop();
            }
            myProgressDialog.dismiss();
            myProgressDialog = null;
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (myProgressDialog != null) {
            if (mProgressView != null) {
                mProgressView.stop();
            }
            myProgressDialog.dismiss();
            myProgressDialog = null;
        }
    }

    @Override
    public void show() {
        if (myProgressDialog != null && myProgressDialog.isShowing()) {
            return;
        }
        super.show();
    }
}