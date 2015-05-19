package com.tz.dingding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.tz.dingding.utils.LogUtil;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
    Button mConLog,mClear;
    StringBuffer sb;
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mConLog = (Button) findViewById(R.id.conLog);//通过id找到的按钮
        mClear = (Button) findViewById(R.id.clear);//通过id找到的按钮
        LogUtil.isDebug = true;//debug模式开启
       mConLog.setOnClickListener(this);
       mClear.setOnClickListener(this);
    }
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.conLog:
			//按钮被点击到了，收集收集日志
			try {
				readLog();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case R.id.clear:
			sb.setLength(0);
			break;
			
		default:
			break;
		}
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	private void readLog() throws IOException {
		LogUtil.i("INFO", "start connectLog");
		Log.w("WARN", "warn");
		Log.w("tag:W", "tag:W");
		Log.e("error", "tag:W");
		sb = new StringBuffer();
		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		cmdLine.add("-d");//收集一次日志停止
		cmdLine.add( "-s");  //过滤
		cmdLine.add( "*:w");//w级别日志
		Process exec = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
		//获取执行命令后的输入流
		InputStream inputStream = exec.getInputStream();
		InputStreamReader buInputStreamReader = new InputStreamReader(inputStream);//装饰器模式
		BufferedReader bufferedReader = new BufferedReader(buInputStreamReader);//直接读字符串
		String str = null;
		while((str = bufferedReader.readLine())!=null){
			sb.append(str);//每读一行拼接到sb里面去
			sb.append("\n");//每一行一个换行符
		}
		//吐司
		LogUtil.shortToast(this, sb.toString());
	}
	
}