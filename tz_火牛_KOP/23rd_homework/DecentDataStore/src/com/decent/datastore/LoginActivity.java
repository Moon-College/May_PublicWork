package com.decent.datastore;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.decent.datastore.bean.UserSession;
import com.decent.datastore.bean.Users;
import com.decent.datastore.util.DecentSQLiteHelper;
import com.decent.datastore.util.DencetDataStoreService;
import com.decent.datastore.util.DencetDataStoreServiceManager;
import com.decent.decentutil.DecentLogUtil;
import com.decent.decentutil.ReflictionUtil;

public class LoginActivity extends Activity implements OnClickListener {

	private static final String TAG = "LoginActivity";
	private EditText username;
	private EditText password;
	private Button loginBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		ReflictionUtil.InjectionView(LoginActivity.class.getName(),
				R.class.getName(), this);
		loginBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// ��õ�¼���û�������
		String strUsername = username.getText().toString().trim();
		String strPassword = password.getText().toString().trim();
		// ʹ��DencetDataStoreServiceManager��ȡddss
		DencetDataStoreService ddss = DencetDataStoreServiceManager
				.getDencetDataStoreService(this, "sport_records.db");
		// �����ѯ���������Ҳ�ѯ
		String[] selectionArgs = { strUsername, strPassword };
		// ֱ��ʹ��ddss�ļ򵥲�ѯ
		List<Users> userList = ddss.query(null, Users.class,
				"name = ? and passwd = ?", selectionArgs);
		DecentLogUtil.d(TAG, "userList.size()=" + userList.size());
		// ���ص�userList��size>0,���¼�ɹ�
		if (userList.size() > 0) {
			Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
			// �Ѳ�ѯ��õ�Users�������뵽UserSession
			UserSession us = (UserSession) getApplication();
			us.setLoginUser(userList.get(0));
			// ����ת����ɾ�Ĳ��б�Ľ���
			Intent intent = new Intent();
			intent.setClass(this, SportRecordsActivity.class);
			startActivity(intent);
			finish();
		} else {
			Toast.makeText(this, "login fail", Toast.LENGTH_LONG).show();
		}
	}

}
