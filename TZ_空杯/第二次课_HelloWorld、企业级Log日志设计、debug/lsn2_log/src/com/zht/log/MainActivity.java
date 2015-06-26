package com.zht.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Config.isDebug = true;
	}

	/**
	 * onClick:收集错误信息. <br/>
	 * 
	 * @author hongtao
	 */
	public void onClick(View v) {
		Config.i("log日志课程_1");
		Config.i("log日志课程_2");
		try {
			String msg = Config.readLog(this);
			Config.showTip(this, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
