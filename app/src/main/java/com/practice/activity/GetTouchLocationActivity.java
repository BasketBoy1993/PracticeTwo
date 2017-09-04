package com.practice.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.practice.R;

/**
 * Created by user on 2017/8/30.
 */

public class GetTouchLocationActivity extends AppCompatActivity implements View.OnTouchListener{

    private LinearLayout rootView;
    private TextView startTv;
    private TextView endTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_touch_location);

        initView();
    }

    private void initView() {
        rootView = (LinearLayout) this.findViewById(R.id.root);
        rootView.setOnTouchListener(this);
        startTv = (TextView) this.findViewById(R.id.tv_start);
        endTv = (TextView) this.findViewById(R.id.tv_end);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            /**
             * 点击的开始位置
             */
            case MotionEvent.ACTION_DOWN:
                startTv.setText("起始位置：(" + motionEvent.getX() + "," + motionEvent.getY());
                break;
            /**
             * 离开屏幕的位置
             */
            case MotionEvent.ACTION_UP:
                endTv.setText("结束位置：(" + motionEvent.getX() + "," + motionEvent.getY());
                break;
            default:
                break;
        }
        /**
         *  注意返回值
         *  true：view继续响应Touch操作；
         *  false：view不再响应Touch操作，故此处若为false，只能显示起始位置，不能显示实时位置和结束位置
         */
        return true;
    }
}
