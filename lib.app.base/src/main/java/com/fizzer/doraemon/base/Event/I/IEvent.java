package com.fizzer.doraemon.base.Event.I;

/**
 * Created by Fizzer on 2019/5/22.
 * Email: fizzer503@gmail.com
 * Function:
 */
public interface IEvent {
    void register(Object object);

    void unregister(Object object);

    void post(IEventModel model);
}
