package com.fizzer.doraemon.base.Http;

import com.fizzer.doraemon.base.Http.Api.ApiService;
import com.fizzer.doraemon.base.Http.Gson.BuildGson;
import com.fizzer.doraemon.base.Http.Interceptor.InterceptorHeader;
import com.fizzer.doraemon.base.Http.Interceptor.LogInterceptor;
import com.fizzer.doraemon.base.Http.Url.UrlUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by Fizzer on 2019/6/18
 * Email: Fizzer503@gmail.com
 * Description: 网络接口管理最终调用类
 */
public class ApiManager {

    private final int TIME_OUT = 20;    //网络请求超时时间

    public ApiManager() {
    }

    public ApiManager getInstance() {
        return holder.apiManager;
    }

    private static class holder {
        private static ApiManager apiManager = new ApiManager();
    }

    /**
     * 初始化Okhttp
     */
    private OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new InterceptorHeader())        //添加Header信息
                .addNetworkInterceptor(new LogInterceptor())    //添加日志拦截器
                .build();

        return client;
    }

    /**
     * 初始化retrofit
     */
    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(BuildGson.builsGson())) //设置Gson模型转换支持
                .baseUrl(UrlUtils.getHostUrl())
                .client(getClient())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
    }
}
