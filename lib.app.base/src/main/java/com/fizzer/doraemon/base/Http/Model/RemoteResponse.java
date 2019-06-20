package com.fizzer.doraemon.base.Http.Model;

import java.io.Serializable;

/**
 * Create by Fizzer on 2019/6/20
 * Email: Fizzer503@gmail.com
 * Description:
*/

public class RemoteResponse implements IModel,Serializable{
    private static final long serialVersionUID = 1L;
    public int errorCode;
    public String errorMsg;

    @Override
    public int getCode() {
        return errorCode;
    }

    @Override
    public String getMsg() {
        return errorMsg;
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
