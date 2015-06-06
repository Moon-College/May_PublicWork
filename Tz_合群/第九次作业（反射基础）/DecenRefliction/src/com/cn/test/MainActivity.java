package com.cn.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.test.utils.Reflection;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private Button bt1,bt2,bt3,bt4;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Reflection.initView(this);
    }
    public void click(View v) {
    	//如果测试成功就会打印出有用
		Toast.makeText(this, bt3.getText(), Toast.LENGTH_SHORT).show();
	}
    
}