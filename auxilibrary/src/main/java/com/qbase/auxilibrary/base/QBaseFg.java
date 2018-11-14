package com.qbase.auxilibrary.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.qbase.auxilibrary.app.QBaseApp;
import com.qbase.auxilibrary.common.CommonHandler;
import com.qbase.auxilibrary.common.IHandlerListener;
import com.qbase.auxilibrary.common.IQBaseCustomListener;
import com.qbase.auxilibrary.common.IQBaseViewClickListener;
import com.qbase.auxilibrary.util.ActivityStack;
import com.qbase.auxilibrary.util.UIManager;
import com.qbase.auxilibrary.util.permission.PermissionGrant;
import com.qbase.auxilibrary.util.permission.PermissionUtil;


public abstract class QBaseFg extends android.support.v4.app.Fragment implements IHandlerListener, PermissionGrant, IQBaseViewClickListener, IQBaseView,IQBaseCustomListener {

    protected final String TAG = this.getClass().getSimpleName();

    protected QBaseApp mApp;

    protected View mView;
    /**
     * base handler
     */
    protected CommonHandler mHandler = new CommonHandler(this);


    public QBaseFg() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mApp = (QBaseApp) (context.getApplicationContext());
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initialize();
    }
    /**初始化*/
    protected abstract void initView();
    /**初始化*/
    protected abstract void initialize();

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler = null;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    /**
     * 更新数据
     */
    public void onUpdateFragmentData() {
    }

    /**
     * 处理Handler message
     */
    @Override
    public void onHandlerMessage(Message msg) {

    }

    /**
     * activity judge
     */
    protected void judgeAct(Class<? extends Context> cls) {
        judgeAct(null, cls);
    }

    /**
     * activity judge
     */
    protected void judgeAct(Bundle bundle, Class<? extends Context> cls) {
        if (getActivity() == null) {
            return;
        }
        ActivityStack.getInstance().finishAppointActivity(cls);
        Intent login = new Intent(getActivity(), cls);
        if (bundle != null) {
            login.putExtras(bundle);
        }
        startActivity(login);
    }

    /**
     * activity judge for result
     */
    protected void judgeActResult(Class<? extends Context> cls, int code) {
        judgeActResult(null, cls, code);
    }

    /**
     * activity judge for result
     */
    protected void judgeActResult(Bundle bundle, Class<? extends Context> cls, int code) {
        if (getActivity() == null) {
            return;
        }
        ActivityStack.getInstance().finishAppointActivity(cls);
        Intent intent = new Intent(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, code);
    }

    /**
     * show toast
     */
    protected synchronized void showToast(String text) {
        cancelProgressDiag();
        if (!TextUtils.isEmpty(text) && getActivity() != null) {
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * 显示空提示框
     */
    @Override
    public View showEmptyView() {
        return showEmptyView(UIManager.EMPTY_TEXT);
    }

    /**
     * 显示空提示框
     */
    @Override
    public View showEmptyView(String text) {
        View emptyView = UIManager.getInstance().showEmptyView(getView(), text, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClickListener(v);
            }
        });
        hideLoadView();
        hideErrorView();
        return emptyView;
    }

    @Override
    public void showProgressDiag(String view) {

    }

    @Override
    public void cancelProgressDiag() {

    }

    /**
     * 隐藏空提示框
     */
    @Override
    public View hideEmptyView() {
        return UIManager.getInstance().hideEmptyView(mView);
    }

    /**
     * 显示错误提示框
     */
    @Override
    public View showErrorView() {
        return showErrorView(UIManager.ERROR_TEXT);
    }

    /**
     * 显示错误提示框
     */
    @Override
    public View showErrorView(String text) {
        View errorView = UIManager.getInstance().showErrorView(mView, text, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorViewClickListener(v);
            }
        });
        hideLoadView();
        hideEmptyView();
        return errorView;
    }

    /**
     * 隐藏错误提示框
     */
    @Override
    public View hideErrorView() {
        return UIManager.getInstance().hideErrorView(mView);
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
        View loadView = UIManager.getInstance().showLoadView(mView, text, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoadViewClickListener(v);
            }
        });
        hideErrorView();
        hideEmptyView();
        return loadView;
    }

    /**
     * 隐藏加载提示框
     */
    @Override
    public View hideLoadView() {
        return UIManager.getInstance().hideLoadView(mView);
    }


    @Override
    public boolean onTitleBackClickListener() {
        return false;
    }

    /**
     * deal with error load
     */
    @Override
    public void afterRequestError(String text) {
        showToast(text);
    }

    @Override
    public void afterRequestSuccess() {

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
     * 错误提示框点击事件
     */
    @Override
    public void onErrorViewClickListener(View view) {
    }

    @Override
    public void onPermissionGranted(int requestCode) {
//        switch (requestCode) {
//            case PermissionUtil.CODE_RECORD_AUDIO:
//                Toast.makeText(_this, "Result Permission Grant CODE_RECORD_AUDIO", Toast.LENGTH_SHORT).show();
//                break;
//            case PermissionUtil.CODE_GET_ACCOUNTS:
//                Toast.makeText(_this, "Result Permission Grant CODE_GET_ACCOUNTS", Toast.LENGTH_SHORT).show();
//                break;
//            case PermissionUtil.CODE_READ_PHONE_STATE:
//                Toast.makeText(_this, "Result Permission Grant CODE_READ_PHONE_STATE", Toast.LENGTH_SHORT).show();
//                break;
//            case PermissionUtil.CODE_CALL_PHONE:
//                Toast.makeText(this, "Result Permission Grant CODE_CALL_PHONE", Toast.LENGTH_SHORT).show();
//                break;
//            case PermissionUtil.CODE_CAMERA:
//                Toast.makeText(_this, "Result Permission Grant CODE_CAMERA", Toast.LENGTH_SHORT).show();
//                break;
//            case PermissionUtil.CODE_ACCESS_FINE_LOCATION:
//                Toast.makeText(_this, "Result Permission Grant CODE_ACCESS_FINE_LOCATION", Toast.LENGTH_SHORT).show();
//                break;
//            case PermissionUtil.CODE_ACCESS_COARSE_LOCATION:
//                Toast.makeText(_this, "Result Permission Grant CODE_ACCESS_COARSE_LOCATION", Toast.LENGTH_SHORT).show();
//                break;
//            case PermissionUtil.CODE_READ_EXTERNAL_STORAGE:
//                Toast.makeText(_this, "Result Permission Grant CODE_READ_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
//                break;
//            case PermissionUtil.CODE_WRITE_EXTERNAL_STORAGE:
//                Toast.makeText(_this, "Result Permission Grant CODE_WRITE_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//        }
    }

    /**
     * 检查权限
     */
    protected boolean checkPermission(int permission) {
        boolean isRequest = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionUtil.requestPermission(getActivity(), permission, this);
            isRequest = true;
        }
        return isRequest;
    }

    /**
     * Called when the 'show camera' button is clicked.
     * Callback is defined in resource layout definition.
     */
    protected void showCamera(View view) {
        PermissionUtil.requestPermission(getActivity(), PermissionUtil.CODE_CAMERA, this);
    }

    protected void getAccounts(View view) {
        PermissionUtil.requestPermission(getActivity(), PermissionUtil.CODE_GET_ACCOUNTS, this);
    }

    protected void callPhone(View view) {
        PermissionUtil.requestPermission(getActivity(), PermissionUtil.CODE_CALL_PHONE, this);
    }

    protected void readPhoneState(View view) {
        PermissionUtil.requestPermission(getActivity(), PermissionUtil.CODE_READ_PHONE_STATE, this);
    }

    protected void accessFineLocation(View view) {
        PermissionUtil.requestPermission(getActivity(), PermissionUtil.CODE_ACCESS_FINE_LOCATION, this);
    }

    protected void accessCoarseLocation(View view) {
        PermissionUtil.requestPermission(getActivity(), PermissionUtil.CODE_ACCESS_COARSE_LOCATION, this);
    }

    protected void readExternalStorage() {
        PermissionUtil.requestPermission(getActivity(), PermissionUtil.CODE_READ_EXTERNAL_STORAGE, this);
    }

    protected void writeExternalStorage() {
        //PermissionUtil.requestPermission(this, PermissionUtil.CODE_WRITE_EXTERNAL_STORAGE, this);

        PermissionUtil.requestMultiPermissions(getActivity(), PermissionUtil.CODE_MULTI_PERMISSION, this);
    }

    protected void recordStorage() {
        PermissionUtil.requestMultiPermissions(getActivity(), PermissionUtil.CODE_SELF_MULTI_PERMISSION, new int[]{PermissionUtil.CODE_READ_EXTERNAL_STORAGE, PermissionUtil.CODE_WRITE_EXTERNAL_STORAGE}, this);
        //PermissionUtil.requestPermission(this, PermissionUtil.CODE_RECORD_AUDIO, this);
    }

    protected void recordAudio() {
        PermissionUtil.requestPermission(getActivity(), PermissionUtil.CODE_RECORD_AUDIO, this);
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionUtil.requestPermissionsResult(getActivity(), requestCode, permissions, grantResults, this);
    }
}
