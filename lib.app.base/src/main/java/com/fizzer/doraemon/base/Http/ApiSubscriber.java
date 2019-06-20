package com.fizzer.doraemon.base.Http;

import com.fizzer.doraemon.base.Http.Exception.ApiException;
import com.fizzer.doraemon.base.Http.Exception.ExceptionCode;
import com.fizzer.doraemon.base.Http.Model.IModel;


import java.net.UnknownHostException;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * @author xiaolei.li
 */

public abstract class ApiSubscriber<T extends IModel> extends ResourceSubscriber<T> {


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(T t) {
        success(t);
    }

    @Override
    public void onError(Throwable e) {
        ApiException ex;
        if (e != null) {
            if (!(e instanceof ApiException)) {
                if (e instanceof UnknownHostException) {
                    ex = new ApiException("", ExceptionCode.NET_ERROR);
                } else {
                    ex = new ApiException("", ExceptionCode.COMMON_ERROR);
                }
            } else {
                ex = (ApiException) e;
            }

            error(ex);
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void success(T t);

    public abstract void error(ApiException ex);

}
