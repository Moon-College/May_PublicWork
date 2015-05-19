package com.tangzhi.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
    private Button log;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initUI();
    }
	
	/**
	 * 初始化控件及绑定监听事件
	 */
	private void initUI() {
		log = (Button)findViewById(R.id.log_btn_get);
		log.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.log_btn_get://捕获日子
			try {
				readLog("w");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}		
	}
	/**
	 * 捕获日子信息
	 * @throws IOException 
	 */
	private void readLog(String strName) throws IOException {       
        DebugUtil.warn(Log.WARN, "WARN", "测试中。。。");
		ArrayList<String> line = new ArrayList<String>();
		line.add("logcat");
		line.add("-d");
//		line.add("-s");
		line.add("*:"+strName);
		Process execs = Runtime.getRuntime().exec(line.toArray(new String[line.size()]));
		InputStream input = execs.getInputStream();
		InputStreamReader isReader = new InputStreamReader(input);
		BufferedReader reader = new BufferedReader(isReader);
		String str = null;
		StringBuffer sb = new StringBuffer();
		while ((str = reader.readLine())!=null) {
			sb.append(str);
			sb.append("\n");
		}
		DebugUtil.toast(this, sb.toString());
		
	}
}