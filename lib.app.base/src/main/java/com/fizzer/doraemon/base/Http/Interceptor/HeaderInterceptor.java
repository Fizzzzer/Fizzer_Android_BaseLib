package com.fizzer.doraemon.base.Http.Interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Create by Fizzer on 2019/6/20
 * Email: Fizzer503@gmail.com
 * Description:网络请求Header
 */

public class HeaderInterceptor implements Interceptor {

    Map<String, String> headerMap = new HashMap<>();

    public HeaderInterceptor(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();
        if (headerMap != null) {
            Set<String> ketSet = headerMap.keySet();
            for (String key : ketSet) {
                builder.addHeader(key, headerMap.get(key));
            }
        }

        return chain.proceed(builder.build());
    }

}
