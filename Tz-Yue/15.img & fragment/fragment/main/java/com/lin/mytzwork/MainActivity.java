package com.lin.mytzwork;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.lin.mytzwork.util.ReflectUtil;


public class MainActivity extends FragmentActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReflectUtil.initView(this);
    }


    public TextView getTv() {
        return tv;
    }
}
