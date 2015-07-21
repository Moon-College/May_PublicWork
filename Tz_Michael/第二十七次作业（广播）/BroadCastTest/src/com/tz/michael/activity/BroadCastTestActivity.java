package com.tz.michael.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BroadCastTestActivity extends Activity implements OnClickListener {
	
	private Button btn_order;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn_order=(Button) findViewById(R.id.btn_order);
        btn_order.setOnClickListener(this);
    }

	public void onClick(View v) {
		Intent intent=new Intent("com.tz.michael.receiver");
		intent.putExtra("index", 123);
		sendOrderedBroadcast(intent, "michael.tz.shi");
	}
}