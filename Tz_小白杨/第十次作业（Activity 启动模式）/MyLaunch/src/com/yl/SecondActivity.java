package com.yl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity implements OnClickListener {
	private Button but;
	private Button butnum;
	private int i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		but=(Button) findViewById(R.id.secondbut);
		but.setOnClickListener(this);
		
		butnum=(Button) findViewById(R.id.secondnum);
		butnum.setOnClickListener(this);
		Log.i("INFO", "second");
	}
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.secondbut:
			Intent intent=new Intent();
			intent.setAction("com.yl.thirdactivity");
			intent.putExtra("aa", "111");
			startActivity(intent);
			break;
		case R.id.secondnum:
			i++;
			butnum.setText(String.valueOf(i));
			break;

		default:
			break;
		}		
	}
}
