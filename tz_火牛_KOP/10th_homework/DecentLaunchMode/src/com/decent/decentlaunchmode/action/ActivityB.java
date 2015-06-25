package com.decent.decentlaunchmode.action;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.decent.decentlaunchmode.util.ReflictionUtil;
import com.decentsoft.DecentLaunchMode.R;

public class ActivityB extends Activity implements OnClickListener
{
	private TextView activityB;
	private Button gotoSelfB;
	private Button gotoC;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
		ReflictionUtil.InjectionView(ActivityB.class.getName(),
				R.class.getName(), this);
		activityB.setText(this.toString()+",TaskId:"+this.getTaskId());;
		gotoSelfB.setOnClickListener(this);
		gotoC.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		/*
		 * ActivityB是singleTop启动自己会一直使用之前的实例，所以this.toString()，一直不会变化
		 */
		case R.id.gotoSelfB:
            Intent intent = new Intent();
            intent.setClass(this, ActivityB.class);
            startActivity(intent);
			break;
		/*
		 * c是sigleTask 
		 */
		case R.id.gotoC:
			Intent intentC = new Intent();
			intentC.setClass(this, ActivityC.class);
			startActivity(intentC);
			break;
		default:
			break;
		}
	}

}
