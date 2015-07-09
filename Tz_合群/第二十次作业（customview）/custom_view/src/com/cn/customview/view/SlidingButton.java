/**
 * 
 */
package com.cn.customview.view;

import android.annotation.SuppressLint;
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

import com.cn.customview.R;
/**
 * �Զ��廬��
 * @author hequn
 *
 */
public class SlidingButton extends View {
	/** ��ɫ���� */
	private Bitmap gray_slider;
	/**��ɫ����*/
	private Bitmap green_slider;
	/**��ť */
	private Bitmap btn;
	/** ���ֱ���ͼƬ */
	private Bitmap bg_number;
	/** ���� */
	private Paint paint = new Paint();
	/** ���ű��� */
	private float scale_h;
	/** һ��С����ռ��ɫ���͵ı��� */
	private float scale_ball = 0.043f;
	/** ��������������֮��ľ��� */
	private float span;
	/** ��ɫ�����ĸ߶� */
	private int gray_slider_height;
	/**�۸������*/
	private int price_up;
	/**�۸������*/
	private int price_low;
	/**������ť�ĺ�����*/
	private float btn_x;
	/** �ϰ�ť���ĵ��y���� */
	private float y_u;
	/*** �°�ť���ĵ��y���� */
	private float y_d;
	/** * ���水ť������ */
	private boolean isUpTouched;
	/** ����İ�ť������ */
	private boolean isDownTouched;
	/** �۸�������� */
	private int PRICE_PADDING=10;
	//�۸�״̬
	private int PRICE_FIRST_STATE=0;
	private int PRICE_SECOND_STATE=200;
	private int PRICE_THRED_STATE=500;
	private int PRICE_FOUTH_STATE=1000;
	private int PRICE_FIVE_STATE=10000;
			
	
	

	@SuppressLint("Recycle")
	public SlidingButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// ����ͼƬ��Դ
		gray_slider = BitmapFactory.decodeResource(getResources(), R.drawable.axis_before);
		green_slider=BitmapFactory.decodeResource(getResources(), R.drawable.axis_after);
		btn = BitmapFactory.decodeResource(getResources(), R.drawable.btn);
		bg_number = BitmapFactory.decodeResource(getResources(), R.drawable.bg_number);
		paint.setColor(Color.WHITE);
		//�����Զ�������
		TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.slidingButton);
		price_up=typedArray.getInt(R.styleable.slidingButton_price_u,PRICE_FIVE_STATE);
		price_low=typedArray.getInt(R.styleable.slidingButton_price_d,PRICE_FIRST_STATE);
	}

	/**
	 * ����xmlȷ��view�ĸ߶ȣ�����Ǹ߶ȵ�1/2 ����Ǹ߶���wrap_content����߶ȱ��ֺͻ�ɫ���͸߶�һ��
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
		int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);

		int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
		int heightSpecMoce = MeasureSpec.getMode(heightMeasureSpec);
		//��ȡ��ɫ���͵ĸ߶�
		gray_slider_height = gray_slider.getHeight();
		// ȷ���߶�
		int height = (heightSpecMoce == MeasureSpec.EXACTLY) ? heightSpecSize : gray_slider_height;
		// ȷ�����
		int width = height / 2;
		// ���û������ű���
		scale_h = height * 1f / gray_slider_height;
		span = (1 - scale_ball) / 4 * gray_slider_height;
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		// ���Ż�������֤ͼƬ������view��ȫ����ʾ ���ź󻭲����ؿ�Ⱥ����ظ߶Ȳ��䣬��λ�÷����˱仯
		canvas.scale(scale_h, scale_h);
		float gray_slider_left = (this.getWidth() / scale_h - gray_slider.getWidth()) / 2;
		// ���ƻ�ɫ����
		canvas.drawBitmap(gray_slider, gray_slider_left, 0, null);// ��ͼ����Ҫ����
		// ���ƻ����Ҳ������
		String[] numbers = { "����",String.valueOf(PRICE_FOUTH_STATE),String.valueOf(PRICE_THRED_STATE),String.valueOf(PRICE_SECOND_STATE), String.valueOf(PRICE_FIRST_STATE) };
		paint.setTextSize(20 / scale_h);
		for (int i = 0; i < numbers.length; i++) {
			float text_y = i * span + scale_ball / 2 * gray_slider_height + paint.descent();
			canvas.drawText(numbers[i], gray_slider_left * 4 / 3, text_y, paint);
		}
		btn_x = (this.getWidth() / scale_h - btn.getWidth()) / 2;
		// �����ϰ�ť���ĵ��y����
		y_u = getBtnYByPrice(price_up);
		// �����ϱߵİ�ť
		canvas.drawBitmap(btn, btn_x, y_u - btn.getHeight() / 2, paint);
		// �����°�ť���ĵ��y����
		y_d = getBtnYByPrice(price_low);
		// �����±ߵİ�ť
		canvas.drawBitmap(btn, btn_x, y_d - btn.getHeight() / 2, paint);
		Rect src=new Rect(0,(int)(y_u+btn.getHeight()/2),green_slider.getWidth(),(int)(y_d-btn.getHeight()/2));
		Rect dst=new Rect((int)gray_slider_left,(int)(y_u+btn.getHeight()/2), (int)(gray_slider_left+green_slider.getWidth()),(int)(y_d-btn.getHeight()/2));
		//������ɫ����
		canvas.drawBitmap(green_slider, src, dst, paint);
		
		// ������ߵļ۸���� ���ε�λ���ɼ۸����
		// �ϱߵľ���
		Rect rect_up = getRectByMidLine(y_u);
		canvas.drawBitmap(bg_number, null, rect_up, paint);
		// �±ߵľ���
		Rect rect_low = getRectByMidLine(y_d);
		canvas.drawBitmap(bg_number, null, rect_low, paint);
		// ���ƾ����е��ı�
		// �������ı���xy����
		float text_u_x = (rect_up.width() * 3 / 4 - paint.measureText(String.valueOf(price_up))) / 2;
		float text_u_y = rect_up.top - paint.ascent()+PRICE_PADDING;
		// �����Ͼ����е��ı�
		canvas.drawText(String.valueOf(price_up), text_u_x, text_u_y, paint);
		// �������ı���xy����
		float text_d_x = (rect_low.width() * 3 / 4 - paint.measureText(String.valueOf(price_low))) / 2;
		float text_d_y = rect_low.top - paint.ascent()+PRICE_PADDING;
		// �����¾����е��ı�
		canvas.drawText(String.valueOf(price_low), text_d_x, text_d_y, paint);

		canvas.restore();
	}

	/**
	 * ���ݰ�ť�е�y���������ε�λ��
	 * 
	 * @param y
	 * @return ����
	 */
	private Rect getRectByMidLine(float y) {
		Rect rect = new Rect();
		rect.left = 0;
		rect.right = (int) btn_x;
		float text_h = paint.descent() - paint.ascent();
		rect.top = (int) (y - text_h / 2)-PRICE_PADDING;
		rect.bottom = (int) (y + text_h / 2)+PRICE_PADDING;
		return rect;
	}

	/**
	 * ���ݼ۸���㰴ť���ĵ��Y����
	 * 
	 * @return
	 */
	private float getBtnYByPrice(float price) {
		float btn_y = 0;
		if (price >= PRICE_FIRST_STATE && price < PRICE_SECOND_STATE) {
			btn_y = scale_ball / 2 * gray_slider_height + 3 * span + (1 - price / PRICE_SECOND_STATE) * span;
		} else if (price >= PRICE_SECOND_STATE && price < PRICE_THRED_STATE) {
			btn_y = scale_ball / 2 * gray_slider_height + 2 * span + (1 - (price - PRICE_SECOND_STATE) / (PRICE_THRED_STATE - PRICE_SECOND_STATE)) * span;
		} else if (price >= PRICE_THRED_STATE && price < PRICE_FOUTH_STATE) {
			btn_y = scale_ball / 2 * gray_slider_height + 1 * span + (1 - (price - PRICE_THRED_STATE) / (PRICE_FOUTH_STATE - PRICE_THRED_STATE)) * span;
		} else {
			btn_y = scale_ball / 2 * gray_slider_height + (1 - (price - PRICE_FOUTH_STATE) / (PRICE_FIVE_STATE - PRICE_FOUTH_STATE)) * span;
		}
		return btn_y;
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//��ȡ���µ�xy���겢ת���ɻ����ϵ�����
			float x=event.getX()/scale_h;
			float y=event.getY()/scale_h;
			if(x>=btn_x&&x<=btn_x+btn.getWidth()){
				//x��������ж�y����
				
				//�ж���ָ�Ƿ�������İ�ť��
				if(y>=y_u-btn.getHeight()/2&&y<=y_u+btn.getHeight()/2){
					isUpTouched=true;
					isDownTouched=false;
					//�ж���ָ�Ƿ�������İ�ť��
				}else if(y>=y_d-btn.getHeight()/2&&y<=y_d+btn.getHeight()/2){
					isDownTouched=true;
					isUpTouched=false;
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			float btn_y=event.getY();
			if(isUpTouched){
				//���ݰ�ť���������۸�
				price_up=getPriceByBtnY(btn_y);
				if(price_up<price_low){
					price_up=price_low;
				}
			}else if(isDownTouched){
				price_low=getPriceByBtnY(btn_y);
				if(price_low>price_up){
					price_low=price_up;
				}
			}
			
			//�ػ�
			this.invalidate();
			break;
		case MotionEvent.ACTION_UP:
			isUpTouched=false;
			isDownTouched=false;
			break;
		default:
			break;
		}
		return true;
	}
	/*
	 * ���ݰ�ť���������۸�
	 */
	private int getPriceByBtnY(float btn_y) {
		int price;
		//ÿ���̶ȵ����곤��
		float span=this.getHeight()*(1-scale_ball)/4;
		//���������߶�
		float half_ball_height=this.getHeight()*scale_ball/2;
		if(btn_y<half_ball_height){
			price=PRICE_FIVE_STATE;
		}else if(btn_y>=half_ball_height&&btn_y<half_ball_height+span){
			//��1000~����֮��
			price=PRICE_FIVE_STATE-(int)((PRICE_FIVE_STATE-PRICE_FOUTH_STATE)*((btn_y-half_ball_height)/span));
		}else if(btn_y>half_ball_height+span&&btn_y<half_ball_height+2*span){
			//��500~1000֮��
			price=PRICE_FOUTH_STATE-(int)((PRICE_FOUTH_STATE-PRICE_THRED_STATE)*((btn_y-half_ball_height-span)/span));
		}else if(btn_y>=half_ball_height+2*span&&btn_y<half_ball_height+3*span){
			//��200~500֮��
			price=PRICE_THRED_STATE-(int) ((PRICE_THRED_STATE-PRICE_SECOND_STATE)*((btn_y-half_ball_height-2*span)/span));
		}else if(btn_y>=half_ball_height+3*span&&btn_y<half_ball_height+4*span){
			//��0~200֮��
			price=PRICE_SECOND_STATE-(int) ((PRICE_SECOND_STATE-0)*((btn_y-half_ball_height-3*span)/span));
		}else{
			price=0;
		}
		//�Լ۸񽫽��н��ȼ���
		if(price<=1000){
			//����Ϊ20
			int mol=price%20;
			if(mol>10){
				price=price-mol+20;//ȥ�����
			}else{
				price=price-mol;//ȥ�����
			}
		}else{
			//����Ϊ1000
			int mol=price%1000;
			if(mol>500){
				price=price-mol+1000;//ȥ�����
			}else{
				price=price-mol;//ȥ�����
			}
		}
		return price;
	}
}
