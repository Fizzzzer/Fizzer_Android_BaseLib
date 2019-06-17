package com.fizzer.doraemon.base.View.Dialog.Base;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.fizzer.doraemon.base.R;
import com.fizzer.doraemon.base.Utils.UiUtils;
import com.fizzer.doraemon.base.View.Dialog.I.IDialogView;
import com.fizzer.doraemon.base.View.I.IView;

import butterknife.ButterKnife;

/**
 * Created by Fizzer on 2019/5/28.
 * Email: fizzer503@gmail.com
 * Function: 弹出类对话框Base类
 */

public abstract class BaseDialog implements IView, IDialogView {
    private Context mContext;
    private Dialog mDialog;

    public BaseDialog(Context context) {
        mContext = context;
        onInitialize();
    }

    private void onInitialize() {
        View view = View.inflate(mContext, getLayoutResId(), null);
        mDialog = new Dialog(mContext, R.style.PageDialogStyle);
        mDialog.setContentView(view);
        UiUtils.resetDialogSize(mDialog);
        BindUI(view);
    }

    @Override
    public void BindUI(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    public void show() {
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void dismiss() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
