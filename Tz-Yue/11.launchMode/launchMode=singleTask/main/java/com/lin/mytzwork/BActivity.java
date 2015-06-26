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
        tv.setText("BActivity:" + num);
        bt.setText("回到MainActivity");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*独享栈顶，MainActivity上的Activity重用MainActivity*/
                Intent intent = new Intent(activity, MainActivity.class);
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
