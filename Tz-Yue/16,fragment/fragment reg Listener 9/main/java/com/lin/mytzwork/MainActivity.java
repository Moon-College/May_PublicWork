package com.lin.mytzwork;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lin.mytzwork.fragment.RegFistFragment;
import com.lin.mytzwork.fragment.RegSecondFragment;
import com.lin.mytzwork.util.ReflectUtil;


public class MainActivity extends FragmentActivity implements View.OnClickListener, FragmentManager.OnBackStackChangedListener, RegFistFragment.OnInput9Listener {

    private FrameLayout fl;
    private Button bt;
    private Activity activity;
    private RegSecondFragment regSecondFragment;
    private RegFistFragment regFistFragment;
    private int step = 0;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        ReflectUtil.initView(activity);
        initView();
    }

    private void initView() {
        bt.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);

        regFistFragment = new RegFistFragment();
        fragmentManager.beginTransaction().replace(R.id.fl, regFistFragment).commit();
        regFistFragment.setInput9Listener(this);
    }

    @Override
    public void onClick(View v) {
        replaceFragment();

        switch (step) {
            case 0:
                bt.setText("注册");
                step = 1;
                break;
            case 1:
                StringBuilder builder = new StringBuilder();
                builder.append("用户名：" + regFistFragment.getEt1().getText().toString().trim());
                builder.append("\n");
                builder.append("密码：" + regFistFragment.getEt2().getText().toString().trim());
                builder.append("\n");
                builder.append("姓名" + regSecondFragment.getEt1().getText().toString().trim());
                builder.append("\n");
                builder.append("地址：" + regSecondFragment.getEt2().getText().toString().trim());
                Toast.makeText(activity, builder.toString(), Toast.LENGTH_LONG).show();
                break;

        }
    }

    private void replaceFragment() {

        if (regSecondFragment == null) {
            regSecondFragment = new RegSecondFragment();
        }

        fragmentManager.beginTransaction().replace(R.id.fl, regSecondFragment).addToBackStack(regSecondFragment.getClass().getName()).commit();
    }

    @Override
    public void onBackStackChanged() {

        if (fragmentManager.getBackStackEntryCount() == 0) {
            step = 0;
            bt.setText("下一步");
        }

    }

    @Override
    public void is9(String s) {
        Toast.makeText(activity, s + " 包含9", Toast.LENGTH_LONG).show();
    }
}
