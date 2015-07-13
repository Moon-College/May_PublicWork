package com.junwen.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlite.R;
import com.junwen.bean.Data;
import com.junwen.bean.RequestHead;
import com.junwen.util.UserDataUtil;

public class MainActivity extends Activity {

	private EditText user; // 用户名
	private EditText pass; // 密码
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		getUserData();
	}

	private void getUserData() {
		String name = (String) UserDataUtil.getUserData(this, "User",
				Context.MODE_PRIVATE, "username");
		Integer pass1 = (Integer) UserDataUtil.getUserData(this, "User",
				Context.MODE_PRIVATE, "password");
		if (name != null && pass1 != null) {
			user.setText(name);
			pass.setText(pass1 + "");
		}
	}

	/**
	 * 初始化
	 */
	private void initView() {
		user = (EditText) findViewById(R.id.username);
		pass = (EditText) findViewById(R.id.password);
		tv = (TextView) findViewById(R.id.showText);
	}

	/**
	 * 按注册的时候
	 * 
	 * @param view
	 */
	public void regist(View view) {
		switch (view.getId()) {
		case R.id.login:
			String username = user.getText().toString().trim();
			String password = pass.getText().toString().trim();
			if (username.equals("") || password.equals("")) {
				Toast.makeText(MainActivity.this, "账号或者密码不可为空", 0).show();
				return;
			}
			if (username.equals("junwen") & password.equals("123")) {
				Toast.makeText(MainActivity.this, "登陆成功", 0).show();
				saveUserData(username, password);
			}
			break;
		case R.id.pullXml:
			// 解析XML
			try {
				parseXML();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}

	}

	/**
	 * 解析XML
	 * 
	 * @throws Exception
	 */
	private void parseXML() throws Exception {
		InputStream inputStream = this.getAssets().open("main.xml");
		XmlPullParser pull = Xml.newPullParser();
		pull.setInput(inputStream, "utf-8");
		int type = pull.getEventType();
		RequestHead head = null;
		Data data = null;
		while (type != XmlPullParser.END_DOCUMENT) {
			switch (type) {
			case XmlPullParser.START_TAG:
				String name = pull.getName();
				if(name.equals("requestHead")){
					head =  new RequestHead();
				}
				else if(name.equals("busCode")){
					String busCode = pull.nextText();
					head.setBusCode(busCode);
				}
				else if(name.equals("hosCode")){
					String hosCode = pull.nextText();
					head.setHosCode(hosCode);
				}
				else if(name.equals("tradeSrc")){
					String tradeSrc = pull.nextText();
					head.setTradeSrc(tradeSrc);
				}
				else if(name.equals("terminalNo")){
					String terminalNo = pull.nextText();
					head.setTerminalNo(terminalNo);
				}
				else if(name.equals("operNo")){
					String operNo = pull.nextText();
					head.setOperNo(operNo);
				}
				else if(name.equals("tradeDate")){
					String tradeDate = pull.nextText();
					head.setTradeDate(tradeDate);
				}
				else if(name.equals("tradeTime")){
					String tradeTime = pull.nextText();
					head.setTradeTime(tradeTime);
				}
				// Data
				else if(name.equals("data")){
					data = new Data();
				}
				else if(name.toString().trim().equals("schedulingDate")){
					String schedulingDate = pull.nextText();
					System.out.println(schedulingDate+"data");
					data.setSchedulingDate(schedulingDate);
				}
				else if(name.equals("schedulingType")){
					String schedulingType = pull.nextText();
					data.setSchedulingType(schedulingType);
				}
				else if(name.equals("requestPage")){
					String requestPage = pull.nextText();
					data.setRequestPage(requestPage);
				}
				else if(name.equals("pageSize")){
					String pageSize = pull.nextText();
					data.setPageSize(pageSize);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			default:
				break;
			}
			type = pull.next();
		}
		String sb  = new String();
		sb = 
				"RequestHead:------------------"+"\n"+
						"BusCode:"+head.getBusCode()+"\n"+
						"HosCode:"+head.getHosCode()+"\n"+
						"OperNo:"+head.getOperNo()+"\n"+
						"TerminalNo:"+head.getTerminalNo()+"\n"+
						"TradeDate:"+head.getTradeDate()+"\n"+
						"TradeSrc:"+head.getTradeSrc()+"\n"+
						"TradeTime:"+head.getTradeTime()+"\n"+
				"Data:------------------"+"\n"+
						"PageSize:"+data.getPageSize()+"\n"+
						"RequestPage:"+data.getRequestPage()+"\n"+
						"SchedulingDate:"+data.getSchedulingDate()+"\n"+
						"SchedulingType:"+data.getSchedulingType()+"\n"
				;
		tv.setText(sb);
		System.out.println(sb);
	}

	/**
	 * 存储用户名和密码
	 * 
	 * @param username
	 * @param password
	 */
	private void saveUserData(String username, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", 123);
		UserDataUtil.saveUserData(this, "User", Context.MODE_PRIVATE, map);
	}
}
