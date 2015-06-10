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
		 * singleInstance�����Լ��Ļ���TaskId����䣬this.toStringҲ�����
		 */
		case R.id.gotoSelfD:
			Intent intent = new Intent();
			intent.setClass(this, ActivityD.class);
			startActivity(intent);
			break;
		/*
		 * ���ʱ���C����D�Ļ���Ҳ������ջ1��ABC   ջ2��D�Ļ����ϣ�Dȥ����singleTask��C��
		 * ���ֵ��������ջ1������ABC��ջ��������D���ڴ�ʱ��ActivityC�����ϵ�����أ������
		 * C-->B--->A---D����� 
		 */
		case R.id.gotoCFromD:
			Intent intentC = new Intent();
			intentC.setClass(this, ActivityC.class);
			startActivity(intentC);
			break;
		/*
		 * ���ʱ���������A�Ļ�����Ҳ������ջ1��ABC   ջ2��D�Ļ�����ȥ����standard��A
		 * ���ֵ�������� ջ1��ABCA   ջ2��D
		 * ���ؾͻ���� A--->C--->B----A----D
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
