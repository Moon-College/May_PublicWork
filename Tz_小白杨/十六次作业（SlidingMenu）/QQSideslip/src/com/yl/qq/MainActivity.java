package com.yl.qq;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.yl.qq.view.MyScrollView;

public class MainActivity extends Activity implements OnTouchListener {
    private MyScrollView msv;
    private float scrollWidth;
    Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			int scx=(Integer) msg.obj;
			Log.i("INFO", "scx="+scx);
			msv.smoothScrollTo(scx,0);
			//msv.scrollTo(scx,0);
		};
	};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        msv=(MyScrollView) findViewById(R.id.qqmenu);
        msv.setOnTouchListener(this);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        scrollWidth= displayMetrics.widthPixels;
    }
	public boolean onTouch(View v, MotionEvent event) {
		int msWidth=(int) (scrollWidth*0.75f);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i("INFO", "down");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("INFO", "move");
			break;
		case MotionEvent.ACTION_UP:
			Log.i("INFO", "up");
			int sx = v.getScrollX();
			int scrollX;
			if (sx <= msWidth * 0.4) {
				scrollX = 0;
			} else {
				scrollX = msWidth;
			}
			Message message=handler.obtainMessage();
			message.obj=scrollX;
			message.sendToTarget();
			break;

		default:
			break;
		}
		return false;
	}
    
}