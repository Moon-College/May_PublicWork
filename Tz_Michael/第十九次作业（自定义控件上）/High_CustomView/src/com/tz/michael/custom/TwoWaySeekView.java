package com.tz.michael.custom;

import com.tz.michael.activity.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
/**
 * 自定义控件的尝试
 * @author admin
 *
 */
public class TwoWaySeekView extends View {

	private Bitmap pole_bg_default;//默认的（灰色）滑竿图片
	private Bitmap pole_bg_select;//选中的（绿色）滑竿图片
	private Bitmap handle_img;//把手图片
	private Bitmap selector_img;//指针图片
	/**画布缩放的比例*/
	private float scall;
	/**画文字的画笔*/
	private Paint paint;
	private int ballWidth;//滑竿上的球的直径
	private int[] moneyLevel=new int[]{0,200,500,1000,10000};
	/**控件的宽高比例*/
	private float xDivideY;
	/**文本x坐标多出控件一半占控件一半的比例*/
	private float textXAddscall;
	/**文本的大小*/
	private float textSize;
	
	
	/**
	 * 设置文本多出控件一半占控件一半的比例
	 * @param textXAddscall
	 */
	public void setTextXAddscall(float textXAddscall) {
		this.textXAddscall = textXAddscall;
		invalidate();
	}

	/**
	 * 设置文字大小
	 * @param textSize
	 */
	public void setTextSize(float textSize) {
		this.textSize=textSize;
		paint.setTextSize(textSize);
		invalidate();
	}

	/**
	 * 设置文字颜色
	 * @param textColor
	 */
	public void setTextColor(int textColor) {
		paint.setColor(textColor);
		invalidate();
	}

	/**
	 * 动态设置宽高比例
	 * @param xDivideY
	 */
	public void setxDivideY(float xDivideY) {
		this.xDivideY = xDivideY;
		invalidate();
		requestLayout();
	}

	public TwoWaySeekView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//加载资源文件
		pole_bg_default=initBitmapResourse(R.drawable.axis_before);
		pole_bg_select=initBitmapResourse(R.drawable.axis_after);
		handle_img=initBitmapResourse(R.drawable.btn);
		selector_img=initBitmapResourse(R.drawable.bg_number);
		//设置画笔
		textSize=14;
		paint=new Paint();
		paint.setColor(Color.GRAY);
		paint.setTextSize(textSize);
		xDivideY=1/3.0f;
		textXAddscall=1/3.0f;
	}

	private Bitmap initBitmapResourse(int resId) {
		return BitmapFactory.decodeResource(getResources(), resId);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measureWidth=MeasureSpec.getSize(widthMeasureSpec);
		int widthMode=MeasureSpec.getMode(widthMeasureSpec);
		int measureHight=MeasureSpec.getSize(heightMeasureSpec);
		int hightMode=MeasureSpec.getMode(heightMeasureSpec);
		//计算当前模式下的高度
		int hight=hightMode==MeasureSpec.EXACTLY?measureHight:pole_bg_select.getHeight();
		//取当前模式下的高度和父容器能给的最大高度值中的最小值，这样可以防止图片太高控件超出屏幕
		measureHight=Math.min(measureHight, hight);
		//这里假设控件的宽高比为1:3
		measureWidth=(int) (measureHight*xDivideY);
		//计算缩放的比例
		scall=measureHight/(float)pole_bg_select.getHeight();
		ballWidth=pole_bg_select.getWidth();
		setMeasuredDimension(measureWidth, measureHight);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();//保存画布当前状态
		canvas.scale(scall, scall);//缩放画布
		//计算滑竿坐标
		//之前仅仅对画布进行了缩放，所以这里需要把控件的整体还原下
		float poleX=(this.getWidth()/scall-ballWidth)/2;
		canvas.drawBitmap(pole_bg_default, poleX, 0, null);
		//计算右边文字的x坐标
		paint.setTextSize(textSize/scall);
		float textX=poleX*(1+textXAddscall);
		for(int i=0;i<moneyLevel.length;i++){
			float textY=i*(this.getHeight()/scall-ballWidth)/(moneyLevel.length-1)+ballWidth/2+(paint.descent()-paint.ascent())/2-paint.descent();
			canvas.drawText(String.valueOf(moneyLevel[moneyLevel.length-1-i]), textX, textY, paint);
		}
		canvas.restore();//还原画布的缩放前的状态
		super.onDraw(canvas);
	}
	
}
