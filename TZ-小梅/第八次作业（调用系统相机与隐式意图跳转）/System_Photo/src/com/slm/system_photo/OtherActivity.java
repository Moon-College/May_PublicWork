package com.slm.system_photo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.slm.system_photo.bean.User;

public class OtherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);
		Intent intent = getIntent();
//		UserInfo info = (UserInfo) intent.getSerializableExtra("data");
//		System.out.println("=="+info);
		User user = (User)intent.getParcelableExtra("data1");
		System.out.println("==" + user);
	}

}
