package com.decent.slidingbutton.view;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
	 * ���ʺ�ͼƬ
	 */
	/**
	 * ��ɫ����
	 */
	private Bitmap gray_bg;
	/**
	 * ��ɫ����
	 */
	private Bitmap green_bg;
	/**
	 * ����ͼ
	 */
	private Bitmap bg_price_pin;
	/**
	 * ����
	 */
	private Bitmap btn;
	/**
	 * ����
	 */
	private Paint paint;

	/**
	 * ���ű���----�ؼ�����Ĵ�С/ͼƬ����Ĵ�С
	 */
	private float scale_h;

	/**
	 * car�ĵȼ��Լ���Ӧ��˵��
	 */
	private int CAR_LEVEL1 = 0;
	private int CAR_LEVEL2 = 200;
	private int CAR_LEVEL3 = 500;
	private int CAR_LEVEL4 = 1000;
	private int CAR_LEVEL5 = 10000;
	/**
	 * �������ε������������ӣ�Ҳ����ÿ���۸񵵴εļ۸�仯����
	 */
	private int CAR_PRICE_FACTOR1 = 20;
	private int CAR_PRICE_FACTOR2 = 50;
	private int CAR_PRICE_FACTOR3 = 100;
	private int CAR_PRICE_FACTOR4 = 1000;
	/**
	 * ��������ȼ�������˵��
	 */
	private String[] CAR_LEVEL_DES = { String.valueOf(CAR_LEVEL5),
			String.valueOf(CAR_LEVEL4), String.valueOf(CAR_LEVEL3),
			String.valueOf(CAR_LEVEL2), String.valueOf(CAR_LEVEL1) };

	/**
	 * ���ӵĳ���
	 */
	private int span_length;
	/**
	 * ������������ֱ��
	 */
	private int ball_width;

	/**
	 * �ϴ����Ӧ�ļ۸�
	 */
	private int price_up;
	/**
	 * ��������Ӧ�ļ۸�
	 */
	private int price_down;
	/**
	 * ��ɫ������x����
	 */
	private int imageX;
	/**
	 * �����x����
	 */
	private float btn_x;
	/**
	 * ��������y����
	 */
	private float btn_y_up;
	/**
	 * ��������y����
	 */
	private float btn_y_down;
	/**
	 * ��ͼƬ��x����
	 */
	private float price_pin_x;
	/**
	 * ������ͼƬ��y����
	 */
	private float price_pin_y_up;
	/**
	 * ������ͼƬ��y����
	 */
	private float price_pin_y_down;
	/**
	 * ������۸��x����
	 */
	private float price_pin_text_x_up;
	/**
	 * ������۸��Y����
	 */
	private float price_pin_text_y_up;
	/**
	 * ������۸��x����
	 */
	private float price_pin_text_x_down;
	/**
	 * ������۸��Y����
	 */
	private float price_pin_text_y_down;
	// private boolean isUpBallTouched = false;
	// private boolean isDownBallTouched = false;

	/**
	 * Ŀǰֻ֧�����㴥���� ��һ���㣬�ڶ����Ĵ������
	 */
	private HashMap<String, Integer>[] touchDetails = new HashMap[2];

	private static final String TOUCHED_INDEX = "TOUCHED_INDEX";
	private static final String TOUCHED_TYPE = "TOUCHED_TYPE";

	
	/**
	 * ���������ʹ����� UPBALL_TOUCHED---ʹ���������
	 */
	private static final Integer UPBALL_TOUCHED = 1;
	/**
	 * DOWNBAL_TOUCHED---ʹ���������
	 */
	private static final Integer DOWNBALL_TOUCHED = 2;
	/**
	 * NOT_USED----û�б�ʹ��
	 */
	private static final Integer NOT_USED = 0;
	
	/**
	 * ���ڴ洢�۸���Ϣ��SharedPreferences������
	 */
	private static final String SP_NAME = "SP";

	/**
	 * SharedPreferences����洢����۸�ı�ǩ
	 */
	private static final String PRICE_TAG_UP = "PRICE_TAG_UP";
	/**
	 * SharedPreferences����洢����۸�ı�ǩ
	 */	
	private static final String PRICE_TAG_DOWN = "PRICE_TAG_DOWN";

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
	 * ������Ҫ��ͼƬ�ͳ�ʼ��paint
	 */
	private void initData() {
		/*
		 * loadͼƬ
		 */
		gray_bg = loadBitmapImage(R.drawable.axis_before);
		green_bg = loadBitmapImage(R.drawable.axis_after);
		bg_price_pin = loadBitmapImage(R.drawable.bg_price);
		btn = loadBitmapImage(R.drawable.btn);
		//paint
		paint = new Paint();
		/*
		 * ��ʼ��touch������Ϣ
		 */
		HashMap<String, Integer> touchDetail0 = new HashMap<String, Integer>();
		touchDetail0.put(TOUCHED_INDEX, -1);
		touchDetail0.put(TOUCHED_TYPE, NOT_USED);
		HashMap<String, Integer> touchDetail1 = new HashMap<String, Integer>();
		touchDetail1.put(TOUCHED_INDEX, -1);
		touchDetail1.put(TOUCHED_TYPE, NOT_USED);
		touchDetails[0] = touchDetail0;
		touchDetails[1] = touchDetail1;
		/*
		 * ��ʼ���۸�,���û�л�ȡ�ɹ�����ֱ�����ù̶�ֵ
		 */
		int tmp_price = getPriceValue(PRICE_TAG_UP);
		if(tmp_price!=-1){
			price_up = tmp_price;
		}else{
			price_up = CAR_LEVEL4;
		}
		tmp_price = getPriceValue(PRICE_TAG_DOWN);
		if(tmp_price!=-1){
			price_down = tmp_price;
		}else{
			price_down = CAR_LEVEL2;
		}		
	}

	/**
	 * ����resource id����ͼƬ
	 * 
	 * @param resourceId
	 *            ��Ҫ���ص�resource id
	 * @return ��Ӧ��bitmap
	 */
	public Bitmap loadBitmapImage(int resourceId) {
		return BitmapFactory.decodeResource(getResources(), resourceId);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		/*
		 * ��ȡ���׿ؼ��������Ŀ�͸ߵ�size����mode
		 */
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		// ��ȡͼƬ�Ŀ�Ⱥ͸߶�
		int picHeight = gray_bg.getHeight();
		int picWidth = gray_bg.getWidth();
		/**
		 * ����Ǽ����Ǹ�������ģ�ÿһ��ֱ�ߵĸ߶ȣ���Ŀ�Ⱥ͸߶ȿ��Ծ���Ϊ��picWidth ����ͼƬ��Ⱥ���ĸ߶���һ���� ÿһ�ڵĿ�� =
		 * (�߶� - (���������/2+���������/2))/4
		 */
		ball_width = picWidth;
		span_length = (picHeight - ball_width) / 4;

		/*
		 * ����onMeasure�����ģʽ��
		 * MeasureSpec.EXACTLY-----��Ӧfill_parent(width����height�͸��׿ؼ�һ��)
		 * ���߾����С����300dp MeasureSpec.AT_MOST-----��Ӧwrap_content,�����ӿؼ�����ܰڷŵĿռ��Ƕ��
		 * 
		 * ����onMeasure������Ҫ����ȥ����MeasureSpec.AT_MOST(wrap_content)�������
		 * �ؼ���С�͸��׿ؼ��Ĳ�һ�µ����
		 */
		int measuredHeight = (heightMode == MeasureSpec.EXACTLY) ? heightSize
				: picHeight;
		// ��Ҫѡ��measuredHeight��heightSize֮�����Сֵ
		measuredHeight = Math.min(measuredHeight, heightSize);
		// ������Ҫ�����Ǹ߶ȵ�2/3
		int measuredWidth = 2 * measuredHeight / 3;

		setMeasuredDimension(measuredWidth, measuredHeight);
		// ��������ű���
		scale_h = (float) measuredHeight / picHeight;
		// TODO Auto-generated method stub
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// �ؼ��ĸ߶Ⱥ�ͼƬ�ĸ߶Ȳ�һ�£�������Ҫ���Ż���
		// 1���ȱ��滭����״̬������ûָ�
		canvas.save();
		/*
		 * 2������x���y������ű�����һ����һ���ģ���������
		 */
		canvas.scale(scale_h, scale_h);
		/*
		 * �������꣬Ȼ�����bitmap imageY ����0��ʼ imageX
		 * ����(x����-ͼƬ�Ŀ��)/2��һ�룬���ǻ�Ҫע��this.getWidth() ������֮��ľ��룬��Ҫ��ԭ����ʵ�ľ�����������
		 */
		int imageY = 0;
		// @@��������1������Ϊʲô��ͼƬ����ĳ��������㣬��Ϊ�����ǻ��������飬drawBitmap��left,top��������1:1�ļ��㣬��draw��ʱ����Ȼ��ȥ��������
		imageX = (int) ((this.getWidth() / scale_h - green_bg.getWidth()) / 2);
		canvas.drawBitmap(gray_bg, imageX, imageY, paint);

		/*
		 * �������ֵĴ�С��Ҳ��Ҫע�����ŵ����� scale_hԽС˵��������Ҫ������Խ��
		 */
		paint.setTextSize(30 / scale_h);
		/*
		 * 3����С���Ա߼۸������˵��,һ��5��
		 */
		for (int i = 0; i < CAR_LEVEL_DES.length; i++) {
			// ���ֵ�x�������ͼƬ��x�����ٹ�ȥһ��㣬��Ŷ�1/4��λ��
			float textX = 5 * imageX / 4;
			/*
			 * ���ֵ�y���꣬������ֵ��� ��:һ����İ뾶 ����:i*���ӵĳ���
			 * ������ߵĵ���:(����ַ������baseline������descent-����ַ������baseline������ascent)/2 -
			 * ����ַ������baseline������descent �����ȥ
			 * descent��ԭ��������������Ĳ���desent�����꣬����baseline������
			 */
			float textY = ball_width
					/ 2
					+ i
					* span_length
					+ ((paint.descent() - paint.ascent()) / 2 - paint.descent());
			// Log.d("INFO",
			// "descent:"+paint.descent()+",ascent:"+paint.ascent());
			// @@��������2��������ʵ������1�����һ���ģ������ǻ��������飬left,top��������1:1�ļ��㣬��draw��ʱ����Ȼ��ȥ��������
			canvas.drawText(CAR_LEVEL_DES[i], textX, textY, paint);
		}
		/*
		 * 4������������ļ۸���ʾ 4.1������ �����x�᣺�ǹ̶��ľ��ǿؼ������ĵ�-(����Ŀ��/2) �����y�᣺�Ǹ��ݼ۸���������
		 */
		btn_x = this.getWidth() / scale_h / 2 - btn.getWidth() / 2;
		btn_y_up = getAxisYByPrice(price_up);
		// ������Ĵ���
		// @@��������3����������꣬ͬ����1
		canvas.drawBitmap(btn, btn_x, btn_y_up, paint);

		btn_y_down = getAxisYByPrice(price_down);
		// ������Ĵ���
		// @@��������4����������꣬ͬ����1
		canvas.drawBitmap(btn, btn_x, btn_y_down, paint);
		/*
		 * 4.2����ߵļ۸�pin �۸�pin��x�᣺�Ǵ����x�� - �۸�pin�Ŀ�� �۸�pin��y�᣺�ʹ����y�� + ����߶�/2 -
		 * �۸�pin�ĸ߶�/2 ʹ��rect������
		 */
		price_pin_x = btn_x - bg_price_pin.getWidth();
		price_pin_y_up = btn_y_up + btn.getHeight() / 2
				- bg_price_pin.getHeight() / 2;
		Rect pin_up_dst = new Rect((int) price_pin_x, (int) price_pin_y_up,
				(int) (price_pin_x + bg_price_pin.getWidth()),
				(int) (price_pin_y_up + bg_price_pin.getHeight()));
		// @@��������5�����������������꣬ͬ����1������1��1�������㣬������draw��ʱ����Ȼ�����ţ��������ƵĲ��ٶ�˵
		canvas.drawBitmap(bg_price_pin, null, pin_up_dst, paint);

		price_pin_y_down = btn_y_down + btn.getHeight() / 2
				- bg_price_pin.getHeight() / 2;
		Rect pin_down_dst = new Rect((int) price_pin_x, (int) price_pin_y_down,
				(int) (price_pin_x + bg_price_pin.getWidth()),
				(int) (price_pin_y_down + bg_price_pin.getHeight()));
		canvas.drawBitmap(bg_price_pin, null, pin_down_dst, paint);
		/*
		 * 4.3���ڼ۸�pin������ʾ�۸�����,ע�����ֵ�baselineѡ�� pinͼ�ĳ����β���������ͼƬ��3/4
		 * ���ֲ��ֵ�x��:price_pin_x+pinͼ�����β��ֿ��/2-���ֵĿ��/2 ���ֲ��ֵ�y��:
		 * price_pin_y_up������price_pin_y_down��+pinͼ�ĸ߶�/2+���ֻ��ߵ���
		 */
		// �������
		price_pin_text_x_up = price_pin_x + 3 * bg_price_pin.getWidth() / 4 / 2
				- paint.measureText(String.valueOf(price_up)) / 2;
		price_pin_text_y_up = price_pin_y_up + bg_price_pin.getHeight() / 2
				+ (paint.descent() - paint.ascent()) / 2 - paint.descent();
		canvas.drawText(String.valueOf(price_up), price_pin_text_x_up,
				price_pin_text_y_up, paint);
		// �������
		price_pin_text_x_down = price_pin_x + 3 * bg_price_pin.getWidth() / 4
				/ 2 - paint.measureText(String.valueOf(price_down)) / 2;
		price_pin_text_y_down = price_pin_y_down + bg_price_pin.getHeight() / 2
				+ (paint.descent() - paint.ascent()) / 2 - paint.descent();
		canvas.drawText(String.valueOf(price_down), price_pin_text_x_down,
				price_pin_text_y_down, paint);

		/*
		 * 5������������ߣ�Ҳ������ʾ��green_bg��һ���֣�ʹ�þ��ι���green_bg_src�����ڼ����ȡͼƬ�ľ��Σ���green_bg_det
		 * ��������ʾ�ڿؼ�����ľ��Σ� green_bg_src(����ͼƬ1:1������): left---0
		 * top----btn_y_up(ת��ΪͼƬ����֮��)+����ĸ߶� right---left+green_bg�Ŀ��
		 * bottom---btn_y_down��ת��ΪͼƬ����֮��
		 */
		Rect green_bg_src = new Rect(0, (int) (btn_y_up + btn.getHeight()),
				(int) green_bg.getWidth(), (int) (btn_y_down));
		/*
		 * green_bg_det(���ջ���1:ʱ������꣬��ʵ�����green_bg_src���Ǹ�λ���ƶ�,
		 * scale��������drawBitmap���ǵ�����): left---����gray_bg��x����imageX
		 * top----btn_y_up+����Ŀ�� right---left+green_bg�Ŀ�� bottom---btn_y_down
		 */
		Rect green_bg_det = new Rect((int) (imageX),
				(int) (btn_y_up + btn.getHeight()),
				(int) (imageX + green_bg.getWidth()), (int) (btn_y_down));
		canvas.drawBitmap(green_bg, green_bg_src, green_bg_det, paint);

		/*
		 * ���:�ָ�canvas��״̬
		 */
		canvas.restore();
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}

	/**
	 * ���ݼ۸����y����
	 * 
	 * @param price
	 *            �۸�
	 * @return ��Ӧ��y����
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
		 * ���ݼ۸�ĵ��μ����Ӧ�۸�
		 */
		// TODO Auto-generated method stub
		return axisY;
	}

	/**
	 * ����y�������۸�
	 * 
	 * @param y
	 *            y���꣬����ʵ��y���꣬���ǻ��������y����
	 * @return ����y�����������ļ۸�
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

		// ��������۸�
		price = roundTheCarPrice(price, isUpPrice);
		// Log.d("INFO",
		// "y="+y+",branch="+branch+",get price="+price+",isUpPrice="+isUpPrice);
		return price;
	}

	/**
	 * �Ѽ۸��ռ۸�����ȥ��������
	 * 
	 * @param price
	 *            �۸�
	 * @return ��������֮��ļ۸�
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
		 * �۸񷴲��ʱ��ļ۸����
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
			 * ������ָ�ģ��ж��Ƿ��ڷ�Χ��
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
			 * ����ȥ����pointerCount��ԭ�����ڣ������touch��ʱ��
			 * ACTION_MOVE��getActionIndex��Զֻ��0��
			 * ��Ҫ����ȥ��������pointerCount��Ȼ��ͨ��
			 *  event.getPointerId(i)ȥ��ȡ��Ӧ��index
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
						 * д������ж�����Ϊ��ACTION_MOVE������ָ��������ʱ�������һ����ָ�Ѿ�����������
						 * ���ʱ��ڶ�����ָ��pointerIndex����1�� ����event.getY��Ҫ����index��0.����
						 */
						if (pointerIndex > pointerCount - 1) {
							pointerIndex = 0;
						}
						if (touchDetail.get(TOUCHED_TYPE) == UPBALL_TOUCHED) {
							y = event.getY(pointerIndex);
							/*
							 * ������Ҫ����scale_h����ΪgetPriceByAxisY����ʹ�õĶ���ͼƬ1:1ʱ��ĳ���
							 * ��onTouchEvent�ǿؼ����������꣬��Ȼ������֮���
							 */
							price_up = getPriceByAxisY(y / scale_h, true);
							storePriceValue(PRICE_TAG_UP,price_up);
							isNeedFresh = true;
						} else if (touchDetail.get(TOUCHED_TYPE) == DOWNBALL_TOUCHED) {
							y = event.getY(pointerIndex);
							/*
							 * ������Ҫ����scale_h����ΪgetPriceByAxisY����ʹ�õĶ���ͼƬ1:1ʱ��ĳ���
							 * ��onTouchEvent�ǿؼ����������꣬��Ȼ������֮���
							 */
							price_down = getPriceByAxisY(y / scale_h, false);
							storePriceValue(PRICE_TAG_DOWN,price_down);
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
			 * up��ʱ���������û�б�ʹ�ã�ֱ�Ӹ��û�б�ʹ��
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

	/**
	 * �洢ѡ��ļ۸���Ϣ�� SharedPreferences
	 * @param priceTag �۸�ı�ǩ
	 * @param value �۸�
	 */
	private void storePriceValue(String priceTag, int value){
		SharedPreferences sp = getContext().getSharedPreferences(SP_NAME, Activity.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt(priceTag, value);
		editor.commit();
	}
	
	/**
	 * ��ȡ��Ӧ�۸��ǩ�ļ۸�
	 * @param priceTag �۸��ǩ
	 * @return �۸�
	 */
	private int getPriceValue(String priceTag){
		SharedPreferences sp = getContext().getSharedPreferences(SP_NAME, Activity.MODE_PRIVATE);
		return sp.getInt(priceTag, -1);
	}
}
