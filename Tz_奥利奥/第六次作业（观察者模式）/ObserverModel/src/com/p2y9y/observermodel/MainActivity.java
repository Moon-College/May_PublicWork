package com.p2y9y.observermodel;

import com.p2y9y.observermodel.model.ActualObserver;
import com.p2y9y.observermodel.model.ActualObserverable;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	AutoCompleteTextView autoCompleteTextView;
	ActualObserverable mObserverable = new ActualObserverable();
	
	private String [] citys = new String[]{
			"London",
			"Paris",
			"Rome",
			"Manchester",
			"Los Angel"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
		autoCompleteTextView.setThreshold(1);
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, citys);
		autoCompleteTextView.setAdapter(adapter);
		
		findViewById(R.id.button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ActualObserver mObserver = new ActualObserver();
				String tag = autoCompleteTextView.getText().toString().trim();
				if(tag == null || "".equals(tag)){
					Toast.makeText(MainActivity.this, "tag can not be null", Toast.LENGTH_LONG).show();
				}
				if("London".equals(tag)){
					mObserverable.registerObserver(mObserver);
					mObserverable.notifyDataSetChange(mObserver);
				}else if("Paris".equals(tag)){
					mObserverable.unregisterObserver(mObserver);
					mObserverable.notifyDataSetChange(mObserver);
				}
			}
		});
	}
}
