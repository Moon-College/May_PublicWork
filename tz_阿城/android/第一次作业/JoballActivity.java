package com.casit.hc.joball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import debugway.MyLog;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class JoballActivity extends Activity{
    /** Called when the activity is first created. */
    Button conlog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
     //   conlog = (Button) findViewById(R.id.conlog);
      //  conlog.setOnClickListener(this);    
    }

    public void startone() throws Throwable{
		  Log.println(Log.WARN, "INFO", "start connectlog");
    	MyLog.showLoginfo(this, "logcat -d -s INFO");
    }
	/*public void startone(View view) {
		// TODO Auto-generated method stub
		   MyLog.println(Log.WARN, "INFO", "start connectlog");
			try {
				MyLog.showLoginfo(this, "logcat -d -s INFO");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}*/
	 /*  public void start(){
	MyLog.println(Log.WARN, "INFO", "start connectlog");
	StringBuffer sb = new StringBuffer();
//	ArrayList<String> cmdLine = new ArrayList<String>();//收集拿不需要ADB
    Process exec = null;
    String cmdLine;
//	ArrayList<String> cmdLine = new ArrayList<String>();//收集拿不需要ADB
 //   cmdLine.add("logcat");
  //  cmdLine.add("-d");
  //  cmdLine.add("-s");
 //   cmdLine.add("INFO"); //为什么不用数组，没有ARRAYLIST
 //   cmdLine.add("*:e"); 
    cmdLine = "logcat -d -s INFO";
     try {
	//	exec = Runtime.getRuntime().exec(cmdLine.toArray(new String [cmdLine.size()]));
    	 exec = Runtime.getRuntime().exec(cmdLine);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	//	exec = Runtime.getRuntime().exec("logcat -d -s INFO");

    InputStream inPutString = exec.getInputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inPutString);//装饰器
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    String str;
    try {
		while (((str = bufferedReader.readLine())!= null)){
			sb.append(str); 
			sb.append("\n");
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    Toast.makeText(this, sb.toString(), 5000).show();    	
}*/

}