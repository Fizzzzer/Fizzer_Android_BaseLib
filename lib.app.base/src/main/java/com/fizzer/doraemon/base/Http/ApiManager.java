package com.fizzer.doraemon.base.Http;

import com.fizzer.doraemon.base.Http.Api.ApiService;
import com.fizzer.doraemon.base.Http.Exception.ApiException;
import com.fizzer.doraemon.base.Http.Exception.ExceptionCode;
import com.fizzer.doraemon.base.Http.Gson.BuildGson;
import com.fizzer.doraemon.base.Http.Interceptor.HeaderInterceptor;
import com.fizzer.doraemon.base.Http.Interceptor.LogInterceptor;
import com.fizzer.doraemon.base.Http.Model.IModel;
import com.fizzer.doraemon.base.Http.Url.UrlUtils;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by Fizzer on 2019/6/18
 * Email: Fizzer503@gmail.com
 * Description: 网络接口管理最终调用类
 */
public class ApiManager {

    private ApiService mApi;
    private Retrofit mRetrofit;

    private final int TIME_OUT = 20;    //网络请求超时时间


    public ApiManager() {
    }

    public static ApiManager getInstance() {
        return holder.apiManager;
    }

    private static class holder {
        private static ApiManager apiManager = new ApiManager();
    }

    public ApiService getRequest() {
        if (mApi == null) {
            synchronized (ApiService.class) {
                mApi = mRetrofit.create(ApiService.class);
            }
        }
        return mApi;
    }

    /**
     * 初始化网络设置
     */
    public void init(){
        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(BuildGson.builsGson())) //设置Gson模型转换支持
                .baseUrl(UrlUtils.getHostUrl())
                .client(getClient())
                .build();
    }

    /**
     * 初始化Okhttp
     */
    private OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor())        //添加Header信息
                .addNetworkInterceptor(new LogInterceptor())    //添加日志拦截器
                .build();

        return client;
    }

    /**
     * 异常处理变换
     *
     * @return
     */
    public static <T extends IModel> FlowableTransformer<T, T> getApiTransformer() {

        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.flatMap(new Function<T, Publisher<T>>() {
                    @Override
                    public Publisher<T> apply(T model) throws Exception {
                        if (model != null && model.getCode() == ExceptionCode.COMMON_ERROR) {
                            return Flowable.error(
                                    new ApiException(model.getMsg(), ExceptionCode.COMMON_ERROR));
                        } else if (model == null || model.isNull()) {
                            return Flowable.error(
                                    new ApiException("", ExceptionCode.DATA_ERROR));
                        } else {
                            return Flowable.just(model);
                        }
                    }
                });
            }
        };
    }

    /**
     * 线程变换
     */
    public static <T extends IModel> FlowableTransformer<T, T> getScheduler() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
