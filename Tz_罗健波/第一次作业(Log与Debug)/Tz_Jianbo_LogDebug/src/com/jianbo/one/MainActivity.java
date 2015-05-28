package com.jianbo.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import com.jianbo.utils.RountLog;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener{
	public static final String TAG = "Log Export";
    private Button btnLog;
	private TextView tvShowLog;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
    }
	
	/**
	 * 初始化控件
	 */
	private void init() {
		btnLog = (Button)findViewById(R.id.btnLog);
		tvShowLog = (TextView)findViewById(R.id.tvShowLog);
		btnLog.setOnClickListener(this);
		RountLog.isDebug = true;
	}

	@Override
	public void onClick(View v) {
		RountLog.i(TAG, "My Frist Onclick Button");
		try {
			achieveLog("I");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取日志  
	 * @throws IOException 
	 */
	private void achieveLog(String typString) throws IOException {
		StringBuffer sBuffer = new StringBuffer();
		ArrayList<String> cmdline = new ArrayList<String>();
		cmdline.add("logcat");
		cmdline.add("-d");//收集日志一次停止
//		cmdline.add("-s");//过滤
//		cmdline.add("com.jianbo.one");
		cmdline.add("*:"+typString);
		//获取要输入的命令并执行
		Process exec = Runtime.getRuntime().exec(cmdline.toArray(new String[cmdline.size()]));
		//获取执行命令后的输入流
		InputStream inStream = exec.getInputStream();
		//装饰器模式
		InputStreamReader readerInput = new InputStreamReader(inStream);
		//直接读取字符串
		BufferedReader reader = new BufferedReader(readerInput);
		String string = null;
		while ((string = reader.readLine()) != null) {
			sBuffer.append(string);//读取拼接
			sBuffer.append("\n");
		}
		tvShowLog.setText(sBuffer);
	}

}
