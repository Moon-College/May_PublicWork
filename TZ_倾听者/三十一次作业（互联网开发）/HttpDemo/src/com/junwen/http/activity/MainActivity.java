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
     * �����ȡ����
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
     * ���ɹ����ʵ�ʱ
     */
	public void success(String result) {
		Toast.makeText(MainActivity.this, result, 1000).show();
	}
	
	/**
	 * ��ʧ��ʱ
	 */
	public void fial(String result) {
		Toast.makeText(MainActivity.this, "ʧ��"+result, 1000).show();
	}
}