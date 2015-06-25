package com.qfx.observe;

import com.qfx.observe.mode.NumberObserver;
import com.qfx.observe.mode.RealNumberObservable;
import com.qfx.observe.mode.RealNumberObserver;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	private EditText mEditText;
	private Button mButton;
	private RealNumberObservable realNumberObservable;
	private RealNumberObserver observer1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mEditText = (EditText) findViewById(R.id.et);
		mButton = (Button) findViewById(R.id.btn);
		mButton.setOnClickListener(this);
		realNumberObservable = new RealNumberObservable();
		observer1 = new RealNumberObserver();
		realNumberObservable.registerObserver(observer1);
		
	}

	@Override
	public void onClick(View v) {
		int number = Integer.valueOf(mEditText.getText().toString().trim());
		//数据变为2时反应
		if (number == 2) {
			realNumberObservable.notifyAllObserver();
		}
	}

}
