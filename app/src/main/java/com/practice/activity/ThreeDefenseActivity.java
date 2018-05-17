package com.practice.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.practice.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by user on 2018/4/13.
 */

public class ThreeDefenseActivity extends AppCompatActivity implements View.OnClickListener{

    private Button getPathBtn;
    private TextView showPathTv;
    private Button writeBtn;
    private Button readBtn;


    private static final String OLD_PATH = "media/cn.isccn.ouyu";
    private static final String NEW_PATH = "data/cn.isccn.ouyu/files";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_defense);

        initView();
    }

    private void initView(){
        getPathBtn = (Button) findViewById(R.id.btn_get_path);
        getPathBtn.setOnClickListener(this);
        showPathTv = (TextView) findViewById(R.id.tv_show_path);
        writeBtn = (Button) findViewById(R.id.btn_write_file);
        writeBtn.setOnClickListener(this);
        readBtn = (Button) findViewById(R.id.btn_read_file);
        readBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_get_path:
                getPath();
                break;
            case R.id.btn_write_file:
                write();
                break;
            case R.id.btn_read_file:
                read();
                break;
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void getPath() {
        String invisibleFilePath = this.getFilesDir().getAbsolutePath();
        String visibleFilePath = this.getExternalFilesDir(null).getAbsolutePath();

        String invisibleCachePath = this.getCacheDir().getAbsolutePath();
        String visibleCachePath = this.getExternalCacheDir().getAbsolutePath();

        File[] files = this.getExternalMediaDirs();
        String externalMediaPath = "";
        for (File file : files){
            externalMediaPath = file.getAbsolutePath()+"\n";
        }

        String externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String externalRootPath = Environment.getRootDirectory().getAbsolutePath();

        System.out.println("应用私密文件路径"+invisibleFilePath);
        System.out.println("应用公开文件路径"+visibleFilePath);
        System.out.println("应用私密缓存路径"+invisibleCachePath);
        System.out.println("应用公开缓存路径"+visibleCachePath);
        System.out.println("应用外部媒体路径"+externalMediaPath);
        System.out.println("应用外部存储路径"+externalStoragePath);
        System.out.println("应用外部根路径"+externalRootPath);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("应用私密文件路径");
        stringBuffer.append(invisibleFilePath);
        stringBuffer.append("\n");
        stringBuffer.append("应用公开文件路径");
        stringBuffer.append(visibleFilePath);
        stringBuffer.append("\n");
        stringBuffer.append("应用私密缓存路径");
        stringBuffer.append(invisibleCachePath);
        stringBuffer.append("\n");
        stringBuffer.append("应用公开缓存路径");
        stringBuffer.append(visibleCachePath);
        stringBuffer.append("\n");
        stringBuffer.append("应用外部媒体路径");
        stringBuffer.append(externalMediaPath);
        stringBuffer.append("\n");
        stringBuffer.append("应用外部存储路径");
        stringBuffer.append(externalStoragePath);
        stringBuffer.append("\n");
        stringBuffer.append("应用外部根路径");
        stringBuffer.append(externalRootPath);
        stringBuffer.append("\n");
        showPathTv.setText(stringBuffer.toString());
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void write(){
        File[] files = this.getExternalMediaDirs();
        String targetDirectoryPath = "";
        for (File file : files){
            targetDirectoryPath = file.getAbsolutePath().replaceAll(OLD_PATH, NEW_PATH);
        }


        File targetDirectory = new File(targetDirectoryPath);
        if (!targetDirectory.exists()){
            targetDirectory.mkdirs();
        }

        File file = new File(targetDirectory, "key.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            String info = "Test write something to TfCard and read something from TfCard ...";
            fos.write(info.getBytes());
            fos.close();
            System.out.println("write-success: "+info);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("write failed cause of exception");
        }
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void read(){
        File[] files = this.getExternalMediaDirs();
        String targetDirectoryPath = "";
        for (File file : files){
            targetDirectoryPath = file.getAbsolutePath().replaceAll(OLD_PATH, NEW_PATH);
        }

        File targetDirectory = new File(targetDirectoryPath);
        if (!targetDirectory.exists()){
            targetDirectory.mkdirs();
        }

        File file = new File(targetDirectory, "key.txt");

        try {
            FileInputStream is = new FileInputStream(file);
            byte[] b = new byte[1024];
            is.read(b);
            String result = new String(b);
            System.out.println("read-success: "+result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            Toast.makeText(this, "读取异常", Toast.LENGTH_LONG).show();
        }
    }

}
