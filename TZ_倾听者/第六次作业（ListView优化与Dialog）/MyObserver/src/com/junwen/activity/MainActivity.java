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
	//添加TextView
	private Button mAddTextView;
	//添加更新
	private Button mChangedTextView;
	//输入框
	private EditText mtext;
	//底部布局
	private LinearLayout mLinearLayout;
	//被观察者
	private RealObservable observable;
	//观察者
	private RealObserver observer;
	//TextView集合
	private List<TextView> data = new ArrayList<TextView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	/**
	 * 初始化控件
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
		//注册观察者
		observable.registerObserver(this);
	}

	/**
	 * 当点击按钮的时候
	 * 
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mAddTextView:
			mLinearLayout.removeAllViews();
			data.clear();
			// 如果点击了添加TextView的按钮
			int num = Integer.valueOf(mtext.getText().toString().trim());
			//运用循环进行对TextView的添加
			for (int i = 0; i < num; i++) {
				TextView text = new TextView(this);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				params.gravity = Gravity.CENTER_HORIZONTAL;
				text.setLayoutParams(params);
				text.setGravity(Gravity.CENTER_HORIZONTAL);
				text.setText("无内容,刚刚创建的");
				text.setBackgroundColor(getResources().getColor(
						android.R.color.holo_orange_dark));
				mLinearLayout.addView(text);
				data.add(text);
			}
			break;
		case R.id.mChangTextView:
			// 如果点击了更新TextView的按钮
			int code = Integer.valueOf(mtext.getText().toString().trim());
			//通知观察者
			observable.nofityAllDataSetObserver(code);
			break;
		default:
			break;
		}
	}
	
	/**
	 * 当被观察者发出通知后，在这里更新
	 */
	@Override
	public void onUpdate(int num) {
		if (num <= data.size()) {
			for (int i = 0; i < num; i++) {
				TextView textView = data.get(i);
				textView.setText("我是第" + (i + 1)+"个"+ "TextView");
			}
		}

	}

}
