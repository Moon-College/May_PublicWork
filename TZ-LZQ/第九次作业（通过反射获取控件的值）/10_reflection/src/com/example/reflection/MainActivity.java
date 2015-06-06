package com.example.reflection;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView tv_one,tv_two,tv_three,tv_four,tv_five,tv_six;
	private EditText tv_seven;
	private String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ReflectionUtil.initViews(this);
	}

	public void show(View view){
    	Toast.makeText(this, tv_seven.getText(), Toast.LENGTH_LONG).show();
    }
}
