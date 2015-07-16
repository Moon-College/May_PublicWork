/**
 * 
 */
package com.cn.customview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.cn.customview.R;
/**
 * 自定义滑竿
 * @author hequn
 *
 */
public class SlidingButton extends View {
	/** 灰色滑竿 */
	private Bitmap gray_slider;
	/**绿色滑竿*/
	private Bitmap green_slider;
	/**按钮 */
	private Bitmap btn;
	/** 数字背景图片 */
	private Bitmap bg_number;
	/** 画笔 */
	private Paint paint = new Paint();
	/** 缩放比例 */
	private float scale_h;
	/** 一个小球所占灰色滑竿的比例 */
	private float scale_ball = 0.043f;
	/** 滑竿上球与与球之间的距离 */
	private float span;
	/** 灰色滑条的高度 */
	private int gray_slider_height;
	/**价格的上限*/
	private int price_up;
	/**价格的下限*/
	private int price_low;
	/**滑动按钮的横坐标*/
	private float btn_x;
	/** 上按钮中心点的y坐标 */
	private float y_u;
	/*** 下按钮中心店的y坐标 */
	private float y_d;
	/** * 上面按钮被按下 */
	private boolean isUpTouched;
	/** 下面的按钮被按下 */
	private boolean isDownTouched;
	/** 价格内容填充 */
	private int PRICE_PADDING=10;
	//价格状态
	private int PRICE_FIRST_STATE=0;
	private int PRICE_SECOND_STATE=200;
	private int PRICE_THRED_STATE=500;
	private int PRICE_FOUTH_STATE=1000;
	private int PRICE_FIVE_STATE=10000;
			
	
	

	@SuppressLint("Recycle")
	public SlidingButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 加载图片资源
		gray_slider = BitmapFactory.decodeResource(getResources(), R.drawable.axis_before);
		green_slider=BitmapFactory.decodeResource(getResources(), R.drawable.axis_after);
		btn = BitmapFactory.decodeResource(getResources(), R.drawable.btn);
		bg_number = BitmapFactory.decodeResource(getResources(), R.drawable.bg_number);
		paint.setColor(Color.WHITE);
		//解析自定义属性
		TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.slidingButton);
		price_up=typedArray.getInt(R.styleable.slidingButton_price_u,PRICE_FIVE_STATE);
		price_low=typedArray.getInt(R.styleable.slidingButton_price_d,PRICE_FIRST_STATE);
	}

	/**
	 * 解析xml确定view的高度，宽度是高度的1/2 如果是高度是wrap_content，则高度保持和灰色滑竿高度一致
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
		int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);

		int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
		int heightSpecMoce = MeasureSpec.getMode(heightMeasureSpec);
		//获取灰色滑竿的高度
		gray_slider_height = gray_slider.getHeight();
		// 确定高度
		int height = (heightSpecMoce == MeasureSpec.EXACTLY) ? heightSpecSize : gray_slider_height;
		// 确定宽度
		int width = height / 2;
		// 设置画布缩放比例
		scale_h = height * 1f / gray_slider_height;
		span = (1 - scale_ball) / 4 * gray_slider_height;
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		// 缩放画布，保证图片可以在view中全部显示 缩放后画布像素宽度和像素高度不变，但位置发生了变化
		canvas.scale(scale_h, scale_h);
		float gray_slider_left = (this.getWidth() / scale_h - gray_slider.getWidth()) / 2;
		// 绘制灰色滑竿
		canvas.drawBitmap(gray_slider, gray_slider_left, 0, null);// 画图不需要画笔
		// 绘制滑竿右侧的文字
		String[] numbers = { "不限",String.valueOf(PRICE_FOUTH_STATE),String.valueOf(PRICE_THRED_STATE),String.valueOf(PRICE_SECOND_STATE), String.valueOf(PRICE_FIRST_STATE) };
		paint.setTextSize(20 / scale_h);
		for (int i = 0; i < numbers.length; i++) {
			float text_y = i * span + scale_ball / 2 * gray_slider_height + paint.descent();
			canvas.drawText(numbers[i], gray_slider_left * 4 / 3, text_y, paint);
		}
		btn_x = (this.getWidth() / scale_h - btn.getWidth()) / 2;
		// 计算上按钮中心点的y坐标
		y_u = getBtnYByPrice(price_up);
		// 绘制上边的按钮
		canvas.drawBitmap(btn, btn_x, y_u - btn.getHeight() / 2, paint);
		// 计算下按钮中心点的y坐标
		y_d = getBtnYByPrice(price_low);
		// 绘制下边的按钮
		canvas.drawBitmap(btn, btn_x, y_d - btn.getHeight() / 2, paint);
		Rect src=new Rect(0,(int)(y_u+btn.getHeight()/2),green_slider.getWidth(),(int)(y_d-btn.getHeight()/2));
		Rect dst=new Rect((int)gray_slider_left,(int)(y_u+btn.getHeight()/2), (int)(gray_slider_left+green_slider.getWidth()),(int)(y_d-btn.getHeight()/2));
		//绘制绿色滑竿
		canvas.drawBitmap(green_slider, src, dst, paint);
		
		// 绘制左边的价格矩形 矩形的位置由价格决定
		// 上边的矩形
		Rect rect_up = getRectByMidLine(y_u);
		canvas.drawBitmap(bg_number, null, rect_up, paint);
		// 下边的矩形
		Rect rect_low = getRectByMidLine(y_d);
		canvas.drawBitmap(bg_number, null, rect_low, paint);
		// 绘制矩形中的文本
		// 计算上文本的xy坐标
		float text_u_x = (rect_up.width() * 3 / 4 - paint.measureText(String.valueOf(price_up))) / 2;
		float text_u_y = rect_up.top - paint.ascent()+PRICE_PADDING;
		// 绘制上矩形中的文本
		canvas.drawText(String.valueOf(price_up), text_u_x, text_u_y, paint);
		// 计算下文本的xy坐标
		float text_d_x = (rect_low.width() * 3 / 4 - paint.measureText(String.valueOf(price_low))) / 2;
		float text_d_y = rect_low.top - paint.ascent()+PRICE_PADDING;
		// 绘制下矩形中的文本
		canvas.drawText(String.valueOf(price_low), text_d_x, text_d_y, paint);

		canvas.restore();
	}

	/**
	 * 根据按钮中点y坐标计算矩形的位置
	 * 
	 * @param y
	 * @return 矩形
	 */
	private Rect getRectByMidLine(float y) {
		Rect rect = new Rect();
		rect.left = 0;
		rect.right = (int) btn_x;
		float text_h = paint.descent() - paint.ascent();
		rect.top = (int) (y - text_h / 2)-PRICE_PADDING;
		rect.bottom = (int) (y + text_h / 2)+PRICE_PADDING;
		return rect;
	}

	/**
	 * 根据价格计算按钮中心点的Y坐标
	 * 
	 * @return
	 */
	private float getBtnYByPrice(float price) {
		float btn_y = 0;
		if (price >= PRICE_FIRST_STATE && price < PRICE_SECOND_STATE) {
			btn_y = scale_ball / 2 * gray_slider_height + 3 * span + (1 - price / PRICE_SECOND_STATE) * span;
		} else if (price >= PRICE_SECOND_STATE && price < PRICE_THRED_STATE) {
			btn_y = scale_ball / 2 * gray_slider_height + 2 * span + (1 - (price - PRICE_SECOND_STATE) / (PRICE_THRED_STATE - PRICE_SECOND_STATE)) * span;
		} else if (price >= PRICE_THRED_STATE && price < PRICE_FOUTH_STATE) {
			btn_y = scale_ball / 2 * gray_slider_height + 1 * span + (1 - (price - PRICE_THRED_STATE) / (PRICE_FOUTH_STATE - PRICE_THRED_STATE)) * span;
		} else {
			btn_y = scale_ball / 2 * gray_slider_height + (1 - (price - PRICE_FOUTH_STATE) / (PRICE_FIVE_STATE - PRICE_FOUTH_STATE)) * span;
		}
		return btn_y;
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//获取按下的xy坐标并转换成画布上的坐标
			float x=event.getX()/scale_h;
			float y=event.getY()/scale_h;
			if(x>=btn_x&&x<=btn_x+btn.getWidth()){
				//x坐标符合判断y坐标
				
				//判断手指是否按在上面的按钮上
				if(y>=y_u-btn.getHeight()/2&&y<=y_u+btn.getHeight()/2){
					isUpTouched=true;
					isDownTouched=false;
					//判断手指是否按在下面的按钮上
				}else if(y>=y_d-btn.getHeight()/2&&y<=y_d+btn.getHeight()/2){
					isDownTouched=true;
					isUpTouched=false;
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			float btn_y=event.getY();
			if(isUpTouched){
				//根据按钮的坐标计算价格
				price_up=getPriceByBtnY(btn_y);
				if(price_up<price_low){
					price_up=price_low;
				}
			}else if(isDownTouched){
				price_low=getPriceByBtnY(btn_y);
				if(price_low>price_up){
					price_low=price_up;
				}
			}
			
			//重绘
			this.invalidate();
			break;
		case MotionEvent.ACTION_UP:
			isUpTouched=false;
			isDownTouched=false;
			break;
		default:
			break;
		}
		return true;
	}
	/*
	 * 根据按钮的坐标计算价格
	 */
	private int getPriceByBtnY(float btn_y) {
		int price;
		//每个刻度的坐标长度
		float span=this.getHeight()*(1-scale_ball)/4;
		//半球的坐标高度
		float half_ball_height=this.getHeight()*scale_ball/2;
		if(btn_y<half_ball_height){
			price=PRICE_FIVE_STATE;
		}else if(btn_y>=half_ball_height&&btn_y<half_ball_height+span){
			//在1000~不限之间
			price=PRICE_FIVE_STATE-(int)((PRICE_FIVE_STATE-PRICE_FOUTH_STATE)*((btn_y-half_ball_height)/span));
		}else if(btn_y>half_ball_height+span&&btn_y<half_ball_height+2*span){
			//在500~1000之间
			price=PRICE_FOUTH_STATE-(int)((PRICE_FOUTH_STATE-PRICE_THRED_STATE)*((btn_y-half_ball_height-span)/span));
		}else if(btn_y>=half_ball_height+2*span&&btn_y<half_ball_height+3*span){
			//在200~500之间
			price=PRICE_THRED_STATE-(int) ((PRICE_THRED_STATE-PRICE_SECOND_STATE)*((btn_y-half_ball_height-2*span)/span));
		}else if(btn_y>=half_ball_height+3*span&&btn_y<half_ball_height+4*span){
			//在0~200之间
			price=PRICE_SECOND_STATE-(int) ((PRICE_SECOND_STATE-0)*((btn_y-half_ball_height-3*span)/span));
		}else{
			price=0;
		}
		//对价格将进行进度计算
		if(price<=1000){
			//精度为20
			int mol=price%20;
			if(mol>10){
				price=price-mol+20;//去零加整
			}else{
				price=price-mol;//去零存整
			}
		}else{
			//精度为1000
			int mol=price%1000;
			if(mol>500){
				price=price-mol+1000;//去零加整
			}else{
				price=price-mol;//去零存整
			}
		}
		return price;
	}
}
