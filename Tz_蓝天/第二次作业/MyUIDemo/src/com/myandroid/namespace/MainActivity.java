package com.myandroid.namespace;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        seachUI();
    }
    
    /**
     * 设置线性布局的方法
     */
    public void seachUI(){
    	//创建一个线性布局容器
    	LinearLayout ll=new LinearLayout(MainActivity.this);
    	 //设置线性布局容器的 宽和高
    	LayoutParams layoutparams=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    	//应用到线性布局容器
    	ll.setLayoutParams(layoutparams);
    	//设置线性布局容器的方向为水平方向
    	ll.setOrientation(LinearLayout.HORIZONTAL);
    	
    	//创建一个文本输入框
    	EditText et=new EditText(MainActivity.this);
    	//设置文本输入框的  宽和高
    	LinearLayout.LayoutParams llp=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
    	et.setLayoutParams(llp);
    	
    	//把文本输入添加到线性布局容器里
    	ll.addView(et, 0);
    	
    	Button bnt=new Button(MainActivity.this);
//    	LinearLayout.LayoutParams llpBnt=new LayoutParams(width, height, weight)
    	LayoutParams lpBnt=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    	bnt.setLayoutParams(lpBnt);
    	bnt.setText("搜索");
    	//把按钮添加到线性布局容器里
    	ll.addView(bnt, 1);
    	
    	//设置显示的布局文件
    	setContentView(ll);
    }
}