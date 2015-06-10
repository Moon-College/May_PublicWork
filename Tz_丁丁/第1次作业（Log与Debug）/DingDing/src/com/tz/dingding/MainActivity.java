package com.tz.dingding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.tz.dingding.utils.LogUtil;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
    Button mConLog,mClear;
    StringBuffer sb;
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mConLog = (Button) findViewById(R.id.conLog);//ͨ��id�ҵ��İ�ť
        mClear = (Button) findViewById(R.id.clear);//ͨ��id�ҵ��İ�ť
        LogUtil.isDebug = true;//debugģʽ����
       mConLog.setOnClickListener(this);
       mClear.setOnClickListener(this);
    }
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.conLog:
			//��ť��������ˣ��ռ��ռ���־
			try {
				readLog();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case R.id.clear:
			sb.setLength(0);
			break;
			
		default:
			break;
		}
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	private void readLog() throws IOException {
		LogUtil.i("INFO", "start connectLog");
		Log.w("WARN", "warn");
		Log.w("tag:W", "tag:W");
		Log.e("error", "tag:W");
		sb = new StringBuffer();
		ArrayList<String> cmdLine = new ArrayList<String>();
		cmdLine.add("logcat");
		cmdLine.add("-d");//�ռ�һ����־ֹͣ
		cmdLine.add( "-s");  //����
		cmdLine.add( "*:w");//w������־
		Process exec = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
		//��ȡִ��������������
		InputStream inputStream = exec.getInputStream();
		InputStreamReader buInputStreamReader = new InputStreamReader(inputStream);//װ����ģʽ
		BufferedReader bufferedReader = new BufferedReader(buInputStreamReader);//ֱ�Ӷ��ַ���
		String str = null;
		while((str = bufferedReader.readLine())!=null){
			sb.append(str);//ÿ��һ��ƴ�ӵ�sb����ȥ
			sb.append("\n");//ÿһ��һ�����з�
		}
		//��˾
		LogUtil.shortToast(this, sb.toString());
	}
	
}