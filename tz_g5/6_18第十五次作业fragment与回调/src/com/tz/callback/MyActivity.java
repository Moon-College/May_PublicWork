package com.tz.callback;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends Activity implements FragmentManager.OnBackStackChangedListener,CallBack {

    private FragmentManager manager;

    private FragmentTransaction transaction;

    private Button btn_left, btn_right;

    private TextView tv_info;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        manager = getFragmentManager();
        manager.addOnBackStackChangedListener(this);
        btn_left = (Button) findViewById(R.id.btn_left);
        btn_right = (Button) findViewById(R.id.btn_right);

        tv_info = (TextView) findViewById(R.id.tv_info);

        pushLoginFragment();

        btn_left.setText("登陆");
        btn_right.setText("注册");
    }

    private void pushLoginFragment() {
        transaction = manager.beginTransaction();
        transaction.add(R.id.container, new LoginFragment(), LoginFragment.TAG);
        transaction.addToBackStack(LoginFragment.TAG);
        transaction.commit();
        btn_left.setText("登陆");
        btn_right.setText("注册");
    }

    private void pushRegisterFragment() {
        transaction = manager.beginTransaction();
        transaction.add(R.id.container, new RegisterFragment(), RegisterFragment.TAG);
        transaction.addToBackStack(RegisterFragment.TAG);
        transaction.commit();
        btn_left.setText("取消");
        btn_right.setText("确定");
    }


    public void onClick(View view) {
        FragmentManager.BackStackEntry entry = getCurrentEntry();
        Log.d("id", entry.getId() + "");
        Log.d("name", entry.getName());
        switch (view.getId()) {
            case R.id.btn_left:
                if (btn_left.getText().toString().equals("取消")) {
                    btn_left.setText("登陆");
                    btn_right.setText("注册");
                    manager.popBackStack();
                } else {
                }
                break;
            case R.id.btn_right:
                if (btn_right.getText().toString().equals("注册")) {
                    pushRegisterFragment();
                }
                break;
        }
    }

    private FragmentManager.BackStackEntry getCurrentEntry() {
        int count = manager.getBackStackEntryCount();
        return manager.getBackStackEntryAt(count - 1);
    }

    @Override
    public void onBackStackChanged() {
        Log.d("change", "change");
    }

    @Override
    public void call(CharSequence cs) {
        if (cs.toString().contains("9")) {
            tv_info.setText("账号输入错误");
        } else {
            tv_info.setText("");

        }
    }
}
