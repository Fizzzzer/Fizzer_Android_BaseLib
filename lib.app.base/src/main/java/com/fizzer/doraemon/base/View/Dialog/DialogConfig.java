package com.fizzer.doraemon.base.View.Dialog;

import android.view.View;

/**
 * Created by Fizzer on 2019/5/28.
 * Email: fizzer503@gmail.com
 * Function:Dialog的配置信息
 */

public class DialogConfig {
    public String title;
    public int titleColor;
    public int titleSize;

    public String content;
    public int contentColor;
    public int contentSize;

    public String leftTip;
    public int leftTipColor;
    public int leftTipSize;

    public String rightTip;
    public int rightTipColor;
    public int rightTipSize;

    public String confirmTip;
    public int confirmColor;
    public int confirmSize;

    public View.OnClickListener leftTipClicklistener;
    public View.OnClickListener rightTipClicklistener;
    public View.OnClickListener confirmTipClicklistener;
}
