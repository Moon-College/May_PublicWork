package com.tz.ays.michael.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tz.ays.michael.R;
import com.tz.ays.michael.bean.request.HttpRequestLoginBean;
import com.tz.ays.michael.bean.response.HttpResultLoginBean;
import com.tz.ays.michael.callback.IHttpCallBack;
import com.tz.ays.michael.common.BaseActivity;
import com.tz.ays.michael.common.Constrant;
import com.tz.ays.michael.http.MyParser;
import com.tz.ays.michael.utils.Check;
import com.tz.ays.michael.utils.MD5;
import com.tz.ays.michael.utils.SharedPreferenceUtil;

public class LoginActivity extends BaseActivity {

	private ProgressDialog dialog;
	private EditText userName;
	private EditText password;
	private String userNameStr;
	private String passwordStr;
	private Context mContext;
	
	@Override
	public void setContentLayout() {
		setContentView(R.layout.login_lay);
		mContext=this;
	}

	@Override
	public void initViews() {
		userName = (EditText) findViewById(R.id.et_login_name);
		password = (EditText) findViewById(R.id.et_login_pwd);
	}

	@Override
	public void setListenners() {

	}

	@Override
	public void getData() {
		//如果已经有账号和密码，需要自动登陆
		userNameStr = (String) SharedPreferenceUtil.getValueFromSP(mContext, Constrant.ACCOUNT, Constrant.USER_NAME, String.class);
		passwordStr = (String) SharedPreferenceUtil.getValueFromSP(mContext, Constrant.ACCOUNT, Constrant.PASSWORD, String.class);
		if(check.strNotNullCheck(userNameStr)||check.strNotNullCheck(passwordStr)){
			//说明没有登陆过
		}else{
			//自动登陆
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
		if(check.pHoneCheck(userNameStr)&&check.strNotNullCheck(passwordStr)){
			doLogin(false);
		}
	}
	
	/**
	 * 登陆的方法
	 * @param b 是否登陆过
	 */
	private void doLogin(boolean isMd5) {
		if(!isMd5){
			passwordStr=MD5.GetMD5Code(passwordStr);
		}
		HttpRequestLoginBean loginBean=new HttpRequestLoginBean(userNameStr, passwordStr);
		hRequest.doQuestByPostMethod(Constrant.LOGIN, loginBean, new IHttpCallBack() {
			
			public void success(String result) {
				HttpResultLoginBean resultBean=MyParser.parseToObject(result, HttpResultLoginBean.class);
				if(resultBean.getRet()==0){
					//登陆成功
					Toast.makeText(mContext, "登陆成功", Toast.LENGTH_SHORT).show();
					//保存账号密码，用于自动登陆时使用
					Map<String,Object> map=new HashMap<String,Object>();
					map.put(Constrant.USER_NAME, userNameStr);
					map.put(Constrant.PASSWORD, passwordStr);
					SharedPreferenceUtil.writeToSP(mContext, Constrant.ACCOUNT, map);
					//跳转主界面
				}else{
					//登陆成功
					Toast.makeText(mContext, "登陆失败", Toast.LENGTH_SHORT).show();
				}
			}
			
			public void failed(String result) {
				Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void startRegist(View v){
		//点击注册
		Intent intent = new Intent();
		intent.setClass(this, RegistActivity.class);
		startActivity(intent);
	}
	
}
