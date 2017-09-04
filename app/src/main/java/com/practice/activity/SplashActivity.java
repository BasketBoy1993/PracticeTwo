package com.practice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.practice.R;

/**
 * Created by user on 2017/8/13.
 */

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private Button chatBtn;
    private Button customCameraBtn;
    private Button autoFitPopupwindowBtn;
    private Button getTouchLocationBtn;
    private Button notificationBtn;
    private Button recyclerViewLinearlayoutBtn;
    private Button recyclerViewGridlayoutBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_splash);

        initView();
    }

    private void initView() {
        chatBtn = (Button) findViewById(R.id.btn_chat);
        chatBtn.setOnClickListener(this);
        customCameraBtn = (Button) findViewById(R.id.btn_custom_camera);
        customCameraBtn.setOnClickListener(this);
        autoFitPopupwindowBtn = (Button) findViewById(R.id.btn_auto_fit_popupwindow);
        autoFitPopupwindowBtn.setOnClickListener(this);
        getTouchLocationBtn = (Button) findViewById(R.id.btn_get_touch_location);
        getTouchLocationBtn.setOnClickListener(this);
        notificationBtn = (Button) findViewById(R.id.btn_notification);
        notificationBtn.setOnClickListener(this);
        recyclerViewLinearlayoutBtn = (Button) findViewById(R.id.btn_recyclerview_linearlayourmanager);
        recyclerViewLinearlayoutBtn.setOnClickListener(this);
        recyclerViewGridlayoutBtn = (Button) findViewById(R.id.btn_recyclerview_gridlayoutmanager);
        recyclerViewGridlayoutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.btn_swipe_refresh:
                intent.setClass(this, MainActivity.class);
                break;
            case R.id.btn_chat:
                intent.setClass(this, ChatActivity.class);
                break;
            case R.id.btn_custom_camera:
                intent.setClass(this, CustomCameraActivity.class);
                break;
            case R.id.btn_auto_fit_popupwindow:
                intent.setClass(this, ShowPopupWindowWithSoftInputActivity.class);
                break;
            case R.id.btn_get_touch_location:
                intent.setClass(this, GetTouchLocationActivity.class);
                break;
            case R.id.btn_notification:
                intent.setClass(this, NotificationActivity.class);
                break;
            case R.id.btn_recyclerview_linearlayourmanager:
                intent.setClass(this, RecyclerviewLinearlayoutManagerActivity.class);
                break;
            case R.id.btn_recyclerview_gridlayoutmanager:
                intent.setClass(this, RecyclerViewGridlayoutManagerActivity.class);
                break;
        }
        startActivity(intent);
    }
}
