package com.xigua.layoutdesign;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initSearchView();		      
	}

	/**
	 * @author XiGua
	 * 
	 */
	private void initSearchView() {
		//new一个线性布局，设置水平布局，宽高属性
		LinearLayout linear = new LinearLayout(this);
		LayoutParams lparams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		linear.setLayoutParams(lparams);
        linear.setOrientation(LinearLayout.HORIZONTAL);
        
        //new一个编辑框，设置权重，宽高，以及单行输入
        EditText edit = new EditText(this);
        LinearLayout.LayoutParams eparams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3);
        edit.setLayoutParams(eparams);
        edit.setSingleLine();
        
        //new一个按钮，设置文本，字体大小，宽高以及权重
        Button btn = new Button(this);
        LinearLayout.LayoutParams bparams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        btn.setLayoutParams(bparams);
        btn.setText("搜索");
        btn.setTextSize(20);
        
        //将子view加入到线性布局
        linear.addView(edit, 0);
        linear.addView(btn, 1);
        
        //将线性布局加载到phone window中去
        setContentView(linear);		
	}


}
