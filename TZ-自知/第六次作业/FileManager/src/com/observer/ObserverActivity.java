package com.observer;

import com.example.filemanager.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ObserverActivity extends Activity{
	private Button mybtn;
	private RealObserver mRealObserver =new RealObserver();
	private RealObserverable mRealObserverable=new RealObserverable();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.observermain);
		mybtn=(Button) findViewById(R.id.mybtn);
		
		mRealObserverable.registObserver(mRealObserver);
		mybtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mRealObserverable.notifyAllObserver();
			}
		});
		
	}
}
