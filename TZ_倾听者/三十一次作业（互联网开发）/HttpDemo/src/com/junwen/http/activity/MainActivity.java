package com.junwen.http.activity;

import java.util.HashMap;
import java.util.Map;

import com.junwen.http.R;
import com.junwen.http.inteface.OnConnectionListener;
import com.junwen.http.util.HttpUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements OnConnectionListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    /**
     * 点击获取数据
     * @param view
     */
    public void onclick(View view){
    	Map<String, String> data = new HashMap<String, String>();
    	data.put("username", "junwen");
    	data.put("password", "123");
    	HttpUtil http = new HttpUtil("GET", "http://10.0.2.2:8080/HttpConnection_server/HttpServlet", data, this);
//    	http.doRequestByHttpurlConnection();
    	http.doRequestByHttpClient();
    }
    
    /**
     * 当成功访问到时
     */
	public void success(String result) {
		Toast.makeText(MainActivity.this, result, 1000).show();
	}
	
	/**
	 * 当失败时
	 */
	public void fial(String result) {
		Toast.makeText(MainActivity.this, "失败"+result, 1000).show();
	}
}