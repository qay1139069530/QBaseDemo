package com.qbase.auxilibrary.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.qbase.auxilibrary.R;
import com.qbase.auxilibrary.app.QBaseApp;
import com.qbase.auxilibrary.common.CommonHandler;
import com.qbase.auxilibrary.common.IHandlerListener;
import com.qbase.auxilibrary.common.IQBaseCustomListener;
import com.qbase.auxilibrary.common.IQBaseViewClickListener;
import com.qbase.auxilibrary.util.ActivityStack;
import com.qbase.auxilibrary.util.StatusBarCompat;
import com.qbase.auxilibrary.util.StatusBarOption;
import com.qbase.auxilibrary.util.UIManager;
import com.qbase.auxilibrary.util.inject.QBaseInject;

/**
 * base activity
 */
public class QBaseAct extends AppCompatActivity implements IHandlerListener,
        IQBaseView, IQBaseCustomListener, IQBaseViewClickListener {

    protected QBaseApp mApp;

    protected QBaseAct _this;
    /**
     * base handler
     */
    protected CommonHandler mHandler = new CommonHandler(this);

    /**
     * tool bar
     */
    protected Toolbar mTbToolbar;

    /**
     * title
     */
    protected TextView mToolbarTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = (QBaseApp) getApplication();
        _this = this;
        QBaseInject.instance().inject(this);
        ActivityStack.getInstance().pushActivity(this);
    }

    /**
     * 设置状态栏颜色
     */
    protected void initStatusColor() {
        this.initStatusColor(mApp.getBarOption());
    }

    /**
     * 设置状态栏颜色
     *
     * @param option
     */
    protected void initStatusColor(StatusBarOption option) {
        if (option == null) {
            return;
        }
        StatusBarCompat.setColor(_this, option.getStatusColor());
    }

    /**
     * 设置toolbar title
     *
     * @param title
     */
    protected void initToolbar(String title) {
        StatusBarOption option = mApp.getBarOption();
        option.setTitle(title);
        this.initToolbar(option);
    }

    /**
     * 设置toolbar title
     *
     * @param option
     */
    protected void initToolbar(StatusBarOption option) {
        if (option == null) {
            return;
        }
        try {
            ViewStub toolbarStub = findViewById(R.id.qbase_title_viewstub);
            if (toolbarStub != null) {
                View view = toolbarStub.inflate();
                CoordinatorLayout coordinatorLayout = view.findViewById(R.id.qbase_title_coordinatorLayout);
                coordinatorLayout.setBackgroundColor(option.getStatusColor());
                mTbToolbar = view.findViewById(R.id.qbase_title_toolbar);
                mToolbarTitle = view.findViewById(R.id.qbase_title_toolbar_title);
                mTbToolbar.setTitle("");
                if (option.getPop_them() != 0) {
                    mTbToolbar.setPopupTheme(option.getPop_them());
                }
                mToolbarTitle.setText(TextUtils.isEmpty(option.getTitle()) ? "" : option.getTitle());
                if (option.getTitleTextColor() != 0) {
                    mToolbarTitle.setTextColor(option.getTitleTextColor());
                }
                if (option.getLeftImage() != 0) {
                    mTbToolbar.setNavigationIcon(option.getLeftImage());
                }
                setSupportActionBar(mTbToolbar);
                if (getSupportActionBar() != null) {
                    if (option.isShowBack()) {
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    } else {
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    }
                }
            } else {
                if (getSupportActionBar() != null) {
                    if (option.isShowBack()) {
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    } else {
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置toolbar title
     *
     * @param title
     */
    protected void initToolBarTitle(String title) {
        if (mToolbarTitle != null) {
            mToolbarTitle.setText(title);
        } else {
            initToolbar(title);
        }
    }


    /**
     * 设置fragment、view调用方法
     */
    public void setFunctionManager(String tag) {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        ActivityStack.getInstance().finishActivity(this);
        if (mHandler != null) {
            mHandler = null;
        }
        super.onDestroy();
    }


    @Override
    public void onHandlerMessage(Message msg) {
        //消息处理
    }

    /**
     * 显示空提示框
     */
    @Override
    public View showEmptyView() {
        return this.showEmptyView(UIManager.EMPTY_TEXT);
    }

    /**
     * 显示空提示框
     */
    @Override
    public View showEmptyView(String text) {
        View view = UIManager.getInstance().showEmptyView(this, text, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClickListener(v);
            }
        });
        this.hideLoadView();
        this.hideErrorView();
        return view;
    }

    /**
     * 隐藏空提示框
     */
    @Override
    public View hideEmptyView() {
        return UIManager.getInstance().hideEmptyView(this);
    }

    /**
     * 显示错误提示框
     */
    @Override
    public View showErrorView() {
        return this.showErrorView(UIManager.ERROR_TEXT);
    }

    /**
     * 显示错误提示框
     */
    @Override
    public View showErrorView(String text) {
        View view = UIManager.getInstance().showErrorView(this, text, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorViewClickListener(v);
            }
        });
        this.hideLoadView();
        this.hideEmptyView();
        return view;
    }

    /**
     * 隐藏错误提示框
     */
    @Override
    public View hideErrorView() {
        return UIManager.getInstance().hideErrorView(this);
    }

    /**
     * 显示加载提示框
     */
    @Override
    public View showLoadView() {
        return this.showLoadView(UIManager.LOAD_TEXT);
    }

    /**
     * 显示加载提示框
     */
    @Override
    public View showLoadView(String text) {
        View view = UIManager.getInstance().showLoadView(this, text, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoadViewClickListener(v);
            }
        });
        this.hideErrorView();
        this.hideEmptyView();
        return view;
    }

    /**
     * 隐藏加载提示框
     */
    @Override
    public View hideLoadView() {
        return UIManager.getInstance().hideLoadView(this);
    }

    /**
     * 空提示框点击事件
     */
    @Override
    public void onEmptyViewClickListener(View view) {
    }

    /**
     * 加载提示框点击事件
     */
    @Override
    public void onLoadViewClickListener(View view) {
    }

    /**
     * 加载提示框点击事件
     */
    @Override
    public void onErrorViewClickListener(View view) {

    }

    @Override
    public boolean onTitleBackClickListener() {
        return false;
    }

    @Override
    public void showProgressDiag(String view) {
    }

    @Override
    public void cancelProgressDiag() {
    }

    @Override
    public void afterRequestError(String text) {

    }

    @Override
    public void afterRequestSuccess() {

    }

}
