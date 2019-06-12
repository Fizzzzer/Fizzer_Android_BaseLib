package com.fizzer.doraemon.base.Event;

import com.fizzer.doraemon.base.Event.I.IEvent;
import com.fizzer.doraemon.base.Event.I.IEventModel;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Fizzer on 2019/5/22.
 * Email: fizzer503@gmail.com
 * Function:
 */

public class EventBusImpl implements IEvent {
    public EventBusImpl() {
    }

    private static class holder {
        private static final EventBusImpl instance = new EventBusImpl();
    }

    public static EventBusImpl get() {
        return holder.instance;
    }

    @Override
    public void register(Object object) {
        EventBus.getDefault().register(object);
    }

    @Override
    public void unregister(Object object) {
        if (EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().unregister(object);
        }
    }

    @Override
    public void post(IEventModel model) {
        synchronized (EventBusImpl.class) {
            EventBus.getDefault().post(model);
        }
    }
}
