package com.qbase.test.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qbase.auxilibrary.base.QBaseFg;
import com.qbase.test.R;
import com.qbase.test.adapter.TextAdapter;

import java.util.ArrayList;
import java.util.List;

public class Test02Fragmnet extends QBaseFg {

    private RecyclerView mRecyclerView;

    private TextAdapter mTextAdapter;

    private List<String> ItemDatas = new ArrayList<>();

    public static Test02Fragmnet newInstance() {
        return new Test02Fragmnet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment02, container, false);
        mRecyclerView = view.findViewById(R.id.test_fragment02_recyclerView);
        return view;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initialize() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        for (int i = 0; i < 20; i++) {
            ItemDatas.add("test Fragment02 : " + i);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTextAdapter = new TextAdapter(ItemDatas);
        mRecyclerView.setAdapter(mTextAdapter);
    }
}
