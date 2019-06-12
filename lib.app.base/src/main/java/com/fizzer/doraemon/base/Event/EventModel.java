package com.fizzer.doraemon.base.Event;

import com.fizzer.doraemon.base.Event.I.IEventModel;

/**
 * Created by Fizzer on 2019/5/22.
 * Email: fizzer503@gmail.com
 * Function:
 */

public class EventModel implements IEventModel {

    private int tag;

    private String msg;

    private Object msgObj;

    public EventModel(int tag) {
        this.tag = tag;
    }

    public EventModel(int tag, String msg) {
        this.tag = tag;
        this.msg = msg;
    }

    public EventModel(int tag, String msg, Object msgObj) {
        this.tag = tag;
        this.msg = msg;
        this.msgObj = msgObj;
    }

    @Override
    public int getTag() {
        return tag;
    }

    public String getMsg() {
        return msg;
    }

    public Object getMsgObj() {
        return msgObj;
    }
}
