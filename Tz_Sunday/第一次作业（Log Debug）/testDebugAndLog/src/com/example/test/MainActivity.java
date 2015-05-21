package com.example.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.example.utils.MyLog;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity   implements OnClickListener {
       /**button to print log*/
	private Button log;
	private InputStream inputStream;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyLog.isDebug=true;
		setContentView(R.layout.activity_main);// 设置该界面的试图
		//初始化试图的
	    initView();
	}
	
     private void initView() {
      //init button by id 
 	   log=(Button) findViewById(R.id.log);
 				//set listener
 	   log.setOnClickListener(this);
	}

	//按钮点击到 ， 从而手机日志
	public void onClick(View v) {
	      try {
			redLog();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    /**
     *     @author  Sunday 110
     * @param InputStreamReader 
     * @throws IOException 
     *     
     *   
     * */
	private void redLog() throws IOException {
		// TODO Auto-generated method stub
        Log.i("Info", "这是我的第一个android程序");
        StringBuffer sb=new StringBuffer();
        ArrayList<String> cd=new ArrayList<String>();
        cd.add("logcat");
        cd.add("-d");//收集一次而停止
        cd.add("-s");//过滤
        cd.add("warn");
        //用于执行命令行
       Process exec = Runtime.getRuntime().exec(cd.toArray(new String [cd.size()]));
       inputStream = exec.getInputStream();
       InputStreamReader  buInputStream= new InputStreamReader(inputStream);
       BufferedReader bufferReader=new BufferedReader(buInputStream) ;
       String  str=null;
       while((str=bufferReader.readLine())!=null){
    	   sb.append(str);
    	   sb.append("\n");
       }
       Toast.makeText(this, sb.toString(),1000).show();
       System.out.println("我是一个特种兵");
    	   
       
        
        
	}
   
}
