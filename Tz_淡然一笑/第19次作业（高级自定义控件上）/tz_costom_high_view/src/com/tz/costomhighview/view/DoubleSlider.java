package com.tz.costomhighview.view;

import com.tz.costomhighview.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * �Զ���ؼ���˫�򻬸�
 * @author fcc
 *
 */
public class DoubleSlider extends View{
	
	private Bitmap gray_bg;   //��ɫ��������
	private Bitmap green_bg;  //��ɫ��������
	private Bitmap circle_bg;    //��ɫ��Բ����
	private Bitmap price_num_bg; //��߼۸���ɫ����
	
	//�����϶�Ӧ�ļ۸�5���ȼ�
	private final int FIRST_STAGE = 0;
	private final int SECOND_STAGE = 200;
	private final int THIRD_STAGE = 500;
	private final int FOURTH_STAGE = 1000;
	private final int FIFTH_STAGE = 10000;	
	
	private Paint paint; //����
	
	private int price_up;   //���߼۸�  10000
	private int price_down; //���޼۸�  0
	
	private float circle_y_up;    //�۸��Ӧ��y��������,ָ���޴�Բ���ĵ��y����
	private float circle_y_down;  //�۸��Ӧ��y��������,ָ���޴�Բ���ĵ��y����
	
	private int slider_height;  //���͵ĸ߶�
	
	private float scale_h;  //��������
	
	private int slide_width;  //���͵Ŀ��,Ҳ���Ǵ�Բ�Ŀ��
	
	private  int span_region_height; //ÿһ������ĸ߶�
	 
	public DoubleSlider(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		gray_bg = bitmap(R.drawable.axis_before);
		green_bg = bitmap(R.drawable.axis_after);
		circle_bg = bitmap(R.drawable.btn);
		price_num_bg = bitmap(R.drawable.bg_number);
		
		paint = new Paint();
		paint.setColor(Color.GRAY);
	}
	
	//����ͼƬid����ͼƬ
	public Bitmap bitmap(int resId){
		return BitmapFactory.decodeResource(getResources(), resId);
	}
	
	//�����Լ��Ŀ��
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);  //������ָ���Ŀ�
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);  //ģʽ
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec); //������ָ���ĸ�
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);  //ģʽ
		//�����wrap_content�����,����ʹ��ϵͳĬ�ϸ����ǵ�fill_parentģʽ,����ҵ����Ҫȷ�����
		//�����wrap_contetnt,������
		slider_height = gray_bg.getHeight();  //���͸߶Ⱦ��ǻ�ɫ��������
		slide_width = gray_bg.getWidth();  //���Ϳ��,Ҳ���Ǵ�Բ�Ŀ��
		span_region_height = (slider_height - slide_width)/4;  //ÿһ������ĸ߶�
		
		int measuredHeight = (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : slider_height;
		measuredHeight = Math.min(measuredHeight, sizeHeight);
		int measuredWidth = 2*measuredHeight/3;  //�����趨���Ǹߵ�2/3
		
		scale_h = (float) measuredHeight/slider_height;  //���ű���
		
		setMeasuredDimension(measuredWidth, measuredHeight);
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	//�������������
	@Override
	protected void onDraw(Canvas canvas) {
		//������������
		canvas.save();
		canvas.scale(scale_h, scale_h);  //�ȱ�������
		//��ʼ����
		//���͵�x����,ע��˴��ǻ����Ŀ�ȡ����ű�����
		int slider_x = (int) ((this.getWidth()/scale_h - gray_bg.getWidth())/2);
		canvas.drawBitmap(gray_bg, slider_x, 0, paint);
		
		//�����ұߵ��ı�
		String [] nums = new String []{
				"����",
				String.valueOf(FOURTH_STAGE),
				String.valueOf(THIRD_STAGE),
				String.valueOf(SECOND_STAGE),
				String.valueOf(FIRST_STAGE)
		}; 
		paint.setTextSize(28/scale_h);  //�����ı����ʳߴ硾�Ի������ű�����
		for (int i = 0; i < nums.length; i++) {
			int text_x = 5*slider_x/4;
//			float text_y = i*span_region_height+slide_width/2;
			float text_y = (i*span_region_height+slide_width/2+(paint.descent()-paint.ascent())/2-paint.descent());
			canvas.drawText(nums[i], text_x, text_y, paint);
		}
		//��������Ժ�
		canvas.restore(); //����,�ָ�֮ǰ��״̬
		super.onDraw(canvas);
	}
}
