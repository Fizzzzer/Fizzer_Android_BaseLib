package com.fizzer.doraemon.base.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.fizzer.doraemon.base.View.I.IView;

import butterknife.ButterKnife;

/**
 * Created by Fizzer on 2019/5/22.
 * Email: fizzer503@gmail.com
 * Function:自定义控件基类
 */

public abstract class BaseView extends LinearLayout implements IView {
    public BaseView(Context context) {
        super(context);
        onInitialize();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onInitialize();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onInitialize();
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        onInitialize();
    }

    private void onInitialize() {
        LayoutInflater.from(getContext().getApplicationContext()).inflate(getLayoutResId(), this);
        BindUI(null);
    }

    public void goneView(View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null && View.GONE != view.getVisibility()) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    public void visibleView(View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null && View.VISIBLE != view.getVisibility()) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void BindUI(View rootView) {
        ButterKnife.bind(this,this);
    }
}
