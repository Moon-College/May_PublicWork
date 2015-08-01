package com.junwen.json.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jsondemo.R;
import com.junwen.json.callback.OnJsonListener;
import com.junwen.json.model.User;
import com.junwen.json.util.HttpUtil;

public class MainActivity extends Activity {
	
	private static final String SERVER_URL = "http://10.0.2.2:8080/Json_Server/JsonServlet";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onclick(View v){
		sendJSONForServer();
	}
	
	/**
	 * 发送JSON数据到服务器
	 */
	private void sendJSONForServer() {
		User user = new User();
		user.setmAge("18");
		user.setmName("俊文");
		user.setmPassword("630203");
		user.setmUsername("junwen");
		HttpUtil.doLoginByHttpClient(SERVER_URL, user,new OnJsonListener() {
			
			@Override
			public void onSuccess(User user) {
				Toast.makeText(MainActivity.this,"姓名"+user.getmName(), 0).show();
			}
			
			@Override
			public void onFail(String result) {
				Log.i("INFO", "失败了");
			}
		});
	}

}
