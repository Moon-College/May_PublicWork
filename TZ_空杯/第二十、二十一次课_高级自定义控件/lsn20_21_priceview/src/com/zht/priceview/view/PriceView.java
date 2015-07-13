/**
 * Project Name:lsn20_21_priceview
 * File Name:PriceView.java
 * Package Name:com.zht.priceview.view
 * Date:2015-7-6下午4:52:43
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.priceview.view;

import com.zht.priceview.R;

import android.annotation.SuppressLint;
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
 * ClassName:PriceView <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-7-6 下午4:52:43 <br/>
 * 
 * @author hongtao
 * @version
 * @since JDK 1.6
 * @see
 */
@SuppressLint("DrawAllocation")
public class PriceView extends View {

	private Bitmap gray_axis;// 灰色轴
	private Bitmap green_axis;// 绿色轴
	private Bitmap roll_btn;// 滑动的大饼
	private Bitmap price_banner;// 价格条
	private int measuredWidth;
	private int measuredHeight;
	private float scale;// 缩放比例
	private Paint paint;// 画笔
	private int span_state;// 每一个等级的区域高度
	public static final int FIRST_STAGE = 0;
	public static final int SECOND_STAGE = 200;
	public static final int THIRD_STAGE = 500;
	public static final int FOURTH_STAGE = 1000;
	public static final int FIFTH_STAGE = 10000;
	private int axis_height;
	private int axis_width;
	private int price_up;
	private int price_down;
	private float y_up;
	private float y_down;
	private float btn_x;
	private final int TEXT_PADDING = 15;
	private boolean isUpTouched;
	private boolean isDownTouched;

	public PriceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		gray_axis = getBitmap(R.drawable.axis_before);
		green_axis = getBitmap(R.drawable.axis_after);
		roll_btn = getBitmap(R.drawable.btn);
		price_banner = getBitmap(R.drawable.bg_number);
		paint = new Paint();
		paint.setColor(Color.GRAY);
		price_up = 1000;
		price_down = 200;
	}

	private Bitmap getBitmap(int rId) {
		return BitmapFactory.decodeResource(getResources(), rId);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		axis_height = gray_axis.getHeight();
		axis_width = gray_axis.getWidth();
		span_state = (axis_height - axis_width) / 4;
		measuredHeight = (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight
				: axis_height;
		measuredWidth = 2 * measuredHeight / 3;
		scale = (float) measuredHeight / axis_height;
		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 对画布进行缩放
		canvas.save();
		canvas.scale(scale, scale);
		// 绘制滑竿
		// 滑竿的x坐标
		int axis_x = (int) ((measuredWidth / scale - gray_axis.getWidth()) / 2);
		canvas.drawBitmap(gray_axis, axis_x, 0, paint);
		// 绘制右边5个文本
		String[] numbers = new String[] { String.valueOf(FIFTH_STAGE),
				String.valueOf(FOURTH_STAGE), String.valueOf(THIRD_STAGE),
				String.valueOf(SECOND_STAGE), String.valueOf(FIRST_STAGE), };
		paint.setTextSize(20 / scale);

		for (int i = 0; i < numbers.length; i++) {
			// 开始绘制文本
			int text_x = 5 * axis_x / 4;
			float text_y = (i * span_state + axis_width / 2
					+ (paint.descent() - paint.ascent()) / 2 - paint.descent());
			canvas.drawText(numbers[i], text_x, text_y, paint);
		}
		btn_x = (this.getWidth() / scale - roll_btn.getWidth()) / 2;
		// 上限价格对应的y轴
		y_up = getBtnYByPrice(price_up);
		canvas.drawBitmap(roll_btn, btn_x, y_up - roll_btn.getWidth() / 2,
				paint);
		// 下限价格对应的y轴
		y_down = getBtnYByPrice(price_down);
		canvas.drawBitmap(roll_btn, btn_x, y_down - roll_btn.getWidth() / 2,
				paint);
		// 画绿色滑竿
		// 裁剪部分矩形
		Rect src = new Rect(0, (int) (y_up + roll_btn.getHeight() / 2),
				axis_width, (int) (y_down - roll_btn.getHeight() / 2));
		// 绘制到画布坐标系的矩形
		Rect dst = new Rect((int) axis_x,
				(int) (y_up + roll_btn.getHeight() / 2),
				(int) (axis_x + axis_width),
				(int) (y_down - roll_btn.getHeight() / 2));
		canvas.drawBitmap(green_axis, src, dst, paint);
		// 绘制左边的价格长方形（图片和文字）
		Rect rect_up = getRectByY(y_up);
		canvas.drawBitmap(price_banner, null, rect_up, paint);
		Rect rect_down = getRectByY(y_down);
		canvas.drawBitmap(price_banner, null, rect_down, paint);
		// 绘制文本
		float text_up_y = y_up + (paint.descent() - paint.ascent()) / 2
				- paint.descent();
		float text_up_x = (3 * rect_up.width() / 4 - paint.measureText(String
				.valueOf(price_up))) / 2;
		canvas.drawText(String.valueOf(price_up), text_up_x, text_up_y, paint);

		float text_down_y = y_down + (paint.descent() - paint.ascent()) / 2
				- paint.descent();
		float text_down_x = (3 * rect_down.width() / 4 - paint
				.measureText(String.valueOf(price_down))) / 2;
		canvas.drawText(String.valueOf(price_down), text_down_x, text_down_y,
				paint);
		// 完成绘制以后
		canvas.restore();// 重置
		super.onDraw(canvas);
	}

	private Rect getRectByY(float y) {
		Rect rect = new Rect();
		rect.left = 0;
		rect.right = (int) btn_x;
		float text_h = paint.descent() - paint.ascent();
		rect.top = (int) (y - text_h / 2 - TEXT_PADDING);
		rect.bottom = (int) (y + text_h / 2 + TEXT_PADDING);
		return rect;
	}

	/**
	 * 
	 * getBtnYByPrice:根据价格算出该价格对应的y轴. <br/>
	 * 
	 * @author acer
	 * @param price
	 * @return
	 */
	private float getBtnYByPrice(int price) {
		float y;
		if (price < 0) {
			price = 0;
		}
		if (price > 10000) {
			price = 10000;
		}
		if (price <= FIFTH_STAGE && price > FOURTH_STAGE) {
			y = span_state * (FIFTH_STAGE - price)
					/ (FIFTH_STAGE - FOURTH_STAGE) + axis_width / 2;
		} else if (price <= FOURTH_STAGE && price > THIRD_STAGE) {
			y = span_state * (FOURTH_STAGE - price)
					/ (FOURTH_STAGE - THIRD_STAGE) + axis_width / 2
					+ span_state;
		} else if (price <= THIRD_STAGE && price > SECOND_STAGE) {
			y = span_state * (THIRD_STAGE - price)
					/ (THIRD_STAGE - SECOND_STAGE) + axis_width / 2 + 2
					* span_state;
		} else if (price <= SECOND_STAGE && price > FIRST_STAGE) {
			y = span_state * (SECOND_STAGE - price)
					/ (SECOND_STAGE - FIRST_STAGE) + axis_width / 2 + 3
					* span_state;
		} else {
			y = 4 * span_state + axis_width / 2;
		}
		return y;
	}

	/**
	 * 根据y坐标来获取价格*Y坐标指的画布的Y坐标
	 */
	public int getPriceByY(float y) {
		int price;
		if (y < axis_width / 2) {
			y = axis_width / 2;
		}
		if (y > (axis_width / 2 + 4 * span_state)) {
			y = axis_width / 2 + 4 * span_state;
		}
		if (y >= axis_width / 2 && y < axis_width / 2 + span_state) {
			// 1000-10000
			price = (int) (FIFTH_STAGE - (FIFTH_STAGE - FOURTH_STAGE)
					* (y - axis_width / 2) / span_state);
		} else if (y >= axis_width / 2 + span_state
				&& y < axis_width / 2 + 2 * span_state) {
			price = (int) (FOURTH_STAGE - (FOURTH_STAGE - THIRD_STAGE)
					* (y - axis_width / 2 - span_state) / span_state);
		} else if (y >= axis_width / 2 + 2 * span_state
				&& y < axis_width / 2 + 3 * span_state) {
			price = (int) (THIRD_STAGE - (THIRD_STAGE - SECOND_STAGE)
					* (y - axis_width / 2 - 2 * span_state) / span_state);
		} else if (y >= axis_width / 2 + 3 * span_state
				&& y < axis_width / 2 + 4 * span_state) {
			price = (int) (SECOND_STAGE - (SECOND_STAGE - FIRST_STAGE)
					* (y - axis_width / 2 - 3 * span_state) / span_state);
		} else {
			price = 0;
		}

		// 对于价格需要进行刻度的最小限制
		if (price <= 1000) {
			int mol = price % 20;
			if (mol >= 10) {
				price = price - mol + 20;
			} else {
				price = price - mol;
			}
		}
		if (price > 1000) {
			int mol = price % 1000;
			if (mol >= 500) {
				price = price - mol + 1000;
			} else {
				price = price - mol;
			}
		}
		return price;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			float x = event.getX() / scale;
			float y = event.getY() / scale;
			if (x >= btn_x && x <= btn_x + roll_btn.getWidth()) {
				if (y >= (y_up - roll_btn.getHeight() / 2)
						&& y <= (y_up + roll_btn.getHeight() / 2)) {
					// 说明按到了上饼
					isUpTouched = true;
					isDownTouched = false;
				}
				if (y >= (y_down - roll_btn.getHeight() / 2)
						&& y <= (y_down + roll_btn.getHeight() / 2)) {
					// 说明按到了下饼
					isDownTouched = true;
					isUpTouched = false;
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			float y2 = event.getY() / scale;
			if (isUpTouched) {
				price_up = getPriceByY(y2);
				if (price_up <= price_down) {
					price_up = price_down;
				}
			}

			if (isDownTouched) {
				price_down = getPriceByY(y2);
				if (price_down >= price_up) {
					price_down = price_up;
				}
			}
			this.invalidate();// 刷新重绘
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
