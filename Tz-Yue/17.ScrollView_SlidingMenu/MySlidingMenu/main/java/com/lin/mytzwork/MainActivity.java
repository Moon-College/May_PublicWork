package com.lin.mytzwork;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lin.mytzwork.util.ReflectUtil;
import com.lin.mytzwork.view.MySlidingMenu;


public class MainActivity extends Activity {
    private MySlidingMenu mySlidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReflectUtil.initView(this);
        boolean b = mySlidingMenu.menuIsDisplay();
        Log.i("debug", b ? "弹出" : "隐藏");
    }


}
