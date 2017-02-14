package com.liwy.anything.view.topbar;

import android.graphics.Color;

/**
 * 标题栏配置类
 * Created by liwy on 16/7/28.
 */
public class ConfigTop {
    public static int transprent = Color.parseColor("#00000000");//透明色
    /**
     * 背景色
     */
    private int backgroundColor = Color.parseColor("#3F51B5");//默认深蓝色
    /**
     * 字体颜色
     */
    private int titleTextColor = Color.parseColor("#ffffff");//默认白色

    /**
     * 标题字体大小
     * @return
     */
    private int titleSize = 20;
    /**
     * 按钮字体大小
     */
    private int buttonTitleSize = 16;

    /**
     * 左侧按钮图片
     * @return
     */
    private int leftButtonImg;

    /**
     * 右侧按钮图片
     * @return
     */
    private int rightButtonImg;

    public int getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * 设置标题栏背景色
     * @param backgroundColor
     */
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getTitleTextColor() {
        return titleTextColor;
    }

    /**
     * 设置标题文字和按钮文字颜色
     * @param titleTextColor
     */
    public void setTitleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(int titleSize) {
        this.titleSize = titleSize;
    }

    public int getButtonTitleSize() {
        return buttonTitleSize;
    }

    public void setButtonTitleSize(int buttonTitleSize) {
        this.buttonTitleSize = buttonTitleSize;
    }

    public int getLeftButtonImg() {
        return leftButtonImg;
    }

    public void setLeftButtonImg(int leftButtonImg) {
        this.leftButtonImg = leftButtonImg;
    }

    public int getRightButtonImg() {
        return rightButtonImg;
    }

    public void setRightButtonImg(int rightButtonImg) {
        this.rightButtonImg = rightButtonImg;
    }
}
