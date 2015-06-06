package com.jim.myintent;

import com.jim.myintent.beans.Users;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Mainctivity extends Activity implements OnClickListener {

	private static final int IMG_REQUST = 0;
	private static final int OTHER_REQUST = 1;

	private Button btn_student;
	private Button btn_camear;
	private ImageView img;
	private TextView tv;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_camear = (Button) findViewById(R.id.btn_camear);
		btn_student = (Button) findViewById(R.id.btn_intent);
		img = (ImageView) findViewById(R.id.img);
		tv = (TextView) findViewById(R.id.tv);

		btn_camear.setOnClickListener(this);
		btn_student.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_camear:
			jumpToCamear();
			break;
		case R.id.btn_intent:
			jumpToOther();
			break;
		default:
			break;
		}
	}

	private void jumpToCamear() {
		// TODO Auto-generated method stub
		Intent getImgFromCamearIntent = new Intent(
				MediaStore.ACTION_IMAGE_CAPTURE);
		if (getImgFromCamearIntent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(getImgFromCamearIntent, IMG_REQUST);
		}
	}

	private void jumpToOther() {
		// TODO Auto-generated method stub
		Intent intent = new Intent("jim.intent.action.OTHER");
		Users users = new Users();
		users.setName("jim");
		users.setAge(17); 
		intent.putExtra("users", users);
		startActivityForResult(intent, OTHER_REQUST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case OTHER_REQUST:
			if (resultCode == Activity.RESULT_OK) {
				Users users = data.getParcelableExtra("users");
				String name = users.getName();
				int age = users.getAge();
				tv.setText("–’√˚:" + name + "ƒÍ¡‰:" + age );
			}
			break;
		case IMG_REQUST:
			if (resultCode == Activity.RESULT_OK) {
				Bundle bmBundle = data.getExtras();
				Bitmap bitmap = (Bitmap) bmBundle.get("data");
				img.setImageBitmap(bitmap);
			}
			break;
		default:
			break;
		}
		if (resultCode == Activity.RESULT_OK)
			super.onActivityResult(requestCode, resultCode, data);
	}
}