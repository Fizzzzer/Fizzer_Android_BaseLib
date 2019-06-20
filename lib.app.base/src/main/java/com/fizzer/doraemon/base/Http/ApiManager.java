package com.fizzer.doraemon.base.Http;

import com.fizzer.doraemon.base.Http.Exception.ApiException;
import com.fizzer.doraemon.base.Http.Exception.ExceptionCode;
import com.fizzer.doraemon.base.Http.Gson.BuildGson;
import com.fizzer.doraemon.base.Http.Interceptor.HeaderInterceptor;
import com.fizzer.doraemon.base.Http.Interceptor.LogInterceptor;
import com.fizzer.doraemon.base.Http.Model.IModel;
import com.fizzer.doraemon.base.Http.Provider.NetProvider;

import org.reactivestreams.Publisher;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
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

    private final int TIME_OUT = 20;    //网络请求超时时间


    public ApiManager() {
    }

    public static ApiManager getInstance() {
        return holder.apiManager;
    }

    private static class holder {
        private static ApiManager apiManager = new ApiManager();
    }

    //网络请求默认时间
    private final int CONNECT_DEFAULT_TIME = 5;
    private final int READ_DEFAULT_TIME = 5;


    private String cacheKey = getClass().getSimpleName();
    //缓存处理
    private static NetProvider mProvider;
    private Map<String, Retrofit> retrofitMap = new HashMap<>();
    private Map<String, NetProvider> providerMap = new HashMap<>();
    private Map<String, OkHttpClient> clientMap = new HashMap<>();

    public Retrofit getRetrofit(NetProvider provider) {

        if (retrofitMap.get(cacheKey) != null) {
            retrofitMap.get(cacheKey);
        }

        if (provider == null) {
            provider = providerMap.get(cacheKey);
            if (provider == null) {
                provider = mProvider;
            }
        } else {
            mProvider = provider;
        }

        checkProvider(provider);

        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(BuildGson.builsGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(provider.getHost())
                .client(getClient(provider));

        Retrofit retrofit = builder.build();
        retrofitMap.put(cacheKey, retrofit);
        providerMap.put(cacheKey, provider);
        return retrofit;
    }

    /**
     * 初始化Okhttp
     */
    private OkHttpClient getClient(NetProvider provider) {

        if (clientMap.get(cacheKey) != null) {
            return clientMap.get(cacheKey);
        }

        checkProvider(provider);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(provider.connectTimeOutMills() == 0
                ? CONNECT_DEFAULT_TIME : provider.connectTimeOutMills(), TimeUnit.SECONDS);

        builder.readTimeout(provider.readTimeOutMills() == 0
                ? READ_DEFAULT_TIME : provider.readTimeOutMills(), TimeUnit.SECONDS);

        SSLSocketFactory ssl = provider.getSSLFactory();

        if (ssl != null) {
            builder.sslSocketFactory(ssl);
            builder.hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        }

        //添加自定义拦截器
        Interceptor[] interceptors = provider.configInterceptor();
        if (interceptors != null) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        //添加自定义的请求Header
        Map<String, String> header = mProvider.getHeader();
        if (header != null) {
            builder.addInterceptor(new HeaderInterceptor(header));
        }

        //添加日志拦截器
        builder.addNetworkInterceptor(new LogInterceptor());

        OkHttpClient client = builder.build();
        clientMap.put(cacheKey, client);
        providerMap.put(cacheKey, provider);

        return client;
    }


    private void checkProvider(NetProvider provider) {
        if (provider == null) {
            throw new IllegalStateException("Fizzer提示您,请先注册provider");
        }
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
