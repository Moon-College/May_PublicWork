package com.decent.decentlaunchmode.action;

import com.decent.decentlaunchmode.util.ReflictionUtil;
import com.decentsoft.DecentLaunchMode.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityD extends Activity implements OnClickListener
{
	private TextView activityD;
	private Button gotoSelfD;
	private Button gotoCFromD;
	private Button gotoAFromD;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_d);
		ReflictionUtil.InjectionView(ActivityD.class.getName(),
				R.class.getName(), this);
		activityD.setText(this.toString()+",TaskId:"+this.getTaskId());
		gotoSelfD.setOnClickListener(this);
		gotoCFromD.setOnClickListener(this);
		gotoAFromD.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		/*
		 * singleInstance启动自己的话的TaskId不会变，this.toString也不会变
		 */
		case R.id.gotoSelfD:
			Intent intent = new Intent();
			intent.setClass(this, ActivityD.class);
			startActivity(intent);
			break;
		/*
		 * 这个时候从C启动D的话，也就是在栈1：ABC   栈2：D的基础上，D去启动singleTask的C，
		 * 出现的情况就是栈1：还是ABC，栈二：还是D，在此时的ActivityC基础上点击返回，会出现
		 * C-->B--->A---D的情况 
		 */
		case R.id.gotoCFromD:
			Intent intentC = new Intent();
			intentC.setClass(this, ActivityC.class);
			startActivity(intentC);
			break;
		/*
		 * 这个时候如果启动A的话，，也就是在栈1：ABC   栈2：D的基础上去启动standard的A
		 * 出现的情况就是 栈1：ABCA   栈2：D
		 * 返回就会出现 A--->C--->B----A----D
		 */
		case R.id.gotoAFromD:
			Intent intentA = new Intent();
			intentA.setClass(this, ActivityA.class);
			startActivity(intentA);
			break;		
		default:
			break;
		}
	}
}
