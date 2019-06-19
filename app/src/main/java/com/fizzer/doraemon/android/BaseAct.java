package com.fizzer.doraemon.android;

import com.fizzer.doraemon.base.Http.Api.ApiService;
import com.fizzer.doraemon.base.Http.ApiManager;
import com.fizzer.doraemon.base.View.BaseActivity;

/**
 * Created by Doraemon on 2019/6/19.
 */

public abstract class BaseAct extends BaseActivity {
    public ApiService mApi = ApiManager.getInstance().getRequest();
}
