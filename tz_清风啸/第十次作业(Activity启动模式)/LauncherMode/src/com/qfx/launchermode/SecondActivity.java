package com.qfx.launchermode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
	}
	
	/**
	 * Activity启动模式（以栈的形式先进后出，每启动一个Activity，都以层叠的方式往栈顶覆盖）：
	 * standard模式：不设置系统会默认为该模式。每次启动Activity都会重新在栈顶创建一个新的实例
	 * singleTop模式：如果在栈顶启动同一个Activity，系统不会创建新的实例
	 * singleTask模式：如果栈里面已经存在Activity的实例，再次启动这个Activity，不会重新创建新的实例，
	 * 				并且移除这个已存在的实例之上的所有activity
	 * singleInstance：不会创建新的实例，会重新开辟一个栈，每个该模式的Activity都会开辟一个单独的栈。关闭Activity时，
	 * 				会先关闭所有其他栈的activity，最后再关闭该模式的activity
	 * 				两个程序测试：一个程序里面的某个activity设置为该模式，当第二个程序启动该activity，并在该activity里面启动第一个程
	 * 				序的Activity，那么关闭顺序是先关第一个程序的所有其他栈，再关该单利模式对应的栈，最后关第二个程序。如果只是在第二个程序里面
	 * 				启动第一个程序为该模式的activity，那么关闭顺序，就只是在第二程序里面进行，先关闭第一个activity的单利，再正常关闭第二个
	 * 				程序所有activity
	 * @param 
	 */
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setClass(this, ThirdActivity.class);
		startActivity(intent);
	}
}
