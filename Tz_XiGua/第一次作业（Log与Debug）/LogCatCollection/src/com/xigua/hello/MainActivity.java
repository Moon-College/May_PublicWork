package com.xigua.hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.xigua.hello.utils.XLog;

public class MainActivity extends Activity implements OnClickListener{

	private Button mButton;
	private AlertDialog alertDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		XLog.sDebug = true;
		XLog.v("vLog", "This is vLog");
		XLog.d("dLog", "This is dLog");
		XLog.i("iLog", "This is iLog");
		XLog.w("wLog", "This is wLog");
		XLog.e("eLog", "This is eLog");
		XLog.println(Log.INFO, "pLog", "This is pLog");
		
		mButton = (Button) findViewById(R.id.colLog);
		mButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
        try {
			readLog();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author XiGua
	 * @throws IOException
	 */
	private void readLog() throws IOException {
		 String str = null;
		 List<String> logInfo = new ArrayList<String>();
		 List<String> cmdLine = new ArrayList<String>();
		 cmdLine.add("logcat");
		 cmdLine.add("-d");
		 cmdLine.add("*:w");
		 Process exec = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
		 InputStream is = exec.getInputStream();
		 InputStreamReader reader = new InputStreamReader(is);
		 BufferedReader bf = new BufferedReader(reader);
		 while ((str=bf.readLine())!=null) {
			 logInfo.add(str);			
		}
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item, logInfo);
		 alertDialog = new AlertDialog.Builder(MainActivity.this).setTitle(R.string.log_info)
				 .setAdapter(adapter, null)
				 .setNegativeButton(getString(R.string.cancel), null)
				 .create();
		 alertDialog.show();
		 
	}

}
