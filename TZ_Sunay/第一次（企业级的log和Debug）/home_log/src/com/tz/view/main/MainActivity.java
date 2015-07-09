package com.tz.view.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.home_log.R;
import com.tz.view.utils.MyUtils;

public class MainActivity extends Activity implements OnClickListener {
 private Button  btn_log;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_log = (Button) findViewById(R.id.button);
	  MyUtils.ISDUG = true;
	   btn_log.setOnClickListener(this);

	}
	@Override
	public void onClick(View v) {
		
           try {
			readLog("w");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
	}
	private void readLog(String ss) throws IOException {
      StringBuffer  buffer = new StringBuffer();
      ArrayList<String> list = new ArrayList<String>();
      list.add("logcat");
      list.add("-d"); //�ռ���һ�ε���־
      list.add("*:" + ss); 
	 
      //�ֻ�ϵͳ����־
     Process  exex = Runtime.getRuntime().exec(list.toArray(new String[list.size()]));
     //��ȡ������
     InputStream  input = exex.getInputStream();
     //װ������ģʽ
     InputStreamReader reader = new InputStreamReader(input);
      //ֱ�Ӷ�ȡ�ַ���
     BufferedReader br = new BufferedReader(reader);
     String str = null;
     while((str = br.readLine())  != null){
    	 buffer.append(str);
    	 
    	 buffer.append("\n"); //����
     }
     
     Toast.makeText(this, buffer.toString(), Toast.LENGTH_SHORT).show(); //������־����Ϣ
		
	}
}
