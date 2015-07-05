package com.dd.dd_custom_view.view;

import com.dd.dd_custom_view.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomView extends View {
	private Bitmap gray_bg;// ��ɫ�ı�������
	private Bitmap green_bg;// ��ɫ�ı�������
	private Bitmap btn;// ���
	private Bitmap num_price;// ��߼۸���ɫ����
	private final int FIRST_STAGE = 0;
	private final int SECOND_STAGE = 200;
	private final int THIRD_STAGE = 500;
	private final int FOURTH_STAGE = 1000;
	Paint paint;// ����
	private int price_up;// �۸�����
	private int price_down;// �۸�����
	private float y_up;// �۸��Ӧ��y�������ޣ�ָ��������ĵ��y����
	private float y_down;// �۸��Ӧ��y�������ޣ�����ָ��������ĵ��y����
	private int bg_height;// ���͵ĸ߶�
	private int bg_width;// ���͵ĸ߶ȣ���ĸ߶�
	private int span_state;// ÿһ���ȼ�������߶�
	private float scale_h;

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// ��ʼ�����ݣ�����ͼƬ
		gray_bg = iBmp(R.drawable.axis_before);
		green_bg = iBmp(R.drawable.axis_after);
		btn = iBmp(R.drawable.btn);
		num_price = iBmp(R.drawable.bg_number);
		paint = new Paint();
		paint.setColor(Color.GRAY);// ��ɫ
	}

	public Bitmap iBmp(int resId) {
		return BitmapFactory.decodeResource(getResources(), resId);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);//������ָ���Ŀ�
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);//������ָ���ĸ�
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		//��wrap_content����£�����ʹ��ϵͳĬ�ϸ����ǿؼ���fill_parentģʽ�����Ǹ����Լ����������������
		//����wrap_contentģʽ�£���ߵ�ȡֵ
		bg_height = gray_bg.getHeight();
		bg_width = gray_bg.getWidth();
		span_state = (bg_height - bg_width)/4;
		int measuredHeight = (modeHeight == MeasureSpec.EXACTLY)?sizeHeight:bg_height;
		measuredHeight = Math.min(measuredHeight, sizeHeight);
		int measuredWidth = 2*measuredHeight/3;
		scale_h = (float)measuredHeight/bg_height;
		setMeasuredDimension(measuredWidth, measuredHeight);
		Log.v("home", measuredWidth+"height:"+measuredHeight);
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		//�Ի�����������
		canvas.save();
		canvas.scale(scale_h, scale_h);
		//��ʼ�滭
		//���㻬�͵�x����
		int bg_x = (int) ((this.getWidth()/scale_h-gray_bg.getWidth())/2);
		canvas.drawBitmap(gray_bg, bg_x, 0, paint);
		
		//�����ұߵ�5���ı�
		String[] str = new String[]{
				"����",
				String.valueOf(FOURTH_STAGE),
				String.valueOf(THIRD_STAGE),
				String.valueOf(SECOND_STAGE),
				String.valueOf(FIRST_STAGE)
		};
		paint.setTextSize(20/scale_h);
		for (int i = 0; i < str.length; i++) {
			int text_x = 5*bg_x/4;
			//(paint.descent()-paint.ascent())�����ı��ĸ߶ȣ�ascent��ֵ�Ǹ���
			float text_y = (i*span_state+bg_width/2+(paint.descent()-paint.ascent())/2-paint.descent());
			canvas.drawText(str[i], text_x, text_y, paint);
		}
		//��ɻ��ƺ�����
		canvas.restore();
		super.onDraw(canvas);
	}
}
