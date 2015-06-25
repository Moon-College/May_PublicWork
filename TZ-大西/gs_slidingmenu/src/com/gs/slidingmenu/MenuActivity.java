package com.gs.slidingmenu;

import com.gs.slidingmenu.view.MySlidingMenu;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MenuActivity extends Activity implements OnTouchListener {
    
	private int widthPixls;
	private MySlidingMenu mySlidingMenu;
	Handler headler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			int sc = (Integer) msg.obj;
			mySlidingMenu.smoothScrollTo(sc, 0);
		}
	};
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mySlidingMenu = (MySlidingMenu) findViewById(R.id.menu);
        mySlidingMenu.setOnTouchListener(this);
        
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthPixls = displayMetrics.widthPixels;//ÏñËØ¿í
    }
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			
			break;
		case MotionEvent.ACTION_MOVE:
			
			break;
		case MotionEvent.ACTION_UP:
			int scrollX = mySlidingMenu.getScrollX();
			int scrollDeltX;
			if(scrollX<0.3*widthPixls){
				scrollDeltX = 0;
			}else{
				scrollDeltX = (int) (0.8*widthPixls);
			}
			Message msg = headler.obtainMessage();
			msg.obj = scrollDeltX;
			msg.sendToTarget();
			break;
		}
		
		return false;
	}
}