package com.practice.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.practice.R;
import com.practice.adapter.ChatAdapter;
import com.practice.bean.ChatMessage;
import com.practice.interfaces.ViewClickCallback;
import com.practice.popupwindow.ChatItemOptionPopupWindow;
import com.practice.tools.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/8/28.
 */

public class ShowPopupWindowWithSoftInputActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, View.OnLongClickListener {


    private InputMethodManager manager;
    private LinearLayout coverLayerLinearLayout;
    private ListView chatMsgLv;
    private EditText inputContentEdit;
    private TextView sendTv;

    private ChatAdapter adapter;
    private List<ChatMessage> chatMessageList;
    private String sendString;

    private int[] popupWindowShowPositions = new int[2];


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_popupwindow_with_softinput);

        initView();
        initData();
    }

    private void initView(){
        coverLayerLinearLayout = (LinearLayout) this.findViewById(R.id.linear_layer_cover);
        coverLayerLinearLayout.setOnTouchListener(this);
        inputContentEdit = (EditText) this.findViewById(R.id.edit_content_input);
        inputContentEdit.setOnTouchListener(this);
        sendTv = (TextView) this.findViewById(R.id.tv_send_chat_message);
        sendTv.setOnClickListener(this);
        chatMsgLv = (ListView) this.findViewById(R.id.listview_chat);
        chatMsgLv.setOnTouchListener(this);
    }

    private void initData(){
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        chatMessageList = new ArrayList<>();
        adapter = new ChatAdapter(this, R.layout.chat_item_layout, chatMessageList);
//        adapter.setOnTouchListener(this);
        adapter.setOnLongClickListener(this);
        chatMsgLv.setAdapter(adapter);

        if (adapter.getCount()-1 >= 0){
            chatMsgLv.setSelection(adapter.getCount()-1);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_send_chat_message:
                sendString = inputContentEdit.getText().toString().replaceAll(" " , "");
                if (TextUtils.isEmpty(sendString)){
                    Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendChatMessage(sendString);
                break;
        }
    }

    private void sendChatMessage(String sendString) {
        inputContentEdit.setText("");
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUserName("小乔");
        chatMessage.setChatContent(sendString);
        chatMessageList.add(chatMessage);
        adapter.resetData(chatMessageList);
        chatMsgLv.setSelection(adapter.getCount()-1);
    }


    private void hideKeyBoard(){
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN){
            if (getCurrentFocus() != null){
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()){
            case R.id.listview_chat:
                chatMsgLv.requestFocus();
                hideKeyBoard();
                break;
            case R.id.edit_content_input:
                chatMsgLv.setSelection(chatMsgLv.getBottom());
                break;
            case R.id.linear_layer_cover:
                switch (motionEvent.getAction()){
                    /**
                     * 点击的开始位置
                     */
                    case MotionEvent.ACTION_DOWN:
                        popupWindowShowPositions[0] = Math.round(motionEvent.getX());
                        popupWindowShowPositions[1] = Math.round(motionEvent.getY());

                        Log.d("=====坐标=====", motionEvent.getX()+"==="+motionEvent.getY());
                        break;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean onLongClick(final View view) {
        view.setActivated(true);

        ChatItemOptionPopupWindow chatItemOptionPopupWindow = new ChatItemOptionPopupWindow(ShowPopupWindowWithSoftInputActivity.this);
        chatItemOptionPopupWindow.setViewClickCallback(new ViewClickCallback() {
            @Override
            public void onViewClicked(View view) {
                switch (view.getId()){
                    case R.id.tv_copy:
                        break;
                    case R.id.tv_forward:
                        break;
                    case R.id.tv_collect:
                        break;
                    case R.id.tv_translate:
                        break;
                    case R.id.tv_delete:
                        break;
                }
            }
        });

        chatItemOptionPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                view.setActivated(false);
            }
        });

        int windowShowPosition[] = CommonUtil.caculatePopupwindowPosition(ShowPopupWindowWithSoftInputActivity.this, popupWindowShowPositions, chatItemOptionPopupWindow.getRootView(), 20, 40);
        chatItemOptionPopupWindow.showAtLocation(view , Gravity.TOP|Gravity.START , windowShowPosition[0], windowShowPosition[1]);
        return false;
    }
}
