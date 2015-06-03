package com.cn.test;

import com.cn.test.observer.FileObserveable;
import com.cn.test.observer.FileObserver;
import com.cn.test.observer.Observeable;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Created on2015-5-30 ÏÂÎç2:59:20 ObserverActivity.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class ObserverActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.observer);
		Button bt = (Button) findViewById(R.id.bt);
		bt.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v ) {
				// TODO Auto-generated method stub
				FileObserveable fileObserveable = new FileObserveable();
				FileObserver fileObserver = new FileObserver();
				fileObserveable.registeObserver(fileObserver);
				fileObserveable.notifyAllObserver();
			}
		});
	}

}
