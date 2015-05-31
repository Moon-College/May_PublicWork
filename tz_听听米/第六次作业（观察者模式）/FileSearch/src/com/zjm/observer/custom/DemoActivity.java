package com.zjm.observer.custom;

import com.zjm.filesearch.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DemoActivity extends Activity implements OnClickListener {
	
	private EditText et;
	private Button btn;
	private MyObservable myObservable;
	private MyObserver myObserver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_layout);
		et = (EditText) findViewById(R.id.et);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(this);
		myObservable = new MyObservable();
		myObserver = new MyObserver();
		myObservable.registerObserver(myObserver);
		
	}

	@Override
	public void onClick(View v) {
		int i = Integer.parseInt(et.getText().toString().trim());
		myObservable.notifyObserver(DemoActivity.this,myObserver, i);
//		myObservable.notifyObserver(i);
	}

}
