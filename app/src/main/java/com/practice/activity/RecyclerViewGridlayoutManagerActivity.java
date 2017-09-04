package com.practice.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.practice.R;

/**
 * Created by user on 2017/9/2.
 */

public class RecyclerViewGridlayoutManagerActivity extends AppCompatActivity{

    private SwipeRefreshLayout gridSwipeRefreshLayout;
    private RecyclerView gridRecyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_gridlayout_manager);

        initView();
    }

    private void initView() {
        gridSwipeRefreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_refresh_layout_grid);
        gridRecyclerview = (RecyclerView) this.findViewById(R.id.recyclerview_gridlayout);
    }
}
