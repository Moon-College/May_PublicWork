package com.decent.slidingbutton.view;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.decent.slidingbutton.R;

public class SlidingButton extends View {

	/*
	 * 画笔和图片
	 */
	/**
	 * 灰色背景
	 */
	private Bitmap gray_bg;
	/**
	 * 绿色背景
	 */
	private Bitmap green_bg;
	/**
	 * 针视图
	 */
	private Bitmap bg_price_pin;
	/**
	 * 打球
	 */
	private Bitmap btn;
	/**
	 * 画笔
	 */
	private Paint paint;

	/**
	 * 缩放比例----控件里面的大小/图片里面的大小
	 */
	private float scale_h;

	/**
	 * car的等级以及对应的说明
	 */
	private int CAR_LEVEL1 = 0;
	private int CAR_LEVEL2 = 200;
	private int CAR_LEVEL3 = 500;
	private int CAR_LEVEL4 = 1000;
	private int CAR_LEVEL5 = 10000;
	/**
	 * 各个档次的四舍五入因子，也就是每个价格档次的价格变化因子
	 */
	private int CAR_PRICE_FACTOR1 = 20;
	private int CAR_PRICE_FACTOR2 = 50;
	private int CAR_PRICE_FACTOR3 = 100;
	private int CAR_PRICE_FACTOR4 = 1000;
	/**
	 * 各个介个等级的文字说明
	 */
	private String[] CAR_LEVEL_DES = { String.valueOf(CAR_LEVEL5),
			String.valueOf(CAR_LEVEL4), String.valueOf(CAR_LEVEL3),
			String.valueOf(CAR_LEVEL2), String.valueOf(CAR_LEVEL1) };

	/**
	 * 棍子的长度
	 */
	private int span_length;
	/**
	 * 棍子上面的球的直径
	 */
	private int ball_width;

	/**
	 * 上大球对应的价格
	 */
	private int price_up = CAR_LEVEL4;
	/**
	 * 下面打球对应的价格
	 */
	private int price_down = CAR_LEVEL2;
	/**
	 * 灰色背景的x坐标
	 */
	private int imageX;
	/**
	 * 大球的x坐标
	 */
	private float btn_x;
	/**
	 * 上面大球的y坐标
	 */
	private float btn_y_up;
	/**
	 * 下面大球的y坐标
	 */
	private float btn_y_down;
	/**
	 * 针图片的x坐标
	 */
	private float price_pin_x;
	/**
	 * 上面针图片的y坐标
	 */
	private float price_pin_y_up;
	/**
	 * 下面针图片的y坐标
	 */
	private float price_pin_y_down;
	/**
	 * 上面针价格的x坐标
	 */
	private float price_pin_text_x_up;
	/**
	 * 上面针价格的Y坐标
	 */
	private float price_pin_text_y_up;
	/**
	 * 下面针价格的x坐标
	 */
	private float price_pin_text_x_down;
	/**
	 * 下面针价格的Y坐标
	 */
	private float price_pin_text_y_down;
	// private boolean isUpBallTouched = false;
	// private boolean isDownBallTouched = false;

	/**
	 * 目前只支持两点触摸的 第一个点，第二个的触摸情况
	 */
	private HashMap<String, Integer>[] touchDetails = new HashMap[2];

	private static final String TOUCHED_INDEX = "TOUCHED_INDEX";
	private static final String TOUCHED_TYPE = "TOUCHED_TYPE";

	/**
	 * 两个大球的使用情况 UPBALL_TOUCHED---使用上面大球被
	 */
	private static final Integer UPBALL_TOUCHED = 1;
	/**
	 * DOWNBAL_TOUCHED---使用下面大球
	 */
	private static final Integer DOWNBALL_TOUCHED = 2;

	/**
	 * NOT_USED----没有被使用
	 */
	private static final Integer NOT_USED = 0;

	public SlidingButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initData();
		// TODO Auto-generated constructor stub
	}

	public SlidingButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initData();
		// TODO Auto-generated constructor stub
	}

	public SlidingButton(Context context) {
		super(context);
		initData();
		// TODO Auto-generated constructor stubs
	}

	/**
	 * 加载需要的图片和初始化paint
	 */
	private void initData() {
		gray_bg = loadBitmapImage(R.drawable.axis_before);
		green_bg = loadBitmapImage(R.drawable.axis_after);
		bg_price_pin = loadBitmapImage(R.drawable.bg_price);
		btn = loadBitmapImage(R.drawable.btn);
		paint = new Paint();
		HashMap<String, Integer> touchDetail0 = new HashMap<String, Integer>();
		touchDetail0.put(TOUCHED_INDEX, -1);
		touchDetail0.put(TOUCHED_TYPE, NOT_USED);
		HashMap<String, Integer> touchDetail1 = new HashMap<String, Integer>();
		touchDetail1.put(TOUCHED_INDEX, -1);
		touchDetail1.put(TOUCHED_TYPE, NOT_USED);
		touchDetails[0] = touchDetail0;
		touchDetails[1] = touchDetail1;
	}

	/**
	 * 根据resource id加载图片
	 * 
	 * @param resourceId
	 *            需要加载的resource id
	 * @return 对应的bitmap
	 */
	public Bitmap loadBitmapImage(int resourceId) {
		return BitmapFactory.decodeResource(getResources(), resourceId);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		/*
		 * 获取父亲控件传过来的宽和高的size还有mode
		 */
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		// 获取图片的宽度和高度
		int picHeight = gray_bg.getHeight();
		int picWidth = gray_bg.getWidth();
		/**
		 * 这个是计算那个除了球的，每一节直线的高度，球的宽度和高度可以就认为是picWidth 假设图片宽度和球的高度是一样的 每一节的宽度 =
		 * (高度 - (最上面半球/2+最下面半球/2))/4
		 */
		ball_width = picWidth;
		span_length = (picHeight - ball_width) / 4;

		/*
		 * 对于onMeasure里面的模式：
		 * MeasureSpec.EXACTLY-----对应fill_parent(width或者height和父亲控件一样)
		 * 或者具体大小比如300dp MeasureSpec.AT_MOST-----对应wrap_content,告诉子控件最大能摆放的空间是多大
		 * 
		 * 所以onMeasure里面主要就是去处理MeasureSpec.AT_MOST(wrap_content)的情况下
		 * 控件大小和父亲控件的不一致的情况
		 */
		int measuredHeight = (heightMode == MeasureSpec.EXACTLY) ? heightSize
				: picHeight;
		// 需要选择measuredHeight和heightSize之间的最小值
		measuredHeight = Math.min(measuredHeight, heightSize);
		// 美工是要求宽度是高度的2/3
		int measuredWidth = 2 * measuredHeight / 3;

		setMeasuredDimension(measuredWidth, measuredHeight);
		// 计算出缩放比例
		scale_h = (float) measuredHeight / picHeight;
		// TODO Auto-generated method stub
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 控件的高度和图片的高度不一致，所以需要缩放画布
		// 1、先保存画布的状态，后面好恢复
		canvas.save();
		/*
		 * 2、设置x轴和y轴的缩放比例，一般是一样的，否则会变形
		 */
		canvas.scale(scale_h, scale_h);
		/*
		 * 计算坐标，然后绘制bitmap imageY 就是0开始 imageX
		 * 就是(x轴宽度-图片的宽度)/2的一半，但是还要注意this.getWidth() 是缩放之后的距离，需要还原成真实的距离再来计算
		 */
		int imageY = 0;
		// @@比例问题1：这里为什么是图片本身的长度来计算，因为缩放是画布的事情，drawBitmap的left,top按照正常1:1的计算，在draw的时候自然会去帮你缩放
		imageX = (int) ((this.getWidth() / scale_h - green_bg.getWidth()) / 2);
		canvas.drawBitmap(gray_bg, imageX, imageY, paint);

		/*
		 * 设置文字的大小，也需要注意缩放的问题 scale_h越小说明文字需要的字体越大
		 */
		paint.setTextSize(30 / scale_h);
		/*
		 * 3、给小球旁边价格的文字说明,一共5挡
		 */
		for (int i = 0; i < CAR_LEVEL_DES.length; i++) {
			// 文字的x坐标就是图片的x坐标再过去一点点，大概多1/4的位置
			float textX = 5 * imageX / 4;
			/*
			 * 文字的y坐标，由三个值组成 球:一个球的半径 杆子:i*杆子的长度
			 * 字体基线的调整:(最低字符相对于baseline的坐标descent-最高字符相对于baseline的坐标ascent)/2 -
			 * 最低字符相对于baseline的坐标descent 后面减去
			 * descent的原因是我们想调整的不是desent的坐标，而是baseline的坐标
			 */
			float textY = ball_width
					/ 2
					+ i
					* span_length
					+ ((paint.descent() - paint.ascent()) / 2 - paint.descent());
			// Log.d("INFO",
			// "descent:"+paint.descent()+",ascent:"+paint.ascent());
			// @@比例问题2：这里其实和问题1的情况一样的，缩放是画布的事情，left,top按照正常1:1的计算，在draw的时候自然会去帮你缩放
			canvas.drawText(CAR_LEVEL_DES[i], textX, textY, paint);
		}
		/*
		 * 4、根据上下球的价格显示 4.1、大球 大球的x轴：是固定的就是控件的中心点-(大球的宽度/2) 大球的y轴：是根据价格计算出来的
		 */
		btn_x = this.getWidth() / scale_h / 2 - btn.getWidth() / 2;
		btn_y_up = getAxisYByPrice(price_up);
		// 画上面的大球
		// @@比例问题3：这里的坐标，同问题1
		canvas.drawBitmap(btn, btn_x, btn_y_up, paint);

		btn_y_down = getAxisYByPrice(price_down);
		// 画下面的大球
		// @@比例问题4：这里的坐标，同问题1
		canvas.drawBitmap(btn, btn_x, btn_y_down, paint);
		/*
		 * 4.2、左边的价格pin 价格pin的x轴：是大球的x轴 - 价格pin的宽度 价格pin的y轴：和大球的y轴 + 大球高度/2 -
		 * 价格pin的高度/2 使用rect更方便
		 */
		price_pin_x = btn_x - bg_price_pin.getWidth();
		price_pin_y_up = btn_y_up + btn.getHeight() / 2
				- bg_price_pin.getHeight() / 2;
		Rect pin_up_dst = new Rect((int) price_pin_x, (int) price_pin_y_up,
				(int) (price_pin_x + bg_price_pin.getWidth()),
				(int) (price_pin_y_up + bg_price_pin.getHeight()));
		// @@比例问题5：这里矩形里面的坐标，同问题1，按照1：1的来计算，其他的draw的时候自然会缩放，后面类似的不再多说
		canvas.drawBitmap(bg_price_pin, null, pin_up_dst, paint);

		price_pin_y_down = btn_y_down + btn.getHeight() / 2
				- bg_price_pin.getHeight() / 2;
		Rect pin_down_dst = new Rect((int) price_pin_x, (int) price_pin_y_down,
				(int) (price_pin_x + bg_price_pin.getWidth()),
				(int) (price_pin_y_down + bg_price_pin.getHeight()));
		canvas.drawBitmap(bg_price_pin, null, pin_down_dst, paint);
		/*
		 * 4.3、在价格pin里面显示价格文字,注意文字的baseline选择 pin图的长方形部分是整个图片的3/4
		 * 文字部分的x轴:price_pin_x+pin图长方形部分宽度/2-文字的宽度/2 文字部分的y轴:
		 * price_pin_y_up（或者price_pin_y_down）+pin图的高度/2+文字基线调整
		 */
		// 上面的字
		price_pin_text_x_up = price_pin_x + 3 * bg_price_pin.getWidth() / 4 / 2
				- paint.measureText(String.valueOf(price_up)) / 2;
		price_pin_text_y_up = price_pin_y_up + bg_price_pin.getHeight() / 2
				+ (paint.descent() - paint.ascent()) / 2 - paint.descent();
		canvas.drawText(String.valueOf(price_up), price_pin_text_x_up,
				price_pin_text_y_up, paint);
		// 下面的字
		price_pin_text_x_down = price_pin_x + 3 * bg_price_pin.getWidth() / 4
				/ 2 - paint.measureText(String.valueOf(price_down)) / 2;
		price_pin_text_y_down = price_pin_y_down + bg_price_pin.getHeight() / 2
				+ (paint.descent() - paint.ascent()) / 2 - paint.descent();
		canvas.drawText(String.valueOf(price_down), price_pin_text_x_down,
				price_pin_text_y_down, paint);

		/*
		 * 5、两个点的连线，也就是显示出green_bg的一部分，使用矩形工具green_bg_src（用于计算截取图片的矩形），green_bg_det
		 * （用于显示在控件里面的矩形） green_bg_src(按照图片1:1的坐标): left---0
		 * top----btn_y_up(转换为图片比例之后)+大球的高度 right---left+green_bg的宽度
		 * bottom---btn_y_down（转换为图片比例之后）
		 */
		Rect green_bg_src = new Rect(0, (int) (btn_y_up + btn.getHeight()),
				(int) green_bg.getWidth(), (int) (btn_y_down));
		/*
		 * green_bg_det(按照画布1:时候的坐标，其实相对于green_bg_src就是个位置移动,
		 * scale的问题是drawBitmap考虑的事情): left---就是gray_bg的x坐标imageX
		 * top----btn_y_up+大球的宽度 right---left+green_bg的宽度 bottom---btn_y_down
		 */
		Rect green_bg_det = new Rect((int) (imageX),
				(int) (btn_y_up + btn.getHeight()),
				(int) (imageX + green_bg.getWidth()), (int) (btn_y_down));
		canvas.drawBitmap(green_bg, green_bg_src, green_bg_det, paint);

		/*
		 * 最后:恢复canvas的状态
		 */
		canvas.restore();
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}

	/**
	 * 根据价格计算y坐标
	 * 
	 * @param price
	 *            价格
	 * @return 对应的y坐标
	 */
	private float getAxisYByPrice(int price) {
		float axisY = 0;
		if (price <= CAR_LEVEL5 && price >= CAR_LEVEL4) {
			axisY = span_length * (CAR_LEVEL5 - price)
					/ (CAR_LEVEL5 - CAR_LEVEL4) + ball_width / 2
					- btn.getWidth() / 2;
		} else if (price < CAR_LEVEL4 && price >= CAR_LEVEL3) {
			axisY = span_length * (CAR_LEVEL4 - price)
					/ (CAR_LEVEL4 - CAR_LEVEL3) + span_length + ball_width / 2
					- btn.getWidth() / 2;
			;
		} else if (price < CAR_LEVEL3 && price >= CAR_LEVEL2) {
			axisY = span_length * (CAR_LEVEL3 - price)
					/ (CAR_LEVEL3 - CAR_LEVEL2) + 2 * span_length + ball_width
					/ 2 - btn.getWidth() / 2;
			;
		} else if (price < CAR_LEVEL2 && price >= CAR_LEVEL1) {
			axisY = span_length * (CAR_LEVEL2 - price)
					/ (CAR_LEVEL2 - CAR_LEVEL1) + 3 * span_length + ball_width
					/ 2 - btn.getWidth() / 2;
			;
		} else {
			axisY = 0;
		}
		/*
		 * 根据价格的档次计算对应价格
		 */
		// TODO Auto-generated method stub
		return axisY;
	}

	/**
	 * 根据y坐标计算价格
	 * 
	 * @param y
	 *            y坐标，是真实的y坐标，不是画布里面的y坐标
	 * @return 根据y坐标计算出来的价格
	 */
	private int getPriceByAxisY(float y, boolean isUpPrice) {
		int price = 0;
		int branch = 0;
		// TODO Auto-generated method stub
		if (y <= ball_width / 2) {
			price = CAR_LEVEL5;
			branch = 0;
		} else if (y > ball_width / 2 && y <= ball_width / 2 + span_length) {
			price = (int) (CAR_LEVEL4 + (ball_width / 2 + span_length - y)
					* (CAR_LEVEL5 - CAR_LEVEL4) / span_length);
			branch = 1;
		} else if (y > ball_width / 2 + span_length
				&& y <= ball_width / 2 + 2 * span_length) {
			price = (int) (CAR_LEVEL3 + (ball_width / 2 + 2 * span_length - y)
					* (CAR_LEVEL4 - CAR_LEVEL3) / span_length);
			branch = 2;
		} else if (y > ball_width / 2 + 2 * span_length
				&& y <= ball_width / 2 + 3 * span_length) {
			price = (int) (CAR_LEVEL2 + (ball_width / 2 + 3 * span_length - y)
					* (CAR_LEVEL3 - CAR_LEVEL2) / span_length);
			branch = 3;
		} else if (y > ball_width / 2 + 3 * span_length
				&& y <= ball_width / 2 + 4 * span_length) {
			price = (int) (CAR_LEVEL1 + (ball_width / 2 + 4 * span_length - y)
					* (CAR_LEVEL2 - CAR_LEVEL1) / span_length);
			branch = 4;
		} else {
			price = 0;
			branch = 6;
		}

		// 四舍五入价格
		price = roundTheCarPrice(price, isUpPrice);
		// Log.d("INFO",
		// "y="+y+",branch="+branch+",get price="+price+",isUpPrice="+isUpPrice);
		return price;
	}

	/**
	 * 把价格按照价格区间去四舍五入
	 * 
	 * @param price
	 *            价格
	 * @return 四舍五入之后的价格
	 */
	private int roundTheCarPrice(int price, boolean isUpPrice) {
		int mol = 0;
		int newPrice;
		int car_price_factor = 0;
		// TODO Auto-generated method stub
		if (price >= CAR_LEVEL4) {
			car_price_factor = CAR_PRICE_FACTOR4;
		} else if (price >= CAR_LEVEL3) {
			car_price_factor = CAR_PRICE_FACTOR3;
		} else if (price >= CAR_LEVEL2) {
			car_price_factor = CAR_PRICE_FACTOR2;
		} else {
			// if(price >= CAR_LEVEL1)
			car_price_factor = CAR_PRICE_FACTOR1;
		}

		mol = price % car_price_factor;
		if (mol >= CAR_PRICE_FACTOR1 / 2) {
			newPrice = price - mol + car_price_factor;
		} else {
			newPrice = price - mol;
		}

		/*
		 * 价格反差的时候的价格调整
		 */
		if (isUpPrice) {
			if (newPrice <= price_down) {
				newPrice = price_down + car_price_factor;
			}
		} else {
			if (newPrice >= price_up) {
				newPrice = price_up - car_price_factor;
			}
		}
		// Log.d("INFO",
		// "isUpPrice="+isUpPrice+",newPrice="+newPrice+",price_down="+price_down+",price_up="+price_up);
		return newPrice;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int pointerCount = event.getPointerCount();
		int index = event.getActionIndex();
		float x = event.getX(index);
		float y = event.getY(index);
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
			/*
			 * 先做单指的，判断是否在范围内
			 */
			for (int i = 0; i < touchDetails.length; i++) {
				HashMap<String, Integer> touchDetail = touchDetails[i];
				if (touchDetail.get(TOUCHED_TYPE) == NOT_USED) {
					if (x / scale_h >= btn_x
							&& x / scale_h <= btn_x + btn.getWidth()) {
						if (y / scale_h >= btn_y_up
								&& y / scale_h <= btn_y_up + btn.getHeight()) {
							touchDetail.put(TOUCHED_TYPE, UPBALL_TOUCHED);
							touchDetail.put(TOUCHED_INDEX, index);
							Log.d("INFO", "touchDetail" + i
									+ " be set to UPBALL_TOUCHED");
							break;
						} else if (y / scale_h >= btn_y_down
								&& y / scale_h <= btn_y_down + btn.getHeight()) {
							touchDetail.put(TOUCHED_TYPE, DOWNBALL_TOUCHED);
							touchDetail.put(TOUCHED_INDEX, index);
							Log.d("INFO", "touchDetail" + i
									+ " be set to DOWNBALL_TOUCHED");
							break;
						}
					}
				}
			}

			break;
		case MotionEvent.ACTION_MOVE:
			boolean isNeedFresh = false;
			/*
			 * 这里去遍历pointerCount的原因是在，多个点touch的时候，
			 * ACTION_MOVE的getActionIndex永远只有0，
			 * 需要这样去遍历各个pointerCount，然后通过
			 *  event.getPointerId(i)去获取对应的index
			 */
			for (int i = 0; i < pointerCount; ++i) {
				// The identifier tells you the actual pointer number associated
				// with the data
				int pointerIndex = event.getPointerId(i);
				Log.d("INFO", "pointerIndex=" + pointerIndex);

				for (int j = 0; j < touchDetails.length; j++) {
					HashMap<String, Integer> touchDetail = touchDetails[j];
					if (touchDetail.get(TOUCHED_TYPE) != NOT_USED
							&& pointerIndex == touchDetail.get(TOUCHED_INDEX)) {
						/*
						 * 
						 * 写下面的判断是因为在ACTION_MOVE两根手指都触摸的时候，如果第一根手指已经放弃触摸，
						 * 这个时候第二根手指的pointerIndex还是1， 但是event.getY需要传的index是0.。。
						 */
						if (pointerIndex > pointerCount - 1) {
							pointerIndex = 0;
						}
						if (touchDetail.get(TOUCHED_TYPE) == UPBALL_TOUCHED) {
							y = event.getY(pointerIndex);
							/*
							 * 这里需要除以scale_h，因为getPriceByAxisY里面使用的都是图片1:1时候的长度
							 * 而onTouchEvent是控件里卖的坐标，自然是缩放之后的
							 */
							price_up = getPriceByAxisY(y / scale_h, true);
							isNeedFresh = true;
						} else if (touchDetail.get(TOUCHED_TYPE) == DOWNBALL_TOUCHED) {
							y = event.getY(pointerIndex);
							/*
							 * 这里需要除以scale_h，因为getPriceByAxisY里面使用的都是图片1:1时候的长度
							 * 而onTouchEvent是控件里卖的坐标，自然是缩放之后的
							 */
							price_down = getPriceByAxisY(y / scale_h, false);
							isNeedFresh = true;
						}
						if (isNeedFresh) {
							this.invalidate();
						}
					}
				}
			}
			break;
		case MotionEvent.ACTION_POINTER_UP:
		case MotionEvent.ACTION_UP:
			/**
			 * up的时候如果不是没有被使用，直接搞成没有被使用
			 */
			Log.d("INFO", "touchDetail set to NOT_USED" + ",index=" + index
					+ ",pointerCount=" + pointerCount);
			for (int i = 0; i < touchDetails.length; i++) {
				HashMap<String, Integer> touchDetail = touchDetails[i];
				if (index == touchDetail.get(TOUCHED_INDEX)
						&& touchDetail.get(TOUCHED_TYPE) != NOT_USED) {
					touchDetail.put(TOUCHED_TYPE, NOT_USED);
					touchDetail.put(TOUCHED_INDEX, -1);
					Log.d("INFO", "touchDetail" + i + " be set to NOT_USED");
					break;
				}
			}

			break;
		default:
			break;
		}
		return true;
	}

}
