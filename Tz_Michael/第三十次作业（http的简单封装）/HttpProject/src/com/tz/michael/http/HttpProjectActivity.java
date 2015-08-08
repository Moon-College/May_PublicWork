package com.tz.michael.http;

import java.util.HashMap;
import java.util.Map;

import com.tz.michael.utils.HttpCallBack;
import com.tz.michael.utils.HttpUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

public class HttpProjectActivity extends Activity {
	
	String path="http://10.0.2.2:8080/WebServerTest/MyServerlet";
	private Context mContext;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext=this;
        Map<String, String> params=new HashMap<String, String>();
        params.put("userName", "michael");
        params.put("pwd", "123");
        HttpUtils httpUtils=new HttpUtils(path, "GET", params, new HttpCallBack() {
			
			public void success(String data) {
				Toast.makeText(mContext, "success"+data, 0).show();
			}
			
			public void failed(String error) {
				Toast.makeText(mContext, error, 0).show();
			}
		});
        httpUtils.getDataFromNet();
    }
}