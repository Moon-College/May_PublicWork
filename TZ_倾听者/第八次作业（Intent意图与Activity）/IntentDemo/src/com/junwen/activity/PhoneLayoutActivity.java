package com.junwen.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intentdemo.R;
import com.junwen.adapter.PhoneAdapter;

public class PhoneLayoutActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	//����
	private String [] code = {"1","2","3","4","5","6","7","8","9","*","0","#"};
	private GridView gridView;
	private ImageView addContects;
	private ImageView playPhone;
	private ImageView delete;
	private TextView tv_number;
	private PhoneAdapter adapter;
	private StringBuffer sb =new StringBuffer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_layout);
		initView();
		//����������
		adapter = new PhoneAdapter(code,this);
		//����adapter
		gridView.setAdapter(adapter);
	}
	/**
	 * ��ʼ��
	 */
	private void initView() {
		//�󶨿ؼ�
		gridView = (GridView) findViewById(R.id.gridView);
		addContects = (ImageView) findViewById(R.id.addContacts);
		playPhone = (ImageView) findViewById(R.id.startPhone);
		delete = (ImageView) findViewById(R.id.delete);
		tv_number = (TextView) findViewById(R.id.tv_number);
		//�¼�
		addContects.setOnClickListener(this);
		playPhone.setOnClickListener(this);
		delete.setOnClickListener(this);
		gridView.setOnItemClickListener(this);
	}

	/**
	 * ����ͼƬ��ťʱ
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addContacts:
			//�����ϵ��
			break;
		case R.id.startPhone:
			//����
			Intent intent = new Intent();
			//�绰Action
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:"+sb.toString().trim()));
			if( intent.resolveActivity(getPackageManager()) != null) {
				startActivity(intent);
			}
			//����ı���
			sb.delete(0, sb.length());
			tv_number.setText("");
			break;
		case R.id.delete:
			//���ˣ�ɾ��
			if(sb.length()-1 >=0 ) {
				sb.deleteCharAt(sb.length()-1);
				tv_number.setText(sb);
			}
			break;
		default:
			break;
		}
	}

	/**
	 * �������ּ�ʱ
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//ȥ�����µ�����
		String num = code[position];
		sb.append(num);
		//�����ı�
		tv_number.setText(sb);
	}
}
