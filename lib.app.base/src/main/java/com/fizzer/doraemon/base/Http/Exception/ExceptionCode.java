package com.fizzer.doraemon.base.Http.Exception;

/**
 * Created by Doraemon on 2019/6/19.
 */

public interface ExceptionCode {
    int NORMAL = 0;         //数据正常，无异常情况
    int COMMON_ERROR = -1;  //通用错误
    int DATA_ERROR = 1;     //数据错误
    int NET_ERROR = 2;      //网络错误
}
