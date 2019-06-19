package com.fizzer.doraemon.base.Http.Api;

import com.fizzer.doraemon.base.Http.Model.DataBaseModel;
import com.fizzer.doraemon.base.Http.Model.DataInfo;
import com.fizzer.doraemon.base.Http.Model.RemoteModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Doraemon on 2019/6/14.
 */

public interface ApiService {

    @GET("wxarticle/chapters/json ")
    Flowable<DataBaseModel> getArticalList();
}
