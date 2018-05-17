package com.practice.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.practice.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/3/13.
 */

public class ImeiImsiActivity extends AppCompatActivity{

    private Button getImeiBtn;
    private Button getImsiBtn;
    private TextView imeiTv;
    private TextView imsiTv;


    private static final int PERMISSIONS_REQUEST_CODE = 1;//申请权限的请求代号

    //请求的权限数组
    private final String[] PERMISSIONS = {
            Manifest.permission.READ_PHONE_STATE
    };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imei_imsi);

        imeiTv = (TextView) findViewById(R.id.tv_imei);
        imsiTv = (TextView) findViewById(R.id.tv_imsi);

        getImeiBtn = (Button) findViewById(R.id.btn_get_device_imei);
        getImeiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imei = getImei(ImeiImsiActivity.this);
                if (TextUtils.isEmpty(imei)){
                    imeiTv.setText("get nothing");
                }else{
                    imeiTv.setText(imei.toString());
                }
            }
        });

        getImsiBtn = (Button) findViewById(R.id.btn_get_device_imsi);
        getImsiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imsi = getImsi(ImeiImsiActivity.this);
                if (TextUtils.isEmpty(imsi)){
                    imsiTv.setText("get nothing");
                }else{
                    imsiTv.setText(imsi.toString());
                }
            }
        });


        requestPermission(PERMISSIONS);

    }

    public static String getImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") final String imei = telephonyManager.getDeviceId();
        return imei;
    }
    public static String getImsi(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") final String imsi =telephonyManager.getSubscriberId();
        return imsi;
    }






    /**
     * 申请权限
     * @param permissions 需要申请的权限
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermission(@NonNull String[] permissions) {
            List<String> requestPermissionList = new ArrayList<>();
            //找出所有未授权的权限
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissionList.add(permission);
                }
            }
            if (!requestPermissionList.isEmpty()) {
                //申请授权
                requestPermissions(requestPermissionList.toArray(new String[requestPermissionList.size()]), PERMISSIONS_REQUEST_CODE);
            }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode != PERMISSIONS_REQUEST_CODE) {
            return;
        }
        if (grantResults.length > 0) {
            List<String> deniedPermissionList = new ArrayList<>();
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissionList.add(permissions[i]);
                }
            }

            if (deniedPermissionList.isEmpty()) {
                new AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setTitle("权限申请")
                        .setMessage("权限不足，请前往“设置-应用-当前应用-权限”中开启相关权限，以保证应用正常使用")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        })
                        .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //开启设置页
                                startActivity(new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS));
                                dialog.dismiss();
                            }
                        }).show();
            }
        }
    }
}
