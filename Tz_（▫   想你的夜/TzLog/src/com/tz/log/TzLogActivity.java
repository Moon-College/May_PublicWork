package com.tz.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.tz.log.utils.MyLog;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TzLogActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	private Button bt_log;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        MyLog.isdebug=true;
        bt_log=(Button) findViewById(R.id.bt_log);
        bt_log.setOnClickListener(this);
        
       MyLog.w("warn", "my name luo");
       MyLog.println(Log.WARN, "warn", "mmmmmmmmm");
       MyLog.println(Log.ERROR, "warn", "mmmmmmmmm");
       MyLog.println(Log.INFO, "warn", "mmmmmmmmm");
    }

	@Override
	public void onClick(View v) {
		String logcon = readlog("E");
		Toast.makeText(this, logcon, Toast.LENGTH_SHORT).show();
	}

	private String readlog(String tag) {
		StringBuffer sb = new StringBuffer();
		ArrayList<String> conlist = new ArrayList<String>();
		conlist.add("logcat");
		conlist.add("-d"); // 收集一次日志停止
		//conlist.add("-s"); // 过滤
		conlist.add("*:"+tag); // 过滤条件
		Process exec;
		try {
			exec = Runtime.getRuntime().exec(
					conlist.toArray(new String[conlist.size()]));
			//获得执行命令输出流
			InputStream inputStream = exec.getInputStream();
			
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				sb.append(str);
				sb.append("\n");
			}
			if (sb.toString().length() != 0) {
				return sb.toString();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
}