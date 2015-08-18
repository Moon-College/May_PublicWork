package cn.ysh.wweb_android_client;

import java.util.HashMap;
import java.util.Map;

import cn.ysh.callback.MyCallBack;
import cn.ysh.http.MyHttpUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {

	private MyHttpUtils utils;
	 private static final String ANDROID_SERVLET_URL = "http://192.168.226.1:8080/web_android_server/MyAndroidServlet";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void doGet(View v){
		Map<String, String> data = new HashMap<String, String>();
		data.put("userName", "yeshihua");
		data.put("password", "yeshihua123");
		utils = new MyHttpUtils(MainActivity.ANDROID_SERVLET_URL, data, "GET", new CallBack());
		utils.doRequestByHttpUrlConnection();
	}
	
	public void doPost(View v){
		Map<String, String> data = new HashMap<String, String>();
		data.put("userName", "yeshihua");
		data.put("password", "yeshihua123");
		utils = new MyHttpUtils(MainActivity.ANDROID_SERVLET_URL, data, "POST", new CallBack());
		utils.doRequestByHttpUrlConnection();
	}
	
	private class CallBack implements MyCallBack{

		@Override
		public void succeed(String result) {
			Toast.makeText(MainActivity.this, result, 1000).show();
			
		}

		@Override
		public void failed(String result) {
			Toast.makeText(MainActivity.this, "«Î«Û“Ï≥£a", 1000).show();
			
		}
		
	}
	
}
