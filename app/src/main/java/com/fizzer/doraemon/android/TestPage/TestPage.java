package com.fizzer.doraemon.android.TestPage;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fizzer.doraemon.android.R;
import com.fizzer.doraemon.base.View.BaseActivity;
import com.fizzer.doraemon.base.View.Dialog.DialogUtils;

import butterknife.BindView;
import butterknife.OnClick;


public class TestPage extends BaseActivity {
    @BindView(R.id.testInfo)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.Test1,R.id.Test2})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.Test1:
                DialogUtils.showBottomDialog(this);
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
