package com.tz.michael.activity;

import com.tz.michael.custom.MySlidingMenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SlidingMenuTestActivity extends Activity implements OnClickListener {
	
	private MySlidingMenu myMenu;
	private Button btn_left,btn_right;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myMenu=(MySlidingMenu) findViewById(R.id.myMenu);
        myMenu.setContentCanScall(false);
        myMenu.setContentCanScroll(false);
        btn_left=(Button) findViewById(R.id.btn_left);
        btn_right=(Button) findViewById(R.id.btn_right);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
    }

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_left:
			myMenu.useLeftMenuHandle();
			break;
		case R.id.btn_right:
			myMenu.useRightMenuHandle();
			break;
		default:
			break;
		}
	}
}