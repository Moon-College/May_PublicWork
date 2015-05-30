package com.limz.observerdemo.activity;

import com.limz.observerdemo.model.RealObservable;
import com.limz.observerdemo.model.RealObserver;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ObserverDemoActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	
	private EditText editText;
	private Button button;
	private RealObservable observable;
	private RealObserver observer;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        editText = (EditText) findViewById(R.id.edit_num);
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(this);
        observable = new RealObservable();
        observer = new RealObserver();
        observable.registeredObserver(observer);
    }

	public void onClick(View v) {
		if(v.getId() == R.id.btn) {
			if(editText.getText().toString().trim() != null) {
				observable.notifyOneObserver(observer, this);
			}
		}
	}
}