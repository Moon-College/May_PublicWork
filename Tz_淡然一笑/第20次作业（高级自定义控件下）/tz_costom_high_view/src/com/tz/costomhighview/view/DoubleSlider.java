package com.tz.costomhighview.view;

import com.tz.costomhighview.R;
import android.content.Context;
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
	private int span_region_height; //每一个区域的高度
	
	private float circle_x;  //大圆的x坐标
	private final int TEXT_PADDINT = 20;
	
	private boolean isUpTouched;
	private boolean isDownTouched;
	 
	public DoubleSlider(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		gray_bg = bitmap(R.drawable.axis_before);
		green_bg = bitmap(R.drawable.axis_after);
		circle_bg = bitmap(R.drawable.btn);
		price_num_bg = bitmap(R.drawable.bg_number);
		
		paint = new Paint();
		paint.setColor(Color.GRAY);
		price_up = 1000;
		price_down = 200;  //自定义属性设置在xml里
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
		
		scale_h = (float) measuredHeight/slider_height;  //缩放比例,注意要转换成float类型
		
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
		
		//画大圆的x坐标，上下大圆的x坐标相等
		circle_x = (this.getWidth()/scale_h - circle_bg.getWidth())/2; 
		//上限价格对应的y坐标
		circle_y_up = getCircleYByPrice(price_up);
		canvas.drawBitmap(circle_bg, circle_x, circle_y_up-circle_bg.getWidth()/2, paint);  //画上限大圆    top=circle_y_up-circle_bg/2;
		
		//下限价格对应的y坐标
		circle_y_down = getCircleYByPrice(price_down);
		canvas.drawBitmap(circle_bg, circle_x, circle_y_down-circle_bg.getWidth()/2, paint); //画下限大圆
		
		//画绿色滑竿
		//裁剪部分矩形
		Rect src = new Rect(0, (int)(circle_y_up+circle_bg.getHeight()/2), slide_width, (int)(circle_y_down-circle_bg.getHeight()/2));
		//绘制到画布坐标系的矩形
		Rect dst = new Rect((int)slider_x, (int)(circle_y_up+circle_bg.getHeight()/2), (int)(slider_x+slide_width), (int)(circle_y_down-circle_bg.getHeight()/2));
		canvas.drawBitmap(green_bg, src, dst, paint);
		
		//绘制左边长方形的图片和里面的价格内容
		Rect rect_up = getRectByY(circle_y_up);
		//bitmap绘制那张图片,src画完整部分【Rect】或是裁剪部分【null】,dst坐标系,paint画笔
//		canvas.drawBitmap(bitmap, src, dst, paint);  
		canvas.drawBitmap(price_num_bg, null, rect_up, paint);   //此方法很重要,要记住
		
		Rect rect_down = getRectByY(circle_y_down);
		canvas.drawBitmap(price_num_bg, null, rect_down, paint);
		
		//绘制文本
		float text_up_x = (3*rect_up.width()/4-paint.measureText(String.valueOf(price_up)))/2;
		float text_up_y = circle_y_up+(paint.descent()-paint.ascent())/2-paint.descent();
		canvas.drawText(String.valueOf(price_up), text_up_x, text_up_y, paint);
		
		float text_down_x = (3*rect_down.width()/4-paint.measureText(String.valueOf(price_down)))/2;
		float text_down_y = circle_y_down+(paint.descent()-paint.ascent())/2-paint.descent();
		canvas.drawText(String.valueOf(price_down), text_down_x, text_down_y, paint);
		
		//绘制完成以后
		canvas.restore(); //重置,恢复之前的状态
		super.onDraw(canvas);
	}

	private Rect getRectByY(float y) {
		Rect rect = new Rect();
		rect.left = 0;
		rect.right = (int) circle_x;
		float text_h = paint.descent()-paint.ascent(); 
		rect.top = (int) (y-text_h/2-TEXT_PADDINT);
		rect.bottom = (int) (y+text_h/2+TEXT_PADDINT);
		return rect;
	} 	

	//根据价格计算出价格所在的y坐标
	private float getCircleYByPrice(int price) {
		float y;
		if(price < 0){
			price = 0;
		}
		if(price > 10000){
			price = 10000;
		}
		if(price<=FIFTH_STAGE&&price>FOURTH_STAGE){
			//1000<price<=10000   	第4区域  
			//9000*(10000-9000)/(10000-1000)+slide_width/2
			y = span_region_height*(FIFTH_STAGE-price)/(FIFTH_STAGE-FOURTH_STAGE)+slide_width/2;
		}else if(price<=FOURTH_STAGE&&price>THIRD_STAGE){
			//500<price<=1000		第3区域+span_region_height
			y = span_region_height*(FOURTH_STAGE-price)/(FOURTH_STAGE-THIRD_STAGE)+slide_width/2+span_region_height;
		}else if(price<=THIRD_STAGE&&price>SECOND_STAGE){
			//200<price<=500		第2区域+2*span_region_height
			y = span_region_height*(THIRD_STAGE-price)/(THIRD_STAGE-SECOND_STAGE)+slide_width/2+2*span_region_height;
		}else if(price<=SECOND_STAGE&&price>FIRST_STAGE){
			//0<price<=200			第1区域+3*span_region_height
			y = span_region_height*(SECOND_STAGE-price)/(SECOND_STAGE-FIRST_STAGE)+slide_width/2+3*span_region_height;
		}else{
			//price<0       4*span_region_height+slide_width/2
			y = 4*span_region_height+slide_width/2;
		}
		return y;
	}
	
	public int getPriceByY(float y){
		int price = 0;
		if(y<slide_width/2){
			y = slide_width/2;
		}
		if(y<slide_width/2+4*span_region_height){
			y = slide_width/2+4*span_region_height;	
		}
		if(y>=slide_width/2&&y<slide_width/2+span_region_height){  //1000~10000
			//10000-(y-slide_width/2)/span_region_height
			price = (int) (FIFTH_STAGE-(FIFTH_STAGE-FOURTH_STAGE)*(y-slide_width/2)/span_region_height);
		}else if(y>=slide_width/2+span_region_height&&y<slide_width/2+2*span_region_height){
			price = (int) (FOURTH_STAGE-(FOURTH_STAGE-THIRD_STAGE)*(y-slide_width/2)/span_region_height)-span_region_height;
		}else if(y>=slide_width/2+2*span_region_height&&y<slide_width/2+3*span_region_height){
			price = (int) (THIRD_STAGE-(THIRD_STAGE-SECOND_STAGE)*(y-slide_width/2)/span_region_height)-2*span_region_height;
		}else if(y>=slide_width/2+3*span_region_height&&y<slide_width/2+4*span_region_height){
			price = (int) (SECOND_STAGE-(SECOND_STAGE-FIRST_STAGE)*(y-slide_width/2)/span_region_height)-3*span_region_height;
		}else{
			y = 0;	
		}
		
		//对应价格进行刻度的限制
		if(price<=1000){
			int mol = price%20;
			if(mol>=10){
				price = price-mol+20;
			}
		}
		if(price>1000){
			int mol = price%1000;
			if(mol>=500){
				price = price-mol+1000;
			}else{
				price = price -mol;
			}
		}
		return price;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			float x = event.getX()/scale_h;
			float y = event.getY()/scale_h;
			if(x>=circle_x&&x<circle_x+circle_bg.getWidth()){
				if(y>=price_up-circle_bg.getHeight()/2&&y<=price_up+circle_bg.getHeight()/2){
					//按到了上限的大圆
					isUpTouched = true;
					isDownTouched = false;
				}
				if(y>=price_down-circle_bg.getHeight()/2&&y<=price_down+circle_bg.getHeight()/2){
					//按到了下限的大圆
					isDownTouched = true;
					isUpTouched = false;
				}
			}
			
			break;
		case MotionEvent.ACTION_MOVE:
			float y2 = event.getY()/scale_h;  //注意此处是除以画笔的比例，画布的y坐标
			if(isUpTouched){
				price_up = getPriceByY(y2);
				if(price_up<=price_down){
					price_up = price_down;
				}
			}
			if(isDownTouched){
				price_down = getPriceByY(y2);
				if(price_down>price_up){
					price_down = price_up;
				}
			}
			this.invalidate(); //刷新重绘,调用onDraw()方法
			break;
		case MotionEvent.ACTION_UP:
			isUpTouched = false;
			isDownTouched = false;
			break;

		default:
			break;
		}
		return true;
	}
	
	
}
