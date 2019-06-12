package com.fizzer.doraemon.base.Event;

/**
 * Created by Fizzer on 2019/5/22.
 * Email: fizzer503@gmail.com
 * Function:
 */

public class EventProvider {
    private volatile static EventBusImpl bus;

    public static EventBusImpl getBus() {
        if (bus == null) {
            synchronized (EventProvider.class) {
                if (bus == null) {
                    bus = EventBusImpl.get();
                }
            }
        }
        return bus;
    }
}
