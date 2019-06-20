package com.fizzer.doraemon.base.Http.Provider;

import java.util.Map;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;

/**
 * Create by Fizzer on 2019/6/20
 * Email: Fizzer503@gmail.com
 * Description:网络请求所需基本数据
*/

public interface NetProvider {
    String getHost();                   //主机地址

    Interceptor[] configInterceptor();  //所需拦截器

    int connectTimeOutMills();         //链接超时时间

    int readTimeOutMills();            //读取超时时间

    Map<String,String> getHeader();     //添加网络请求的Header

    SSLSocketFactory getSSLFactory();   //获取SSL证书
}
