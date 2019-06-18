package com.fizzer.doraemon.base.Http.Data;

/**
 * Create by Fizzer on 2019/6/17
 * Email: Fizzer503@gmail.com
 * Description:数据解析基类
*/

public class RemoteModel<T extends RemoteResponse> extends RemoteResponse {
    public T content;
}
