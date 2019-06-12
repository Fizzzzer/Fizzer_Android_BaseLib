package com.fizzer.doraemon.base.View.I;

import android.view.View;

/**
 * Created by Fizzer on 2019/5/22.
 * Email: fizzer503@gmail.com
 * Function:
 */

public interface IView {
    void BindUI(View rootView);

    int getLayoutResId();

    void showError();
}
