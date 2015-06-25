package com.lin.mytzwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends BaseActivity {
    private int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();


        tv.setText("MainActivity:" + num);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AAcitivity.class);
                intent.putExtra("num", 1);
                startActivity(intent);
            }
        });
    }


}
