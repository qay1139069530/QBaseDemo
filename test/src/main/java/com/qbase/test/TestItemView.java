
package com.qbase.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public final class TestItemView extends FrameLayout {

    public TestItemView(Context context) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.behavior_item, this);
    }

    public synchronized void setTitleId(int titleId) {
        ((TextView) (findViewById(R.id.name))).setText(titleId);
    }

    public synchronized void setTitleId(int titleId, boolean issub) {
        String title = this.getResources().getString(titleId);
        if (issub) {
            ((TextView) (findViewById(R.id.name))).setText(" " + title);
        } else {
            ((TextView) (findViewById(R.id.name))).setText(title);
        }

    }
}
