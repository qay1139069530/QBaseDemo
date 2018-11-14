package com.qbase.auxilibrary.common;

/**
 * Created by qay on 2016/8/17.
 */
public interface IProgressDialogListener {
    /**
     * show load dialog
     */
    void showProgressDiag(String view);

    /**
     * cancel load dialog
     */
    void cancelProgressDiag();

}
