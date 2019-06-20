package com.fizzer.doraemon.android.model;

import com.fizzer.doraemon.base.Http.Model.RemoteResponse;

/**
 * Created by Doraemon on 2019/6/20.
 */

public class TestData extends RemoteResponse{
    public String curPage;
    public String offset;
    public boolean over;
    public int pageCount;
    public int size;
    public int total;
}
