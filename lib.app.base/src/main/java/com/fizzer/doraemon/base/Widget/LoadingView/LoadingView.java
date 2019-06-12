package com.fizzer.doraemon.base.Widget.LoadingView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.fizzer.doraemon.base.R;
import com.fizzer.doraemon.base.R2;
import com.fizzer.doraemon.base.View.BaseView;

import butterknife.BindView;

/**
 * Created by Fizzer on 2019/6/12.
 * Email: fizzer503@gmail.com
 * Function:
 */

public class LoadingView extends BaseView {

    @BindView(R2.id.ivLoading)
    ImageView ivLoading;

    public LoadingView(Context context) {
        super(context);
        setLoadingAnimation();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLoadingAnimation();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLoadingAnimation();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.view_loading_layout;
    }

    private void setLoadingAnimation() {

        final RotateAnimation AnimRotateLoading = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AnimRotateLoading.setInterpolator(new LinearInterpolator());
        AnimRotateLoading.setRepeatCount(99);// 重复次数
        AnimRotateLoading.setDuration(1000);
        AnimRotateLoading.setAnimationListener(null);

        AnimRotateLoading.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivLoading.startAnimation(AnimRotateLoading);
            }
        });

        ivLoading.startAnimation(AnimRotateLoading);
    }
}
