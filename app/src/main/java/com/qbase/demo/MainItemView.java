
package com.qbase.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public final class MainItemView extends FrameLayout {

    public MainItemView(Context context) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.main_item, this);
    }

    public synchronized void setTitleId(int titleId) {
        ((TextView) (findViewById(R.id.title))).setText(titleId);
    }

    public synchronized void setTitleId(int titleId, boolean issub) {
        String title = this.getResources().getString(titleId);
        if (issub) {
            ((TextView) (findViewById(R.id.title))).setText("         " + title);
        } else {
            ((TextView) (findViewById(R.id.title))).setText(title);
        }

    }
}
