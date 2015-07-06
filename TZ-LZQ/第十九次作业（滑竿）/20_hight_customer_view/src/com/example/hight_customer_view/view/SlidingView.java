package com.example.hight_customer_view.view;

import com.example.hight_customer_view.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;

/**
 * ����
 * 
 * @author L_ZQ
 * 
 */
public class SlidingView extends View {

	private Bitmap gray_bg;// ��ɫ�ı�������
	private Bitmap green_bg;// ��ɫ�ı�������
	private Bitmap btn;// ���
	private Bitmap num_price;// ��ߵļ۸�

	private final int FIRST_STAGE = 0;
	private final int SECOND_STAGE = 200;
	private final int THIRD_STAGE = 500;
	private final int FOURTH_STAGE = 1000;
	private final int FIFTH_STAGE = 10000;

	private int bg_height;// ���͸߶�
	private float scale_h;
	private Paint paint;

	private int span_state;// ÿһ���ȼ�������߶�

	public SlidingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		gray_bg = getBmp(R.drawable.axis_before);
		green_bg = getBmp(R.drawable.axis_after);
		btn = getBmp(R.drawable.btn);
		num_price = getBmp(R.drawable.bg_number);
		paint = new Paint();
		paint.setColor(Color.GRAY);// ��ɫ

	}

	public Bitmap getBmp(int id) {
		return BitmapFactory.decodeResource(getResources(), id);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

		// ���͵ĸ߶�
		bg_height = gray_bg.getHeight();

		span_state = (bg_height - gray_bg.getWidth()) / 4;

		// �����warp_content
		int measureHeight = (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight
				: bg_height;

		measureHeight = Math.min(sizeHeight, measureHeight);

		int measureWidth = 2 * measureHeight / 3;

		scale_h = (measureHeight * 1.0f) / bg_height;

		setMeasuredDimension(measureWidth, measureHeight);

		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		canvas.save();

		canvas.scale(scale_h, scale_h);

		int bg_x = (int) ((this.getWidth() / scale_h - gray_bg.getWidth()) / 2);
		canvas.drawBitmap(gray_bg, bg_x, 0, paint);

		// �����ұߵ�5���ı�
		String[] numbers = new String[] { "����", String.valueOf(FOURTH_STAGE),
				String.valueOf(THIRD_STAGE), String.valueOf(SECOND_STAGE),
				String.valueOf(FIRST_STAGE) };

		float text_x = bg_x * 5 / 4;
		paint.setTextSize(20 / scale_h);// �����ı��Ļ��ʳߴ�
		for (int i = 0; i < numbers.length; i++) {

			float text_y = span_state * i + gray_bg.getWidth() / 2
					+ (paint.descent() - paint.ascent()) / 2 - paint.descent();
			canvas.drawText(numbers[i], text_x, text_y, paint);
		}

		canvas.restore();

		super.onDraw(canvas);
	}

}
