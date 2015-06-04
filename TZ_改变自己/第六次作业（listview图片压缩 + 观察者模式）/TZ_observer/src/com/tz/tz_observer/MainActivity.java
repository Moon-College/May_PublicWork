package com.tz.tz_observer;

import com.tz.observer.RealNumberObserveable;
import com.tz.observer.RealNumberObserver;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	RealNumberObserveable numberObserveable;
	
	private Button button;
	
	private EditText edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	
	

	private void initView() {
		edit = (EditText) findViewById(R.id.edit);
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
		numberObserveable = new RealNumberObserveable();
		numberObserveable.registerObserver(new RealNumberObserver());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(edit.getText().toString().equals("2")) {
			numberObserveable.notifyAllObserver();
		}
	}

}
