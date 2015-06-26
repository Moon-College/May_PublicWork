package com.yl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Third extends Activity implements OnClickListener {
	private Button but;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third);
		but=(Button) findViewById(R.id.thirdbut);
		but.setOnClickListener(this);
		Log.i("INFO", "third");
		Intent it=this.getIntent();
		Toast.makeText(this, it.getStringExtra("aa"), 1000).show();
	}
	public void onClick(View v) {
		Intent intent=new Intent();
		intent.setClass(this, FirstActivity.class);
		startActivity(intent);
	
	}
}
