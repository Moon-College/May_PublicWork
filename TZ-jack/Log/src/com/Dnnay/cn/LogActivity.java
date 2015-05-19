package com.Dnnay.cn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.Dnnay.untils.Mylog;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class LogActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.button).setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {		
		Toast.makeText(this, "ccc", 0).show();		
		// TODO Auto-generated method stub
		//读取日志
		Log.i("info", "cxc");
		List<String> list=new ArrayList<String>();
		list.add("logcat");
		list.add("-d");
		list.add("-s");//获取 一次
		list.add("INFO");
		try {
			Process process=Runtime.getRuntime().exec(list.toArray(new String[list.size()]));
			InputStream input=process.getInputStream();
			InputStreamReader inputStreamReader=new InputStreamReader(input);
			BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
			StringBuffer a=new StringBuffer();
			String st;
			while((st=bufferedReader.readLine())!=null){
				a.append(st);
				a.append("\n");
			}
		 Toast.makeText(this, a.toString(), 0).show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
}