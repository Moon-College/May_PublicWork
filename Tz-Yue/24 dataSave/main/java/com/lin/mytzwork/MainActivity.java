package com.lin.mytzwork;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lin.mytzwork.util.ReflectUtil;
import com.lin.mytzwork.util.ShareDataUtil;


public class MainActivity extends Activity implements View.OnClickListener {

    private EditText et_name, et_pwd;
    private Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReflectUtil.initView(this);
        initView();
        initData();
    }

    private void initData() {
        String name = ShareDataUtil.readData(this, "name");
        String pwd = ShareDataUtil.readData(this, "pwd");
        et_name.setText(name);
        et_pwd.setText(pwd);
    }

    private void initView() {
        bt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString().trim();
        String pwd = et_pwd.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入用户名！", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }


        if ("admin".equals(name) && "admin".equals(pwd)) {
            ShareDataUtil.saveData(this, "name", name);
            ShareDataUtil.saveData(this, "pwd", pwd);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("登录成功");
            builder.setPositiveButton("确认", null);
            builder.show();
        } else {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
            et_name.setText("");
            et_pwd.setText("");
            ShareDataUtil.saveData(this, "name", "");
            ShareDataUtil.saveData(this, "pwd", "");
        }


    }
}
