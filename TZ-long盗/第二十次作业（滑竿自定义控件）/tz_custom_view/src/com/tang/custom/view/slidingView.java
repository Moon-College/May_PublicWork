package com.tang.custom.view;

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
import android.view.View.MeasureSpec;

import com.tang.custom.R;

public class slidingView extends View {
	private Bitmap gray_bg;// 灰色滑竿
	private Bitmap grean_bg;// 绿色滑竿
	private Bitmap pie;// 大饼
	private Bitmap price_bg;// 左边价格绿色背景
	private final int ONE_NUM = 0;
	private final int TWO_NUM = 200;
	private final int HTERR_NUM = 500;
	private final int FOUR_NUM = 1000;
	private final int FIVE_NUM = 10000;
	private int price_up;// 左边价格上限
	private int price_down;// 左边价格下限
	private float price_y_up;// 价格上限的y坐标(上饼)
	private float price_y_down;// 价格下限的y坐标(下饼)
	private Paint paint;// 画笔
	private int bg_height;// 滑竿高度
	private float scale_h;
	private int bg_width;// 滑竿的高度，球的高度
	private int span_state;// 每一个等级的区域高度
	private float pie_x;// 饼的x坐标
	private final int TEXT_PADDING = 15;

	private boolean isUpTouched = false;
	private boolean isDownTouched = false;

	/**
	 * 构造函数
	 * 
	 * @param context
	 * @param attrs
	 */
	public slidingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.slidingView);
		price_up=a.getInt(R.styleable.slidingView_priceUp,1000);
		price_down=a.getInt(R.styleable.slidingView_priceDown, 200);
		
		gray_bg = initBitmap(R.drawable.axis_before);
		grean_bg = initBitmap(R.drawable.axis_after);
		pie = initBitmap(R.drawable.btn);
		price_bg = initBitmap(R.drawable.bg_number);
		paint = new Paint();
		paint.setColor(Color.GRAY);

	}

	/**
	 * 根据id加载图片
	 * 
	 * @param id
	 * @return
	 */
	private Bitmap initBitmap(int id) {
		return BitmapFactory.decodeResource(getResources(), id);
	}

	/**
	 * 测量自己宽高
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
		measuredHeight = Math.min(measuredHeight, sizeHeight);
		int measuredWidth = 2 * measuredHeight / 3;
		scale_h = (float) measuredHeight / bg_height;
		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	/**
	 * 绘制内容
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// 对画布缩放
		canvas.save();
		canvas.scale(scale_h, scale_h);
		// 计算滑竿的x坐标
		int bg_x = (int) ((this.getWidth() / scale_h - gray_bg.getWidth()) / 2);
		canvas.drawBitmap(gray_bg, bg_x, 0, paint);

		// 绘制右边的文本
		String[] numbers = new String[] { "无限", String.valueOf(FOUR_NUM),
				String.valueOf(HTERR_NUM), String.valueOf(TWO_NUM),
				String.valueOf(ONE_NUM) };
		paint.setTextSize(24);
		for (int i = 0; i < numbers.length; i++) {
			int text_x = 5 * bg_x / 4;
			float text_y = i * span_state + bg_width / 2
					+ (paint.descent() - paint.ascent()) / 2 - paint.descent();
			canvas.drawText(numbers[i], text_x, text_y, paint);
		}

		// 话大饼。上下价格的大饼
		pie_x = (this.getWidth() / scale_h - pie.getWidth()) / 2;
		// 根据价格算出所在的y坐标
		price_y_up = getPieYByPrice(price_up);
		canvas.drawBitmap(pie, pie_x, price_y_up - pie.getWidth() / 2, paint);
		price_y_down = getPieYByPrice(price_down);
		canvas.drawBitmap(pie, pie_x, price_y_down - pie.getWidth() / 2, paint);

		// 画左边长方形价格
		Rect rect_up = getRectPrice(price_y_up);
		canvas.drawBitmap(price_bg, null, rect_up, paint);
		Rect rect_down = getRectPrice(price_y_down);
		canvas.drawBitmap(price_bg, null, rect_down, paint);

		// 话绿杆
		Rect src = new Rect(0, (int) (price_y_up + pie.getHeight() / 2), bg_width,
				(int) (price_y_down - pie.getHeight() / 2));
		Rect dst = new Rect((int) bg_x, (int) (price_y_up + pie.getHeight() / 2),
				(int) (bg_x + bg_width), (int) (price_y_down - pie.getHeight() / 2));
		canvas.drawBitmap(grean_bg, src, dst, paint);

		// 绘制文本
		float text_up_y = price_y_up + (paint.descent() - paint.ascent()) / 2
				- paint.descent();
		float text_up_x = 3 * rect_up.width() / 6
				- paint.measureText(String.valueOf(price_up));
		canvas.drawText(String.valueOf(price_up), text_up_x, text_up_y, paint);
		float text_down_y = price_y_down + (paint.descent() - paint.ascent())
				/ 2 - paint.descent();
		float text_down_x = 3 * rect_down.width() / 6
				- paint.measureText(String.valueOf(price_down));
		canvas.drawText(String.valueOf(price_down), text_down_x, text_down_y,
				paint);

		canvas.restore();// 重置
		super.onDraw(canvas);
	}

	/**
	 * 长方形
	 * 
	 * @param y
	 * @return
	 */
	private Rect getRectPrice(float y) {
		Rect rect = new Rect();
		rect.left = 10;
		rect.right = (int) pie_x;
		float text_h = paint.descent() - paint.ascent();
		rect.top = (int) (y - text_h / 2 - TEXT_PADDING);
		rect.bottom = (int) (y + text_h / 2 + TEXT_PADDING);

		return rect;
	}

	/**
	 * 根据价格算出所在的y坐标
	 * 
	 * @param price
	 * @return
	 */
	private float getPieYByPrice(int price) {
		float y = 0;
		if (price < 0) {
			price = 0;
		}
		if (price > 10000) {
			price = 10000;
		}
		if (price <= FIVE_NUM && price > FOUR_NUM) {
			y = bg_width / 2 + span_state * (FIVE_NUM - price)
					/ (FIVE_NUM - FOUR_NUM);
		} else if (price <= FOUR_NUM && price > HTERR_NUM) {
			y = bg_width / 2 + span_state * (FOUR_NUM - price)
					/ (FOUR_NUM - HTERR_NUM) + span_state;
		} else if (price <= HTERR_NUM && price > TWO_NUM) {
			y = bg_width / 2 + span_state * (HTERR_NUM - price)
					/ (HTERR_NUM - TWO_NUM) + 2 * span_state;
		} else if (price <= TWO_NUM && price > ONE_NUM) {
			y = bg_width / 2 + span_state * (TWO_NUM - price)
					/ (TWO_NUM - ONE_NUM) + 3 * span_state;
		} else {
			y = 4 * span_state + bg_width / 2;
		}
		return y;
	}

	/**
	 * 根据y坐标来获取价格
	 * 
	 * @param y
	 *            控件的y坐标
	 * @return
	 */
	public int getPriceY(float y) {
		int price;
		if (y < bg_width / 2) {
			y = bg_width / 2;
		}
		if (y > (bg_width / 2 + 4 * span_state)) {
			y = bg_width / 2 + 4 * span_state;
		}
		if (y >= bg_width / 2 && y < bg_width / 2 + span_state) {
			// 1000-10000
			price = (int) (FIVE_NUM - (FIVE_NUM - FOUR_NUM)
					* (y - bg_width / 2) / span_state);
		} else if (y >= bg_width / 2 + span_state
				&& y < bg_width / 2 + 2 * span_state) {
			price = (int) (FOUR_NUM - (FOUR_NUM - HTERR_NUM)
					* (y - bg_width / 2 - span_state) / span_state);
		} else if (y >= bg_width / 2 + 2 * span_state
				&& y < bg_width / 2 + 3 * span_state) {
			price = (int) (HTERR_NUM - (HTERR_NUM - TWO_NUM)
					* (y - bg_width / 2 - 2 * span_state) / span_state);
		} else if (y >= bg_width / 2 + 3 * span_state
				&& y < bg_width / 2 + 4 * span_state) {
			price = (int) (TWO_NUM - (TWO_NUM - ONE_NUM)
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

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			float x = event.getX() / scale_h;
			float y = event.getY() / scale_h;
			if (x >= pie_x && x <= pie_x + pie.getWidth()) {
				if (y >= (price_y_up - pie.getHeight() / 2)
						&& y <= (price_y_up + pie.getHeight() / 2)) {
					isUpTouched = true;
					isDownTouched = false;
				}
				if (y >= (price_y_down - pie.getHeight() / 2)
						&& y <= (price_y_down + pie.getHeight() / 2)) {
					isDownTouched = true;
					isUpTouched = false;
				}
				if (price_y_up == price_y_down) {
					if(price_up == 0){
						isUpTouched = true;
						isDownTouched = false;
					}
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			float newY = event.getY() / scale_h;
			if (isUpTouched) {
				price_up = getPriceY(newY);
				if (price_up <= price_down) {
					price_up = price_down;
				}
			}

			if (isDownTouched) {
				price_down = getPriceY(newY);
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
