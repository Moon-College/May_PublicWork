package com.decent.slidingbutton.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.decent.slidingbutton.R;

public class SlidingButton extends View {

	private Bitmap gray_bg;
	private Bitmap green_bg;
    private Paint paint;
	private float scale_h;
	
	private String[] CAR_LEVEL = {"������","��˹��˹","���۱���","�������","BYD����"};
	private int span_length;
	private int ball_width;
	
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

	private void initData() {
		gray_bg = loadBitmapImage(R.drawable.axis_before);
		green_bg = loadBitmapImage(R.drawable.axis_after);
		paint = new Paint();
	}

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
		
		//��ȡͼƬ�Ŀ�Ⱥ͸߶�
		int picHeight = gray_bg.getHeight();
		int picWidth = gray_bg.getWidth();
		//����Ǽ����Ǹ�������ģ�ÿһ��ֱ�ߵĸ߶ȣ���Ŀ�Ⱥ͸߶ȿ��Ծ���Ϊ��picWidth
		span_length = (picHeight - 5*picWidth)/4;
		ball_width = picWidth;
		/*
		 * ����onMeasure�����ģʽ��
		 * MeasureSpec.EXACTLY-----��Ӧfill_parent(width����height�͸��׿ؼ�һ��)
		 *                         ���߾����С����300dp
		 * MeasureSpec.AT_MOST-----��Ӧwrap_content,�����ӿؼ�����ܰڷŵĿռ��Ƕ��
		 * 
		 * ����onMeasure������Ҫ����ȥ����MeasureSpec.AT_MOST(wrap_content)�������
		 * �ؼ���С�͸��׿ؼ��Ĳ�һ�µ����
		 */
		int measuredHeight = (heightMode == MeasureSpec.EXACTLY)?heightSize:picHeight;
		int measuredWidth = 2*measuredHeight/3;
		
		setMeasuredDimension(measuredWidth, measuredHeight);
		scale_h = (float)measuredHeight/picHeight;
		// TODO Auto-generated method stub
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}	
	
	@Override
	protected void onDraw(Canvas canvas) {
		//�ؼ��ĸ߶Ⱥ�ͼƬ�ĸ߶Ȳ�һ�£�������Ҫ���Ż���
		//1���ȱ��滭����״̬������ûָ�
		canvas.save();
		/*
		 * 2������x���y������ű�����һ����һ���ģ���������
		 */
		canvas.scale(scale_h, scale_h);
		/*
		 * �������꣬Ȼ�����bitmap
		 * imageY ����0��ʼ
		 * imageX ����(x����-ͼƬ�Ŀ��)/2��һ�룬���ǻ�Ҫע��this.getWidth()
		 *        ������֮��ľ��룬��Ҫ��ԭ����ʵ�ľ�����������
		 */
		int imageY = 0;
		int imageX = (int) ((this.getWidth()/scale_h - green_bg.getWidth() )/2);
		canvas.drawBitmap(green_bg, imageX, imageY, paint);
		
		/*
		 * �������ֵĴ�С��Ҳ��Ҫע�����ŵ�����
		 * scale_hԽС˵��������Ҫ������Խ��
		 */
		paint.setTextSize(20/scale_h);
		/*
		 * 3����������,һ��5��
		 */
		for(int i=0;i<CAR_LEVEL.length;i++){
			//���ֵ�x�������ͼƬ��x�����ٹ�ȥһ��㣬��Ŷ�1/4��λ��
			float textX = 5*imageX/4;
			/*
			 * ���ֵ�y���꣬������ֵ���
			 * ��:(i*2+1)��İ뾶
			 * ����:i*���ӵĳ���
			 * ������ߵĵ���:(����ַ������baseline������descent-����ַ������baseline������ascent)/2 - ����ַ������baseline������descent
			 *            �����ȥ descent��ԭ��������������Ĳ���desent�����꣬����baseline������
			 */
			float textY = (i*2+1)*ball_width/2 
					+ i*span_length 
					+ ((paint.descent()-paint.ascent())/2-paint.descent());
			Log.d("INFO", "descent:"+paint.descent()+",ascent:"+paint.ascent());
			canvas.drawText(CAR_LEVEL[i], textX, textY, paint);	
		}
		/*
		 * �ָ�canvas��״̬
		 */
		canvas.restore();
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}

}
