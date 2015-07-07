package com.rocy.class18;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;

public class RocyView extends View {
	private Context context ;
	private Bitmap bgAfter;
	private Bitmap bgBefore;
	private Bitmap bgText;
	private Bitmap controlBtn;
	private int first;
	private int second;
	private int third;
	private int fourth;
	private int fifth;
	private Paint paint;
	private float los;
	private int bgHeight;
	private int bgWidth;
	private int upControlheight;
	private int downControlheight;
	private int nodeWidth;
	private int intveral;
	private boolean pointerMove1 = false ;
	private boolean pointerMove2 = false ;
	private boolean isUpMove = false ;
	private boolean isDownMove = false;
	private float controlBtnX;
	private float upControlY;
	private float downControlY;
	private float distace;

	

	public RocyView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		//初始化控件
		initView();
	}

	public RocyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initView();
		initData(attrs);
	}

	public RocyView(Context context) {
		super(context);
		this.context = context;
		initView();
	}
	@SuppressLint("ResourceAsColor")
	private void initData(AttributeSet attrs) {
		// 获取区间限定值
		TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.rocyView);
		first = array.getInteger(R.styleable.rocyView_first, 10000);
		second = array.getInteger(R.styleable.rocyView_second, 1000);
		third = array.getInteger(R.styleable.rocyView_third, 500);
		fourth = array.getInteger(R.styleable.rocyView_fourth, 200);
		fifth = array.getInteger(R.styleable.rocyView_fifth, 0);
		array.recycle();
		//获取画笔
		paint = new Paint();
		Color color = new Color();
		paint.setColor(Color.BLACK);
		paint.setTextSize(12f);
		//初始化位置
		upControlheight = 7000;
		downControlheight = 350;
		
		
	}
	
	

	private void initView() {
		// 初始化所有的图片
		bgAfter = resourceBitmap(R.drawable.axis_after);
		bgBefore = resourceBitmap(R.drawable.axis_before);
		bgText = resourceBitmap(R.drawable.bg_number);
		controlBtn = resourceBitmap(R.drawable.btn);
		Bitmap node =resourceBitmap(R.drawable.node_after);
		nodeWidth = node.getWidth();
		
	}
	
	private Bitmap resourceBitmap(int rID) {
		// 初始化图片
		return BitmapFactory.decodeResource(getResources(), rID);
	}

	/**绘制视图
	 * 
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save();
		//调整视距
		canvas.scale(los, los);
		//背景宽度
		float bgAfterWidth = (this.getWidth()/los-bgAfter.getWidth())/2;
		//画背景
		canvas.drawBitmap(bgBefore, (this.getWidth()/los-bgAfter.getWidth())/2, 0, null);
		//画上下控制按钮
		controlBtnX = (this.getWidth()/los-controlBtn.getWidth())/2;
		upControlY = getMoveHight(upControlheight);
		canvas.drawBitmap(controlBtn, controlBtnX, upControlY, null);
		downControlY =getMoveHight(downControlheight);
		canvas.drawBitmap(controlBtn, controlBtnX, downControlY, null);
		//画需要显示的绿色背景
		Rect src = new Rect(0,(int)(upControlY+controlBtn.getWidth()/2) ,bgAfter.getWidth(),(int)(downControlY+controlBtn.getWidth()/2));
		Rect dst = new Rect((int)bgAfterWidth,(int)(upControlY+controlBtn.getWidth()/2),(int)(bgAfterWidth+bgAfter.getWidth()),(int)(downControlY+controlBtn.getWidth()/2));
		canvas.drawBitmap(bgAfter, src, dst, null);
		//画标题
		paint.setTextSize(20/los);
		drawLabel(canvas,bgAfterWidth);
		//画上显示板
		Rect upDst = new Rect(0, (int)(upControlY),(int)((this.getWidth()/los)/2-controlBtn.getWidth()/2) ,(int)(upControlY+controlBtn.getWidth()) );
		canvas.drawBitmap(bgText, null, upDst, null);
		paint.setTextSize(40/los);
		canvas.drawText(String.valueOf(upControlheight), upDst.centerX()/3,upDst.centerY()+(paint.descent()-paint.ascent())/4, paint);
		//画下显示版
		Rect downDst = new Rect(0, (int)(downControlY),(int)((this.getWidth()/los)/2-controlBtn.getWidth()/2) ,(int)(downControlY+controlBtn.getWidth()) );
		canvas.drawBitmap(bgText, null, downDst, null);
		canvas.drawText(String.valueOf(downControlheight), downDst.centerX()/3,downDst.centerY()+(paint.descent()-paint.ascent())/4, paint);
		
		canvas.restore();
	}
	private void drawLabel(Canvas canvas, float width) {
		// 画标签
		for (int i = 0; i < 5; i++) {
			switch (i) {
			case 0:
				canvas.drawText(String.valueOf(first), 5*(width)/4 ,getLine(i), paint);
				break;
			case 1:
				canvas.drawText(String.valueOf(second), 5*(width)/4, getLine(i), paint);
				break;
			case 2:
				canvas.drawText(String.valueOf(third), 5*(width)/4, getLine(i), paint);
				break;
			case 3:
				canvas.drawText(String.valueOf(fourth), 5*(width)/4, getLine(i), paint);
				break;
			case 4:
				canvas.drawText(String.valueOf(fifth), 5*(width)/4,getLine( i), paint);
				break;
			}
			
	}
		
	}



	private float getLine(int i) {
		// 获取文字中心
		return i*intveral+nodeWidth/2 +(paint.descent()-paint.ascent())/2-paint.descent();
	}
	
	private int getPrice(float textY){
		
		int price = 0 ;
		if(textY <= nodeWidth/2){
			price = first;
		}else if(textY > nodeWidth/2 && textY <= nodeWidth/2+intveral){
			price = (int) (first-(first-second)*(textY-nodeWidth/2)/intveral);
		}else if(textY > nodeWidth/2+intveral && textY <= nodeWidth/2+2*intveral){
			price = (int) (second-(second-third)*(textY-nodeWidth/2)/(2*intveral));
		}else if(textY > nodeWidth/2+2*intveral && textY <= nodeWidth/2+3*intveral){
			price = (int) (third-(third-fourth)*(textY-nodeWidth/2)/(3*intveral));
		}else if(textY > nodeWidth/2+3*intveral && textY <= nodeWidth/2+4*intveral){
			price = (int) (fourth-(fourth-fifth)*(textY-nodeWidth/2)/(4*intveral));
		}else{
			price = fifth;
		}
		//进行转化成整数
		if(price>=1000){
		int p =	price%1000;
		 price = p >= 1000/2? price+1000-p:price-p;
		}else{
			int p = price%50;
			price = p >=50/2? price+50-p:price-p;
		}
		return price;
		
	}
	
	private float getMoveHight(int height) {
		float y =0f;
		// 获取高度
		if(height >= first){
			//在一区间上
			y = nodeWidth/2;
		}else if(height < first && height >= second){
			//在一区间里面
			y = intveral*(first-height)/(first-second)-nodeWidth/2;
		}else if(height < second && height >= third){
			y = intveral*(second-height)/(second-third)+intveral-nodeWidth/2;
		}else if(height < third && height >= fourth){
			y = intveral*(third-height)/(third-fourth)+2*intveral-nodeWidth/2;
		}else if(height < fourth && height >= fifth){
			y = intveral*(fourth-height)/(fourth-fifth)+3*intveral-nodeWidth/2;
		}else {
			y = bgHeight-nodeWidth/2;
		}
		return y ;
	}

	/**
	 * 测量视图
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		//根据需求假定 宽是高的3分之2；
		bgHeight = bgBefore.getHeight();
		bgWidth = bgBefore.getWidth(); 
		//获取区间单位
		intveral =(bgHeight-nodeWidth)/4;
		los = heightSize/(float)bgHeight;
		if(heightMode == MeasureSpec.AT_MOST ){
			heightSize = bgHeight <= heightSize?bgHeight:heightSize;
		}
		
		setMeasuredDimension(2*heightSize/3, heightSize);
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int pointerCount = event.getPointerCount();
		switch (pointerCount) {
		case 1:
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				float pointerY2 = event.getY()/los;
				float pointerX2 = event.getX()/los;
				canMove(pointerX2,pointerY2);
				break;
			case MotionEvent.ACTION_MOVE:
				float moveY = event.getY()/los;
				startMove(moveY);
				this.invalidate();
				break;
			case MotionEvent.ACTION_UP:
				if(isUpMove){
					isUpMove = false ;
				}
				if(isDownMove){
					isDownMove = false;
				}
				break;

			default:
				break;
			}
			break;
		case 2:
			switch (event.getActionMasked()) {
			case MotionEvent.ACTION_POINTER_DOWN:
				float pointerY2 = event.getY(1)/los;
				float pointerX2 = event.getX(1)/los;
				canMove(pointerX2,pointerY2);
				float pointerY1 = event.getY()/los;
				float pointerX1 = event.getX()/los;
				canMove(pointerX1,pointerY1);
				distace =Math.abs( pointerY2 - pointerY1 );
				break;
				
			case MotionEvent.ACTION_MOVE:
				float moveY1 = event.getY(1)/los;
				float moveY = event.getY()/los;
				float newDistance = startMove2(moveY,moveY1);
				distace = newDistance;
				this.invalidate();
				break;
			case MotionEvent.ACTION_POINTER_UP:
				if(isUpMove) isUpMove = false;
				if(isDownMove) isDownMove = false ;
				break;

			default:
				break;
			}
			break;

		default:
			break;
		}
		
		return true;
	}

	private float startMove2(float moveY, float moveY1) {
		// 俩个一起移动
		float newDistance = Math.abs( moveY - moveY1 );
		float dis = (newDistance-distace)/2;
		if(newDistance >= distace){
			//距离扩大
			upControlheight = getPrice(Math.min(moveY,moveY1)-dis/2);
			downControlheight = getPrice(Math.max(moveY,moveY1)+dis/2);
		}else{
			upControlheight = getPrice(Math.min(moveY,moveY1)+dis/2);
			downControlheight = getPrice(Math.max(moveY,moveY1)-dis/2);
		}
		return newDistance;
	}

	private void startMove(float y) {
		// TODO Auto-generated method stub
		if(isUpMove){
			int price = getPrice(y);
			if(price>downControlheight){
				
				upControlheight = price;
			}
		}
		if(isDownMove ){
			int price = getPrice(y);
			if(price <upControlheight){
				downControlheight = price;
			}
		}
		
	}

	private void canMove(float x, float y) {
		//判断位置
		if(x >= controlBtnX && x <= controlBtnX+controlBtn.getWidth() ){
			if((y >=upControlY && y <= upControlY+controlBtn.getHeight())){
				isUpMove = true ;
			} 
			
			if(y >=downControlY && y <= downControlY+controlBtn.getHeight()){
				isDownMove = true ;
			}
		}
	}
}
