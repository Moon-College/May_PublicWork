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
		// 获得登录的用户名密码
		String strUsername = username.getText().toString().trim();
		String strPassword = password.getText().toString().trim();
		// 使用DencetDataStoreServiceManager获取ddss
		DencetDataStoreService ddss = DencetDataStoreServiceManager
				.getDencetDataStoreService(this, "sport_records.db");
		// 构造查询条件，并且查询
		String[] selectionArgs = { strUsername, strPassword };
		// 直接使用ddss的简单查询
		List<Users> userList = ddss.query(null, Users.class,
				"name = ? and passwd = ?", selectionArgs);
		DecentLogUtil.d(TAG, "userList.size()=" + userList.size());
		// 返回的userList的size>0,则登录成功
		if (userList.size() > 0) {
			Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
			// 把查询获得的Users变量加入到UserSession
			UserSession us = (UserSession) getApplication();
			us.setLoginUser(userList.get(0));
			// 则跳转到增删改插列表的界面
			Intent intent = new Intent();
			intent.setClass(this, SportRecordsActivity.class);
			startActivity(intent);
			finish();
		} else {
			Toast.makeText(this, "login fail", Toast.LENGTH_LONG).show();
		}
	}

}
