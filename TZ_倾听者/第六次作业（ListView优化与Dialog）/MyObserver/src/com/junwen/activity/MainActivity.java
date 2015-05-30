package com.junwen.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myobserver.R;
import com.junwen.observer.MyObserver;
import com.junwen.observer.RealObservable;
import com.junwen.observer.RealObserver;

public class MainActivity extends Activity implements OnClickListener,
		MyObserver {
	//���TextView
	private Button mAddTextView;
	//��Ӹ���
	private Button mChangedTextView;
	//�����
	private EditText mtext;
	//�ײ�����
	private LinearLayout mLinearLayout;
	//���۲���
	private RealObservable observable;
	//�۲���
	private RealObserver observer;
	//TextView����
	private List<TextView> data = new ArrayList<TextView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		mAddTextView = (Button) findViewById(R.id.mAddTextView);
		mChangedTextView = (Button) findViewById(R.id.mChangTextView);
		mtext = (EditText) findViewById(R.id.mtext);
		mLinearLayout = (LinearLayout) findViewById(R.id.mLIinearLayout);
		mAddTextView.setOnClickListener(this);
		mChangedTextView.setOnClickListener(this);
		observable = new RealObservable();
		observer = new RealObserver();
		//ע��۲���
		observable.registerObserver(this);
	}

	/**
	 * �������ť��ʱ��
	 * 
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mAddTextView:
			mLinearLayout.removeAllViews();
			data.clear();
			// �����������TextView�İ�ť
			int num = Integer.valueOf(mtext.getText().toString().trim());
			//����ѭ�����ж�TextView�����
			for (int i = 0; i < num; i++) {
				TextView text = new TextView(this);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				params.gravity = Gravity.CENTER_HORIZONTAL;
				text.setLayoutParams(params);
				text.setGravity(Gravity.CENTER_HORIZONTAL);
				text.setText("������,�ոմ�����");
				text.setBackgroundColor(getResources().getColor(
						android.R.color.holo_orange_dark));
				mLinearLayout.addView(text);
				data.add(text);
			}
			break;
		case R.id.mChangTextView:
			// �������˸���TextView�İ�ť
			int code = Integer.valueOf(mtext.getText().toString().trim());
			//֪ͨ�۲���
			observable.nofityAllDataSetObserver(code);
			break;
		default:
			break;
		}
	}
	
	/**
	 * �����۲��߷���֪ͨ�����������
	 */
	@Override
	public void onUpdate(int num) {
		if (num <= data.size()) {
			for (int i = 0; i < num; i++) {
				TextView textView = data.get(i);
				textView.setText("���ǵ�" + (i + 1)+"��"+ "TextView");
			}
		}

	}

}
