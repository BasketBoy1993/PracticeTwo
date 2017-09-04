package com.practice.tools;

import android.content.Context;
import android.view.View;

/**
 * Created by user on 2017/8/29.
 */

public class CommonUtil {

    /**
     * 计算出来的位置，Y方向就在anchorView的上面和下面对齐显示，X方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     * @param context
     * @param clickPositions
     * @param contentView
     * @param widthOffset   点击位置精度调整，正数是向右移，负数是想向
     * @param heightOffset
     * @return
     */
    public static int[] caculatePopupwindowPosition(Context context, final int[] clickPositions, final View contentView, int widthOffset, int heightOffset){
        final int windowPosition[] = new int[2];
        final int xOffset = Math.abs(widthOffset);
        final int yOffset = Math.abs(heightOffset);

        //获取屏幕的高宽
        final int screenHeight = getScreenHeight(context);
        final int screenWidth = getScreenWidth(context);

        //计算contentView的高宽
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();

        if (clickPositions[0] > screenWidth/2){
            windowPosition[0] = clickPositions[0] - windowWidth - xOffset;//点击右边就往左边移动xOffset
        }else{
            windowPosition[0] = clickPositions[0] + xOffset;//点击左边就往右边移动xOffset
        }

        if (clickPositions[1] > screenHeight/2){
            windowPosition[1] = clickPositions[1] - windowHeight + yOffset;//点击下边就往下边移动yOffset
        }else{
            windowPosition[1] = clickPositions[1] + yOffset;//点击上边就往下边移动yOffset
        }

        return windowPosition;
    }

    /**
     * 获取屏幕高度(px)
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }


    /**
     * 获取屏幕宽度(px)
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }


}
