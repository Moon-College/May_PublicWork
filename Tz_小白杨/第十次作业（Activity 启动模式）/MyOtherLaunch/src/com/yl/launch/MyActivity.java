package com.yl.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyActivity extends Activity implements OnClickListener {
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }
	public void onClick(View v) {
		Intent intent=new Intent();
		intent.setAction("com.yl.secondactivity");
		startActivity(intent);
		
	}
}