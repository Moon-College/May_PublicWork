package com.tz.view.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import com.example.home_practice_myview.R;

public class MySliding  extends ViewGroup {
    private Bitmap gray_bg,green_bg, btn;
    private Bitmap left_price; 
	private final int FIRST = 0 ;
	private final int SECOND = 200 ;
	private final int THIRD = 500;
	private final int FOURTH = 1000 ;
	private final int FIFTH = 10000 ;
	private Paint paint;
	private float scale_h;
	private int price_up ; //左边价格上升值
	private int price_down; //左边价格下降的值
	private int y_up;  // 价格对应的y坐标上限 ， 值button的中心点的
	private int y_down;  // 价格对应的y坐标下限  ， 值button的中心点的目标
	private int bg_height;
	private  int bg_widht;
	private int dis; //每一个等级额高度
	private String [] names;
	
	//初始化 我们的
	public MySliding(Context context, AttributeSet attrs) {
		super(context, attrs);
      gray_bg = getBitmap(R.drawable.axis_before);
      green_bg = getBitmap(R.drawable.axis_after);
      btn = getBitmap(R.drawable.btn);
      paint = new Paint();
      paint.setColor(Color.GRAY);   // 的类型 都 就是这样的 类型
      left_price = getBitmap(R.drawable.bg_number); 
      names = new String []{
 			 "无限",
 			 String.valueOf(FOURTH),
 			 String.valueOf(THIRD),
 			 String.valueOf(SECOND),
 			 String.valueOf(FIRST)
 			 
 	 };
	}
	
	public Bitmap getBitmap(int id){
		return BitmapFactory.decodeResource(getResources(), id);
	}
	
	@Override //测量宽高 
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
       int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
       int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
       int modeHeight = MeasureSpec.getMode(heightMeasureSpec); 
       
      //测量控件自己的宽度
      bg_height = gray_bg.getHeight(); //滑竿的高度
      bg_widht = gray_bg.getWidth();
      dis = (bg_height - bg_widht)/4;
      Log.i("INFO", "sfhsf"  + bg_widht);
      int measureHeight = (modeHeight == MeasureSpec.EXACTLY)?sizeHeight : bg_height;
      measureHeight = Math.min(measureHeight, sizeHeight);
      int measureWidth = measureHeight*2 / 3;
      scale_h = (float)measureHeight/bg_height;
      setMeasuredDimension(measureWidth, measureHeight);
   
       
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
   //测量自己饿跨高
	protected void onDraw(Canvas canvas) {
      //通过实现画布的缩放 来实现控件的、 同事 实现画布和图片的等比例的缩放
	   canvas.save(); //保存当前画布的状态  存放到栈里面
	   canvas.scale(scale_h, scale_h); 
	   //开始绘画   入伙缩放图片的话 这样会使图片的像素点降低 
        int  x = (int) ((this.getWidth()/scale_h - gray_bg.getWidth())/2);
       canvas.drawBitmap(gray_bg, x, 0, paint);
	
	 paint.setTextSize(20/scale_h);
	 for (int i = 0; i < names.length; i++) {
	    int textX = x*5/4; 
	    float textY =  (i*dis + bg_widht/2 + (paint.descent() - paint.ascent()/2 - paint.descent()));
	    canvas.drawText(names[i], textX, textY, paint);
	}
       
		//完成绘制之后   重置 
	     canvas.restore();
	    
	    super.onDraw(canvas);
	}
	
   //重新摆放自己的位置
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
       
	}

}
