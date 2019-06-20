package com.fizzer.doraemon.android.http;

import com.fizzer.doraemon.base.Http.Provider.NetProvider;

import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;

/**
 * Create by Fizzer on 2019/6/20
 * Email: Fizzer503@gmail.com
 * Description:自定义实现类
*/

public class ApiProvider implements NetProvider{
    @Override
    public String getHost() {
        return "https://www.wanandroid.com";
    }

    @Override
    public Interceptor[] configInterceptor() {
        return new Interceptor[0];
    }

    @Override
    public int connectTimeOutMills() {
        return 5;
    }

    @Override
    public int readTimeOutMills() {
        return 5;
    }

    @Override
    public Map<String, String> getHeader() {
        Map<String,String> header = new HashMap<>();
        header.put("versionCode","1100");
        header.put("versionName","1.2.3");
        header.put("devices","Android");
        return header;
    }

    @Override
    public SSLSocketFactory getSSLFactory() {
        return null;
    }
}
