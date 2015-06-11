package com.lin.mytzwork;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import com.lin.mytzwork.util.ReflectUtil;

/**
 * Created by Administrator on 2015/6/9.
 */
public class BaseActivity extends Activity {
    protected TextView tv;
    protected Button bt;
    protected Activity activity;


    public void initView() {
        setContentView(R.layout.activity_main);
        activity = this;
        ReflectUtil.initView(this);
    }
}
