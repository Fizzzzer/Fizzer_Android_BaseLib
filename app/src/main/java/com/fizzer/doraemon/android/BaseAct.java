package com.fizzer.doraemon.android;

import com.fizzer.doraemon.android.http.ApiProvider;
import com.fizzer.doraemon.android.http.ApiService;
import com.fizzer.doraemon.base.Http.ApiManager;
import com.fizzer.doraemon.base.View.BaseActivity;

/**
 * Created by Doraemon on 2019/6/19.
 */

public abstract class BaseAct extends BaseActivity {
    public ApiService mApi = ApiManager.getInstance().getRetrofit(new ApiProvider()).create(ApiService.class);
}
