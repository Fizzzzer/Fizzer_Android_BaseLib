package com.fizzer.doraemon.base.Http.Data;

import java.io.Serializable;

/**
 * Create by Fizzer on 2019/6/17
 * Email: Fizzer503@gmail.com
 * Description:数据解析基类
 */

public class RemoteResponse implements Serializable, IModel {
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
}
