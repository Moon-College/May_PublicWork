package com.ccgao.observer;

import com.ccgao.observer.designpattern.NumObserver;
import com.ccgao.observer.designpattern.NumObserverable;
import com.ccgao.observer.designpattern.RealObserver;
import com.ccgao.observer.designpattern.RealObserverable;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	private EditText num;
	private Button btn;
	private NumObserver realObserver;
	private NumObserverable realObserverable;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		num = (EditText) findViewById(R.id.num);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(this);
		realObserver = new RealObserver();
		realObserverable = new RealObserverable();
		realObserverable.registeObserver(realObserver);
	}

	public void onClick(View v) {
		String numStr = num.getText().toString().trim();
		int number = Integer.valueOf(numStr);
		if (number == 2) {
			((RealObserverable)realObserverable).notifyAllObservers();
		}
	}
}