package com.qbase.auxilibrary.common;

import android.app.Activity;
import android.view.WindowManager;
import android.widget.PopupWindow.OnDismissListener;

/**
 * Dismiss 阴影消失监听
 */
public class CommonDismissListener implements OnDismissListener {

    private Activity context;
    private float bgAlpha;

    public CommonDismissListener(Activity context) {
        this.context = context;
        this.bgAlpha = 1f;
    }

    public CommonDismissListener(Activity context, float bgAlpha) {
        this.context = context;
        this.bgAlpha = bgAlpha;
    }

    @Override
    public void onDismiss() {
        try {
            WindowManager.LayoutParams lp = context.getWindow().getAttributes();
            lp.alpha = bgAlpha; //0.0-1.0  
            context.getWindow().setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
