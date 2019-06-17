package com.fizzer.doraemon.base.Http.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Doraemon on 2019/6/14.
 */

public class RemoteResponse {
    public String errorCode;
    public String errorMsg;
    public List<DataInfo> data = new ArrayList();
}
