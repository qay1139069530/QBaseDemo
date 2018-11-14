package com.qbase.demo;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.qbase.rxjava.RxJavaAct;
import com.qbase.test.TestAct;

public class MainAct extends ListActivity {

    private final MainItemBean[] demos = {
            //显示Item
            new MainItemBean(R.string.RxJava, RxJavaAct.class),
            new MainItemBean(R.string.Test, TestAct.class)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
        setTitle("QBase Demo");
        ListAdapter adapter = new MainItemAdapter(this, demos);
        setListAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        MainItemBean demo = (MainItemBean) getListAdapter().getItem(position);
        if (demo.activityClass != null) {
            startActivity(new Intent(this.getApplicationContext(), demo.activityClass));
        }
    }

    private class MainItemAdapter extends ArrayAdapter<MainItemBean> {

        private MainItemAdapter(Context context, MainItemBean[] demos) {
            super(context, R.layout.main_item, R.id.title, demos);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            MainItemView itemView;
            if (convertView instanceof MainItemView) {
                itemView = (MainItemView) convertView;
            } else {
                itemView = new MainItemView(getContext());
            }

            MainItemBean demo = getItem(position);
            if (demo != null) {
                itemView.setTitleId(demo.titleId, demo.activityClass != null);
            }
            return itemView;
        }
    }


    private class MainItemBean {
        private final int titleId;
        private final Class<? extends android.app.Activity> activityClass;

        private MainItemBean(int titleId, Class<? extends android.app.Activity> activityClass) {
            super();
            this.titleId = titleId;
            this.activityClass = activityClass;
        }
    }
}
