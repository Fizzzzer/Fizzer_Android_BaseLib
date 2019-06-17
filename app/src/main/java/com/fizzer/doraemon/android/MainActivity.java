package com.fizzer.doraemon.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fizzer.doraemon.android.TestPage.TestPage;
import com.fizzer.doraemon.base.View.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @BindView(R.id.tvInfo)
    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btnJump)
    public void onClick(View view){
        startActivity(new Intent(this, TestPage.class));
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }
}
