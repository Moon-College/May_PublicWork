package com.myandroid.fileSearch.observer;

import com.myandroid.fileSearch.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TestObserver extends Activity implements OnClickListener {

	private EditText text;
	private Button click;
	private RealObserveable realobserveable = new RealObserveable();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.observer);
		text = (EditText) findViewById(R.id.text);
		click = (Button) findViewById(R.id.bnt);
		realobserveable.registerObserver(new RealObserver());
		click.setOnClickListener(this);

	}

	public void onClick(View v) {
		String content = text.getText().toString().trim();
		if (!TextUtils.isEmpty(content)) {
			Log.i("INFO", "---------------------------");
			realobserveable.notifyAllObserver();
		}
	}

}
