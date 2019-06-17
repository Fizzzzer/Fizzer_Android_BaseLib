package com.fizzer.doraemon.base.Http.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Create by Fizzer on 2019/6/14
 * Email: Fizzer503@gmail.com
 * Description:Retrofit解析Json数据的Gson构建类
 *
 * 使用：retrofit.addConverterFactory(GsonConverterFactory.create(BuildGson.buildGson()))
 */

public class BuildGson {
    public static Gson builsGson() {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(int.class, new GsonIntegerDefaultAdapter())
                .create();

        return gson;
    }
}
