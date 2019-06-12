package com.fizzer.doraemon.base.View.I;

/**
 * Created by Fizzer on 2019/6/12.
 * Email: fizzer503@gmail.com
 * Function: 页面类型
 */

public interface IViewType {
    /**
     * 正常布局
     */
    int NORMAL = 0x0000;
    /**
     * 显示加载布局
     */
    int LOADING = 0x0002;
    /**
     * 显示错误布局
     */
    int ERROR = 0x0004;
}
