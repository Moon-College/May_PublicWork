package com.lin.mytzwork;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lin.mytzwork.utils.ReflectUtil;

/**
 * Created by Administrator on 2015/6/6.
 */
public class OtherActivity extends Activity implements View.OnClickListener {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReflectUtil.initView(this);
        tv.setOnClickListener(this);

        tv.setText("我是OtherActivity");


    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}
