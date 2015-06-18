package com.tz.fragment;

import com.tz.fragment.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//如果sdk是2.2使用v4包,MainActivity要继承FragmentActivity
public class MainActivity extends FragmentActivity {

	// private ImageView img;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("Activity", "create");
		setContentView(R.layout.activity_main);

		// img = (ImageView) findViewById(R.id.img);
		// img.setOnClickListener(this);
		
		//执行下面这句代码前，会先调用Fragment中attach,create,createView。然后执行此行代码，在调用Fragment中activityCreated，要注意它的执行顺序，观察生命周期
		tv = ((TextView) findViewById(R.id.tv));
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("Activity", "start");
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("Activity", "resume");
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("Activity", "pause");
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("Activity", "stop");
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("Activity", "destroy");
	}

	public TextView getTv() {
		return tv;
	}

	public void setTv(TextView tv) {
		this.tv = tv;
	}

//	@Override
//	public void onClick(View v) {
//		// Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
//	}

}
