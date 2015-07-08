package com.junwen.view;

import com.example.customview.R;

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

public class CustomView extends View {
	// 灰色的轴
	private Bitmap bg_Gray;
	// 绿色的轴
	private Bitmap bg_Green;
	// 数字
	private Bitmap bg_Number;
	// 大饼
	private Bitmap bg_bing;
	// 画笔
	private Paint paint;
	// 缩放比例
	private float scale;
	// 宽度
	private int measureWidht;
	/**
	 * 各个状态值
	 */
	private final int FIRST_STAGE = 0;
	private final int SECOND_STAGE = 200;
	private final int THIRD_STAGE = 500;
	private final int FOURTH_STAGE = 1000;
	private final int FIFTH_STAGE = 10000;
	//小饼的宽度
	private int bg_width;
	//区间的距离
	private int span_widht;
	//灰色的X坐标
	private float btn_x;
	//上面的价格
	private float price_up = 10000;
	//下面的价格
	private float price_down = 200;
	//间距
	private int PADDING = 25;
	//上面大饼的y坐标
	private float btn_yup;
	//下面大饼的Y坐标
	private float btn_ydown;
	//上面大饼被按下
	private boolean isUpEnalbe;
	//下面大饼被按下
	private boolean isDownEnalbe;
	/**
	 * 构造
	 * 
	 * @param context
	 * @param attrs
	 */
	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		bg_Gray = getBitmap(R.drawable.axis_before);
		bg_Green = getBitmap(R.drawable.axis_after);
		bg_Number = getBitmap(R.drawable.bg_number);
		bg_bing = getBitmap(R.drawable.btn);
		paint = new Paint();
		paint.setColor(Color.RED);
	}

	/**
	 * 确认大小
	 * 
	 * @param widthMeasureSpec
	 * @param heightMeasureSpec
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// fill_parent或者固定值的时候
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int widhtMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		// 如果是Wrap_content方式,则取轴的高度
		bg_width = bg_Gray.getWidth();
		int measureHeight = (heightMode == MeasureSpec.AT_MOST) ? bg_Gray
				.getHeight() : heightSize;
		measureHeight = Math.min(bg_Gray.getHeight(), heightSize);
		measureWidht = measureHeight * 2 / 3;
		scale = (float) measureHeight / bg_Gray.getHeight();
		span_widht = (bg_Gray.getHeight() - bg_width) / 4;
		setMeasuredDimension(measureWidht, measureHeight);
	}

	/**
	 * 绘制
	 * 
	 * @param canvas
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		//缩放
		canvas.scale(scale, scale);
		//计算灰色轴的x坐标
		float left = (measureWidht / scale - bg_Gray.getWidth()) / 2;
		//绘制灰色轴
		canvas.drawBitmap(bg_Gray, left, 0, paint);
		
		//绘制文本
		String[] number = new String[] { "无限", String.valueOf(FOURTH_STAGE),
				String.valueOf(THIRD_STAGE), String.valueOf(SECOND_STAGE),
				String.valueOf(FIRST_STAGE) };
		paint.setTextSize(50 / scale);
		for (int i = 0; i < number.length; i++) {
			float text_X = left * 5 / 4;
			float text_Y = i * span_widht + bg_width / 2;
			canvas.drawText(number[i], text_X, text_Y
					+ (paint.descent() - paint.ascent()) / 2 - paint.descent(),
					paint);
		}
		//获取大饼的X坐标
		btn_x = (this.getWidth() / scale - bg_bing.getWidth()) / 2;

		// 画大饼
		btn_yup = getBingByPrice(price_up);
		canvas.drawBitmap(bg_bing, btn_x, btn_yup - bg_bing.getWidth() / 2,
				paint);
		btn_ydown = getBingByPrice(price_down);
		canvas.drawBitmap(bg_bing, btn_x, btn_ydown - bg_bing.getWidth() / 2,
				paint);
		
		//画绿色的轴
		Rect src = new Rect(0, (int)(btn_yup+bg_bing.getHeight()/2),bg_width,(int) (btn_ydown - bg_bing.getHeight()/2));
		Rect dst = new Rect((int)left, (int)(btn_yup+bg_bing.getHeight()/2),(int) (left + bg_width), (int) (btn_ydown - bg_bing.getHeight()/2));
		canvas.drawBitmap(bg_Green, src, dst, paint);
		
		// 画左上面的number
		Rect rect_up = gerRectByY(btn_yup);
		canvas.drawBitmap(bg_Number, null, rect_up, paint);
		// 画左下面的number
		Rect rect_down = gerRectByY(btn_ydown);
		canvas.drawBitmap(bg_Number, null, rect_down, paint);
		
		// 绘制文本
		float text_upX = (rect_up.width() * 2.7f / 4 - paint.measureText(String
				.valueOf(price_up))) / 2;
		float text_upY = btn_yup + ((paint.descent() - paint.ascent()) / 2)
				- paint.descent();
		canvas.drawText(String.valueOf(price_up), text_upX, text_upY, paint);
		
		// 绘制下面的文本
		float text_downX = (rect_down.width() * 2.7f / 4 - paint
				.measureText(String.valueOf(price_down))) / 2;
		float text_downY = btn_ydown + ((paint.descent() - paint.ascent()) / 2)
				- paint.descent();
		canvas.drawText(String.valueOf(price_down), text_downX, text_downY,
				paint);
		//重置
		canvas.restore();
	}

	/**
	 * 根据Y坐标算出矩形
	 * 
	 * @param btn_y
	 * @return
	 */
	private Rect gerRectByY(float btn_y) {
		Rect rect = new Rect();
		rect.left = 0;
		rect.right = (int) btn_x;
		float text_h = paint.descent() - paint.ascent();
		rect.top = (int) (btn_y - text_h / 2 - PADDING);
		rect.bottom = (int) (btn_y + text_h / 2 + PADDING);
		return rect;
	}

	/**
	 * 根据价钱来返回指定的Y坐标
	 * 
	 * @param price_up2
	 * @return
	 */
	private float getBingByPrice(float price_up) {
		float y = 0;
		if (price_up < 0) {
			price_up = 0;
		}
		if (price_up >= 10000) {
			price_up = 10000;
		}
		if (price_up <= FIFTH_STAGE && price_up > FOURTH_STAGE) {
			y = span_widht * (FIFTH_STAGE - price_up)
					/ (FIFTH_STAGE - FOURTH_STAGE) + bg_width / 2;
		} else if (price_up <= FOURTH_STAGE && price_up > THIRD_STAGE) {
			y = span_widht * (FOURTH_STAGE - price_up)
					/ (FOURTH_STAGE - THIRD_STAGE) + bg_width / 2 + span_widht;
		} else if (price_up <= THIRD_STAGE && price_up > SECOND_STAGE) {
			y = span_widht * (THIRD_STAGE - price_up)
					/ (THIRD_STAGE - SECOND_STAGE) + bg_width / 2 + 2
					* span_widht;
		} else if (price_up <= SECOND_STAGE && price_up >= FIRST_STAGE) {
			y = span_widht * (SECOND_STAGE - price_up)
					/ (SECOND_STAGE - FIRST_STAGE) + bg_width / 2 + 3
					* span_widht;
		} else {
			y = 4 * span_widht + bg_width / 2;
		}
		return y;
	}

	/**
	 * 返回制定ID的图片
	 * 
	 * @param axisBefore
	 * @return
	 */
	private Bitmap getBitmap(int axisBefore) {
		return BitmapFactory.decodeResource(getResources(), axisBefore);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			float x = event.getX() / scale;
			float y = event.getY() / scale;
			//如果X轴对应
			if(x >= btn_x && x <= btn_x + bg_bing.getWidth()){
				//上面
				if(y >= btn_yup - bg_bing.getHeight()/2 && y<= btn_yup + bg_bing.getHeight()/2){
					isUpEnalbe = true;
					isDownEnalbe = false;
				}else if(y >= btn_ydown - bg_bing.getHeight()/2 && y<= btn_ydown + bg_bing.getHeight()/2){
					//下面
					isDownEnalbe = true;
					isUpEnalbe = false;
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			float py = event.getY()/ scale;
			if(isDownEnalbe){
				//如果下面可以按的话
				int priceByY = getPriceByY(py);
				price_down = priceByY;
				if(price_down>=price_up){
					price_down = price_up;
				}
			}
			if(isUpEnalbe){
				//如果上面可以按的话
				int priceByY = getPriceByY(py);
				price_up = priceByY;
				if(price_up <= price_down){
					price_up = price_down;
				}
			}
		
			this.invalidate();
			break;
		case MotionEvent.ACTION_UP:
			isDownEnalbe = false;
			isUpEnalbe = false;
			break;

		default:
			break;
		}
		return true;
	}
	
	/**
	 * 根据Y坐标获取Y坐标上对应的价钱
	 * @param y
	 * @return
	 */
	private int getPriceByY(float y) {
		int price = 0;
		if( y< bg_width / 2){
			 y = bg_width / 2;
		}
		if(y >(bg_width /2 +  4 * span_widht)){
			 y = bg_width /2 +  4 * span_widht;
		}
		
		if( y >= bg_width / 2 && y < bg_width /2 + span_widht){
			//1000 - 10000
			price = (int) (FIFTH_STAGE - (FIFTH_STAGE - FOURTH_STAGE)*((y - bg_width / 2) / span_widht));
		}
		else if( y >= bg_width /2 + span_widht && y < bg_width / 2 + span_widht * 2 ){
			// 500-1000
			price = (int) (FOURTH_STAGE - (FOURTH_STAGE - THIRD_STAGE)*((y - bg_width / 2 - span_widht) / span_widht));
		}
		else if( y >=bg_width / 2 + span_widht * 2&& y < bg_width / 2 + span_widht * 3 ){
			// 200-500
			price = (int) (THIRD_STAGE - (THIRD_STAGE -SECOND_STAGE)*((y - bg_width / 2 - span_widht* 2) / span_widht));
		}
		else if( y >= bg_width / 2 + span_widht * 3 && y < bg_width / 2 + span_widht * 4 ){
			// 0-200
			price = (int) (SECOND_STAGE - (SECOND_STAGE -FIRST_STAGE)*((y - bg_width / 2 - span_widht* 3) / span_widht));
		}else{
			price = 0;
		}
		if(price <= 1000){
			int mol = price % 20;
			if(mol >=10){
				price = price - mol + 20;
			}else{
				price = price - mol;
			}
		}
		if(price >1000){
			int mol = price %1000;
			if(mol >=500){
				price = price - mol + 1000;
			}else{
				price = price - mol;
			}
		}
		
		return price;
	}
}
