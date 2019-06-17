package com.fizzer.doraemon.base.Http.Api;

import com.fizzer.doraemon.base.Http.Data.RemoteResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Doraemon on 2019/6/14.
 */

public interface ApiService {

    @GET("wxarticle/chapters/json ")
    Call<RemoteResponse> getArticalList();
}
