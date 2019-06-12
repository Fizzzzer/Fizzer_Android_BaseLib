package com.fizzer.doraemon.base.Widget.TitleBarView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.fizzer.doraemon.base.R;
import com.fizzer.doraemon.base.R2;
import com.fizzer.doraemon.base.View.BaseView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Fizzer on 2019/5/22.
 * Email: fizzer503@gmail.com
 * Function:
 */

public class TitleBarView extends BaseView {

    @BindView(R2.id.titleBack)
    TextView titleBack;

    @BindView(R2.id.titleRight)
    TextView titleRight;

    @BindView(R2.id.titlePage)
    TextView titlePage;

    private ITtileBar mITtileBar;

    public TitleBarView(Context context) {
        super(context);
    }

    public TitleBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public int getLayoutResId() {
        return R.layout.view_title_bar_layout;
    }

    public void setTitlePage(String title) {
        titlePage.setText(title);
        visibleView(this);
        if (mITtileBar != null) {
            mITtileBar.onTitlePress();
        }
    }

    public void setTitleRightBtn(String tip, int color) {
        titleRight.setText(tip);
        //-1表示使用默认的
        if (color != -1) {
            titleRight.setTextColor(color);
        }
        visibleView(titleRight);
    }

    public void setITitleBar(ITtileBar iTitleBar) {
        this.mITtileBar = iTitleBar;
    }

    @OnClick({R2.id.titleBack, R2.id.titleRight})
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.titleBack) {
            if (mITtileBar != null) {
                mITtileBar.onTitleLeftPress();
            }
        } else if (i == R.id.titleRight) {
            if (mITtileBar != null) {
                mITtileBar.onTitleRightPress();
            }
        }
    }
}
