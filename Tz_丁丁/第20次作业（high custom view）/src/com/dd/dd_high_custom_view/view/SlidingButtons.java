package com.dd.dd_high_custom_view.view;

import com.dd.dd_high_custom_view.R;

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

public class SlidingButtons extends View {
	private Bitmap gray_bg;// 灰色的背景滑竿
	private Bitmap green_bg;// 绿色的背景滑竿
	private Bitmap btn;// 大饼
	private Bitmap num_price;// 左边价格绿色背景
	private final int FIRST_STAGE = 0;
	private final int SECOND_STAGE = 200;
	private final int THIRD_STAGE = 500;
	private final int FOURTH_STAGE = 1000;
	private final int FIFTH_STAGE = 10000;
	Paint paint;// 画笔
	private int price_up;// 价格上限
	private int price_down;// 价格下限
	private float y_up;// 价格对应的y坐标上限，指大饼的中心点的y坐标
	private float y_down;// 价格对应的y坐标下限，下面指大饼的中心点的y坐标
	private int bg_height;// 滑竿的高度
	private int bg_width;// 滑竿的高度，球的高度
	private int span_state;// 每一个等级的区域高度
	private float scale_h;
	private float btn_x;
	private final int TEXT_PADDING = 15;
	private boolean isUpTouched;
	private boolean isDownTouched;

	public SlidingButtons(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.slidingView);
		price_up = a.getInt(R.styleable.slidingView_priceUp, 1500);
		price_down =  a.getInt(R.styleable.slidingView_priceDown, 500);
		if (price_up>10000) {
			price_up=10000;
		}
		if (price_down<0) {
			price_down=0;
		}
		// 初始化数据，比如图片
		gray_bg = iBmp(R.drawable.axis_before);
		green_bg = iBmp(R.drawable.axis_after);
		btn = iBmp(R.drawable.btn);
		num_price = iBmp(R.drawable.bg_number);
		paint = new Paint();
		paint.setColor(Color.GRAY);// 灰色
//		price_up = 1000;
//		price_down = 200;// 尽量自定义属性设置在xml里
	}

	public Bitmap iBmp(int resId) {
		return BitmapFactory.decodeResource(getResources(), resId);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);// 父容器指定的宽
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);// 父容器指定的高
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		// 就是改在wrap_content情况下，不再使用系统默认给我们控件的fill_parent模式，而是根据自己的需求来决定宽高
		// 计算wrap_content模式下，宽高的取值
		bg_height = gray_bg.getHeight();// 滑竿高度
		bg_width = gray_bg.getWidth();// 滑竿宽度
		span_state = (bg_height - bg_width) / 4;
		int measuredHeight = (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight
				: bg_height;
		measuredHeight = Math.min(measuredHeight, sizeHeight);// 测量的高度不能比父容器还高
		int measuredWidth = 2 * measuredHeight / 3;
		scale_h = (float) measuredHeight / bg_height;
		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 对画布进行缩放
		canvas.save();// 保存当前画布状态
		canvas.scale(scale_h, scale_h);
		// 开始绘画
		// 计算滑竿的x坐标
		int bg_x = (int) ((this.getWidth() / scale_h - gray_bg.getWidth()) / 2);
		canvas.drawBitmap(gray_bg, bg_x, 0, paint);

		// 绘制右边的5个文本
		String[] numbers = new String[] { "无限", String.valueOf(FOURTH_STAGE),
				String.valueOf(THIRD_STAGE), String.valueOf(SECOND_STAGE),
				String.valueOf(FIRST_STAGE) };
		paint.setTextSize(20 / scale_h);// 设置文本的画笔尺寸
		for (int i = 0; i < numbers.length; i++) {
			// 开始绘制文本
			int text_x = 5 * bg_x / 4;
			float text_y = (i * span_state + bg_width / 2
					+ (paint.descent() - paint.ascent()) / 2 - paint.descent());
			canvas.drawText(numbers[i], text_x, text_y, paint);
		}
		// 画大饼，上下价格的大饼
		btn_x = (this.getWidth() / scale_h - btn.getWidth()) / 2;
		// 上限价格对应的y坐标
		y_up = getBtnYByPrice(price_up);
		canvas.drawBitmap(btn, btn_x, y_up - btn.getWidth() / 2, paint);
		// 下限价格对应的y坐标
		y_down = getBtnYByPrice(price_down);
		canvas.drawBitmap(btn, btn_x, y_down - btn.getWidth() / 2, paint);

		//画绿色滑竿
		//裁剪部分矩形
		Rect src = new Rect(0, (int)(y_up+btn.getHeight()/2), bg_width, (int)(y_down-btn.getHeight()/2));
		//绘制到画布坐标系的矩形
		Rect dst = new Rect((int)bg_x, (int)(y_up+btn.getHeight()/2), (int)(bg_x+bg_width), (int)(y_down-btn.getHeight()/2));
		canvas.drawBitmap(green_bg, src, dst, paint);
		
		// 绘制左边的价格长方形（图片和文字）
		Rect rect_up = getRectByY(y_up);
		canvas.drawBitmap(num_price, null, rect_up, paint);
		Rect rect_down = getRectByY(y_down);
		canvas.drawBitmap(num_price, null, rect_down, paint);

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
	 * 根据价格算出价格所在的Y坐标
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
					/ (FIFTH_STAGE - FOURTH_STAGE) + bg_width / 2;
		} else if (price <= FOURTH_STAGE && price > THIRD_STAGE) {
			y = span_state * (FOURTH_STAGE - price)
					/ (FOURTH_STAGE - THIRD_STAGE) + bg_width / 2 + span_state;
		} else if (price <= THIRD_STAGE && price > SECOND_STAGE) {
			y = span_state * (THIRD_STAGE - price)
					/ (THIRD_STAGE - SECOND_STAGE) + bg_width / 2 + 2
					* span_state;
		} else if (price <= SECOND_STAGE && price > FIRST_STAGE) {
			y = span_state * (SECOND_STAGE - price)
					/ (SECOND_STAGE - FIRST_STAGE) + bg_width / 2 + 3
					* span_state;
		} else {
			y = 4 * span_state + bg_width / 2;
		}
		return y;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			float x = event.getX() / scale_h;
			float y = event.getY() / scale_h;
			Log.v("home", "x"+x);
			if (x >= btn_x && x <= btn_x + btn.getWidth()) {
				if (y >= (y_up - btn.getHeight() / 2)
						&& y <= (y_up + btn.getHeight() / 2)) {
					// 说明按到了上饼
					isUpTouched = true;
					isDownTouched = false;
				}
				if (y >= (y_down - btn.getHeight() / 2)
						&& y <= (y_down + btn.getHeight() / 2)) {
					// 说明按到了下饼
					isDownTouched = true;
					isUpTouched = false;
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			float y2 = event.getY() / scale_h;
			if (isUpTouched) {
				price_up = getPriceByY(y2);
				Log.v("home", "price_up"+price_up);
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
			if (price_up==0&&price_down==0) {
				isUpTouched = true;
				isDownTouched = false;
			}
			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * 根据y坐标来获取价格*Y坐标指的画布的Y坐标
	 */
	private int getPriceByY(float y) {
		int price;
		if (y < bg_width / 2) {
			y = bg_width / 2;
		}
		if (y > (bg_width / 2 + 4 * span_state)) {
			y = bg_width / 2 + 4 * span_state;
		}
		if (y >= bg_width / 2 && y < bg_width / 2 + span_state) {
			// 1000-10000
			price = (int) (FIFTH_STAGE - (FIFTH_STAGE - FOURTH_STAGE)
					* (y - bg_width / 2) / span_state);
		} else if (y >= bg_width / 2 + span_state
				&& y < bg_width / 2 + 2 * span_state) {
			price = (int) (FOURTH_STAGE - (FOURTH_STAGE - THIRD_STAGE)
					* (y - bg_width / 2 - span_state) / span_state);
		} else if (y >= bg_width / 2 + 2 * span_state
				&& y < bg_width / 2 + 3 * span_state) {
			price = (int) (THIRD_STAGE - (THIRD_STAGE - SECOND_STAGE)
					* (y - bg_width / 2 - 2 * span_state) / span_state);
		} else if (y >= bg_width / 2 + 3 * span_state
				&& y < bg_width / 2 + 4 * span_state) {
			price = (int) (SECOND_STAGE - (SECOND_STAGE - FIRST_STAGE)
					* (y - bg_width / 2 - 3 * span_state) / span_state);
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
}
