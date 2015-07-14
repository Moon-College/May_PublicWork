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
		
		//����õ�½�ɹ�����ȡ�û��������룬���ø��༭��
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
		
		//�����ݿ�
//		this.openOrCreateDatabase("students.db", Context.MODE_WORLD_WRITEABLE, null);
	}

	@Override
	public void onClick(View v) {	
		userName = et_userName.getText().toString().trim();
		password = et_password.getText().toString().trim();
		if(userName.equals("admin")&&password.equals("123456")){
			//�����û���Ϣ�û���������,sharedpreference�洢
			saveUserDate(userName, password);
		}
		try {
			parserXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * pull��ʽ����xml
	 * @throws Exception 
	 */
	public void parserXml() throws Exception{
		List<Student> stus = new ArrayList<Student>();
		XmlPullParser pull = Xml.newPullParser(); //����xml�����ĺ�����
		InputStream inputStream = this.getAssets().open("students.xml");  //ע��Ҫд��׺��.xml
//		pull.setInput(inputStream, "uft-8");
		pull.setInput(inputStream, "gbk");
		int type = pull.getEventType();  //������һ�е�����
		Student stu = null;    //ע��stu������λ�ã�����
		while(type!=XmlPullParser.END_DOCUMENT){
			//�������½���
			switch (type) {
			case XmlPullParser.START_TAG: //��ʼ���
				//��Ҫ����<stu>
				String startTagName = pull.getName(); //��ʼ�������
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
				String endTagName = pull.getName();  //�����������
				if(endTagName.equals("stu")){  
					stus.add(stu); //��stu��ӵ�stus��,name,age,no������ǲ��ý���
				}
				break;
			default:
				break;
			}
			type = pull.next(); //������һ��stu,ע��λ�ã�����
		}
		for (Student student : stus) {
			Log.i("INFO", student.getName());
			Log.i("INFO", String.valueOf(student.getAge()));
		}
	}
	
	/**
	 * ͨ��key��ȡ�û���Ϣ
	 * @param key
	 * @return
	 */
	public String getUserData(String key){
		SharedPreferences sharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE);
		String str = sharedPreferences.getString(key, "");
		return str;
	}

	/**
	 * �����û���Ϣ
	 * @param name
	 * @param pwd
	 */
	public void saveUserDate(String name, String pwd) {
		//"user" xml�ļ���
		SharedPreferences sharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE);
		Editor edit = sharedPreferences.edit();  //�༭��,sharedPreferences������
		edit.putString("name", userName);
		edit.putString("pwd", password);
		edit.commit(); //�ύ
	}

}
