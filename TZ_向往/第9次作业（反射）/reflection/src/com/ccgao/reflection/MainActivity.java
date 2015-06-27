package com.ccgao.reflection;

import com.ccgao.reflection.util.ReflectionUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6,tv7,tv_8,tv_9,tv_10;
	private ImageView img;
	private String str="ccgao";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        tv_1 = (TextView) findViewById(R.id.tv_1);
        ReflectionUtil.initView(this);
    }
    
    public void show(View v){
    	Toast.makeText(this, tv_10.getText(), Toast.LENGTH_LONG).show();
    }
}