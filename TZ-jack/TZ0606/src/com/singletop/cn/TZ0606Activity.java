package com.singletop.cn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TZ0606Activity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         button=(Button) findViewById(R.id.singtopbutton0ne);
         button.setOnClickListener(this);
    }
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent();
		intent.setClass(this, TwoActivity.class);
		startActivity(intent);
		
	}
    
}