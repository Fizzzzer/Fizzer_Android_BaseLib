package com.fizzer.doraemon.base.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.fizzer.doraemon.base.Event.EventProvider;
import com.fizzer.doraemon.base.R;
import com.fizzer.doraemon.base.View.I.IViewEvent;
import com.fizzer.doraemon.base.View.I.IView;
import com.fizzer.doraemon.base.Widget.TitleBarView.TitleBarView;

import butterknife.ButterKnife;

/**
 * Created by Fizzer on 2019/5/22.
 * Email: fizzer503@gmail.com
 * Function:
 */

public abstract class BaseActivity extends Activity implements IView,IViewEvent {

    private RelativeLayout mContentView;
    public TitleBarView mTitleBarView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (useEvent()) {
            EventProvider.getBus().register(this);
        }

        if (getLayoutResId() > 0) {
            setContentView(getLayoutResId());
            BindUI(null);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (useEvent()) {
            EventProvider.getBus().unregister(this);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.view_page_layout);
        mTitleBarView = findViewById(R.id.titleBar);
        mContentView = findViewById(R.id.contentView);
        View content = View.inflate(this, layoutResID, null);
        mContentView.addView(content);
    }

    @Override
    public boolean useEvent() {
        return false;
    }

    @Override
    public void BindUI(View rootView) {
        ButterKnife.bind(this);
    }
}
