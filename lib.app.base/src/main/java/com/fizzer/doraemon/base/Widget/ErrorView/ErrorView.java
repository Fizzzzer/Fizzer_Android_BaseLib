package com.fizzer.doraemon.base.Widget.ErrorView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.fizzer.doraemon.base.View.BaseView;

/**
 * Created by Fizzer on 2019/6/12.
 * Email: fizzer503@gmail.com
 * Function:
 */

public class ErrorView extends BaseView {
    public ErrorView(Context context) {
        super(context);
    }

    public ErrorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ErrorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResId() {
        return 0;
    }
}
