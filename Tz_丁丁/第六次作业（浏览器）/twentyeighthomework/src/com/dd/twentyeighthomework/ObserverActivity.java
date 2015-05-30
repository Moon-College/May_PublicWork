package com.dd.twentyeighthomework;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.dd.observer.RealButtonObserveable;
import com.dd.observer.RealButtonObserver;
import com.example.twentyeighthomework.R;

public class ObserverActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_observer);
		Button btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RealButtonObserveable realButtonObserveable = new RealButtonObserveable();
				RealButtonObserver realButtonObserver = new RealButtonObserver();
				realButtonObserveable.registeObserver(realButtonObserver);
				realButtonObserveable.notifyAllObserver();
			}
		});
	}

}
