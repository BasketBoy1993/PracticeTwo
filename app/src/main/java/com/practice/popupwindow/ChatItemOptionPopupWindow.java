package com.practice.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.practice.R;
import com.practice.interfaces.ViewClickCallback;

/**
 * Created by user on 2017/8/29.
 */

public class ChatItemOptionPopupWindow extends PopupWindow implements View.OnClickListener {

    private LayoutInflater inflater;
    private View rootView;
    private TextView copyTv;
    private TextView forwardTv;
    private TextView collectTv;
    private TextView translateTv;
    private TextView deleteTv;


    private ViewClickCallback viewClickCallback;


    public ChatItemOptionPopupWindow(Context context){
        this.inflater = LayoutInflater.from(context);
        rootView = inflater.inflate(R.layout.chat_item_option_popupwindow_layout, null);
        this.setContentView(rootView);
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(false);

        //这个属性很重要，不设置的话，popupwindow显示位置会被输入法挤变了
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);

        ColorDrawable colorDrawable = new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(colorDrawable);

        initView(rootView);
    }

    private void initView(View rootView) {
        copyTv = rootView.findViewById(R.id.tv_copy);
        copyTv.setOnClickListener(this);
        forwardTv = rootView.findViewById(R.id.tv_forward);
        forwardTv.setOnClickListener(this);
        collectTv = rootView.findViewById(R.id.tv_collect);
        collectTv.setOnClickListener(this);
        translateTv = rootView.findViewById(R.id.tv_translate);
        translateTv.setOnClickListener(this);
        deleteTv = rootView.findViewById(R.id.tv_delete);
        deleteTv.setOnClickListener(this);
    }

    public void setViewClickCallback(ViewClickCallback viewClickCallback){
        this.viewClickCallback = viewClickCallback;
    }


    @Override
    public void onClick(View view) {
        this.dismiss();
        if (viewClickCallback != null){
            viewClickCallback.onViewClicked(view);
        }
    }


    public View getRootView(){
        return this.rootView;
    }


}
