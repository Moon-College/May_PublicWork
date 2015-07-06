package com.home.main;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.home.view.MyMenu;
import com.tz.qqslidingmenu.R;

public class MainActivity extends Activity {
   private MyMenu myMenu;
   private int  mainWidthMetrics;
    
   private  Handler handler = new Handler(){
     public void handleMessage(Message msg) {
    	 int  a = (Integer) msg.obj;
    	 Log.i("SIZE", "Size :"+ a);
    	 myMenu.smoothScrollTo(a, 0);
    	 
     }; 
   };
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myMenu = (MyMenu) findViewById(R.id.menu);
        myMenu.setOnTouchListener( new MyListener());
//   	   int width = MyMenu.getWindowMetrics(getBaseContext());
//	   Log.i("INFO", "MainActivity"+width);
         DisplayMetrics outMetrics = new DisplayMetrics();
         this.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
         mainWidthMetrics =outMetrics.widthPixels;
          Log.i("INFO", "mainSize" + mainWidthMetrics);
	}
	
	/**
	 * @author sunday
	 * 为 设置监听器
	 * 
	 * */
	private class  MyListener implements OnTouchListener{
  
		public boolean onTouch(View v, MotionEvent event) {
		   int msMetrics = (int) (mainWidthMetrics*0.75f);
		   Log.i("INFO", "rrrrrr" + msMetrics * 0.4);
		   Log.i("INFO", "sfsf" + msMetrics);
		    //获取事件
		 int  ee = event.getAction();
		 switch (ee & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			//Log.i("INFO", "MyMeny DOWN");
		 break;
		case MotionEvent.ACTION_MOVE:
			//Log.i("INFO", "MyMeny MOVE");
			break;
		case MotionEvent.ACTION_UP:
		  //  Log.i("INFO", "MyMenu UP");
		   int myMenyScrollX = v.getScrollX();
		   //Log.i("INFO", "myScollX" +myMenyScrollX);
		  // Log.i("INFO", "my"+myMenu.getScrollX());
		   int x;
		   if(myMenyScrollX < msMetrics*0.4){
			 
			    x = 0;
		   }else{
			    x = mainWidthMetrics;
		   }
		   Log.i("ZIZE", "size" + x);
		   Message ss = handler.obtainMessage();
		   ss.obj = x;
		   ss.sendToTarget();
			break;
		default:
			break;
		}
			return false;
		}
		
	}
	
}