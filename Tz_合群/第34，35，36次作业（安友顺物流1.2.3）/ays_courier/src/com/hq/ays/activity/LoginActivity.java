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
			Toast.makeText(this, "�˺Ż����벻��Ϊ��", 1000).show();
			return;
		}
		password = MD5.GetMD5Code(password);
		dialog = ProgressDialog.show(this, null, getString(R.string.login_msg));
		//��¼
		RequestCourierLogin courierLogin = new RequestCourierLogin();
		courierLogin.setUserName(userName);
		courierLogin.setPassword(password);
		//�첽�����¼
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
				//����ɹ���ֻ�Ǳ�ʾ���ӷ������ɹ���
				ResultUserLogin resultUserlogin = new ResultUserLogin();
				resultUserlogin = request.formatResponse(result, resultUserlogin);
				Toast.makeText(LoginActivity.this, resultUserlogin.getMsg(), 1000).show();
				if(resultUserlogin.getRet() == 0){
					//��ת
					startBusinessActivity();
				}
			}else{
				//�������쳣
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
