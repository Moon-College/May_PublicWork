package com.first.work.logjar.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.first.work.logjar.R;
import com.first.work.logjar.utils.GetAppLogInformation;
import com.first.work.logjar.utils.MyLog;

public class MainActivity extends Activity {

	private Button btn_get2, btn_get3, btn_get4, btn_get5, btn_get6,
			btn_get_tag;
	private ListView show_log;
	private List<String> mLogInfo=new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		printlog();
		inin_id();
		init_listener();
	}

	private void printlog() {
		MyLog.isDebug=true;
		try {
			MyLog.ClearLogInformation();
		} catch (IOException e) {
			MyLog.printMyLog(Log.VERBOSE, "VERBOSE", e.toString());
		}
		Log.v("dddd", "ewewe");
		MyLog.printMyLog(Log.VERBOSE, "liyawei", "hahhahahhaVERBOSE");
		MyLog.printMyLog(Log.DEBUG, "liyawei", "hahhahahhaDEBUG");
		MyLog.printMyLog(Log.INFO, "liyawei", "hahhahahhaINFO");
		MyLog.printMyLog(Log.WARN, "liyawei", "hahhahahhaWARN");
		MyLog.printMyLog(Log.ERROR, "liyawei", "hahhahahhaERROR");
		MyLog.printMyLog(Log.ASSERT, "liyawei", "hahhahahhaASSERT");
	}
	

	/**
	 * 初始化控件的id
	 */
	private void inin_id() {
		btn_get2 = (Button) findViewById(R.id.btn1);
		btn_get3 = (Button) findViewById(R.id.btn2);
		btn_get4 = (Button) findViewById(R.id.btn3);
		btn_get5 = (Button) findViewById(R.id.btn4);
		btn_get6 = (Button) findViewById(R.id.btn5);
		btn_get_tag = (Button) findViewById(R.id.btn6);
		show_log = (ListView) findViewById(R.id.show_log);
	}

	/**
	 * 注册按钮的点击事件
	 */
	private void init_listener() {
		btn_get2.setOnClickListener(onclick);
		btn_get3.setOnClickListener(onclick);
		btn_get4.setOnClickListener(onclick);
		btn_get5.setOnClickListener(onclick);
		btn_get6.setOnClickListener(onclick);
		btn_get_tag.setOnClickListener(onclick);
	}

	/**
	 * 定义事件
	 */
	private OnClickListener onclick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn1:
				try {
					mLogInfo.clear();
					mLogInfo = GetAppLogInformation.GetLogInformation(
							Log.VERBOSE, null);
				} catch (IOException e) {
					MyLog.printMyLog(Log.VERBOSE, "VERBOSE", e.toString());
				}
				show_log(mLogInfo);
				break;
			case R.id.btn2:
				try {
					mLogInfo.clear();
					mLogInfo = GetAppLogInformation.GetLogInformation(
							Log.DEBUG, null);
				} catch (IOException e) {
					MyLog.printMyLog(Log.DEBUG, "DEBUG", e.toString());
				}
				show_log(mLogInfo);
				break;
			case R.id.btn3:
				try {
					mLogInfo.clear();
					mLogInfo = GetAppLogInformation.GetLogInformation(
							Log.INFO, null);
				} catch (IOException e) {
					MyLog.printMyLog(Log.INFO, "INFO", e.toString());
				}
				show_log(mLogInfo);
				break;
			case R.id.btn4:
				try {
					mLogInfo.clear();
					mLogInfo = GetAppLogInformation.GetLogInformation(
							Log.WARN, null);
				} catch (IOException e) {
					MyLog.printMyLog(Log.WARN, "WARN", e.toString());
				}
				show_log(mLogInfo);
				break;
			case R.id.btn5:
				try {
					mLogInfo.clear();
					mLogInfo = GetAppLogInformation.GetLogInformation(
							Log.ERROR, null);
				} catch (IOException e) {
					MyLog.printMyLog(Log.ERROR, "ERROR", e.toString());
				}
				show_log(mLogInfo);
				break;
			case R.id.btn6:
				try {
					mLogInfo.clear();
					mLogInfo = GetAppLogInformation.GetLogInformation(
							Log.VERBOSE, "liyawei");
				} catch (IOException e) {
					MyLog.printMyLog(Log.VERBOSE, "liyawei", e.toString());
				}
				show_log(mLogInfo);
				break;
			default:
				break;
			}
		}
	};

	/**
	 * 显示获取到的LOG
	 * @param mLogInfo
	 */
	private void show_log(List<String> mLogInfo) {
		if (mLogInfo != null && mLogInfo.size() > 0) {
			show_log.setAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_expandable_list_item_1, mLogInfo));
		}
		else
		{
			show_log.setAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_expandable_list_item_1,getData()));
		}
	}
	/**
	 * 无数据时候的显示内容
	 * @return
	 */
	private List<String> getData(){ 
	        List<String> data = new ArrayList<String>(); 
	        data.add("没有数据啊"); 
	        return data; 
	    } 

}
