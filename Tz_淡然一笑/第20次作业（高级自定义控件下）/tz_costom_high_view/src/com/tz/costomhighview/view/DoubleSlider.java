package com.tz.costomhighview.view;

import com.tz.costomhighview.R;
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
	private int span_region_height; //ÿһ������ĸ߶�
	
	private float circle_x;  //��Բ��x����
	private final int TEXT_PADDINT = 20;
	
	private boolean isUpTouched;
	private boolean isDownTouched;
	 
	public DoubleSlider(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		gray_bg = bitmap(R.drawable.axis_before);
		green_bg = bitmap(R.drawable.axis_after);
		circle_bg = bitmap(R.drawable.btn);
		price_num_bg = bitmap(R.drawable.bg_number);
		
		paint = new Paint();
		paint.setColor(Color.GRAY);
		price_up = 1000;
		price_down = 200;  //�Զ�������������xml��
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
		
		scale_h = (float) measuredHeight/slider_height;  //���ű���,ע��Ҫת����float����
		
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
		
		//����Բ��x���꣬���´�Բ��x�������
		circle_x = (this.getWidth()/scale_h - circle_bg.getWidth())/2; 
		//���޼۸��Ӧ��y����
		circle_y_up = getCircleYByPrice(price_up);
		canvas.drawBitmap(circle_bg, circle_x, circle_y_up-circle_bg.getWidth()/2, paint);  //�����޴�Բ    top=circle_y_up-circle_bg/2;
		
		//���޼۸��Ӧ��y����
		circle_y_down = getCircleYByPrice(price_down);
		canvas.drawBitmap(circle_bg, circle_x, circle_y_down-circle_bg.getWidth()/2, paint); //�����޴�Բ
		
		//����ɫ����
		//�ü����־���
		Rect src = new Rect(0, (int)(circle_y_up+circle_bg.getHeight()/2), slide_width, (int)(circle_y_down-circle_bg.getHeight()/2));
		//���Ƶ���������ϵ�ľ���
		Rect dst = new Rect((int)slider_x, (int)(circle_y_up+circle_bg.getHeight()/2), (int)(slider_x+slide_width), (int)(circle_y_down-circle_bg.getHeight()/2));
		canvas.drawBitmap(green_bg, src, dst, paint);
		
		//������߳����ε�ͼƬ������ļ۸�����
		Rect rect_up = getRectByY(circle_y_up);
		//bitmap��������ͼƬ,src���������֡�Rect�����ǲü����֡�null��,dst����ϵ,paint����
//		canvas.drawBitmap(bitmap, src, dst, paint);  
		canvas.drawBitmap(price_num_bg, null, rect_up, paint);   //�˷�������Ҫ,Ҫ��ס
		
		Rect rect_down = getRectByY(circle_y_down);
		canvas.drawBitmap(price_num_bg, null, rect_down, paint);
		
		//�����ı�
		float text_up_x = (3*rect_up.width()/4-paint.measureText(String.valueOf(price_up)))/2;
		float text_up_y = circle_y_up+(paint.descent()-paint.ascent())/2-paint.descent();
		canvas.drawText(String.valueOf(price_up), text_up_x, text_up_y, paint);
		
		float text_down_x = (3*rect_down.width()/4-paint.measureText(String.valueOf(price_down)))/2;
		float text_down_y = circle_y_down+(paint.descent()-paint.ascent())/2-paint.descent();
		canvas.drawText(String.valueOf(price_down), text_down_x, text_down_y, paint);
		
		//��������Ժ�
		canvas.restore(); //����,�ָ�֮ǰ��״̬
		super.onDraw(canvas);
	}

	private Rect getRectByY(float y) {
		Rect rect = new Rect();
		rect.left = 0;
		rect.right = (int) circle_x;
		float text_h = paint.descent()-paint.ascent(); 
		rect.top = (int) (y-text_h/2-TEXT_PADDINT);
		rect.bottom = (int) (y+text_h/2+TEXT_PADDINT);
		return rect;
	} 	

	//���ݼ۸������۸����ڵ�y����
	private float getCircleYByPrice(int price) {
		float y;
		if(price < 0){
			price = 0;
		}
		if(price > 10000){
			price = 10000;
		}
		if(price<=FIFTH_STAGE&&price>FOURTH_STAGE){
			//1000<price<=10000   	��4����  
			//9000*(10000-9000)/(10000-1000)+slide_width/2
			y = span_region_height*(FIFTH_STAGE-price)/(FIFTH_STAGE-FOURTH_STAGE)+slide_width/2;
		}else if(price<=FOURTH_STAGE&&price>THIRD_STAGE){
			//500<price<=1000		��3����+span_region_height
			y = span_region_height*(FOURTH_STAGE-price)/(FOURTH_STAGE-THIRD_STAGE)+slide_width/2+span_region_height;
		}else if(price<=THIRD_STAGE&&price>SECOND_STAGE){
			//200<price<=500		��2����+2*span_region_height
			y = span_region_height*(THIRD_STAGE-price)/(THIRD_STAGE-SECOND_STAGE)+slide_width/2+2*span_region_height;
		}else if(price<=SECOND_STAGE&&price>FIRST_STAGE){
			//0<price<=200			��1����+3*span_region_height
			y = span_region_height*(SECOND_STAGE-price)/(SECOND_STAGE-FIRST_STAGE)+slide_width/2+3*span_region_height;
		}else{
			//price<0       4*span_region_height+slide_width/2
			y = 4*span_region_height+slide_width/2;
		}
		return y;
	}
	
	public int getPriceByY(float y){
		int price = 0;
		if(y<slide_width/2){
			y = slide_width/2;
		}
		if(y<slide_width/2+4*span_region_height){
			y = slide_width/2+4*span_region_height;	
		}
		if(y>=slide_width/2&&y<slide_width/2+span_region_height){  //1000~10000
			//10000-(y-slide_width/2)/span_region_height
			price = (int) (FIFTH_STAGE-(FIFTH_STAGE-FOURTH_STAGE)*(y-slide_width/2)/span_region_height);
		}else if(y>=slide_width/2+span_region_height&&y<slide_width/2+2*span_region_height){
			price = (int) (FOURTH_STAGE-(FOURTH_STAGE-THIRD_STAGE)*(y-slide_width/2)/span_region_height)-span_region_height;
		}else if(y>=slide_width/2+2*span_region_height&&y<slide_width/2+3*span_region_height){
			price = (int) (THIRD_STAGE-(THIRD_STAGE-SECOND_STAGE)*(y-slide_width/2)/span_region_height)-2*span_region_height;
		}else if(y>=slide_width/2+3*span_region_height&&y<slide_width/2+4*span_region_height){
			price = (int) (SECOND_STAGE-(SECOND_STAGE-FIRST_STAGE)*(y-slide_width/2)/span_region_height)-3*span_region_height;
		}else{
			y = 0;	
		}
		
		//��Ӧ�۸���п̶ȵ�����
		if(price<=1000){
			int mol = price%20;
			if(mol>=10){
				price = price-mol+20;
			}
		}
		if(price>1000){
			int mol = price%1000;
			if(mol>=500){
				price = price-mol+1000;
			}else{
				price = price -mol;
			}
		}
		return price;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			float x = event.getX()/scale_h;
			float y = event.getY()/scale_h;
			if(x>=circle_x&&x<circle_x+circle_bg.getWidth()){
				if(y>=price_up-circle_bg.getHeight()/2&&y<=price_up+circle_bg.getHeight()/2){
					//���������޵Ĵ�Բ
					isUpTouched = true;
					isDownTouched = false;
				}
				if(y>=price_down-circle_bg.getHeight()/2&&y<=price_down+circle_bg.getHeight()/2){
					//���������޵Ĵ�Բ
					isDownTouched = true;
					isUpTouched = false;
				}
			}
			
			break;
		case MotionEvent.ACTION_MOVE:
			float y2 = event.getY()/scale_h;  //ע��˴��ǳ��Ի��ʵı�����������y����
			if(isUpTouched){
				price_up = getPriceByY(y2);
				if(price_up<=price_down){
					price_up = price_down;
				}
			}
			if(isDownTouched){
				price_down = getPriceByY(y2);
				if(price_down>price_up){
					price_down = price_up;
				}
			}
			this.invalidate(); //ˢ���ػ�,����onDraw()����
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
