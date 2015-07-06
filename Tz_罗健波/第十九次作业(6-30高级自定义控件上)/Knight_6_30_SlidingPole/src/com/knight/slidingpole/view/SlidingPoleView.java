package com.knight.slidingpole.view;

import com.knight.slidingpole.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SlidingPoleView extends View{

	private Bitmap gray_bg,green_bg;//两个背景滑竿灰色和绿色
	private Bitmap bigRound;//滑竿上的大圆圈
	private Bitmap tagBmp;//左侧价格标签
	//5个区间的价格
	private final int FIRST_PRICE = 0;
	private final int SECOND_PRICE = 200;
	private final int THIRD_PRICE = 500;
	private final int FOURTH_PRICE = 1000;
	private final int FIFTH_PRICE = 10000;
	//画布跟画框的比率
	private float radio_h;
	//图片的高
	private int pole_h;
	//画布的高
	private int canv_h;
	//图片宽
	private int pole_w;
	//滑竿区4个间的高度
	private int section_h;
	//画文字需要画笔
	private Paint paint;
	//左侧价格上限
	private int price_up;//价格上限
	private int price_down;//价格下限
	private float y_up;//上面圆圈中心点Y坐标
	private float y_down;//下面圆圈中心点Y坐标
	
	public SlidingPoleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//初始化数据 
		gray_bg = getBitmap(R.drawable.axis_before);//灰色背景
		green_bg = getBitmap(R.drawable.axis_after);//绿色背景
		bigRound = getBitmap(R.drawable.btn);//绿色大饼
		tagBmp = getBitmap(R.drawable.bg_number);//价格标签
		paint = new Paint();//初始化画笔
		paint.setColor(Color.GRAY);//画笔颜色灰色
	}

	private Bitmap getBitmap(int resId) {
		// 取得bitmap对象
		return BitmapFactory.decodeResource(getResources(), resId);
	}

	/**
	 *  测量控件的大小
	 *  控件大小的模式有三种 ：fill_parent(宽高有父容器决定)
	 *  200dp、
	 *  wrap_content
	 *  画布比率=(float)画框的大小/画布的大小
	 *  内容高度、宽度 
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//获取控件大小和控件设置的模式
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);//父容器指定的宽度
		int modWidth = MeasureSpec.getMode(widthMeasureSpec);//设置的模式
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);//父容器指定的高度
		int modHeight = MeasureSpec.getMode(heightMeasureSpec);//设置的模式
		//判断控件设置的模式来设置控件的大小
		//自定义控件就是 修改wrap_content模式时候系统的默认配置 按照我们自己的需求来修改
		//wrap_content 模式下控件的宽高
		//控件高度=画布高度 + 球的高度 ;如果内容过高 子控件wrap_content也不能高过父控件指定的高
		pole_h = gray_bg.getHeight();//滑竿图片高
		canv_h = pole_h + bigRound.getHeight();//画布高=图片高+大饼高度
		pole_w = gray_bg.getWidth();//滑竿图片宽
		//区间的高度=(滑竿高度 - 小求的高度(宽))/4
		section_h = (pole_h - pole_w)/4;
		int measuredHeight = (modHeight == MeasureSpec.EXACTLY)?sizeHeight:pole_h;
		measuredHeight = Math.min(measuredHeight, sizeHeight);//控件内容高度与父控件给定的高度取小
		//控件宽度=2*高度/3
		int measuredWidth = 2* measuredHeight / 3;
		Log.e("INFO", "width:"+measuredWidth+"+height:"+measuredHeight);
		//比率 画框的高度 / 画布内容高度  = 要缩小的倍数
		radio_h = (float)measuredHeight / canv_h;
		setMeasuredDimension(measuredWidth, measuredHeight);
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//禁止调用父类提供的setMeasuredDimension
	}

	/**
	 * 画控件
	 * 滑竿的位置设置在画布的中心点 
	 * 中心点 = (画框宽度/比率 - 图片宽度)/2
	 * 画框宽度/比率 就是画布缩小前的大小 
	 * 画布缩小前 画框中只能显示 部分的视图内容(原来600px的画显示在300px的画框 框中只显示了部分画)
	 * 通过缩小画布 画框中能显示的画布内容变多(通过画框与画布内容的比率来缩小画布 等比缩放) 
	 * 
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// 缩放画布让内容完整显示
		canvas.save();
		canvas.scale(radio_h, radio_h);
		//画图
		//画滑竿
		//计算滑竿x坐标 和y坐标
		int left = (int) ((this.getWidth()/radio_h - gray_bg.getWidth())/2);
		int top = bigRound.getHeight()/2;
		canvas.drawBitmap(gray_bg, left, top, paint);
		//定义滑竿右侧的固定区间值
		String[] stage = new String[]{
				"不限",
				String.valueOf(FOURTH_PRICE),
				String.valueOf(THIRD_PRICE),
				String.valueOf(SECOND_PRICE),
				String.valueOf(FIRST_PRICE)
		};
		//画笔文字大小 
		paint.setTextSize(20/radio_h);
		//画文字 
		for (int i = 0; i < stage.length; i++) {
			//文字的X坐标 在滑竿的右侧 
			int text_x = 5 * left / 4;
			//文字的Y坐标 与滑竿的圆圈中心点对齐
			//文字基线的Y坐标 = 求中心点Y坐标 + 文字高度/2-基线离文字底部距离+大球高/2
			//paint.descent(基线离底部距离是正数)-paint.ascent(基线离文字顶部距离是负数)=文字高度
			int text_y = (int) (i * section_h + pole_w/2 + (paint.descent() - paint.ascent())/2 - paint.descent()+bigRound.getHeight()/2);
			canvas.drawText(stage[i], text_x, text_y, paint);
		}
		
		//画大圆
		
		//画左侧价格标签
		
		//重置画布
		canvas.restore();
		super.onDraw(canvas);
	}
}
