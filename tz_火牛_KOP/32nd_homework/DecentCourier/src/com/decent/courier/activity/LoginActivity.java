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
		//��ȡ�Ѿ�����ĵ�¼��Ϣ
		String storedUserName = MyDataUtils.getData(this, DecentConstants.USER, DecentConstants.USERNAME, String.class);
		String storedPassword = MyDataUtils.getData(this, DecentConstants.USER, DecentConstants.PASSWORD, String.class);
		//���õ��ؼ���
		username.setText(storedUserName);
		password.setText(storedPassword);
		//������������ǿճ����Զ���¼
		if(!RegExUtil.isStringEmpty(storedUserName)&&!RegExUtil.isStringEmpty(storedPassword)){
			mPd = ProgressDialog.show(this, "�Զ���½", "��½������");
			doHttpLogin(storedUserName, storedPassword, true);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_login:
			// TODO Auto-generated method stub
			// ��ȡ�ؼ�ֵ
			String strUsername = username.getText().toString().trim();
			String strPassword = password.getText().toString().trim();
			// �ж��Ƿ�Ϊ��
			if (RegExUtil.isStringEmpty(strUsername)
					|| RegExUtil.isStringEmpty(strPassword)) {
				DecentToast.showToastLong(this, "�û������߲���Ϊ��");
				return;
			}
			// �жϹ��ˣ���ʼ��¼
			mPd = ProgressDialog.show(this, "���ڵ�½", "��½������");
			// Ĭ��activityapp 1234567890�ǿ��Ե�¼
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
		// ���ret��ok�Ļ�,��¼password��md5��ʽ��sharedpreference,��ת��������
		if (httpResultLogin.getRet() == DecentConstants.RET_OK) {
			String strUsername = username.getText().toString().trim();
			String strPassword = MD5.GetMD5Code(password.getText().toString()
					.trim());

			Map<String, Object> map = new HashMap<String, Object>();
			map.put(DecentConstants.USERNAME, strUsername);
			// �����md5����֮���password
			map.put(DecentConstants.PASSWORD, strPassword);
			MyDataUtils.putData(this, DecentConstants.USER, map);
			// MainActivity
		}
		// �������ok��һ������û����������(ǰ���toast������)����ͣ���������
	}

}
