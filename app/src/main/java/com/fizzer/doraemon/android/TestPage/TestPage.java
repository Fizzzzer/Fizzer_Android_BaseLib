package com.fizzer.doraemon.android.TestPage;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fizzer.doraemon.android.BaseAct;
import com.fizzer.doraemon.android.R;
import com.fizzer.doraemon.android.model.TestData;
import com.fizzer.doraemon.base.Http.ApiManager;
import com.fizzer.doraemon.base.Http.ApiSubscriber;
import com.fizzer.doraemon.base.Http.Exception.ApiException;
import com.fizzer.doraemon.base.Http.Model.RemoteModel;

import butterknife.BindView;
import butterknife.OnClick;


public class TestPage extends BaseAct {
    @BindView(R.id.testInfo)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.Test1, R.id.Test2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Test1:
                mApi.getTestData(405)
                        .compose(ApiManager.<RemoteModel<TestData>>getApiTransformer())
                        .compose(ApiManager.<RemoteModel<TestData>>getScheduler())
                        .subscribe(new ApiSubscriber<RemoteModel<TestData>>() {
                            @Override
                            public void success(RemoteModel<TestData> testDataRemoteModel) {
                                Log.e("lib.net",testDataRemoteModel.data.curPage+"");
                            }

                            @Override
                            public void error(ApiException ex) {

                            }
                        });
                break;
            case R.id.Test2:
                break;
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_test_page;
    }

}
