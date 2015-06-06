package com.dd.sixfourhomework;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView textView1,textView2,textView3;
	private String s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void toast(View v){
		Toast.makeText(this, textView1.getText(), Toast.LENGTH_SHORT).show();
	}
}
