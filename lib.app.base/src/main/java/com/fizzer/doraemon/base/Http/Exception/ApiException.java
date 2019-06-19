package com.fizzer.doraemon.base.Http.Exception;

/**
 * Create by Fizzer on 2019/6/19
 * Email: Fizzer503@gmail.com
 * Description:
 */

public class ApiException extends Exception {

    private int type = ExceptionCode.NORMAL;
    private Throwable ex;

    public ApiException(String message, int type) {
        super(message);
        this.type = type;
    }

    public ApiException(int type, Throwable ex) {
        this.type = type;
        this.ex = ex;
    }

    @Override
    public String getMessage() {
        if (ex != null) {
            return ex.getMessage();
        }
        return super.getMessage();
    }

    public int getType() {
        return type;
    }
}
