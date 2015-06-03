package com.tz.add.view;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private int tvID = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		final LinearLayout parent = new LinearLayout(this);
		parent.setOrientation(LinearLayout.VERTICAL);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
		parent.setLayoutParams(params);
		
		LinearLayout ll = new LinearLayout(this);
		LayoutParams params2 = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		ll.setLayoutParams(params2);
		
		//添加EditText
		EditText editText = new EditText(this);
		LayoutParams editParams = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT,1);
		editText.setLayoutParams(editParams);
		ll.addView(editText,0);
		
		//添加Button
		Button button = new Button(this);
		LayoutParams buttonParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,0);
		button.setLayoutParams(buttonParams);
		button.setText("添加控件");
		ll.addView(button,1);
		
		parent.addView(ll);
		
		
		ScrollView scrollView = new ScrollView(this);
		LayoutParams params3 = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
		scrollView.setLayoutParams(params3);

		final LinearLayout ll2 = new LinearLayout(this);
		ll2.setOrientation(LinearLayout.VERTICAL);
		LayoutParams params4 = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
		ll2.setLayoutParams(params4);
		scrollView.addView(ll2);
		parent.addView(scrollView);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView tv = new TextView(MainActivity.this);
				tv.setBackgroundColor(getResources().getColor(R.color.red));
				tv.setText("" + tvID++);
				tv.setPadding(3, 3, 3, 3);
				LayoutParams params5 = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
				params5.setMargins(5, 5, 5, 5);
				tv.setLayoutParams(params5); 
				Log.e("wdj","onclick");
				ll2.addView(tv);
			}
		});
		
		setContentView(parent);
		
		
		
		
		
		
	}

	

}
