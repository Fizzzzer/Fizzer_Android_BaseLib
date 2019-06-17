package com.fizzer.doraemon.base.Http.Interceptor;

import com.fizzer.doraemon.base.Utils.ApkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Doraemon on 2019/6/14.
 */

public class InterceptorHeader implements Interceptor{


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request origin = chain.request();
        Request request = origin.newBuilder()
                .header("versonName", "1.2")
                .build();
        return chain.proceed(request);
    }

}
