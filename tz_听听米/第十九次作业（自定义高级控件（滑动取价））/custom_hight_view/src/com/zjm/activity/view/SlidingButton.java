package com.zjm.activity.view;

import com.zjm.activity.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;

public class SlidingButton extends View {
	private PriceListener listener;
	
	public void setOnChangerPriceListener(PriceListener listener){
		this.listener = listener;
	};
	
	private Bitmap gray_bg;//灰色滑竿
	private Bitmap green_bg;//绿色滑竿
	private Bitmap bigBtn;//大圆形
	private Bitmap num_price;//价格背景图片
	
	private final int FIRST_STAGE = 0;
	private final int SECOND_STAGE = 200;
	private final int THIRD_STAGE = 500;
	private final int FOURTH_STAGE = 1000;
	private final int FIFTH_STAGE = 10000;
	
	private Paint paint;//画笔
	
	private int price_up;//价格上限
	private int price_down;//价格下限
	private float y_up;//价格对应的y坐标以及大圆形中心点的y坐标
	private float y_down;
	
	private int bg_height;//滑竿的高度
	private int bg_width;//滑竿的宽度
	private int span_height;//每一个等级的高度
	private float scale_h;//画布的缩放比例
	
	private float btn_x;//大圆形x坐标
	private final int TEXT_PADDING = 15;

	public SlidingButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		//获取自定义的XML属性名称
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.slidingbtn);
		price_up = a.getInt(R.styleable.slidingbtn_price_up, 1000);
		price_down = a.getInt(R.styleable.slidingbtn_price_down, 200);
		initView();
	}
	
	/**
	 * 初始化
	 *@author 邹继明
	 *@date 2015-7-5下午2:44:37
	 *@return void
	 */
	private void initView() {
		gray_bg = getBmp(R.drawable.axis_before);
		green_bg = getBmp(R.drawable.axis_after);
		bigBtn = getBmp(R.drawable.btn);
		num_price = getBmp(R.drawable.bg_number);
		paint = new Paint();
		paint.setColor(Color.GRAY);
	}
	
	private Bitmap getBmp(int resId){
		return BitmapFactory.decodeResource(getResources(), resId);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);//父容器指定的高度
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		
		//wrap_content模式下宽高的取值
		bg_height = gray_bg.getHeight();//控件的高=滑竿的高度
		bg_width = gray_bg.getWidth();//滑竿的宽度==滑竿球的宽高
		span_height = (bg_height - bg_width)/4;
		measuredHeight = (modeHeight == MeasureSpec.EXACTLY)?sizeHeight:bg_height;
		measuredHeight = Math.min(measuredHeight, sizeHeight);//取最小值
		measuredWidth = 2*measuredHeight/3;
		scale_h = (float)measuredHeight/bg_height;//容器的高度/图片的高度 = 画布缩放比例
		setMeasuredDimension(measuredWidth, measuredHeight);//设置控件的宽高
	}
	
		
	//右边的5个文本
	String [] numbers = new String[]{
			"无限",
			String.valueOf(FOURTH_STAGE),
			String.valueOf(THIRD_STAGE),
			String.valueOf(SECOND_STAGE),
			String.valueOf(FIRST_STAGE)
	};
	//测量容器的宽高
	private int measuredHeight;
	private int measuredWidth;
	private boolean isUpTouched;
	private boolean isDownTouched;
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();//保存当前画布状态
		//缩放画布
		canvas.scale(scale_h, scale_h);
		
		//开始绘画
		//计算滑竿x坐标
		/**
		 * 容器的宽度/缩放比例 = 画布的宽度
		 * (画布的宽度-滑竿的宽度)/2 = 滑竿的x坐标（滑竿在中心的位置）
		 * */
		int bg_x = (int) ((measuredWidth/scale_h- bg_width)/2);
		canvas.drawBitmap(gray_bg, bg_x, 0, paint);
		
		paint.setTextSize(35/scale_h);//画笔的尺寸
		//文本的x坐标
		float text_x = bg_x*5/4;
		for (int i = 0; i < numbers.length; i++) {
			float text_y = i*span_height+bg_width/2+((paint.descent()-paint.ascent())/2-paint.descent());
			canvas.drawText(numbers[i], text_x, text_y, paint);
		}
		
		//画两个大圆形
		btn_x = (measuredWidth/scale_h - bigBtn.getWidth())/2;
		
		//上限价格对应的y坐标
		y_up = getBtnYByPrice(price_up);
		canvas.drawBitmap(bigBtn,btn_x,y_up-bigBtn.getHeight()/2, paint);
		//下限价格对应的y坐标
		y_down = getBtnYByPrice(price_down);
		canvas.drawBitmap(bigBtn,btn_x,y_down - bigBtn.getHeight()/2, paint);
		
		//画绿色滑竿
		//裁剪部分矩形
		Rect src = new Rect(0, (int)(y_up+bigBtn.getHeight()/2), bg_width, (int)(y_down-bigBtn.getHeight()/2));
		//将剪裁的矩形绘制到画布坐标系的矩形
		Rect dst = new Rect(bg_x,(int)(y_up+bigBtn.getHeight()/2),bg_x+bg_width,(int)(y_down-bigBtn.getHeight()/2));
		canvas.drawBitmap(green_bg, src, dst, paint);
		
		//绘制坐标价格矩形
		Rect rect_up = getRectByY(y_up);
		canvas.drawBitmap(num_price, null, rect_up, paint);
		Rect rect_down = getRectByY(y_down);
		canvas.drawBitmap(num_price, null, rect_down, paint);
		
		//绘制价格
		float text_up_y = y_up + (paint.descent()-paint.ascent())/2 - paint.descent();
		float text_up_x = (3*rect_up.width()/4 - paint.measureText(String.valueOf(price_up)))/2;
		canvas.drawText(String.valueOf(price_up), text_up_x, text_up_y, paint);
		
		float text_down_y = y_down + (paint.descent()-paint.ascent())/2 - paint.descent();
		float text_down_x = (3*rect_down.width()/4 - paint.measureText(String.valueOf(price_down)))/2;
		canvas.drawText(String.valueOf(price_down), text_down_x, text_down_y, paint);
		
		canvas.restore();//完成后重置
		super.onDraw(canvas);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//获取触摸在画布中的坐标
			float x = event.getX()/scale_h;
			float y = event.getY()/scale_h;
			if(x>=btn_x&&x<=bigBtn.getWidth()+btn_x){
				if(y>=(y_up-bigBtn.getHeight()/2)&&y<=(y_up+bigBtn.getHeight()/2)){
					//按到上限价格
					isUpTouched = true;
					isDownTouched = false;
				}
				if(y>=(y_down-bigBtn.getHeight()/2)&&y<=(y_down+bigBtn.getHeight()/2)){
					//按到下限价格
					isDownTouched = true;
					isUpTouched = false;
				}
			}
			
			break;
		case MotionEvent.ACTION_MOVE:
			float y2 = event.getY()/scale_h;
			if(isUpTouched){
				isDownTouched = false;
				price_up = getPriceByY(y2);
				if(price_up<=price_down){
					break;
				}else{
					listener.onChanger(price_up, price_down);
				}
			}
			if(isDownTouched){
				isUpTouched = false;
				price_down = getPriceByY(y2);
				if(price_down>=price_up){
					break;
				}else{
					listener.onChanger(price_up, price_down);
				}
			}
			
			this.invalidate();//重绘
			break;
		case MotionEvent.ACTION_UP:
			
			break;

		default:
			break;
		}
		
		return true;
	}

	/**
	 * 根据Y坐标求价格
	 *@author 邹继明
	 *@date 2015-7-6下午10:48:04
	 *@param y
	 *@return
	 *@return int
	 */
	private int getPriceByY(float y) {
		int price = 0;
		if(y < bg_width/2){
			y = bg_width/2;
			price = 10000;
		}
		if(y>4*span_height+bg_width/2){
			y = 4*span_height+bg_width/2;
			price = 0;
		}
		if(y>=bg_width/2&&y<bg_width/2+span_height){
			//1000-10000
			price = (int) (FIFTH_STAGE - (FIFTH_STAGE - FOURTH_STAGE)*(y-bg_width/2)/span_height);
		}else if(y>=bg_width/2+span_height&&y<bg_width/2+2*span_height){
			price = (int) (FOURTH_STAGE - (FOURTH_STAGE - THIRD_STAGE)*(y-bg_width/2-span_height)/span_height);
		}else if(y>=bg_width/2+2*span_height&&y<bg_width/2+3*span_height){
			price = (int) (THIRD_STAGE - (THIRD_STAGE - SECOND_STAGE)*(y-bg_width/2-2*span_height)/span_height);
		}else if(y>=bg_width/2+3*span_height&&y<bg_width/2+4*span_height){
			price = (int) (SECOND_STAGE - (SECOND_STAGE - FIRST_STAGE)*(y-bg_width/2-3*span_height)/span_height);
		}else{
			price = 0 ;
		}
		
		//对于价格需要进行刻度的最小限制
		if(price<=1000){
			int mol = price%20;
			if(mol>=10){
				price = price - mol + 20;
			}else{
				price = price - mol;
			}
		}else if(price>1000){
			int mol = price%1000;
			if(mol>=500){
				price = price - mol + 500;
			}else{
				price = price - mol;
			}
		}
		
		return price;
	}

	/**
	 * 根据y坐标获取矩形
	 *@author 邹继明
	 *@date 2015-7-6下午10:11:34
	 *@param y
	 *@return
	 *@return Rect
	 */
	private Rect getRectByY(float y) {
		Rect rect = new Rect();
		rect.left = 0;
		rect.right = (int) btn_x;
		float text_h = paint.descent()-paint.ascent();//文字的高度
		rect.top = (int) (y - text_h/2 - TEXT_PADDING);
		rect.bottom = (int) (y + text_h/2 + TEXT_PADDING);
		return rect;
	}

	/**
	 * 根据价格求Y坐标
	 *@author 邹继明
	 *@date 2015-7-6下午9:30:04
	 *@param price
	 *@return
	 *@return float
	 */
	private float getBtnYByPrice(int price) {
		if(price<=0){
			price = 0 ;
		}
		if(price>10000){
			price = 10000;
		}
		float y = 0;
		if(price<=FIFTH_STAGE&&price>FOURTH_STAGE){
			y = bg_width/2 + span_height*(FIFTH_STAGE-price)/(FIFTH_STAGE-FOURTH_STAGE);
		}else if(price<=FOURTH_STAGE&&price>THIRD_STAGE){
			y = bg_width/2 + span_height*(FOURTH_STAGE-price)/(FOURTH_STAGE-THIRD_STAGE)+span_height;
		}else if(price<=THIRD_STAGE&&price>SECOND_STAGE){
			y = bg_width/2 + span_height*(THIRD_STAGE-price)/(THIRD_STAGE-SECOND_STAGE)+2*span_height;
		}else if(price<=SECOND_STAGE&&price>FIRST_STAGE){
			y = bg_width/2 + span_height*(SECOND_STAGE-price)/(SECOND_STAGE-FIRST_STAGE)+3*span_height;
		}else{
			y = bg_width/2 +4*span_height;
		}
		
		return y;
	}
	
	public int getPrice_up() {
		return price_up;
	}

	public void setPrice_up(int price_up) {
		this.price_up = price_up;
	}

	public int getPrice_down() {
		return price_down;
	}

	public void setPrice_down(int price_down) {
		this.price_down = price_down;
	}



	public interface PriceListener{
		public void onChanger(int priceUp,int priceDown);
	}
}

