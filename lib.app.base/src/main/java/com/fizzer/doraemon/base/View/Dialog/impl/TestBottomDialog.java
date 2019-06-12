package com.fizzer.doraemon.base.View.Dialog.impl;

import android.content.Context;

import com.fizzer.doraemon.base.R;
import com.fizzer.doraemon.base.View.Dialog.Base.BaseBottomDialog;

/**
 * Created by Fizzer on 2019/5/30.
 * Email: fizzer503@gmail.com
 * Function:
 */

public class TestBottomDialog extends BaseBottomDialog {
    public TestBottomDialog(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.view_test_bottom_dialog_layout;
    }
}
