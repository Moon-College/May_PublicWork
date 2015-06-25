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
		 * ����ActivityA�Ǳ�׼��launchMode����ÿ�ζ����µ�ʵ��
		 * ����this.toString()����һֱ�仯
		 */
		case R.id.gotoSelfA:
            Intent intent = new Intent();
            intent.setClass(this, ActivityA.class);
            startActivity(intent);
			break;
		/*
		 * ActivityB��signleTop
		 */
		case R.id.gotoB:
            Intent intentB = new Intent();
            intentB.setClass(this, ActivityB.class);
            startActivity(intentB);			
			break;

		/*
		 * ���֮ǰջ1��ABCA���������֮��ǰջ1��ABC,ջ2:D,A������C��ջλ��������ᱻ�ɵ�����C����ջ��
		 * ���ؾ��ǣ�C---->B---->A-----D
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
