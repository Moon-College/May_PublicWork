package com.junwen.activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.junwen.utils.InputUtil;
import com.junwen.utils.LogUitl;

public class MainActivity extends Activity {
	// Log
	private TextView tv_log;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 初始化
		initView();
		LogUitl.w("INFO", "初始化");
	}

	/**
	 * 初始化控件
	 * 
	 * @author June
	 */
	private void initView() {
		tv_log = (TextView) findViewById(R.id.tv_logInfo);
	}

	/**
	 * 获取Log信息
	 * 
	 * @param view
	 * @author June
	 * @throws IOException
	 */
	public void loadlog(View view) throws IOException {
		//命令集合
		List<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		cmdLine.add("-d");
		cmdLine.add("W");
		//执行命令
		Process exec = Runtime.getRuntime().exec(
				cmdLine.toArray(new String[cmdLine.size()]));
		//获取返回的Log信息
		InputStream inputStream = exec.getInputStream();
		//解析数据，返回字符串
		String message = InputUtil.getString(inputStream);
		//设置TextView
		if (message != null) {
			tv_log.setText(message);
		}
	}
}