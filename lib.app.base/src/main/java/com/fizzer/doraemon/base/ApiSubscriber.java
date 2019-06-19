package com.fizzer.doraemon.base;

import com.fizzer.doraemon.base.Http.Model.IModel;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Doraemon on 2019/6/19.
 */

public abstract class ApiSubscriber<T extends IModel> extends ResourceSubscriber<T>{

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable t) {

    }

    public ApiSubscriber() {
    }

    @Override
    public void onComplete() {

    }

    public abstract void next(T t);
}
