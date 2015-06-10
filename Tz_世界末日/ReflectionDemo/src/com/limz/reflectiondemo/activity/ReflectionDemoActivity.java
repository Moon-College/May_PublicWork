package com.limz.reflectiondemo.activity;

import com.limz.reflectiondemo.utils.FindViewId;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReflectionDemoActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	
	private Button button1,button2,button3,button4,button5,button6;
	private TextView text1,text2,text3,text4,text5,text6;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //初始化
        init();
        //设置按钮监听
        linsen();
    }

    /**
     * 设置Button的监听
     */
    private void linsen() {
    	button1.setOnClickListener(this);
    	button2.setOnClickListener(this);
    	button3.setOnClickListener(this);
    	button4.setOnClickListener(this);
    	button5.setOnClickListener(this);
    	button6.setOnClickListener(this);
	}

	/**
     * 初始化
     * @author limingzhu
     * 
     */
	private void init() {
		FindViewId.initView(this);
	}

	public void onClick(View v) {
		String textString = null;
		switch (v.getId()) {
			case R.id.button1:
				textString = "你点击的是按钮1， 它对应的内容为：" + 
						text1.getText().toString().trim();
				break;
				
			case R.id.button2:
				textString = "你点击的是按钮2， 它对应的内容为：" + 
						text2.getText().toString().trim();
				break;
				
			case R.id.button3:
				textString = "你点击的是按钮3， 它对应的内容为：" + 
						text3.getText().toString().trim();
				break;
				
			case R.id.button4:
				textString = "你点击的是按钮4， 它对应的内容为：" + 
						text4.getText().toString().trim();
				break;
				
			case R.id.button5:
				textString = "你点击的是按钮5， 它对应的内容为：" + 
						text5.getText().toString().trim();
				break;
				
			case R.id.button6:
				textString = "你点击的是按钮6， 它对应的内容为：" + 
						text6.getText().toString().trim();
				break;
	
			default:
				break;
		}
		if(textString != null) {
			Toast.makeText(this, textString, Toast.LENGTH_LONG).show();
		}
	}
}