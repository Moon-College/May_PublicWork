package com.tz.refectandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tz.refectandroid.utils.Utils;

public class MainActivity extends Activity {
	private EditText testInputEditText;
	private Button showButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Utils.initView(this);
		showButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String text = testInputEditText.getText().toString();
				Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG)
						.show();
			}
		});
	}
}
