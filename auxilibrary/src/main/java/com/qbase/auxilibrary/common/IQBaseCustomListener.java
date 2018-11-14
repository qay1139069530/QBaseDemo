package com.qbase.auxilibrary.common;

import android.view.View;

/**
 * Created by qay on 2016/8/17.
 * load, empty,error view load interface
 */
public interface IQBaseCustomListener extends IProgressDialogListener {
    /**
     * show empty View
     *
     * @return View
     */
    View showEmptyView();

    /**
     * show empty View
     *
     * @param text empty show content
     * @return View
     */
    View showEmptyView(String text);

    /**
     * hide empty View
     *
     * @return View
     */
    View hideEmptyView();

    /**
     * show error View
     *
     * @return View
     */
    View showErrorView();


    /**
     * show error View
     *
     * @param text empty show content
     * @return View
     */
    View showErrorView(String text);

    /**
     * hide error View
     *
     * @return View
     */
    View hideErrorView();


    /**
     * show load View
     *
     * @return View
     */
    View showLoadView();


    /**
     * show load View
     *
     * @param text empty show content
     * @return View
     */
    View showLoadView(String text);

    /**
     * hide load View
     *
     * @return View
     */
    View hideLoadView();


    /**
     * title back click listener
     */
    boolean onTitleBackClickListener();

}
