package com.fizzer.doraemon.base.View.Dialog;

import android.content.Context;

import com.fizzer.doraemon.base.View.Dialog.impl.TestBottomDialog;

/**
 * Created by Fizzer on 2019/5/30.
 * Email: fizzer503@gmail.com
 * Function:
 */

public class DialogUtils {

    public static void showBottomDialog(Context context){
        TestBottomDialog bottomDialog = new TestBottomDialog(context);
        bottomDialog.show();
    }
}
