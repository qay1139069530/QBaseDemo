package com.qbase.auxilibrary.view.table;

import android.os.Build;

public class ViewUtil {

    public static final ValueAnimatorCompat.Creator DEFAULT_ANIMATOR_CREATOR
            = new ValueAnimatorCompat.Creator() {
        @Override
        public ValueAnimatorCompat createAnimator() {
            return new ValueAnimatorCompat(Build.VERSION.SDK_INT >= 12
                    ? new ValueAnimatorCompatImplHoneycombMr1()
                    : new ValueAnimatorCompatImplHoneycombMr1());
        }
    };

    public static ValueAnimatorCompat createAnimator() {
        return DEFAULT_ANIMATOR_CREATOR.createAnimator();
    }
}
