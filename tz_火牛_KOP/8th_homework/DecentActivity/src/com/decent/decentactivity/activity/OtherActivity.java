package com.decent.decentactivity.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.decent.decentactivity.R;
import com.decent.decentactivity.bean.User;

public class OtherActivity extends Activity implements OnClickListener
{
	private Button mBtn;
	private EditText mEt;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.other_activity);

		mBtn = (Button) findViewById(R.id.btn);
		mBtn.setOnClickListener(this);
		mEt = (EditText) findViewById(R.id.et);
        
	}

	@Override
	public void onClick(View v)
	{
		String name = mEt.getText().toString().trim();
		User user = new User(name,18);
		
		Intent data = new Intent();
		data.putExtra("user", user);
		// TODO Auto-generated method stub
		setResult(Activity.RESULT_OK, data);
		finish();
	}

}
