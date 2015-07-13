package com.casit.hc.view;

import com.casit.hc.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class slindingButtons extends View {
	private Bitmap grayBg;//灰色背景
	private Bitmap greenBg;//绿色背景
	private Bitmap bigButton; //大饼
	private Bitmap num_price;//左边价格绿色背景
	private final int FIRST_STAGE = 0;
	private final int SECOND_STAGE = 200;
	private final int THIRD_STAGE = 500;
	private final int FOURTH_STAGE = 1200;
	private final int FIFTH_STAGE = 10000;
	Paint paint;
	private int price_up ;
	private int price_down ;
	private float y_up; //价格上饼对应的Y坐标
	private float y_down; //价格下饼对应的Y坐标
	private int bgHeight;
	private int bgWidth;
	private float scale_h;
    private float bg_width;//球的宽度
	private float span_height;
	private float btnx;
	private final int TEXT_PADDING = 15;
	private boolean isUpTouched;
	private boolean isDownTouched;
    //构造函数
	public slindingButtons(Context context, AttributeSet attrs) {
		super(context, attrs);
		grayBg =  initBitmap( R.drawable.axis_before);
		greenBg = initBitmap( R.drawable.axis_after);
		bigButton =  initBitmap( R.drawable.btn);
		num_price =  initBitmap(R.drawable.bg_number);	
		paint = new Paint();
		paint.setColor(Color.GRAY);
		price_up= 1000;//自定义属性尽量写在XML
		price_down = 200;
		//DisplayMetrics displayMetrics = new DisplayMetrics();
		//context.gets
		// TODO Auto-generated constructor stub
	}
	
	
	public Bitmap initBitmap(int resId) {
		// TODO Auto-generated method stub

		return BitmapFactory.decodeResource(getResources(), resId);
		
	}


	//测量自己宽高
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		//fillparent 是跟父容器来决定的，父容器设置PADING会影响大小
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int modeWidth  = MeasureSpec.getSize(widthMeasureSpec);//wrapconten 其实就是FILLPARENT,父容器不知道子容器的宽高
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight  = MeasureSpec.getSize(heightMeasureSpec);
		/**如果是WRAPCONT就是需要自己决定
		 * 驻澳是改不是系统默认情况下给我们的FILLPARENT模式，而是根据需求来给定宽高
		 */
		bgHeight = grayBg.getHeight();
		bg_width = grayBg.getWidth();
		span_height = (bgHeight-bg_width)/4;
		int measuredHeight =  (modeHeight==MeasureSpec.EXACTLY)?sizeHeight:bgHeight;
	    measuredHeight = Math.min(measuredHeight, sizeHeight);	  
		int measureWidth = measuredHeight*2/3;
		bgWidth = measureWidth;
		scale_h = (float)measuredHeight/bgHeight;
		setMeasuredDimension(measureWidth, measuredHeight);
		//int widthMeasureSpec(宽的模式和宽的大小), int heightMeasureSpec (高的模式和高的大小)
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	//绘制
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.save();
		canvas.scale(scale_h, scale_h);
	    int bg_x = (int) ((this.getWidth()/scale_h- grayBg.getWidth())/2);
		canvas.drawBitmap(grayBg, bg_x, 0, paint);
	//	int bg_x
		String [] numbers = new String[]{
		"无限",
		String.valueOf(FOURTH_STAGE),
		String.valueOf(THIRD_STAGE),
		String.valueOf(SECOND_STAGE),
        String.valueOf(FIRST_STAGE)
		};
		paint.setTextSize(20/scale_h);
		for(int i = 0;i<numbers.length;i++){
			int text_x = bg_x*5/4;
			float text_y = bg_width/2+i*span_height +(paint.descent()-paint.ascent())/2-paint.descent();
			canvas.drawText(numbers[i], text_x, text_y, paint);
		}
		//画饼
		btnx = (this.getWidth()/scale_h - bigButton.getWidth())/2;
		
		y_up = getYByPrice(price_up);
		canvas.drawBitmap(bigButton, btnx, y_up-bigButton.getHeight()/2, paint);
		y_down =  getYByPrice(price_down);
		canvas.drawBitmap(bigButton, btnx, y_down-bigButton.getHeight()/2, paint);
		
		Rect rect_up = getRectByY( y_up);
		canvas.drawBitmap(num_price, null, rect_up, paint);
		Rect rect_down = getRectByY( y_down);
		canvas.drawBitmap(num_price, null, rect_down, paint);
	    float text_upy = y_up +(paint.descent()-paint.ascent())/2-paint.descent();
	    float text_downy = y_down + (paint.descent()-paint.ascent())/2-paint.descent();
	    float text_upx = (rect_up.width()*3/4 - paint.measureText(String.valueOf(price_up)))/2;
	    float text_downx = (rect_up.width()*3/4 - paint.measureText(String.valueOf(price_down)))/2;
	    canvas.drawText(String.valueOf(price_up), text_upx, text_upy, paint);
	    canvas.drawText(String.valueOf(price_down), text_downx, text_downy, paint);			    
	    int priceLeftUp =  getPriceByY(y_up);
		int priceLeftDown = getPriceByY(y_down);
		//canvas.drawText(text, x, y, paint)
		
		canvas.restore();		
	}
	private int getPriceByY(float y) {
		// TODO Auto-generated method stub
		int price;
		if(y<bg_width/2){
			y = bg_width/2;
		}
		if(y>bg_width/2+span_height*4){
			y = bg_width/2 + span_height*4;
		}
		if(y>=bg_width/2&&y<bg_width+span_height){
			price =(int) (FIFTH_STAGE- (FIFTH_STAGE-FOURTH_STAGE)*(y-bg_width/2)/span_height);
		}else if (y>=bg_width+span_height&&y<bg_width+span_height*2){
			price =(int) (FOURTH_STAGE- (FOURTH_STAGE-THIRD_STAGE)*(y-bg_width/2-span_height)/span_height);
		}else if (y>=bg_width+span_height*2&&y<bg_width+span_height*3){
			price =(int) (THIRD_STAGE- (THIRD_STAGE-SECOND_STAGE)*(y-bg_width/2-2*span_height)/span_height);			
		}else if (y>=bg_width+span_height*3&&y<bg_width+span_height*4){
			price =(int) (SECOND_STAGE- (SECOND_STAGE-FIRST_STAGE)*(y-bg_width/2-3*span_height)/span_height);		
		}else{	
			price = 0;
		}
		if(price <= FOURTH_STAGE){
			int mol = price%20;
			if(mol>=10){
				price = price+20-mol;
			}else{
				price = price -mol;
			}
		}else if (price >FOURTH_STAGE){
			int mol = price % 1000;
			if(mol>=500){
				price = price - mol +1000;
			}else {
				price = price - mol;
			}
		}
		return price;
	}


	private float getYByPrice(float price){
        float yPrice = 0;
		if(price<0){
			price = 0;
		}
		if(price>10000){
			price =10000;
		}
		if(price<=FIFTH_STAGE&&price>FOURTH_STAGE){
			yPrice = bg_width/2 + (1-(price-FOURTH_STAGE)/(FIFTH_STAGE-FOURTH_STAGE))*span_height;
		}else if(price<=FOURTH_STAGE&&price>THIRD_STAGE){
			yPrice = bg_width/2 + (1-(price-THIRD_STAGE)/(FOURTH_STAGE-THIRD_STAGE))*span_height+1*span_height;
		}else if(price<=THIRD_STAGE&&price>SECOND_STAGE){
			yPrice = bg_width/2 + (1-(price-SECOND_STAGE)/(THIRD_STAGE-SECOND_STAGE))*span_height+2*span_height;
		}else if(price<=SECOND_STAGE&&price>FIRST_STAGE){
			yPrice = bg_width/2 + (1-(price-FIRST_STAGE)/(SECOND_STAGE-FIRST_STAGE))*span_height+3*span_height;
		}else if(price<=FIRST_STAGE){
			yPrice = bg_width/2 +4*span_height;
		}
		return yPrice;
	}
	
	private Rect getRectByY(float y){
		Rect rect = new Rect();
		rect.left = 0 ;
		rect.right = (int) btnx;
		float text_h = paint.descent() - paint.ascent();
		rect.top = (int) (y - text_h/2  - TEXT_PADDING);
		rect.bottom = (int) (y + text_h/2 + TEXT_PADDING);
		return rect;
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			float x = event.getX()/scale_h;
			float y = event.getY()/scale_h;
			Log.i("INFO", "ydown"+y);
			if(x>btnx&&x<=btnx+bigButton.getWidth()){
				if(y>=y_up - bigButton.getHeight()/2&&y<=y_up+bigButton.getHeight()/2){
					isUpTouched = true;
					isDownTouched=false;
					Log.i("INFO", "ydown1"+y);
				}
				if(y>=(y_down - bigButton.getHeight()/2)&&y<=y_down+bigButton.getHeight()/2){
					isUpTouched = false;
					isDownTouched = true;
					Log.i("INFO", "ydown2"+y);
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			float y2 =event.getY()/scale_h;
			Log.i("INFO", "ymove"+y2);
			if(isUpTouched){
				 price_up = getPriceByY(y2);
				if(price_up <=price_down){
					price_up = price_down;
				}
			}
			if(isDownTouched){
				price_down = getPriceByY(y2);
				if(price_down>price_up){
					price_down = price_up;
				}
			}
			this.invalidate();
			break;
		case MotionEvent.ACTION_UP:
			isUpTouched =false;
			isDownTouched = false;
			break;
 
		default:
			break;
		}
		return true;
	}
	

}
