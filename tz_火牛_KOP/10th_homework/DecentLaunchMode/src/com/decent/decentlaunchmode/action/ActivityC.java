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

public class ActivityC extends Activity implements OnClickListener
{
	private TextView activityC;
	private Button gotoSelfC;
	private Button gotoD;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_c);
		ReflictionUtil.InjectionView(ActivityC.class.getName(),
				R.class.getName(), this);
		activityC.setText(this.toString()+",TaskId:"+this.getTaskId());;
		gotoSelfC.setOnClickListener(this);
		gotoD.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		/*
		 * C是singleTask会重用之前的实例，所以this.toString()，一直不会变化
		 */
		case R.id.gotoSelfC:
			Intent intent = new Intent();
			intent.setClass(this, ActivityC.class);
			startActivity(intent);
			break;
		/*
		 * D是singleInstance
		 */
		case R.id.gotoD:
			Intent intentD = new Intent();
			intentD.setClass(this, ActivityD.class);
			startActivity(intentD);
			break;
		default:
			break;
		}
	}

}
