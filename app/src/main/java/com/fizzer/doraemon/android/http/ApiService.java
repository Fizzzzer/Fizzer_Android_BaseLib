package com.fizzer.doraemon.android.http;

import com.fizzer.doraemon.android.model.TestData;
import com.fizzer.doraemon.base.Http.Model.RemoteModel;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Doraemon on 2019/6/20.
 *
 */

public interface ApiService {
    @GET("wxarticle/list/{id}/1/json?k=Java")
    Flowable<RemoteModel<TestData>> getTestData(@Path("id") int id);
}
