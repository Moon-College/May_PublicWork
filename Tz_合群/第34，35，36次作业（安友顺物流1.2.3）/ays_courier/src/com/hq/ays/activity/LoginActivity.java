package com.hq.ays.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hq.ays.common.BaseActivity;
import com.hq.ays.entity.request.RequestCourierLogin;
import com.hq.ays.entity.response.AysResponse;
import com.hq.ays.entity.result.ResultUserLogin;
import com.hq.ays.utils.MD5;
import com.hq.ays.utils.MyConstants;
import com.hq.ays.activity.R;

public class LoginActivity extends BaseActivity {
	private EditText etLoginName;
	private EditText etLoginPwd;
	private ProgressDialog dialog;
	@Override
	public void setContentView() {
		setContentView(R.layout.activity_login);
	}

	@Override
	public void initViews() {
		etLoginName = (EditText) findViewById(R.id.et_login_name);
		etLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
	}

	@Override
	public void initListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}
	
	public void back(View v){
		finish();
	}
	
	public void startRegist(View v){
		Intent intent = new Intent();
		intent.setClass(this, RegisteActivity.class);
		startActivity(intent);
		finish();
	}
	public void startLogin(View v){
		String userName = etLoginName.getText().toString().trim();
		String password = etLoginPwd.getText().toString().trim();
		if(userName.equals("")||password.equals("")){
			Toast.makeText(this, "账号或密码不能为空", 1000).show();
			return;
		}
		password = MD5.GetMD5Code(password);
		dialog = ProgressDialog.show(this, null, getString(R.string.login_msg));
		//登录
		RequestCourierLogin courierLogin = new RequestCourierLogin();
		courierLogin.setUserName(userName);
		courierLogin.setPassword(password);
		//异步任务登录
		LoginTask task = new LoginTask();
		task.execute(courierLogin);
	}
	
	private class LoginTask extends AsyncTask<RequestCourierLogin, Void, AysResponse>{

		@Override
		protected AysResponse doInBackground(RequestCourierLogin... params) {
			// TODO Auto-generated method stub
			RequestCourierLogin courierLogin = params[0];
			AysResponse aysResponse = request.RequestByPost(MyConstants.LOGIN, courierLogin);
			return aysResponse;
		}
		
		@Override
		protected void onPostExecute(AysResponse result) {
			// TODO Auto-generated method stub
			dialog.dismiss();
			if(result.getException() == null){
				//请求成功，只是表示连接服务器成功了
				ResultUserLogin resultUserlogin = new ResultUserLogin();
				resultUserlogin = request.formatResponse(result, resultUserlogin);
				Toast.makeText(LoginActivity.this, resultUserlogin.getMsg(), 1000).show();
				if(resultUserlogin.getRet() == 0){
					//跳转
					startBusinessActivity();
				}
			}else{
				//出现了异常
				String exception = request.formatException(result);
				Toast.makeText(LoginActivity.this, exception, 1000).show();
			}
			super.onPostExecute(result);
		}
		
	}
	
	public void startBusinessActivity(){
		Intent intent = new Intent();
		intent.setClass(this, BusinessActivity.class);
		startActivity(intent);
		finish();
	}
}
