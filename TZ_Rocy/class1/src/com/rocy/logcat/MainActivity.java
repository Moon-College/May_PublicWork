package com.rocy.logcat;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.rocy.logcat.util.MyLog;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener{
	private Button mButton;
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MyLog.isOpenLog=true;
		mButton=(Button) findViewById(R.id.btn_logcat);
		mTextView=(TextView) findViewById(R.id.tv_logcat);
		mButton.setOnClickListener(this);
		mTextView.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_logcat:
			MyLog.e("myerror", "o my old");
			StringBuffer sb =new StringBuffer();
			ArrayList<String> arr =new ArrayList<String>();
			arr.add("logcat");
			arr.add("-s");
			arr.add("-d");
			arr.add("myerror:e");
			try {
				Process exec = Runtime.getRuntime().exec(arr.toArray(new String[arr.size()]));
				InputStream stream = exec.getInputStream();
				InputStreamReader buffis  =new InputStreamReader(stream);
				BufferedReader reader =new BufferedReader(buffis);
				while (reader.readLine()!=null) {
					sb.append(reader.readLine());
					
				}
				Toast.makeText(MainActivity.this, sb.toString(), 1000).show();
				//mTextView.setText(sb.toString());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}
}
