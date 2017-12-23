package com.practice.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.practice.R;
import com.practice.receiver.PhoneStateReceiver;

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
    private Button pictureViewerBtn;
    private Button lightScreenBtn;
    private Button changeSysBrightnessBtn;
    private Button explicitStartActivityBtn;
    private Button implicitStartActivityBtn;


    private PhoneStateReceiver phoneStateReceiver;

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
        pictureViewerBtn = (Button) findViewById(R.id.btn_picture_viewer);
        pictureViewerBtn.setOnClickListener(this);
        lightScreenBtn = (Button) findViewById(R.id.btn_light_screen);
        lightScreenBtn.setOnClickListener(this);
        changeSysBrightnessBtn = (Button) findViewById(R.id.btn_change_system_brightness);
        changeSysBrightnessBtn.setOnClickListener(this);
        explicitStartActivityBtn = (Button) findViewById(R.id.btn_explicit_start_activity);
        explicitStartActivityBtn.setOnClickListener(this);
        implicitStartActivityBtn = (Button) findViewById(R.id.btn_implicit_start_activity);
        implicitStartActivityBtn.setOnClickListener(this);


        phoneStateReceiver = new PhoneStateReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        registerReceiver(phoneStateReceiver, intentFilter);

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
            case R.id.btn_picture_viewer:
                intent.setClass(this, PictureViewerActivity.class);
                break;
            case R.id.btn_light_screen:
                lightScreen(this, 255);
                break;
            case R.id.btn_change_system_brightness:
                changeSystemBrightness(this, 160);
                break;
            case R.id.btn_explicit_start_activity:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_implicit_start_activity:
                Intent intent2 = new Intent("com.practice.activity.MainActivity");
                startActivity(intent2);
                break;
        }
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(phoneStateReceiver);
        super.onDestroy();
    }

    /**
     * 展示二维码的时候点亮屏幕（不会随着系统的亮度自动变化，退出当前页面，其他页面亮度受系统亮度控制）
     * @param activity  当前页面activity对象
     * @param brightness  亮度(范围为0-255)
     */
    private void lightScreen(Activity activity, int brightness){
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.screenBrightness = Float.valueOf(brightness) * (1f / 255f);
        activity.getWindow().setAttributes(lp);
    }




    private void changeSystemBrightness(Activity activity, int brightness){
        Uri uri = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS);
        Settings.System.putInt(activity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brightness);
        activity.getContentResolver().notifyChange(uri, null);
    }

}
