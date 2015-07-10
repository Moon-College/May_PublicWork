package com.tz.xmldata;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import com.tz.xmldata.R;
import com.tz.xmldata.bean.Student;
import com.tz.xmldata.utils.DataUtils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel.Stub;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	
	private EditText et_userName;
	private EditText et_password;
	private Button btn_login;
	private Button btn_xml;
	
	private String userName,password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_userName = (EditText) findViewById(R.id.userName);
		et_password = (EditText) findViewById(R.id.password);
		
		//如果用登陆成功，获取用户名和密码，设置给编辑框
//		String name = getUserData("name");
//		String pwd = getUserData("pwd");
		
		String name = (String) DataUtils.getData(this, "user", "name", String.class);
		String pwd = (String) DataUtils.getData(this, "user", "pwd", String.class);
		et_userName.setText(name);
		et_password.setText(pwd);
		
		btn_login = (Button) findViewById(R.id.login);
		btn_xml = (Button) findViewById(R.id.xml);
		
		btn_login.setOnClickListener(this);
		btn_xml.setOnClickListener(this);
		
		//打开数据库
//		this.openOrCreateDatabase("students.db", Context.MODE_WORLD_WRITEABLE, null);
	}

	@Override
	public void onClick(View v) {	
		userName = et_userName.getText().toString().trim();
		password = et_password.getText().toString().trim();
		if(userName.equals("admin")&&password.equals("123456")){
			//保存用户信息用户名和密码,sharedpreference存储
			saveUserDate(userName, password);
		}
		try {
			parserXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * pull方式解析xml
	 * @throws Exception 
	 */
	public void parserXml() throws Exception{
		List<Student> stus = new ArrayList<Student>();
		XmlPullParser pull = Xml.newPullParser(); //创建xml解析的核心类
		InputStream inputStream = this.getAssets().open("students.xml");  //注意要写后缀名.xml
//		pull.setInput(inputStream, "uft-8");
		pull.setInput(inputStream, "gbk");
		int type = pull.getEventType();  //解析第一行的类型
		Student stu = null;    //注意stu声明的位置？？？
		while(type!=XmlPullParser.END_DOCUMENT){
			//继续往下解析
			switch (type) {
			case XmlPullParser.START_TAG: //开始标记
				//主要解析<stu>
				String startTagName = pull.getName(); //开始标记名字
				if(startTagName.equals("stu")){
					stu = new Student();
					int count = pull.getAttributeCount();
					for (int i = 0; i < count; i++) {
						String id = pull.getAttributeValue(i);
						stu.setId(Integer.valueOf(id));
					}
				}else if(startTagName.equals("name")){
					String name = pull.nextText();
					stu.setName(name);
				}else if(startTagName.equals("age")){
					String age = pull.nextText();
					stu.setAge(Integer.valueOf(age));
				}else if(startTagName.equals("no")){
					String no = pull.nextText();
					stu.setNo(Integer.valueOf(no));
				}
				break;
			case XmlPullParser.END_TAG:
				String endTagName = pull.getName();  //结束标记名字
				if(endTagName.equals("stu")){  
					stus.add(stu); //将stu添加到stus中,name,age,no结束标记不用解析
				}
				break;
			default:
				break;
			}
			type = pull.next(); //解析下一个stu,注意位置？？？
		}
		for (Student student : stus) {
			Log.i("INFO", student.getName());
			Log.i("INFO", String.valueOf(student.getAge()));
		}
	}
	
	/**
	 * 通过key获取用户信息
	 * @param key
	 * @return
	 */
	public String getUserData(String key){
		SharedPreferences sharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE);
		String str = sharedPreferences.getString(key, "");
		return str;
	}

	/**
	 * 保存用户信息
	 * @param name
	 * @param pwd
	 */
	public void saveUserDate(String name, String pwd) {
		//"user" xml文件名
		SharedPreferences sharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE);
		Editor edit = sharedPreferences.edit();  //编辑者,sharedPreferences的秘书
		edit.putString("name", userName);
		edit.putString("pwd", password);
		edit.commit(); //提交
	}

}
