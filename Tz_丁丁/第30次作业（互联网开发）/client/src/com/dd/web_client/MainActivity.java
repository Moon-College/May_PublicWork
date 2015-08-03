package com.dd.web_client;

import java.util.HashMap;
import java.util.Map;

import com.dd.web_client.util.MyHttpUtils;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	 private MyHttpUtils httpUtils;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	  public void conn(View v){
	    	Map<String,String> data = new HashMap<String, String>();
	    	data.put("userName", "Danny");
	    	data.put("password", "123");
	    	httpUtils = new MyHttpUtils("服务器地址", data, "POST", new MyHttpCallback() {
				
				public void success(String result) {
					// TODO Auto-generated method stub
					Toast.makeText(MainActivity.this, result, 1000).show();
				}
				
				public void failed(String result) {
					// TODO Auto-generated method stub
					Toast.makeText(MainActivity.this, "请求异常", 1000).show();
				}
			});
	    	httpUtils.doRequestByHttpUrlConnection();
	    }
}
