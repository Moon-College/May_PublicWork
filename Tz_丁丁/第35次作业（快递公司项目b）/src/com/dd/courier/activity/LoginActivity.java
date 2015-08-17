package com.dd.courier.activity;

import java.util.HashMap;
import java.util.Map;

import com.dd.ays_dd_courier.R;
import com.dd.courier.bean.request.HttpRequestLogin;
import com.dd.courier.bean.result.HttpResultLogin;
import com.dd.courier.common.BaseActivity;
import com.dd.courier.common.LocationService;
import com.dd.courier.common.MyConstants;
import com.dd.courier.listener.HttpCallback;
import com.dd.courier.utils.Check;
import com.dd.courier.utils.MD5;
import com.dd.courier.utils.MyDataUtils;
import com.dd.courier.utils.RegistCheck;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends BaseActivity{
	private ProgressDialog dialog;
	private EditText userName;
	private EditText password;
	private RegistCheck check;
	private String userNameStr;
	private String passwordStr;
	@Override
	public void initContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_login);
	}

	@Override
	public void initView() {
		userName = (EditText) findViewById(R.id.et_login_name);
		password = (EditText) findViewById(R.id.et_login_pwd);
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		check = new RegistCheck();
		//如果已经有账号和密码，需要自动登陆
		userNameStr = (String) MyDataUtils.getData(this, MyConstants.ACCOUNT, MyConstants.USER_NAME, String.class);
		passwordStr = (String) MyDataUtils.getData(this, MyConstants.ACCOUNT, MyConstants.PASSWORD, String.class);
		if(Check.isEmpty(userNameStr)||Check.isEmpty(passwordStr)){
			//说明没有登陆过
		}else{
			//说明已经登陆过
			userName.setText(userNameStr);
			password.setText(passwordStr);
			doLogin(true);
		}
		
	}
	
	public void back(View v){
		//点击返回
		this.finish();
	}

	public void startLogin(View v){
		//点击登陆按钮
		userNameStr = userName.getText().toString().trim();
		passwordStr = password.getText().toString().trim();
		//正则
		if(Check.isEmpty(userNameStr)||Check.isEmpty(passwordStr)){
			Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
		}else{
			doLogin(false);
		}
	}
	
	
	public void startRegist(View v){
		//点击注册
		Intent intent = new Intent();
		intent.setClass(this, RegistActivity.class);
		startActivity(intent);
	}
	public void doLogin(boolean isMd5){
		if(!isMd5){
			passwordStr= MD5.GetMD5Code(passwordStr);
		}
		HttpRequestLogin login = new HttpRequestLogin(userNameStr,passwordStr);
		dialog = new ProgressDialog(this);
		dialog.setMessage("正在登陆");
		dialog.show();
		request.doQuestByPostMethod(MyConstants.LOGIN, login, new HttpCallback() {
			
			public void success(String result) {
				// TODO Auto-generated method stub
				if(dialog.isShowing()){
					dialog.dismiss();
				}
				HttpResultLogin resultLogin = request.formatResult(result, HttpResultLogin.class);
				Toast.makeText(LoginActivity.this, resultLogin.getMsg(), 1000).show();
				if(resultLogin.getRet() == 0){
					//登陆成功
					Map<String,Object> map = new HashMap<String,Object>();
					map.put(MyConstants.USER_NAME, userNameStr);
					map.put(MyConstants.PASSWORD, passwordStr);
					MyDataUtils.putData(LoginActivity.this, MyConstants.ACCOUNT, map);
					Intent intent = new Intent();
					intent.setClass(LoginActivity.this, BusinessActivity.class);
					startActivity(intent);
					finish();
					//启动服务
					Intent service = new Intent();
					service.setClass(LoginActivity.this, LocationService.class);
					startService(service);
				}else{
					//登陆失败
				}
			}
			
			public void fail(String result) {
				// TODO Auto-generated method stub
				if(dialog.isShowing()){
					dialog.dismiss();
				}
				Toast.makeText(LoginActivity.this, result, 1000).show();
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
