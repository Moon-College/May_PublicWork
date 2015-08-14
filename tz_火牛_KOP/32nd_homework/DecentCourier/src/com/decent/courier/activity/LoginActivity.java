package com.decent.courier.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.decent.courier.bean.request.HttpRequestLogin;
import com.decent.courier.bean.result.HttpResultLogin;
import com.decent.courier.common.BaseActivity;
import com.decent.courier.listener.IHttpRequestCallback;
import com.decent.courier.utils.DecentConstants;
import com.decent.courier.utils.DecentLogUtil;
import com.decent.courier.utils.MD5;

public class LoginActivity extends BaseActivity implements OnClickListener, IHttpRequestCallback {
	private EditText username;
	private EditText password;
	private Button loginBtn;
	private ProgressDialog mPd;

	@Override
	public void initContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.login);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		loginBtn = (Button) findViewById(R.id.loginBtn);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		loginBtn.setOnClickListener(this);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String strUsername = username.getText().toString().trim();
		String strPassword = password.getText().toString().trim();
		mPd = ProgressDialog.show(this, "正在登陆", "登陆进行中");
		doHttpLogin(strUsername, strPassword);
	}

	private void doHttpLogin(String strUsername, String strPassword) {
		// TODO Auto-generated method stub
		HttpRequestLogin httpRequestLogin = new HttpRequestLogin(strUsername,
				MD5.GetMD5Code(strPassword));
		mHttpRequest.doQuestByGetMethod(DecentConstants.LOGIN_URL, httpRequestLogin, true, this);
	}

	@Override
	public void onRequestFail(String result) {
		DecentLogUtil.d("into onRequestFail");
		mPd.dismiss();
		Toast.makeText(this, result, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onRequestSuccess(String result) {
		DecentLogUtil.d("into onRequestSuccess");
		mPd.dismiss();
		// TODO Auto-generated method stub
		HttpResultLogin httpResultLogin = JSON.parseObject(result, HttpResultLogin.class);
		Toast.makeText(this, httpResultLogin.getMsg(), Toast.LENGTH_LONG).show();
	}

}
