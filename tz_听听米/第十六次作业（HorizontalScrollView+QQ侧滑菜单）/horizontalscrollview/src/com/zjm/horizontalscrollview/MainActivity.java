package com.zjm.horizontalscrollview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnTouchListener {
	
	private LinearLayout firstpage,secondpage,threepage;
	private HorizontalScrollView hrscroll;
	private int widthPixls,heightPixls;//屏幕的像素宽高

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		DisplayMetrics dism = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dism);
		widthPixls = dism.widthPixels;
		heightPixls = dism.heightPixels;
		initView();
	}

	/**
	 *@author 邹继明
	 *@date 2015-6-28下午11:05:49
	 *@return void
	 */
	private void initView() {
		firstpage = (LinearLayout) findViewById(R.id.firstpage);
		secondpage = (LinearLayout) findViewById(R.id.secondpage);
		threepage = (LinearLayout) findViewById(R.id.threepage);
		hrscroll = (HorizontalScrollView) findViewById(R.id.hrscroll);
		//设置各个页面最小宽度
//		firstpage.setMinimumWidth((int) (widthPixls*0.8f));
//		secondpage.setMinimumWidth(widthPixls);
//		threepage.setMinimumWidth(widthPixls);
		//设置各个页面宽度
		firstpage.setLayoutParams(new LinearLayout.LayoutParams((int) (widthPixls*0.8f), LayoutParams.MATCH_PARENT));
		secondpage.setLayoutParams(new LinearLayout.LayoutParams(widthPixls, LayoutParams.MATCH_PARENT));
		threepage.setLayoutParams(new LinearLayout.LayoutParams(widthPixls, LayoutParams.MATCH_PARENT));
		hrscroll.setOnTouchListener(this);
	}
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			hrscroll.smoothScrollTo((int) msg.obj, 0);//平滑的移动
		};
	};

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			break;
		case MotionEvent.ACTION_MOVE:
			
			break;
		case MotionEvent.ACTION_UP:
			int scrollX = hrscroll.getScrollX();//水平滚动的距离
			int scrollDeltX;//移动的距离
			String page = "";
			if(scrollX <= 0.4*widthPixls){
				//第一页
				scrollDeltX = 0;
				page = "第一页";
			}else if(scrollX>0.4*widthPixls&&scrollX<=1.3*widthPixls){
				//第二页
				scrollDeltX = (int) (0.8*widthPixls);
				page = "第二页";
			}else{
				//第三页
				scrollDeltX = (int) (1.8*widthPixls);
				page = "第三页";
			}
			Log.i("INFO", 0.4*widthPixls+"scrollX:"+scrollX+page+"<="+1.3*widthPixls);
			Message msg = mHandler.obtainMessage();
			msg.obj = scrollDeltX;
			msg.sendToTarget();
			break;

		default:
			break;
		}
		return false;
	}

	
}
