package com.dawndaybreak.logappliction;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.dawndaybreak.logappliction.utils.LogUtils;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button buttonCollectLog;
	private Spinner spinner;
	private String[] data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// set isDebug to LogUtils can print log
		LogUtils.setDebug(true);
		
		initData();
		initView();
	}

	private void initData() {
		data = new String[] { LogUtils.Level.VERBOSE, LogUtils.Level.DEBUG,
				LogUtils.Level.INFO, LogUtils.Level.WARN, LogUtils.Level.ERROR };
	}

	/**
	 * initialize view
	 */
	private void initView() {
		LogUtils.info("INFO", "start initialize view");
		
		buttonCollectLog = (Button) findViewById(R.id.buttonCollectLog);
		buttonCollectLog.setOnClickListener(this);
		
		spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);
		spinner.setAdapter(adapter);
		
		LogUtils.info("INFO", "initialize view complete");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// get selected log level
		String level = spinner.getSelectedItem().toString();
//		String logMessage = LogUtils
//				.collectLogMessageByLevel(LogUtils.Level.ERROR);
		String logMessage = LogUtils
				.collectLogMessageByLevel(level);
		Toast.makeText(this, logMessage, Toast.LENGTH_SHORT).show();
	}

}
