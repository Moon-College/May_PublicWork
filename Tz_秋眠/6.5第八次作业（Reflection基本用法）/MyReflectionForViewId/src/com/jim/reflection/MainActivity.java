package com.jim.reflection;

import com.jim.reflection.utils.ReflectionUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7;
	private Button btn;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflectionUtils.initViews(this);
	}

	public void jump(View view) {
		Toast.makeText(this, tv3.getText(), Toast.LENGTH_SHORT).show();
	}
}