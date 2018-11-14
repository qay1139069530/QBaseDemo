package com.qbase.auxilibrary.common;

import android.view.View;

/**
 * Created by qay on 2016/8/17.
 * load, empty,error view click event
 */
public interface IQBaseViewClickListener {
    /**
     * Empty View ClickListener
     */
    void onEmptyViewClickListener(View view);

    /**
     * Load View ClickListener
     */
    void onLoadViewClickListener(View view);

    /**
     * Error View ClickListener
     */
    void onErrorViewClickListener(View view);

}
