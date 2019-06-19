package com.fizzer.doraemon.android;

import android.app.Application;

import com.fizzer.doraemon.base.Http.ApiManager;

/**
 * Created by Doraemon on 2019/6/19.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ApiManager.getInstance().init();
    }
}
