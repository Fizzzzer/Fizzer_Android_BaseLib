package com.fizzer.doraemon.base.Http;

/**
 * Created by Doraemon on 2019/6/14.
 */

public interface RequestListener<T> {

    void Error();

    void Success(T t);
}
