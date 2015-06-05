package com.lzq.observer;

import com.lzq.observer.mode.NumberObserver;
import com.lzq.observer.mode.NumberObserverable;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	private EditText et;
	private Button bt;
	private NumberObserverable numberObserverable;
	private NumberObserver observer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et = (EditText) findViewById(R.id.et);
		bt = (Button) findViewById(R.id.bt);
		bt.setOnClickListener(this);
		// 被观察者
		numberObserverable = new NumberObserverable();
		// 观察者
		observer = new NumberObserver();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt:
			int number = Integer.parseInt(et.getText().toString());
			if(number == 2){
				numberObserverable.notifyObserver(observer);
			}
			break;

		}
	}

}
