package com.decent.slidingbutton.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.decent.slidingbutton.R;

public class SlidingButton extends View {

	private Bitmap gray_bg;
	private Bitmap green_bg;
    private Paint paint;
	private float scale_h;
	
	private String[] CAR_LEVEL = {"无上限","劳斯莱斯","奔驰宝马","本田丰田","BYD吉利"};
	private int span_length;
	private int ball_width;
	
	public SlidingButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initData();
		// TODO Auto-generated constructor stub
	}

	public SlidingButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initData();
		// TODO Auto-generated constructor stub
	}

	public SlidingButton(Context context) {
		super(context);
		initData();
		// TODO Auto-generated constructor stubs
	}

	private void initData() {
		gray_bg = loadBitmapImage(R.drawable.axis_before);
		green_bg = loadBitmapImage(R.drawable.axis_after);
		paint = new Paint();
	}

	public Bitmap loadBitmapImage(int resourceId) {
		return BitmapFactory.decodeResource(getResources(), resourceId);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		/*
		 * 获取父亲控件传过来的宽和高的size还有mode
		 */
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		
		//获取图片的宽度和高度
		int picHeight = gray_bg.getHeight();
		int picWidth = gray_bg.getWidth();
		//这个是计算那个除了球的，每一节直线的高度，球的宽度和高度可以就认为是picWidth
		span_length = (picHeight - 5*picWidth)/4;
		ball_width = picWidth;
		/*
		 * 对于onMeasure里面的模式：
		 * MeasureSpec.EXACTLY-----对应fill_parent(width或者height和父亲控件一样)
		 *                         或者具体大小比如300dp
		 * MeasureSpec.AT_MOST-----对应wrap_content,告诉子控件最大能摆放的空间是多大
		 * 
		 * 所以onMeasure里面主要就是去处理MeasureSpec.AT_MOST(wrap_content)的情况下
		 * 控件大小和父亲控件的不一致的情况
		 */
		int measuredHeight = (heightMode == MeasureSpec.EXACTLY)?heightSize:picHeight;
		//需要选择measuredHeight和heightSize之间的最小值
		measuredHeight = Math.min(measuredHeight,heightSize);
		int measuredWidth = 2*measuredHeight/3;
		
		setMeasuredDimension(measuredWidth, measuredHeight);
		scale_h = (float)measuredHeight/picHeight;
		// TODO Auto-generated method stub
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}	
	
	@Override
	protected void onDraw(Canvas canvas) {
		//控件的高度和图片的高度不一致，所以需要缩放画布
		//1、先保存画布的状态，后面好恢复
		canvas.save();
		/*
		 * 2、设置x轴和y轴的缩放比例，一般是一样的，否则会变形
		 */
		canvas.scale(scale_h, scale_h);
		/*
		 * 计算坐标，然后绘制bitmap
		 * imageY 就是0开始
		 * imageX 就是(x轴宽度-图片的宽度)/2的一半，但是还要注意this.getWidth()
		 *        是缩放之后的距离，需要还原成真实的距离再来计算
		 */
		int imageY = 0;
		int imageX = (int) ((this.getWidth()/scale_h - green_bg.getWidth() )/2);
		canvas.drawBitmap(green_bg, imageX, imageY, paint);
		
		/*
		 * 设置文字的大小，也需要注意缩放的问题
		 * scale_h越小说明文字需要的字体越大
		 */
		paint.setTextSize(20/scale_h);
		/*
		 * 3、绘制文字,一共5挡
		 */
		for(int i=0;i<CAR_LEVEL.length;i++){
			//文字的x坐标就是图片的x坐标再过去一点点，大概多1/4的位置
			float textX = 5*imageX/4;
			/*
			 * 文字的y坐标，由三个值组成
			 * 球:(i*2+1)球的半径
			 * 杆子:i*杆子的长度
			 * 字体基线的调整:(最低字符相对于baseline的坐标descent-最高字符相对于baseline的坐标ascent)/2 - 最低字符相对于baseline的坐标descent
			 *            后面减去 descent的原因是我们想调整的不是desent的坐标，而是baseline的坐标
			 */
			float textY = (i*2+1)*ball_width/2 
					+ i*span_length 
					+ ((paint.descent()-paint.ascent())/2-paint.descent());
			Log.d("INFO", "descent:"+paint.descent()+",ascent:"+paint.ascent());
			canvas.drawText(CAR_LEVEL[i], textX, textY, paint);	
		}
		/*
		 * 恢复canvas的状态
		 */
		canvas.restore();
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}

}
