package com.jim.hellolog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.jim.hellolog.utils.MyLog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Button errorButton, warnButton;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		MyLog.isDebug = true;
		errorButton = (Button) findViewById(R.id.er_button);
		warnButton = (Button) findViewById(R.id.w_button);
		MyLog.e("error", "Error...");
		MyLog.w("warn", "warn...");
		errorButton.setOnClickListener(this);
		warnButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		int mId = v.getId();
		switch (mId) {
		case R.id.er_button:
			readLog("error");
			break;
		case R.id.w_button:
			readLog("warn");
			break;
		default:
			break;
		}
	}

	private void readLog(String msg) {
		// TODO Auto-generated method stub

		StringBuffer sb = new StringBuffer();
		ArrayList<String> cmdLine = new ArrayList<String>();

		cmdLine.add("logcat");
		cmdLine.add("-d");
		cmdLine.add("-s");
		cmdLine.add(msg);

		try {
			Process exec = Runtime.getRuntime().exec(
					cmdLine.toArray(new String[cmdLine.size()]));
			InputStream is = exec.getInputStream();

			InputStreamReader inputStreamReader = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = reader.readLine()) != null) {
				sb.append(str);
				sb.append("\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
	}
}