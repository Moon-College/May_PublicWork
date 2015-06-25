package com.lin.mytzwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2015/6/7.
 * 单例模式，最后一个死亡，
 *
 */
public class AAcitivity extends BaseActivity {
    private int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        bt.setText("跳转到BActivity");
        tv.setText("AAcitivity");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, BActivity.class);
                startActivity(intent);
            }
        });

        initIntent(getIntent());

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initIntent(intent);
    }

    private void initIntent(Intent intent) {
        int num = intent.getIntExtra("num", 0);
        a += num;
        tv.setText(a + "");
    }
}
