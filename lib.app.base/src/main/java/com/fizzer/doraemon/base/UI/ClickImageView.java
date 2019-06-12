package com.fizzer.doraemon.base.UI;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Create by: Fizzer on 2019/6/11
 * Email: doraemonmqq@sina.com
 * Function:
*/
public class ClickImageView extends AppCompatImageView {

    public ClickImageView(Context context) {
        super(context);
        setClickable(true);
    }

    public ClickImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
    }

    public ClickImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.setColorFilter(0x99000000);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                this.setColorFilter(null);
                break;
        }
        return super.onTouchEvent(event);
    }
}