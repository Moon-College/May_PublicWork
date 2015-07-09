package com.dd.dd_custom_view.view;

import com.dd.dd_custom_view.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomView extends View {
	private Bitmap gray_bg;// 灰色的背景滑竿
	private Bitmap green_bg;// 绿色的背景滑竿
	private Bitmap btn;// 大饼
	private Bitmap num_price;// 左边价格绿色背景
	private final int FIRST_STAGE = 0;
	private final int SECOND_STAGE = 200;
	private final int THIRD_STAGE = 500;
	private final int FOURTH_STAGE = 1000;
	Paint paint;// 画笔
	private int price_up;// 价格上限
	private int price_down;// 价格下限
	private float y_up;// 价格对应的y坐标上限，指大饼的中心点的y坐标
	private float y_down;// 价格对应的y坐标下限，下面指大饼的中心点的y坐标
	private int bg_height;// 滑竿的高度
	private int bg_width;// 滑竿的高度，球的高度
	private int span_state;// 每一个等级的区域高度
	private float scale_h;

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 初始化数据，比如图片
		gray_bg = iBmp(R.drawable.axis_before);
		green_bg = iBmp(R.drawable.axis_after);
		btn = iBmp(R.drawable.btn);
		num_price = iBmp(R.drawable.bg_number);
		paint = new Paint();
		paint.setColor(Color.GRAY);// 灰色
	}

	public Bitmap iBmp(int resId) {
		return BitmapFactory.decodeResource(getResources(), resId);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);//父容器指定的宽
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);//父容器指定的高
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		//在wrap_content情况下，不再使用系统默认给我们控件的fill_parent模式，而是根据自己的需求来决定宽高
		//计算wrap_content模式下，宽高的取值
		bg_height = gray_bg.getHeight();
		bg_width = gray_bg.getWidth();
		span_state = (bg_height - bg_width)/4;
		int measuredHeight = (modeHeight == MeasureSpec.EXACTLY)?sizeHeight:bg_height;
		measuredHeight = Math.min(measuredHeight, sizeHeight);
		int measuredWidth = 2*measuredHeight/3;
		scale_h = (float)measuredHeight/bg_height;
		setMeasuredDimension(measuredWidth, measuredHeight);
		Log.v("home", measuredWidth+"height:"+measuredHeight);
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		//对画布进行缩放
		canvas.save();
		canvas.scale(scale_h, scale_h);
		//开始绘画
		//计算滑竿的x坐标
		int bg_x = (int) ((this.getWidth()/scale_h-gray_bg.getWidth())/2);
		canvas.drawBitmap(gray_bg, bg_x, 0, paint);
		
		//绘制右边的5个文本
		String[] str = new String[]{
				"无限",
				String.valueOf(FOURTH_STAGE),
				String.valueOf(THIRD_STAGE),
				String.valueOf(SECOND_STAGE),
				String.valueOf(FIRST_STAGE)
		};
		paint.setTextSize(20/scale_h);
		for (int i = 0; i < str.length; i++) {
			int text_x = 5*bg_x/4;
			//(paint.descent()-paint.ascent())这是文本的高度，ascent的值是负的
			float text_y = (i*span_state+bg_width/2+(paint.descent()-paint.ascent())/2-paint.descent());
			canvas.drawText(str[i], text_x, text_y, paint);
		}
		//完成绘制后重置
		canvas.restore();
		super.onDraw(canvas);
	}
}
