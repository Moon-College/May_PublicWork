package com.casit.hc;

import com.casit.hc.container.MySlidingMenu;
import com.nineoldandroids.view.ViewHelper;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Qq_slidingMenuActivity extends Activity  {
    private MySlidingMenu mySlidingMenu;
	private int showWidth;
	private int showHeight;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
     //   mySlidingMenu = (MySlidingMenu) layoutInflater.inflate(R.layout.main, null);
     //   mySlidingMenu.setOnTouchListener(this);
      //  setContentView(view)
    }

/*	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		mySlidingMenu.onTouchEvent(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("INFO","Down");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("INFO","Move");   
			break;
			
		case MotionEvent.ACTION_UP:
			float scroll = mySlidingMenu.getScrollX();
			Log.i("INFO", scroll+"up");
			if(scroll<=showWidth*0.4f){
				ViewHelper.setTranslationX(mySlidingMenu.mContent, scroll);
			}else{
				ViewHelper.setTranslationX(mySlidingMenu.mContent, 0.8f*showWidth-scroll);
			}
			break;

		default:
			break;
		}
		return true;
	}*/
}