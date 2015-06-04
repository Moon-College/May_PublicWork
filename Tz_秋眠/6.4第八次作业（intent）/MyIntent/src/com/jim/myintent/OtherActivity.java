package com.jim.myintent;

import com.jim.myintent.beans.Users;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class OtherActivity extends Activity implements OnClickListener {

	private TextView mTextView;
	private static final int TEXTVIEW_ID = 10000;
	private RelativeLayout mRelativeLayout;
	private Intent intent;
	private Users users;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		initView();
		initData();

	}

	private void initData() {
		intent = getIntent();
		users = intent.getParcelableExtra("users");
		String name = users.getName();
		Log.i("name", name);
		int age = users.getAge();
		Log.i("age", age+"");
		mTextView.setText("–’√˚:" + name + "ƒÍ¡‰:" + age);
	}

	private void initView() {
		mRelativeLayout = (RelativeLayout) findViewById(R.id.myRelativeLayout);

		mTextView = new TextView(this);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		mTextView.setId(TEXTVIEW_ID);
		layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

		mRelativeLayout.addView(mTextView);

		mTextView.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		intent.putExtra("users", users);
		setResult(Activity.RESULT_OK, intent);
		finish();
	}
}
