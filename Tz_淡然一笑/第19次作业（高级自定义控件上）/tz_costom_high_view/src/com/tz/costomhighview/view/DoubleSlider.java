package com.tz.costomhighview.view;

import com.tz.costomhighview.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义控件：双向滑竿
 * @author fcc
 *
 */
public class DoubleSlider extends View{
	
	private Bitmap gray_bg;   //灰色背景滑竿
	private Bitmap green_bg;  //绿色背景滑竿
	private Bitmap circle_bg;    //绿色大圆背景
	private Bitmap price_num_bg; //左边价格绿色背景
	
	//滑竿上对应的价格5个等级
	private final int FIRST_STAGE = 0;
	private final int SECOND_STAGE = 200;
	private final int THIRD_STAGE = 500;
	private final int FOURTH_STAGE = 1000;
	private final int FIFTH_STAGE = 10000;	
	
	private Paint paint; //画笔
	
	private int price_up;   //上线价格  10000
	private int price_down; //下限价格  0
	
	private float circle_y_up;    //价格对应的y坐标上限,指上限大圆中心点的y坐标
	private float circle_y_down;  //价格对应的y坐标下限,指下限大圆中心点的y坐标
	
	private int slider_height;  //滑竿的高度
	
	private float scale_h;  //画布缩放
	
	private int slide_width;  //滑竿的宽度,也就是大圆的宽度
	
	private  int span_region_height; //每一个区域的高度
	 
	public DoubleSlider(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		gray_bg = bitmap(R.drawable.axis_before);
		green_bg = bitmap(R.drawable.axis_after);
		circle_bg = bitmap(R.drawable.btn);
		price_num_bg = bitmap(R.drawable.bg_number);
		
		paint = new Paint();
		paint.setColor(Color.GRAY);
	}
	
	//根据图片id加载图片
	public Bitmap bitmap(int resId){
		return BitmapFactory.decodeResource(getResources(), resId);
	}
	
	//测量自己的宽高
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);  //父容器指定的宽
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);  //模式
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec); //父容器指定的高
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);  //模式
		//如果在wrap_content情况下,不再使用系统默认给我们的fill_parent模式,根据业务需要确定宽高
		//如果是wrap_contetnt,计算宽高
		slider_height = gray_bg.getHeight();  //滑竿高度就是灰色背景滑竿
		slide_width = gray_bg.getWidth();  //滑竿宽度,也就是大圆的宽度
		span_region_height = (slider_height - slide_width)/4;  //每一个区域的高度
		
		int measuredHeight = (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : slider_height;
		measuredHeight = Math.min(measuredHeight, sizeHeight);
		int measuredWidth = 2*measuredHeight/3;  //我们设定宽是高的2/3
		
		scale_h = (float) measuredHeight/slider_height;  //缩放比例
		
		setMeasuredDimension(measuredWidth, measuredHeight);
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	//绘制里面的内容
	@Override
	protected void onDraw(Canvas canvas) {
		//画布进行缩放
		canvas.save();
		canvas.scale(scale_h, scale_h);  //等比例缩放
		//开始绘制
		//滑竿的x坐标,注意此处是画布的宽度【缩放比例】
		int slider_x = (int) ((this.getWidth()/scale_h - gray_bg.getWidth())/2);
		canvas.drawBitmap(gray_bg, slider_x, 0, paint);
		
		//绘制右边的文本
		String [] nums = new String []{
				"无限",
				String.valueOf(FOURTH_STAGE),
				String.valueOf(THIRD_STAGE),
				String.valueOf(SECOND_STAGE),
				String.valueOf(FIRST_STAGE)
		}; 
		paint.setTextSize(28/scale_h);  //设置文本画笔尺寸【以画布缩放比例】
		for (int i = 0; i < nums.length; i++) {
			int text_x = 5*slider_x/4;
//			float text_y = i*span_region_height+slide_width/2;
			float text_y = (i*span_region_height+slide_width/2+(paint.descent()-paint.ascent())/2-paint.descent());
			canvas.drawText(nums[i], text_x, text_y, paint);
		}
		//绘制完成以后
		canvas.restore(); //重置,恢复之前的状态
		super.onDraw(canvas);
	}
}
