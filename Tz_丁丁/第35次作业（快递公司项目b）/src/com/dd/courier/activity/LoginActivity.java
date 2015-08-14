package com.dd.courier.activity;

import com.dd.ays_dd_courier.R;
import com.dd.courier.bean.request.HttpRequestLogin;
import com.dd.courier.bean.result.HttpResultLogin;
import com.dd.courier.common.BaseActivity;
import com.dd.courier.common.MyConstants;
import com.dd.courier.listener.HttpCallback;
import com.dd.courier.utils.MD5;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.Toast;


public class LoginActivity extends BaseActivity{

	private ProgressDialog dialog;

	@Override
	public void initContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.login_activity);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

	public void dopost(View v){
		HttpRequestLogin login = new HttpRequestLogin("activityapp",MD5.GetMD5Code("1234567890"));
		dialog = new ProgressDialog(this);
		dialog.setMessage("正在加载");
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
