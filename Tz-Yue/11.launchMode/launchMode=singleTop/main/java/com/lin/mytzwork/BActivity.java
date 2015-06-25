package com.lin.mytzwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/6/7.
 */
public class BActivity extends BaseActivity {
    private int num = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        num = getIntent().getIntExtra("num", -1);
        tv.setText("BActivity:" + num);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*独享栈顶，自己跳自己只会有一个自己*/
                Intent intent = new Intent(activity, BActivity.class);
                intent.putExtra("num", num++);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        tv.setText("BActivity:" + num);
    }
}
