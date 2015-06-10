package com.lin.mytzwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        tv.setText("MainActivity");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, AAcitivity.class));
            }
        });
    }

}
