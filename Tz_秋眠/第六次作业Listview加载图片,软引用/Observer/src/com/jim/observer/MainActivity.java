package com.jim.observer;

import com.jim.observer.decentobserver.JimDecentNewsObserver;
import com.jim.observer.observable.JimNewsObservable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	private EditText editText;
	private Button btn;
	private JimNewsObservable newsObservable;
	private JimDecentNewsObserver decentNewsObserver1;
	private JimDecentNewsObserver decentNewsObserver2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initDAO();
		initView();
	}

	/**
	 * 初始化视图并添加监听事件
	 */
	private void initView() {
		// TODO Auto-generated method stub
		editText = (EditText) findViewById(R.id.et);
		btn = (Button) findViewById(R.id.btn);

		btn.setOnClickListener(this);
	}

	/**
	 * 初始化观察者与被观察者，并注册观察者
	 */
	private void initDAO() {
		// TODO Auto-generated method stub
		newsObservable = new JimNewsObservable();
		decentNewsObserver1 = new JimDecentNewsObserver(this);
		decentNewsObserver2 = new JimDecentNewsObserver(this);

		newsObservable.addDecentObserver(decentNewsObserver1);
		newsObservable.addDecentObserver(decentNewsObserver2);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		int num = Integer.valueOf(editText.getText().toString().trim());
		Log.i("MainActivity", "num=>>" + num);

		String[] str = { String.valueOf(num) };
		newsObservable.notifyAllDecentObserver(str);
	}
}