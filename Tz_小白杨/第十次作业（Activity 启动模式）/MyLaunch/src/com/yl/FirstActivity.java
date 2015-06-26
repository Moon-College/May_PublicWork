package com.yl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstActivity extends Activity implements OnClickListener {
	private Button btn;
	private Button btnnum;
	private int i;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn=(Button) findViewById(R.id.firstbut);
        btn.setOnClickListener(this);
        btnnum=(Button) findViewById(R.id.firstnum);
        btnnum.setOnClickListener(this);
        Log.i("INFO", "first");
    }
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.firstbut:
			Intent intent=new Intent();
			intent.setAction("com.yl.secondactivity");
			startActivity(intent);
			break;
		case R.id.firstnum:
			i++;
			btnnum.setText(String.valueOf(i));
			break;

		default:
			break;
		}
		
	}
}