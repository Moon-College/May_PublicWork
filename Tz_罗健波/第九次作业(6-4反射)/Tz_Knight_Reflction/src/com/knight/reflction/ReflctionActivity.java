package com.knight.reflction;

import com.knight.reflction.utils.ReflctionInstan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ReflctionActivity extends Activity {
	
	private EditText editText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reflction);
		ReflctionInstan.Instance(this);
	}
	public void down(View view){
		Toast.makeText(this, editText.getText().toString().trim(), 1000).show();
	}
}
