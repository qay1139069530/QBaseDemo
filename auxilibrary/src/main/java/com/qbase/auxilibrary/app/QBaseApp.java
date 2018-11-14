package com.qbase.auxilibrary.app;

import android.app.Application;

import com.qbase.auxilibrary.R;
import com.qbase.auxilibrary.util.PathUtil;
import com.qbase.auxilibrary.util.StatusBarOption;
import com.qbase.auxilibrary.util.inject.QBaseInject;

/**
 * 基础app
 */
public class QBaseApp extends Application {

    private StatusBarOption barOption;

    @Override
    public void onCreate() {
        super.onCreate();
        /**添加注解注册*/
        QBaseInject.Ext.init(this);
        /**添加本地文件夹*/
        PathUtil.getInstance().initDirs("qbase", "data", this);
    }

    /**
     * 获取statusbar option
     */
    public StatusBarOption getBarOption() {
        if (barOption == null) {
            barOption = getDefaultBarOption();
        }
        return barOption;
    }

    /**
     * 设置statusbar option
     */
    public void setBarOption(StatusBarOption barOption) {
        this.barOption = barOption;
    }

    /**
     * 获取默认设置的bar option
     */
    private StatusBarOption getDefaultBarOption() {
        StatusBarOption option = new StatusBarOption();
        option.setStatusColor(getResources().getColor(R.color.qbase_color_statusbar));
        option.setTitleTextColor(getResources().getColor(R.color.qbase_white));
        option.setLeftImage(R.mipmap.back);
        option.setPop_them(R.style.qbase_menu);
        option.setShowBack(true);
        return option;
    }
}
