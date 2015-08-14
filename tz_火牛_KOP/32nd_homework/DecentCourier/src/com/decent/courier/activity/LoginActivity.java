package com.decent.courier.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.decent.courier.utils.DecentToast;
import com.decent.courier.utils.MD5;
import com.decent.courier.utils.MyDataUtils;
import com.decent.courier.utils.RegExUtil;

public class LoginActivity extends BaseActivity implements OnClickListener,
		IHttpRequestCallback {
	private EditText username;
	private EditText password;
	private Button loginBtn;
	private ProgressDialog mPd;
    private Button bt_register;
	
	@Override
	public void initContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_login);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		username = (EditText) findViewById(R.id.et_login_name);
		password = (EditText) findViewById(R.id.et_login_pwd);
		loginBtn = (Button) findViewById(R.id.bt_login);
		bt_register = (Button) findViewById(R.id.bt_register);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		loginBtn.setOnClickListener(this);
		bt_register.setOnClickListener(this);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		//读取已经保存的登录信息
		String storedUserName = MyDataUtils.getData(this, DecentConstants.USER, DecentConstants.USERNAME, String.class);
		String storedPassword = MyDataUtils.getData(this, DecentConstants.USER, DecentConstants.PASSWORD, String.class);
		//设置到控件中
		username.setText(storedUserName);
		password.setText(storedPassword);
		//如果两个都不是空尝试自动登录
		if(!RegExUtil.isStringEmpty(storedUserName)&&!RegExUtil.isStringEmpty(storedPassword)){
			mPd = ProgressDialog.show(this, "自动登陆", "登陆进行中");
			doHttpLogin(storedUserName, storedPassword, true);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_login:
			// TODO Auto-generated method stub
			// 获取控件值
			String strUsername = username.getText().toString().trim();
			String strPassword = password.getText().toString().trim();
			// 判断是否为空
			if (RegExUtil.isStringEmpty(strUsername)
					|| RegExUtil.isStringEmpty(strPassword)) {
				DecentToast.showToastLong(this, "用户名或者不能为空");
				return;
			}
			// 判断过了，开始登录
			mPd = ProgressDialog.show(this, "正在登陆", "登陆进行中");
			// 默认activityapp 1234567890是可以登录
			doHttpLogin(strUsername, strPassword, false);
			
			break;
		case R.id.bt_register:
			Intent intent = new Intent();
			intent.setClass(this, RegisterActivity.class);
			startActivity(intent);
        	break;
		default:
			break;
		}
	}

	private void doHttpLogin(String strUsername, String strPassword,
			boolean isPasswordMd5) {
		String codedPassword = strPassword;
		if (!isPasswordMd5) {
			codedPassword = MD5.GetMD5Code(strPassword);
		}
		// TODO Auto-generated method stub
		HttpRequestLogin httpRequestLogin = new HttpRequestLogin(strUsername,
				codedPassword);
		mHttpRequest.doQuestByPostMethod(DecentConstants.LOGIN_URL,
				httpRequestLogin, true, this);
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

		HttpResultLogin httpResultLogin = JSON.parseObject(result,
				HttpResultLogin.class);
		Toast.makeText(this, httpResultLogin.getMsg(), Toast.LENGTH_LONG)
				.show();
		// 如果ret是ok的话,记录password的md5格式到sharedpreference,跳转到主界面
		if (httpResultLogin.getRet() == DecentConstants.RET_OK) {
			String strUsername = username.getText().toString().trim();
			String strPassword = MD5.GetMD5Code(password.getText().toString()
					.trim());

			Map<String, Object> map = new HashMap<String, Object>();
			map.put(DecentConstants.USERNAME, strUsername);
			// 存的是md5加密之后的password
			map.put(DecentConstants.PASSWORD, strPassword);
			MyDataUtils.putData(this, DecentConstants.USER, map);
			// MainActivity
		}
		// 如果不是ok，一般就是用户名密码错了(前面的toast会提醒)，就停在这个界面
	}

}
