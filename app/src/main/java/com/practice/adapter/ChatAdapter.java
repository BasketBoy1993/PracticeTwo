package com.practice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.practice.R;
import com.practice.bean.ChatMessage;

import java.util.List;

/**
 * Created by user on 2017/8/28.
 */

public class ChatAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private int layoutId;
    private List<ChatMessage> dataList;
    private View.OnTouchListener onTouchListener;
    private View.OnLongClickListener onLongClickListener;


    public ChatAdapter(Context context, int layoutId, List<ChatMessage> dataList){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.dataList = dataList;
    }

    public void resetData(List<ChatMessage> dataList){
        this.dataList = dataList;
        this.notifyDataSetChanged();
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener){
        this.onTouchListener = onTouchListener;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener){
        this.onLongClickListener = onLongClickListener;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        ChatMessage itemData = (ChatMessage) getItem(i);

        if (view == null){
            view = inflater.inflate(layoutId, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.userNameTv.setText(itemData.getUserName());
        viewHolder.chatContentTv.setText(itemData.getChatContent());

        if (onTouchListener != null) viewHolder.chatContentTv.setOnTouchListener(onTouchListener);
        if (onLongClickListener != null) viewHolder.chatContentTv.setOnLongClickListener(onLongClickListener);

        return view;
    }



    public class ViewHolder{
        public ImageView headImg;
        public TextView userNameTv;
        public TextView chatContentTv;

        public ViewHolder(View itemView){
            headImg = itemView.findViewById(R.id.img_chat_item_head);
            userNameTv = itemView.findViewById(R.id.tv_chat_item_user_name);
            chatContentTv = itemView.findViewById(R.id.tv_chat_item_chat_content);
        }
    }


}
