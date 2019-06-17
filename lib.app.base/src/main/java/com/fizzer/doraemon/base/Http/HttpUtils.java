package com.fizzer.doraemon.base.Http;


import android.util.Log;

import com.fizzer.doraemon.base.Http.Api.ApiService;
import com.fizzer.doraemon.base.Http.Data.RemoteResponse;
import com.fizzer.doraemon.base.Http.Gson.BuildGson;
import com.fizzer.doraemon.base.Http.Interceptor.InterceptorHeader;
import com.fizzer.doraemon.base.Http.Interceptor.LogInterceptor;
import com.fizzer.doraemon.base.Http.Url.UrlUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by Fizzer on 2019/6/14
 * Email: Fizzer503@gmail.com
 * Description:
 */

public class HttpUtils {

    public static void getList() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new InterceptorHeader())        //添加Header信息
                .addNetworkInterceptor(new LogInterceptor())    //添加日志拦截器
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(BuildGson.builsGson())) //设置Gson模型转换支持
                .baseUrl(UrlUtils.getHostUrl())
                .client(client)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<RemoteResponse> call = apiService.getArticalList();
        call.enqueue(new Callback<RemoteResponse>() {
            @Override
            public void onResponse(Call<RemoteResponse> call, Response<RemoteResponse> response) {
            }

            @Override
            public void onFailure(Call<RemoteResponse> call, Throwable t) {

                t.printStackTrace();
            }
        });
    }
}
