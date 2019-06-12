package com.fizzer.doraemon.base.Utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by Fizzer on 2019/5/23.
 * Email: fizzer503@gmail.com
 * Function:
 */

public class ApkUtils {
    /**
     * 获取版本信息
     *
     * @param context
     * @return 返回"1.1.1"格式
     */
    @SuppressWarnings("finally")
    public static String getVersionName(Context context) {
        String version = "";
        try {
            // get packagemanager
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()--your current package name�?0 means get package
            // info
            PackageInfo packInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            version = packInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            return version;
        }

    }
    public static String getMobleName(){
        return Build.MODEL;
    }
    @SuppressWarnings("finally")
    public static int getVersionCode(Context context) {
        int version = 0;
        try {
            // get packagemanager
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()--your current package name�?0 means get package
            // info
            PackageInfo packInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            version = packInfo.versionCode;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return version;
        }

    }

    public static String getModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 当前应用是否处于前台
     *
     * @param context
     * @return true 在前台   false 在后台
     */
    public static boolean isForeground(Context context) {
        if (context != null) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> processes = activityManager.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : processes) {
                if (processInfo.processName.equals(context.getPackageName())) {
                    if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断应用是否已经启动
     *
     * @param context     一个context
     * @param packageName 要判断应用的包名
     * @return boolean
     */
    public static boolean isAppAlive(Context context, String packageName) {
        /** 前台是否运行 */
        boolean isFrontAppRuning = false;
        /** 后台是否运行 */
        boolean isBgAppRuning = false;
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (!TextUtils.isEmpty(currentPackageName)
                && currentPackageName.equals(packageName)) {
            isFrontAppRuning = true;
        }
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(packageName)
                    && info.baseActivity.getPackageName().equals(packageName)) {
                isBgAppRuning = true;
                break;
            }
        }
        return isFrontAppRuning || isBgAppRuning;
    }


    /**
     * 方法描述：判断某一应用是否正在运行
     *
     * @param context     上下文
     * @param packageName 应用的包名
     * @return true 表示正在运行，false表示没有运行
     */
    public static boolean isAppRunning(Context context, String packageName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(30);
        if (list.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.baseActivity.getPackageName().equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    public static String getApplicationMetaData(Context context, String key) {
        String value = "";
        try {
            String packageName = context.getPackageName();
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }
}
