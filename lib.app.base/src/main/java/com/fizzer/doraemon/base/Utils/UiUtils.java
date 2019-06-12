package com.fizzer.doraemon.base.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Fizzer on 2019/5/23.
 * Email: fizzer503@gmail.com
 * Function:
 */

public class UiUtils {
    public static int SCREEN_WIDTH = -1; // 屏幕宽度

    public static int SCREEN_HEIGHT = -1; // 屏幕高度

    private static float SCALE_X = -1; // 横向缩放比例

    private static float SCALE_Y = -1; // 纵向缩放比例

    private static float DENSITY = -1; // 屏幕密度

    public static float getSCALE_X(Context context) {
        if (SCALE_X > 0) {
            return SCALE_X;
        }
        init(context);
        return SCALE_X;
    }

    public static float getSCALE_Y(Context context) {
        if (SCALE_Y > 0) {
            return SCALE_Y;
        }
        init(context);
        return SCALE_Y;
    }

    public static float getDENSITY(Context context) {
        if (DENSITY > 0) {
            return DENSITY;
        }
        init(context);
        return DENSITY;
    }

    public static void init(Context context) {
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        SCREEN_WIDTH = display.widthPixels;
        SCREEN_HEIGHT = display.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int flag = SCREEN_WIDTH;
            SCREEN_WIDTH = SCREEN_HEIGHT;
            SCREEN_HEIGHT = flag;
        }
        DENSITY = display.density;
        SCALE_X = SCREEN_WIDTH / 480f;
        SCALE_Y = SCREEN_HEIGHT / 800f;
    }

    /**
     * 获得经过缩放的int值
     *
     * @param i
     * @return
     */
    public static int getInt(Context context, int i) {
        return getIntForScalX(context, i);
    }

    /**
     * 获得经过X轴方向缩放的int值
     *
     * @param i
     * @return
     */
    public static int getIntForScalX(Context context, int i) {
        return (int) (i * getSCALE_X(context));
    }

    /**
     * 获得经过Y轴方向缩放的int值
     *
     * @param i
     * @return
     */
    public static int getIntForScalY(Context context, int i) {
        return (int) (i * getSCALE_Y(context));
    }

    /**
     * 屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        if (SCREEN_WIDTH > 0) {
            return SCREEN_WIDTH;
        }
        init(context);
        return SCREEN_WIDTH;
    }

    /**
     * 屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        if (SCREEN_HEIGHT > 0) {
            return SCREEN_HEIGHT;
        }
        init(context);
        return SCREEN_HEIGHT;
    }

    /**
     * 判断屏幕方向
     *
     * @param context
     * @return
     */
    public static boolean isProt(Context context) {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return false;
        }
        return true;
    }

    public static String getString(Context context, int res) {
        return context.getResources().getString(res);
    }

    /**
     * 自适应字体
     *
     * @param context
     * @param i
     * @return
     */
    public static int getTextSize(Context context, int i) {
        return (int) (i * getSCALE_X(context) / DENSITY);
    }

    /**
     * @param activity
     * @return > 0 success; <= 0 fail
     */
    public static int getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int dip2px(Context context, float dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    public static int px2dip(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f * (px >= 0 ? 1 : -1));
    }


    /**
     * @param backgroundTop
     * @param backgroundBottom
     * @param paint
     * @return paint绘制居中文字时，获取文本底部坐标
     */
    public static float getTextBaseLine(float backgroundTop, float backgroundBottom, Paint paint) {
        final Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (backgroundTop + backgroundBottom - fontMetrics.bottom - fontMetrics.top) / 2;
    }

    public static void addDrawableBottom(Context context, TextView textView, int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        addDrawableBottom(context, textView, drawable);
    }

    public static void addDrawableBottom(Context context, TextView textView, Drawable drawable) {
        if (drawable == null) {
            textView.setCompoundDrawables(null, null, null, null);
            return;
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, null, drawable);
    }

    public static void addDrawableLeft(TextView textView, Drawable drawable) {
        if (drawable == null) {
            textView.setCompoundDrawables(null, null, null, null);
            return;
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    public static void addDrawableTop(TextView textView, Drawable drawable) {
        if (drawable == null) {
            textView.setCompoundDrawables(null, null, null, null);
            return;
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
    }

    public static void addDrawableRight(TextView textView, Drawable drawable) {
        if (drawable == null) {
            textView.setCompoundDrawables(null, null, null, null);
            return;
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, drawable, null);
    }

    /**
     * 重置dialog的大小及位置
     */
    public static void resetDialogSize(Dialog mDialog) {
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        /*
         * 将对话框的大小按屏幕大小的百分比设置
         */
        WindowManager m = dialogWindow.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.7); // 宽度设置为屏幕的0.65
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(p);
    }

    public static void setViewVisible(View view,int visible) {
        if (view.getVisibility() != visible) {
            view.setVisibility(visible);
        }
    }
}
