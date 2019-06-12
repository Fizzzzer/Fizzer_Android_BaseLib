package com.fizzer.doraemon.base.View.Dialog;

import android.content.Context;
import android.view.View;

import com.fizzer.doraemon.base.R;
import com.fizzer.doraemon.base.View.Dialog.impl.PageDoubleBtnDialog;
import com.fizzer.doraemon.base.View.Dialog.impl.PageSingleBtnDialog;

/**
 * Created by Fizzer on 2019/5/29.
 * Email: fizzer503@gmail.com
 * Function:
 */

public class DialogBuilder {
    private static final class holder {
        private static final DialogBuilder INSTANCE = new DialogBuilder();
    }

    public static DialogBuilder getInstance() {
        return holder.INSTANCE;
    }


    DialogConfig mConfig;

    public DialogBuilder() {
        mConfig = new DialogConfig();
    }

    public DialogBuilder setTitle(String title) {
        mConfig.title = title;
        return this;
    }

    public DialogBuilder setTitleColor(int color) {
        mConfig.titleColor = color;
        return this;
    }

    public DialogBuilder setTitleSize(int size) {
        mConfig.titleSize = size;
        return this;
    }

    public DialogBuilder setContent(String tip) {
        mConfig.content = tip;
        return this;
    }

    public DialogBuilder setContentColor(int color) {
        mConfig.contentColor = color;
        return this;
    }

    public DialogBuilder setContentSize(int size) {
        mConfig.contentSize = size;
        return this;
    }

    public DialogBuilder setLeftTip(String tip) {
        mConfig.leftTip = tip;
        return this;
    }

    public DialogBuilder setLeftColor(int color) {
        mConfig.leftTipColor = color;
        return this;
    }

    public DialogBuilder setLeftSize(int size) {
        mConfig.leftTipSize = size;
        return this;
    }

    public DialogBuilder setLeftClick(View.OnClickListener listener) {
        mConfig.leftTipClicklistener = listener;
        return this;
    }

    public DialogBuilder setRightTip(String tip) {
        mConfig.rightTip = tip;
        return this;
    }

    public DialogBuilder setRightColor(int color) {
        mConfig.rightTipColor = color;
        return this;
    }

    public DialogBuilder setRightSize(int size) {
        mConfig.rightTipSize = size;
        return this;
    }

    public DialogBuilder setRightClick(View.OnClickListener listener) {
        mConfig.rightTipClicklistener = listener;
        return this;
    }

    public DialogBuilder setConfirmClick(View.OnClickListener listener) {
        mConfig.confirmTipClicklistener = listener;
        return this;
    }

    public void buildPageDoubleBtnDialog(Context context) {
        PageDoubleBtnDialog dialog = new PageDoubleBtnDialog(context);
        dialog.setConfig(mConfig);
        dialog.show();
    }

    public void buildPageSingleBtnDialog(Context context) {
        PageSingleBtnDialog dialog = new PageSingleBtnDialog(context);
        dialog.setConfig(mConfig);
        dialog.show();
    }
}
