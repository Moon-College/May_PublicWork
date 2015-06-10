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

public class ActivityA extends Activity implements OnClickListener
{
    private TextView activityA;
    private Button gotoSelfA;
    private Button gotoB;
    private Button fromAGotoC;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);
		ReflictionUtil.InjectionView(ActivityA.class.getName(),R.class.getName(),this);
		activityA.setText(this.toString()+",TaskId:"+this.getTaskId());
		gotoSelfA.setOnClickListener(this);
		gotoB.setOnClickListener(this);
		fromAGotoC.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		/*
		 * 由于ActivityA是标准的launchMode所以每次都有新的实例
		 * 所以this.toString()，会一直变化
		 */
		case R.id.gotoSelfA:
            Intent intent = new Intent();
            intent.setClass(this, ActivityA.class);
            startActivity(intent);
			break;
		/*
		 * ActivityB是signleTop
		 */
		case R.id.gotoB:
            Intent intentB = new Intent();
            intentB.setClass(this, ActivityB.class);
            startActivity(intentB);			
			break;

		/*
		 * 如果之前栈1：ABCA，经过这次之后前栈1：ABC,栈2:D,A由于在C的栈位置智商则会被干掉，让C保持栈顶
		 * 返回就是：C---->B---->A-----D
		 */
		case R.id.fromAGotoC:
            Intent intentC = new Intent();
            intentC.setClass(this, ActivityC.class);
            startActivity(intentC);			
			break;			
		default:
			break;
		}
	}

}
