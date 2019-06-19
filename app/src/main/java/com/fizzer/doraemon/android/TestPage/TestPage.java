package com.fizzer.doraemon.android.TestPage;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fizzer.doraemon.android.BaseAct;
import com.fizzer.doraemon.android.R;
import com.fizzer.doraemon.base.ApiSubscriber;
import com.fizzer.doraemon.base.Http.ApiManager;
import com.fizzer.doraemon.base.Http.Model.DataBaseModel;
import com.fizzer.doraemon.base.Http.Model.DataInfo;
import com.fizzer.doraemon.base.Http.Model.IModel;
import com.fizzer.doraemon.base.Http.Model.RemoteModel;
import com.fizzer.doraemon.base.View.BaseActivity;
import com.fizzer.doraemon.base.View.Dialog.DialogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


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
                mApi.getArticalList()
                        .compose(ApiManager.getScheduler())
                        .compose(ApiManager.getApiTransformer())
                        .subscribe(new Consumer<IModel>() {
                            @Override
                            public void accept(IModel iModel) throws Exception {

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
