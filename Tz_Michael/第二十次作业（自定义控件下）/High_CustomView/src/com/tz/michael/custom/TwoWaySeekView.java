package com.tz.michael.custom;

import com.tz.michael.activity.R;

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
	/**可以拖动的大圆的直径*/
	private int handleWidth;
	/**可以拖动的大圆的x坐标*/
	private float handleX;
	/**上限价格*/
	private int price_up;
	/**下限价格*/
	private int price_down;
	/**划分的每个区间的高度*/
	private float areaHeight;
	/**价格文本的上下内边距*/
	private final int TEXTPADDING=15;
	/**上面可拖动的大圆的中心点y坐标*/
	private float handle_up_Y;
	/**下面可拖动的大圆的中心点y坐标*/
	private float handle_down_Y;
	/**上把手是否被按倒*/
	private boolean isUpTouched;
	/**下把手是否被按倒*/
	private boolean isDownTouched;
	
	
	
	public int getPrice_up() {
		return price_up;
	}

	/**
	 *设置上限价格
	 * @param price_up
	 */
	public void setPrice_up(int price_up) {
		this.price_up = price_up;
		invalidate();
	}

	public int getPrice_down() {
		return price_down;
	}

	/**
	 * 设置下限价格
	 * @param price_down
	 */
	public void setPrice_down(int price_down) {
		this.price_down = price_down;
		invalidate();
	}

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
		TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.TwoWaySeekView);
		price_up=a.getInt(R.styleable.TwoWaySeekView_priceUp,1000);
		price_down=a.getInt(R.styleable.TwoWaySeekView_priceDown, 200);
		a.recycle();
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
		handleWidth=handle_img.getWidth();
		setMeasuredDimension(measureWidth, measureHight);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		areaHeight = (this.getHeight()/scall-ballWidth)/(moneyLevel.length-1);
		canvas.save();//保存画布当前状态
		canvas.scale(scall, scall);//缩放画布
		//计算滑竿坐标
		//之前仅仅对画布进行了缩放，所以这里需要把控件的整体还原下
		float poleX=(this.getWidth()/scall-ballWidth)/2;
		canvas.drawBitmap(pole_bg_default, poleX, 0, null);
		//计算右边文字的x坐标
		paint.setTextSize(textSize/scall);
		float textX=poleX*(1+textXAddscall)+0.5f*handleWidth;
		for(int i=0;i<moneyLevel.length;i++){
			float textY=i*areaHeight+ballWidth/2+(paint.descent()-paint.ascent())/2-paint.descent();
			canvas.drawText(String.valueOf(moneyLevel[moneyLevel.length-1-i]), textX, textY, paint);
		}
		//画可以拖动的上下两个大圆
		handleX=(this.getWidth()/scall-handleWidth)/2;
		handle_up_Y = getYByPrice(price_up);
		float handle_upY=handle_up_Y-0.5f*handleWidth;
		canvas.drawBitmap(handle_img, handleX, handle_upY, null);
		handle_down_Y = getYByPrice(price_down);
		float handle_downY=handle_down_Y-0.5f*handleWidth;
		canvas.drawBitmap(handle_img, handleX, handle_downY, null);
		//画绿色图片
		//裁剪部分矩形
		Rect src=new Rect(0, (int)(handle_up_Y+0.5f*handleWidth), ballWidth, (int) (handle_down_Y-0.5f*handleWidth));
		Rect dst=new Rect((int)poleX, (int)(handle_up_Y+0.5f*handleWidth), (int) (poleX+ballWidth), (int) (handle_down_Y-0.5f*handleWidth));
		canvas.drawBitmap(pole_bg_select, src, dst, null);
		//画左侧的指针图片
		Rect rect_up=getRectByY(handle_up_Y);
		canvas.drawBitmap(selector_img, null, rect_up, null);
		Rect rect_down=getRectByY(handle_down_Y);
		canvas.drawBitmap(selector_img, null, rect_down, null);
		//画指针图片上的价格文本
		float textPrice_upY=handle_up_Y+(paint.descent()-paint.ascent())/2-paint.descent();
		float textPrice_upX=(3*rect_up.width()/4-paint.measureText(String.valueOf(price_up)))/2;
		canvas.drawText(String.valueOf(price_up), textPrice_upX, textPrice_upY, paint);
		float textPrice_downY=handle_down_Y+(paint.descent()-paint.ascent())/2-paint.descent();
		float textPrice_downX=(3*rect_up.width()/4-paint.measureText(String.valueOf(price_down)))/2;
		canvas.drawText(String.valueOf(price_down), textPrice_downX, textPrice_downY, paint);
		canvas.restore();//还原画布的缩放前的状态
		super.onDraw(canvas);
	}

	/**
	 * 根据y坐标，得到一个巨型
	 * @param handle_up_Y
	 * @return
	 */
	private Rect getRectByY(float y) {
		Rect rect=new Rect();
		rect.left=0;
		rect.right=(int) handleX;
		int textHight=(int) (paint.descent()-paint.ascent());
		rect.top=(int) (y-0.5f*textHight-TEXTPADDING);
		rect.bottom=(int) (y+0.5f*textHight+TEXTPADDING);
		return rect;
	}

	/**
	 * 根据价格获得对应的y坐标
	 * @param price
	 * @return
	 */
	private float getYByPrice(int price) {
		float y=0;
		if(price<0){
			price=0;
		}
		if(price>10000){
			price=10000;
		}
		if(price<=10000&&price>1000){
			y=0.5f*ballWidth+areaHeight*(10000-price)/(10000-1000)+0*areaHeight;
		}else if(price<=1000&&price>500){
			y=0.5f*ballWidth+areaHeight*(1000-price)/(1000-500)+1*areaHeight;
		}else if(price<=500&&price>200){
			y=0.5f*ballWidth+areaHeight*(500-price)/(500-200)+2*areaHeight;
		}else if(price<=200&&price>0){
			y=0.5f*ballWidth+areaHeight*(200-price)/(200-0)+3*areaHeight;
		}else{
			y=0.5f*ballWidth+4*areaHeight;
		}
		return y;
	}
	
	/**
	 * 根据y坐标获取对应的价格
	 * @param y
	 * @return
	 */
	private int getPriceByY(float y){
		int price=0;
		if(y<0.5f*ballWidth){
			y=0.5f*ballWidth;
		}
		if(y>0.5f*ballWidth+4*areaHeight){
			y=0.5f*ballWidth+4*areaHeight;
		}
		if(y>=0.5f*ballWidth&&y<0.5f*ballWidth+1*areaHeight){
			price=(int) (10000-(10000-1000)*(0.5f*ballWidth-y)/(0.5f*ballWidth-(0.5f*ballWidth+1*areaHeight)));
		}else if(y>=0.5f*ballWidth+1*areaHeight&&y<0.5f*ballWidth+2*areaHeight){
			price=(int) (1000-(1000-500)*(0.5f*ballWidth+1*areaHeight-y)/(0.5f*ballWidth+1*areaHeight-(0.5f*ballWidth+2*areaHeight)));
		}else if(y>=0.5f*ballWidth+2*areaHeight&&y<0.5f*ballWidth+3*areaHeight){
			price=(int) (500-(500-200)*(0.5f*ballWidth+2*areaHeight-y)/(0.5f*ballWidth+2*areaHeight-(0.5f*ballWidth+3*areaHeight)));
		}else if(y>=0.5f*ballWidth+3*areaHeight&&y<0.5f*ballWidth+4*areaHeight){
			price=(int) (200-(200-0)*(ballWidth+3*areaHeight-y)/(ballWidth+3*areaHeight-(0.5f*ballWidth+4*areaHeight)));
		}else{
			price=0;
		}
		if(price<1000){
			int mod=price%20;
			if(mod>=10){
				price=price-mod+20;
			}else{
				price=price-mod;
			}
		}
		if(price>1000){
			int mod=price%1000;
			if(mod>=500){
				price=price-mod+1000;
			}else{
				price=price-mod;
			}
		}
		return price;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//注意这里指的是画布坐标系--所以需要除以缩放比例
			float x=event.getX()/scall;
			float y=event.getY()/scall;
			if(x>=handleX&&x<=handleX+handle_img.getWidth()){
				if(y>=handle_up_Y-handle_img.getWidth()/2&&y<=handle_up_Y+handle_img.getWidth()/2){
					//按到了上把手
					isUpTouched=true;
					isDownTouched=false;
				}
				if(y>=handle_down_Y-handle_img.getWidth()/2&&y<=handle_down_Y+handle_img.getWidth()/2){
					//按到了下把手
					isUpTouched=false;
					isDownTouched=true;
				}
				if(handle_up_Y==handle_down_Y){
					if(handle_down_Y==0.5f*ballWidth){
						isUpTouched=false;
						isDownTouched=true;
					}
					if(handle_down_Y==0.5f*ballWidth+4*areaHeight){
						isUpTouched=true;
						isDownTouched=false;
					}
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			float yMove=event.getY()/scall;
			if(isUpTouched){
				price_up=getPriceByY(yMove);
				if(price_up<=price_down){
					price_up=price_down;
				}
			}
			if(isDownTouched){
				price_down=getPriceByY(yMove);
				if(price_down>=price_up){
					price_down=price_up;
				}
			}
			invalidate();
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
	
}
