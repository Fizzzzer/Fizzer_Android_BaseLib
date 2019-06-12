package com.fizzer.doraemon.base.View.Dialog.impl;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.fizzer.doraemon.base.R;
import com.fizzer.doraemon.base.R2;
import com.fizzer.doraemon.base.Utils.StringUtils;
import com.fizzer.doraemon.base.Utils.UiUtils;
import com.fizzer.doraemon.base.View.Dialog.Base.BaseDialog;
import com.fizzer.doraemon.base.View.Dialog.DialogConfig;

import butterknife.BindView;

/**
 * Created by Fizzer on 2019/5/29.
 * Email: fizzer503@gmail.com
 * Function:
 */

public class PageSingleBtnDialog extends BaseDialog {
    @BindView(R2.id.tvTitle)
    TextView mTitle;

    @BindView(R2.id.tvContent)
    TextView mContent;

    @BindView(R2.id.tvConfirm)
    TextView mConfirm;

    private DialogConfig mConfig;

    public PageSingleBtnDialog(Context context) {
        super(context);
    }


    @Override
    public int getLayoutResId() {
        return R.layout.view_page_single_btn_dialog_layout;
    }

    public void setConfig(DialogConfig config) {
        mConfig = config;
        setDialogView();
    }

    private void setDialogView() {
        if (mConfig == null) {
            return;
        }

        if (StringUtils.isNotEmpty(mConfig.title)) {
            UiUtils.setViewVisible(mTitle, View.VISIBLE);
            mTitle.setText(mConfig.title);
        } else {
            UiUtils.setViewVisible(mTitle, View.GONE);
        }

        if (0 != mConfig.titleSize) {
            mTitle.setTextSize(mConfig.titleSize);
        }

        if (0 != mConfig.titleColor) {
            mTitle.setTextColor(mConfig.titleColor);
        }

        if (StringUtils.isNotEmpty(mConfig.content)) {
            UiUtils.setViewVisible(mContent, View.VISIBLE);
            mContent.setText(mConfig.content);
        } else {
            UiUtils.setViewVisible(mContent, View.GONE);
        }
        if (0 != mConfig.contentColor) {
            mContent.setTextColor(mConfig.contentColor);
        }

        if (0 != mConfig.contentSize) {
            mContent.setTextSize(mConfig.contentSize);
        }

        if (StringUtils.isNotEmpty(mConfig.confirmTip)) {
            mConfirm.setText(mConfig.confirmTip);
        }
        if (0 != mConfig.confirmSize) {
            mConfirm.setTextSize(mConfig.confirmSize);
        }
        if (0 != mConfig.confirmColor) {
            mConfirm.setTextColor(mConfig.confirmColor);
        }

        mConfirm.setOnClickListener(v -> {
            dismiss();
            if (mConfig.confirmTipClicklistener != null) {
                mConfig.confirmTipClicklistener.onClick(v);
            }
        });
    }
}
