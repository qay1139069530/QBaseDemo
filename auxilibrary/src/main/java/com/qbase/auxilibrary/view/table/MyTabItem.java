package com.qbase.auxilibrary.view.table;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dell on 2016/10/9.
 */
class MyTabItem extends View {
    final CharSequence mText;
    final Drawable mIcon;
    final int mCustomLayout;

    @Override
    public boolean isInEditMode() {
        return super.isInEditMode();
    }

    public MyTabItem(Context context) {
        this(context, null);
    }

    public MyTabItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs,
                android.support.design.R.styleable.TabItem);
        mText = a.getText(android.support.design.R.styleable.TabItem_android_text);
        mIcon = a.getDrawable(android.support.design.R.styleable.TabItem_android_icon);
        mCustomLayout = a.getResourceId(android.support.design.R.styleable.TabItem_android_layout, 0);
        a.recycle();
    }
}
