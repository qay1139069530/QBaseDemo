package com.qbase.auxilibrary.util;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qbase.auxilibrary.R;

public class UIManager {

    public static final String EMPTY_TEXT = "暂无数据，点击再加载";
    public static final String LOAD_TEXT = "加载中...";
    public static final String ERROR_TEXT = "加载失败，点击重试";
    private static UIManager mInstace;

    public UIManager() {
    }

    public synchronized static UIManager getInstance() {
        if (mInstace == null) {
            mInstace = new UIManager();
        }
        return mInstace;
    }

    /*******************************************EmptyView****************************************************/
    /**
     * show empty View
     *
     * @param activity show empty View by activity
     * @return View
     */
    public View showEmptyView(@NonNull Activity activity) {
        return this.showEmptyView(activity, EMPTY_TEXT, null);
    }

    /**
     * show empty View
     *
     * @param activity show empty View by activity
     * @param msg      show empty view show text
     * @return View
     */
    public View showEmptyView(@NonNull Activity activity, String msg) {
        return this.showEmptyView(activity, msg, null);
    }

    /**
     * show empty View
     *
     * @param activity show empty View by activity
     * @param listener show empty view clickListener
     * @return View
     */
    public View showEmptyView(@NonNull Activity activity, View.OnClickListener listener) {
        return this.showEmptyView(activity, EMPTY_TEXT, listener);
    }

    /**
     * show empty View
     *
     * @param activity show empty View by activity
     * @param msg      show empty view show text
     * @param listener show empty view clickListener
     * @return View
     */
    public View showEmptyView(@NonNull Activity activity, String msg, View.OnClickListener listener) {
        View error = null;
        if (activity != null) {
            ViewStub errorStub = ((ViewStub) activity.findViewById(R.id.view_emptystub));
            if (errorStub != null) {
                error = errorStub.inflate();
                LinearLayout mLlContent = (LinearLayout) activity.findViewById(R.id.view_empty_content);
                mLlContent.setOnClickListener(listener);
                TextView textView = (TextView) error.findViewById(R.id.view_empty_text);
                textView.setText(msg);
            } else {
                LinearLayout mLlContent = (LinearLayout) activity.findViewById(R.id.view_empty_content);
                TextView textView = (TextView) activity.findViewById(R.id.view_empty_text);
                if (textView != null) {
                    textView.setText(msg);
                    textView.setVisibility(View.VISIBLE);
                }
                if (mLlContent != null) {
                    mLlContent.setVisibility(View.VISIBLE);
                    error = mLlContent;
                }
            }
        }
        return error;
    }

    /**
     * show empty View
     *
     * @param view show empty View by fragment or custom view
     * @return View
     */
    public View showEmptyView(@NonNull View view) {
        return this.showEmptyView(view, EMPTY_TEXT, null);
    }

    /**
     * show empty View
     *
     * @param view     show empty View by fragment or custom view
     * @param listener show empty view clickListener
     * @return View
     */
    public View showEmptyView(@NonNull View view, View.OnClickListener listener) {
        return this.showEmptyView(view, EMPTY_TEXT, listener);
    }

    /**
     * show empty View
     *
     * @param view show empty View by fragment or custom view
     * @param msg  show empty view show text
     * @return View
     */
    public View showEmptyView(@NonNull View view, String msg) {
        return this.showEmptyView(view, msg, null);
    }

    /**
     * show empty View
     *
     * @param view     show empty View by fragment or custom view
     * @param msg      show empty view show text
     * @param listener show empty view clickListener
     * @return View
     */
    public View showEmptyView(@NonNull View view, String msg, View.OnClickListener listener) {
        View error = null;
        if (view != null) {
            ViewStub errorStub = (ViewStub) view.findViewById(R.id.view_emptystub);
            if (errorStub != null) {
                error = errorStub.inflate();
                LinearLayout mLlContent = (LinearLayout) error.findViewById(R.id.view_empty_content);
                mLlContent.setOnClickListener(listener);
                TextView textView = (TextView) error.findViewById(R.id.view_empty_text);
                textView.setText(msg);
            } else {
                LinearLayout mLlContent = (LinearLayout) view.findViewById(R.id.view_empty_content);
                TextView textView = (TextView) view.findViewById(R.id.view_empty_text);
                if (textView != null) {
                    textView.setText(msg);
                    textView.setVisibility(View.VISIBLE);
                }
                if (mLlContent != null) {
                    mLlContent.setVisibility(View.VISIBLE);
                    error = mLlContent;
                }
            }
        }
        return error;
    }

    /**
     * hide empty View
     *
     * @param activity hide empty View by activity
     * @return View
     */
    public View hideEmptyView(@NonNull Activity activity) {
        View error = null;
        if (activity != null) {
            ViewStub errorStub = ((ViewStub) activity.findViewById(R.id.view_emptystub));
            if (errorStub != null) {
                error = errorStub.inflate();
                error.setVisibility(View.GONE);
            } else {
                LinearLayout mLlContent = (LinearLayout) activity.findViewById(R.id.view_empty_content);
                if (mLlContent != null) {
                    mLlContent.setVisibility(View.GONE);
                    error = mLlContent;
                }
            }
        }
        return error;
    }

    /**
     * hide empty View
     *
     * @param view hide empty View by fragment or custom view
     * @return View
     */
    public View hideEmptyView(@NonNull View view) {
        View error = null;
        if (view != null) {
            ViewStub errorStub = ((ViewStub) view.findViewById(R.id.view_emptystub));
            if (errorStub != null) {
                error = errorStub.inflate();
                error.setVisibility(View.GONE);
            } else {
                LinearLayout mLlContent = (LinearLayout) view.findViewById(R.id.view_empty_content);
                if (mLlContent != null) {
                    mLlContent.setVisibility(View.GONE);
                    error = mLlContent;
                }
            }
        }
        return error;
    }
    /*******************************************EmptyView****************************************************/

    /*******************************************ErrorView****************************************************/
    /**
     * show error View
     *
     * @param activity show error View by activity
     * @return View
     */
    public View showErrorView(@NonNull Activity activity) {
        return this.showErrorView(activity, ERROR_TEXT, null);
    }

    /**
     * show error View
     *
     * @param activity show error View by activity
     * @param msg      show error view show text
     * @return View
     */
    public View showErrorView(@NonNull Activity activity, String msg) {
        return this.showErrorView(activity, msg, null);
    }

    /**
     * show error View
     *
     * @param activity show error View by activity
     * @param listener show error view clickListener
     * @return View
     */
    public View showErrorView(@NonNull Activity activity, View.OnClickListener listener) {
        return this.showErrorView(activity, ERROR_TEXT, listener);
    }

    /**
     * show error View
     *
     * @param activity show error View by activity
     * @param msg      show error view show text
     * @param listener show error view clickListener
     * @return View
     */
    public View showErrorView(@NonNull Activity activity, String msg, View.OnClickListener listener) {
        View error = null;
        if (activity != null) {
            ViewStub errorStub = ((ViewStub) activity.findViewById(R.id.view_errorstub));
            if (errorStub != null) {
                error = errorStub.inflate();
                LinearLayout mLlContent = (LinearLayout) activity.findViewById(R.id.view_error_content);
                mLlContent.setOnClickListener(listener);
                TextView textView = (TextView) error.findViewById(R.id.view_error_text);
                textView.setText(msg);
            } else {
                LinearLayout mLlContent = (LinearLayout) activity.findViewById(R.id.view_error_content);
                TextView textView = (TextView) activity.findViewById(R.id.view_error_text);
                if (textView != null) {
                    textView.setText(msg);
                    textView.setVisibility(View.VISIBLE);
                }
                if (mLlContent != null) {
                    mLlContent.setVisibility(View.VISIBLE);
                    error = mLlContent;
                }
            }
        }
        return error;
    }

    /**
     * show error View
     *
     * @param view show error View by fragment or custom view
     * @return View
     */
    public View showErrorView(@NonNull View view) {
        return this.showErrorView(view, ERROR_TEXT, null);
    }

    /**
     * show error View
     *
     * @param view     show error View by fragment or custom view
     * @param listener show error view clickListener
     * @return View
     */
    public View showErrorView(@NonNull View view, View.OnClickListener listener) {
        return this.showErrorView(view, ERROR_TEXT, listener);
    }

    /**
     * show error View
     *
     * @param view show error View by fragment or custom view
     * @param msg  show error view show text
     * @return View
     */
    public View showErrorView(@NonNull View view, String msg) {
        return this.showErrorView(view, msg, null);
    }

    /**
     * show error View
     *
     * @param view     show error View by fragment or custom view
     * @param msg      show error view show text
     * @param listener show error view clickListener
     * @return View
     */
    public View showErrorView(@NonNull View view, String msg, View.OnClickListener listener) {
        View error = null;
        if (view != null) {
            ViewStub errorStub = (ViewStub) view.findViewById(R.id.view_errorstub);
            if (errorStub != null) {
                error = errorStub.inflate();
                LinearLayout mLlContent = (LinearLayout) error.findViewById(R.id.view_error_content);
                mLlContent.setOnClickListener(listener);
                TextView textView = (TextView) error.findViewById(R.id.view_error_text);
                textView.setText(msg);
            } else {
                LinearLayout mLlContent = (LinearLayout) view.findViewById(R.id.view_error_content);
                TextView textView = (TextView) view.findViewById(R.id.view_error_text);
                if (textView != null) {
                    textView.setText(msg);
                    textView.setVisibility(View.VISIBLE);
                }
                if (mLlContent != null) {
                    mLlContent.setVisibility(View.VISIBLE);
                    error = mLlContent;
                }
            }
        }
        return error;
    }

    /**
     * hide error View
     *
     * @param activity hide error View by activity
     * @return View
     */
    public View hideErrorView(@NonNull Activity activity) {
        View error = null;
        if (activity != null) {
            ViewStub errorStub = ((ViewStub) activity.findViewById(R.id.view_errorstub));
            if (errorStub != null) {
                error = errorStub.inflate();
                error.setVisibility(View.GONE);
            } else {
                LinearLayout mLlContent = (LinearLayout) activity.findViewById(R.id.view_error_content);
                if (mLlContent != null) {
                    mLlContent.setVisibility(View.GONE);
                    error = mLlContent;
                }
            }
        }
        return error;
    }

    /**
     * hide error View
     *
     * @param view hide error View by fragment or custom view
     * @return View
     */
    public View hideErrorView(@NonNull View view) {
        View error = null;
        if (view != null) {
            ViewStub errorStub = ((ViewStub) view.findViewById(R.id.view_errorstub));
            if (errorStub != null) {
                error = errorStub.inflate();
                error.setVisibility(View.GONE);
            } else {
                LinearLayout mLlContent = (LinearLayout) view.findViewById(R.id.view_error_content);
                if (mLlContent != null) {
                    mLlContent.setVisibility(View.GONE);
                    error = mLlContent;
                }
            }
        }
        return error;
    }
    /*******************************************ErrorView****************************************************/


    /*******************************************loading view*************************************************/
    /**
     * show ic_load View
     *
     * @param activity show ic_load View by activity
     * @return View
     */
    public View showLoadView(@NonNull Activity activity) {
        return this.showLoadView(activity, LOAD_TEXT, null);
    }

    /**
     * show ic_load View
     *
     * @param activity show ic_load View by activity
     * @param listener show ic_load View clickListener
     * @return View
     */
    public View showLoadView(@NonNull Activity activity, View.OnClickListener listener) {
        return this.showLoadView(activity, LOAD_TEXT, listener);
    }

    /**
     * show ic_load View
     *
     * @param activity show ic_load View by activity
     * @param msg      show ic_load View show text
     * @return View
     */
    public View showLoadView(@NonNull Activity activity, String msg) {
        return this.showLoadView(activity, msg, null);
    }

    /**
     * show ic_load View
     *
     * @param activity show ic_load View by activity
     * @param msg      show ic_load View show text
     * @param listener show ic_load View clickListener
     * @return View
     */
    public View showLoadView(@NonNull Activity activity, String msg, View.OnClickListener listener) {
        View load = null;
        if (activity != null) {
            ViewStub loadStub = ((ViewStub) activity.findViewById(R.id.view_loadstub));
            if (loadStub != null) {
                load = loadStub.inflate();
                LinearLayout mLlContent = (LinearLayout) activity.findViewById(R.id.view_load_content);
                mLlContent.setOnClickListener(listener);
                TextView textView = (TextView) activity.findViewById(R.id.view_load_text);
                textView.setText(msg);
            } else {
                LinearLayout mLlContent = (LinearLayout) activity.findViewById(R.id.view_load_content);
                TextView textView = (TextView) activity.findViewById(R.id.view_load_text);
                textView.setText(msg);
                if (mLlContent != null) {
                    mLlContent.setVisibility(View.VISIBLE);
                    load = mLlContent;
                }
            }
        }
        return load;
    }


    /**
     * show ic_load View
     *
     * @param view show ic_load View by fragment or custom view
     * @return View
     */
    public View showLoadView(@NonNull View view) {
        return this.showLoadView(view, LOAD_TEXT, null);
    }

    /**
     * show ic_load View
     *
     * @param view     show ic_load View by fragment or custom view
     * @param listener show ic_load View clickListener
     * @return View
     */
    public View showLoadView(@NonNull View view, View.OnClickListener listener) {
        return this.showLoadView(view, LOAD_TEXT, listener);
    }

    /**
     * show ic_load View
     *
     * @param view show ic_load View by fragment or custom view
     * @param msg  show ic_load View show text
     * @return View
     */
    public View showLoadView(@NonNull View view, String msg) {
        return this.showLoadView(view, msg, null);
    }


    /**
     * show ic_load View
     *
     * @param view     show ic_load View by fragment or custom view
     * @param msg      show ic_load View show text
     * @param listener show ic_load View clickListener
     * @return View
     */
    public View showLoadView(@NonNull View view, String msg, View.OnClickListener listener) {
        View load = null;
        if (view != null) {
            ViewStub loadStub = ((ViewStub) view.findViewById(R.id.view_loadstub));
            if (loadStub != null) {
                load = loadStub.inflate();
                LinearLayout mLlContent = (LinearLayout) view.findViewById(R.id.view_load_content);
                mLlContent.setOnClickListener(listener);
                TextView textView = (TextView) view.findViewById(R.id.view_load_text);
                textView.setText(msg);
            } else {
                LinearLayout mLlContent = (LinearLayout) view.findViewById(R.id.view_load_content);
                TextView textView = (TextView) view.findViewById(R.id.view_load_text);
                textView.setText(msg);
                if (mLlContent != null) {
                    mLlContent.setVisibility(View.VISIBLE);
                    load = mLlContent;
                }
            }
        }
        return load;
    }

    /**
     * hide ic_load view
     *
     * @param activity hide ic_load view by activity
     * @return View
     */
    public View hideLoadView(@NonNull Activity activity) {
        View load = null;
        if (activity != null) {
            ViewStub loadStub = ((ViewStub) activity.findViewById(R.id.view_loadstub));
            if (loadStub != null) {
                load = loadStub.inflate();
                load.setVisibility(View.GONE);
            } else {
                LinearLayout mLlContent = (LinearLayout) activity.findViewById(R.id.view_load_content);
                if (mLlContent != null) {
                    mLlContent.setVisibility(View.GONE);
                    load = mLlContent;
                }
            }
        }
        return load;
    }

    /**
     * hide ic_load view
     *
     * @param view hide ic_load view by fragment or custom view
     * @return View
     */
    public View hideLoadView(@NonNull View view) {
        View load = null;
        if (view != null) {
            ViewStub loadStub = ((ViewStub) view.findViewById(R.id.view_loadstub));
            if (loadStub != null) {
                load = loadStub.inflate();
                load.setVisibility(View.GONE);
            } else {
                LinearLayout mLlContent = (LinearLayout) view.findViewById(R.id.view_load_content);
                if (mLlContent != null) {
                    mLlContent.setVisibility(View.GONE);
                    load = mLlContent;
                }
            }
        }
        return load;
    }
    /*******************************************loading view*************************************************/

}
