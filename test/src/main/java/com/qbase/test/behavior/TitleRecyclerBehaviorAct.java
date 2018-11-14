package com.qbase.test.behavior;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qbase.auxilibrary.base.QBaseBarAct;
import com.qbase.test.R;
import com.qbase.test.adapter.TextAdapter;

import java.util.ArrayList;
import java.util.List;

public class TitleRecyclerBehaviorAct extends QBaseBarAct {

    private RecyclerView mRecyclerView;
    private TextAdapter mTextAdapter;

    private List<String> ItemDatas = new ArrayList<>();

    @Override
    protected void initialize() {
        setContentView(R.layout.titlerecycler_behavior_act);
        initToolbar("Title Recycler Behavior");
        initView();
        initRecyclerView();
    }


    private void initView() {
        mRecyclerView = findViewById(R.id.title_behavior_act_recyclerView);
    }

    private void initRecyclerView() {

        for (int i = 0; i < 20; i++) {
            ItemDatas.add("test : " + i);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTextAdapter = new TextAdapter(ItemDatas);
        mRecyclerView.setAdapter(mTextAdapter);
    }
}
