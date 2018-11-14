package com.qbase.test;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.qbase.test.behavior.TextTabBehaviorAct;
import com.qbase.test.behavior.TitleBehaviorAct;
import com.qbase.test.behavior.TitleRecyclerBehaviorAct;

public class TestAct extends ListActivity {

    private final BehavoirItemBean[] demos = {
            //显示Item
            new BehavoirItemBean(R.string.item_behavior_title, TitleBehaviorAct.class),
            new BehavoirItemBean(R.string.item_behavior_title_recycler, TitleRecyclerBehaviorAct.class),
            new BehavoirItemBean(R.string.item_behavior_text_tab_recycler, TextTabBehaviorAct.class),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.behavior_act);
        setTitle("Behavior Test");
        ListAdapter adapter = new BehaviorItemAdapter(this, demos);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        BehavoirItemBean demo = (BehavoirItemBean) getListAdapter().getItem(position);
        if (demo.activityClass != null) {
            startActivity(new Intent(this.getApplicationContext(), demo.activityClass));
        }
    }

    private class BehaviorItemAdapter extends ArrayAdapter<BehavoirItemBean> {

        private BehaviorItemAdapter(Context context, BehavoirItemBean[] demos) {
            super(context, R.layout.behavior_item, R.id.name, demos);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            TestItemView itemView;
            if (convertView instanceof TestItemView) {
                itemView = (TestItemView) convertView;
            } else {
                itemView = new TestItemView(getContext());
            }

            BehavoirItemBean demo = getItem(position);
            if (demo != null) {
                itemView.setTitleId(demo.titleId, demo.activityClass != null);
            }
            return itemView;
        }
    }


    private class BehavoirItemBean {
        private final int titleId;
        private final Class<? extends android.app.Activity> activityClass;

        private BehavoirItemBean(int titleId, Class<? extends android.app.Activity> activityClass) {
            super();
            this.titleId = titleId;
            this.activityClass = activityClass;
        }
    }
}
