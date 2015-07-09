package com.casit.hc;

import com.casit.hc.view.MyLinearLayout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyTouchActivity extends Activity implements OnTouchListener {
    /** Called when the activity is first created. */
  TextView textView;
  MyTouchEvent myTouchEvent ;
  MyLinearLayout linearLayout;
	@Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView = (TextView) findViewById(R.id.tv);
        textView.setOnTouchListener(this);
        linearLayout = (MyLinearLayout) findViewById(R.id.ll);
        myTouchEvent = new MyTouchEvent();
        linearLayout.setOnTouchListener(myTouchEvent);
        
    }
	private class MyTouchEvent implements OnTouchListener{

		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				Log.i("INFO", "LayOUt DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.i("INFO", "LayOUt MOVE");
				break;
			case MotionEvent.ACTION_UP:
				Log.i("INFO", "LayOUt UP");
				break;
			default:
				break;
			}
			return true;
		}
		
	}
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
	//	Log.i("INFO", "onTouch");		
		int action = event.getAction();
		int pointercount = event.getPointerCount();
		Log.i("INFO", "pointercount" + pointercount);
		switch(action&event.getActionMasked()){//getActionMasked()
		case MotionEvent.ACTION_DOWN:
			Log.i("INFO", "DOWN");
			float x2 = event.getX();
			float y2 = event.getY();
			break;
		case MotionEvent.ACTION_POINTER_DOWN://从第二根手指开始产生
			Log.i("INFo", "其他手指陆续下来");
			if(pointercount==2){
			 float x3 = event.getX(pointercount-1);
			 float y3 = event.getY(pointercount-1);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i("INFO", "MOVE");
			float x = event.getX();
			float y = event.getY();
	//		float xx = event.getRawX(); //屏幕的高
	//		float yy = event.getRawY(); //屏幕的宽
			Log.i("INFO:","x" +x);
			Log.i("INFO:","y" +y);
		    break;
		case MotionEvent.ACTION_UP:
			Log.i("INFO", "UP");			
			break;
		default:
			break;
		}
	//	return false;
	//	return onTouchEvent(event);
		return true;
	}
}