package com.fizzer.doraemon.base.View.Dialog.Base;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.fizzer.doraemon.base.R;
import com.fizzer.doraemon.base.Utils.UiUtils;
import com.fizzer.doraemon.base.View.Dialog.I.IDialogView;
import com.fizzer.doraemon.base.View.I.IView;

import butterknife.ButterKnife;

/**
 * Created by Fizzer on 2019/5/29.
 * Email: fizzer503@gmail.com
 * Function:
 */

public abstract class BaseBottomDialog implements IView, IDialogView {
    private Context mContext;
    private Dialog mDialog;

    public BaseBottomDialog(Context context) {
        mContext = context;
        onInitialize();
    }

    private void onInitialize() {
        View view = View.inflate(mContext, getLayoutResId(), null);
        mDialog = new Dialog(mContext, R.style.PageDialogStyle);
        mDialog.setContentView(view);
        BindUI(view);
        Window window = mDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.dialog_bottom_animation);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = UiUtils.SCREEN_WIDTH;
        window.setAttributes(lp);
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
    public void dismiss() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
