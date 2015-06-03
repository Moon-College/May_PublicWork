package com.tz.observer;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.tz.observer.observer.BaseObserver;
import com.tz.observer.observer.ViewObservable;
import com.tz.observer.observer.ViewObserver;

public class MainActivity extends Activity {

	private EditText observerInput;
	private TextView showView1;
	private TextView showView2;
	private ViewObservable observable;
	
	private String content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		observable = new ViewObservable();
		
		showView1 = (TextView) findViewById(R.id.observable1);
		showView2 = (TextView) findViewById(R.id.observable2);
		
		TitleObserver titleObserver = new TitleObserver();
		final ViewObserver viewObserver1 = new ViewObserver(showView1, content);
		final ViewObserver viewObserver2 = new ViewObserver(showView2, content);

		observable.registeObserver(titleObserver);
		observable.registeObserver(viewObserver1);
		
		// 输入的内容，同事通过观察者模式显示到其他控件上去
		observerInput = (EditText) findViewById(R.id.observer);
		observerInput.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String str = observerInput.getText().toString().trim();
				viewObserver1.setShowContent(str);
				observable.notifyDataChange();
				
				viewObserver2.setShowContent(str);
				observable.notifyObserver(viewObserver2);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}
	
	class TitleObserver extends BaseObserver{

		@Override
		public void onChange() {
			String str = observerInput.getText().toString().trim();
			setTitle(str);			
		}		
	}
}
