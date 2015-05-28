package com.itskylin.android.mylog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.itskylin.android.mylog.utils.LogUtils;

/**
 * ClassName: MainActivity
 * @Description: TODO
 * @author BlueSky QQ：345066543
 * @date 2015年5月26日
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LogUtils.isDebug = true;
	}

	public void click(View v) {
		outLog("INFO");
	}

	/**
	 * @Description: TODO
	 * @param logLevel
	 *            日志级别
	 */
	private void outLog(String logLevel) {
		LogUtils.i(Log.INFO, "MainActivity", "输出...");
		try {
			ArrayList<String> cmds = new ArrayList<String>();
			cmds.add("logcat");
			cmds.add("-d");
			cmds.add("-s");
			cmds.add(logLevel);
			Process exec = Runtime.getRuntime().exec(cmds.toArray(new String[cmds.size()]));
			InputStream inputStream = exec.getInputStream();
			InputStreamReader isr = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(isr);
			String str = null;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null) {
				sb.append(str);
				sb.append("\n");
			}
			br.close();
			isr.close();
			inputStream.close();
			exec.destroy();

			Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}