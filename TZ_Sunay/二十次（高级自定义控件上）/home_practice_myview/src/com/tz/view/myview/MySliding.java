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
	private int price_up ; //��߼۸�����ֵ
	private int price_down; //��߼۸��½���ֵ
	private int y_up;  // �۸��Ӧ��y�������� �� ֵbutton�����ĵ��
	private int y_down;  // �۸��Ӧ��y��������  �� ֵbutton�����ĵ��Ŀ��
	private int bg_height;
	private  int bg_widht;
	private int dis; //ÿһ���ȼ���߶�
	private String [] names;
	
	//��ʼ�� ���ǵ�
	public MySliding(Context context, AttributeSet attrs) {
		super(context, attrs);
      gray_bg = getBitmap(R.drawable.axis_before);
      green_bg = getBitmap(R.drawable.axis_after);
      btn = getBitmap(R.drawable.btn);
      paint = new Paint();
      paint.setColor(Color.GRAY);   // ������ �� ���������� ����
      left_price = getBitmap(R.drawable.bg_number); 
      names = new String []{
 			 "����",
 			 String.valueOf(FOURTH),
 			 String.valueOf(THIRD),
 			 String.valueOf(SECOND),
 			 String.valueOf(FIRST)
 			 
 	 };
	}
	
	public Bitmap getBitmap(int id){
		return BitmapFactory.decodeResource(getResources(), id);
	}
	
	@Override //������� 
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
       int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
       int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
       int modeHeight = MeasureSpec.getMode(heightMeasureSpec); 
       
      //�����ؼ��Լ��Ŀ��
      bg_height = gray_bg.getHeight(); //���͵ĸ߶�
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
	
   //�����Լ������
	protected void onDraw(Canvas canvas) {
      //ͨ��ʵ�ֻ��������� ��ʵ�ֿؼ��ġ� ͬ�� ʵ�ֻ�����ͼƬ�ĵȱ���������
	   canvas.save(); //���浱ǰ������״̬  ��ŵ�ջ����
	   canvas.scale(scale_h, scale_h); 
	   //��ʼ�滭   �������ͼƬ�Ļ� ������ʹͼƬ�����ص㽵�� 
        int  x = (int) ((this.getWidth()/scale_h - gray_bg.getWidth())/2);
       canvas.drawBitmap(gray_bg, x, 0, paint);
	
	 paint.setTextSize(20/scale_h);
	 for (int i = 0; i < names.length; i++) {
	    int textX = x*5/4; 
	    float textY =  (i*dis + bg_widht/2 + (paint.descent() - paint.ascent()/2 - paint.descent()));
	    canvas.drawText(names[i], textX, textY, paint);
	}
       
		//��ɻ���֮��   ���� 
	     canvas.restore();
	    
	    super.onDraw(canvas);
	}
	
   //���°ڷ��Լ���λ��
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
       
	}

}
