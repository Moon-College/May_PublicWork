package com.example.com.tz.ssk.mylog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

    private final String TGA="MainActivity";
    private Button bt_go;
    private TextView tv_content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt_go=(Button) findViewById(R.id.bt_go);
		bt_go.setOnClickListener(this);
		tv_content=(TextView) findViewById(R.id.tv_content);
		LogUtils.isDebug=true;
		LogUtils.myPrintln(Log.WARN,TGA, "我是最牛逼的。");
		LogUtils.i(TGA, "我叫I..");
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_go:
			Process p;
			String strtxt="\n";
			try {
				p = Runtime.getRuntime().exec("logcat -d -s MainActivity");
				BufferedReader in=new BufferedReader(new InputStreamReader(p.getInputStream()));
			    String line=null;
			    while ((line=in.readLine())!=null) {
			    	strtxt += line + "\n"; 
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tv_content.setText(strtxt);
			break;
		default:
			break;
		}
	}

}
