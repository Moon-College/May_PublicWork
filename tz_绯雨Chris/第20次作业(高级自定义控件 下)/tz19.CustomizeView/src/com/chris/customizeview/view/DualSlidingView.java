package com.chris.customizeview.view;

import com.chris.customizeview.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DualSlidingView extends View
{
	private static final String tag = "DualSlidingView";
	private static final String FIRST_STAGE = "100";
	private static final float MOL_STEP1 = 10;	//第1阶段的跳动阶数
	private static final String SECOND_STAGE = "500";
	private static final float MOL_STEP2 = 50;	//第2阶段的跳动阶数
	private static final String THIRD_STAGE = "2000";
	private static final float MOL_STEP3 = 100;	//第3阶段的跳动阶数
	private static final String FOURTH_STAGE = "5000";
	private static final float MOL_STEP4 = 500;	//第4阶段的跳动阶数
	private static final String FIFTH_STAGE = "10000";
	
	private static final float RULER_TEXT_PADDING = 6;	//控件最上面的坐标尺字体内边距
	private static final float RULER_TEXT_SIZE = 25;	//控件最上面的坐标尺字体大小
	private static final float POINTER_TEXT_SIZE = 35;	//指针图片中数值的文本大小
	private static final float BOTTOM_DUMMY_SIZE = 10;	//控件最下方空出来的预定距离
	private static final int DEF_VAL_LEFT = 300;	//初始默认左边大饼的数值
	private static final int DEF_VAL_RIGHT = 8000;	//初始默认右边大饼的数值

	private Bitmap axis_background, axis_foreground, bg_pointer, node_background, node_foreground, pan, dialog_background;
	private Paint paintRuler, paintPointer;
	private float scaleRate;
	private NinePatch mNinePatch;
	private Rect mRect;

	int pLeft = this.getPaddingLeft();
	int pRight = this.getPaddingRight();
	int pTop = this.getPaddingTop();
	int pBottom = this.getPaddingBottom();

	String[] stage = new String[]
	{ FIRST_STAGE, SECOND_STAGE, THIRD_STAGE, FOURTH_STAGE, FIFTH_STAGE };
	private int valueLeft, valueRight;
	private float locationIncreaseStep;
	private int firstStageValue;
	private int secondStageValue;
	private int thirdStageValeu;
	private int fourthStageValue;
	private int fifthStageValue;
	private Point leftPanPoint;
	private Point rightPanPoint;
	private boolean isLeftTouched;
	private boolean isRightTouched;

	public DualSlidingView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initViews();
	}

	public DualSlidingView(Context context)
	{
		super(context);
		initViews();
	}

	private void initViews()
	{
		valueLeft = DEF_VAL_LEFT;
		valueRight = DEF_VAL_RIGHT;
		firstStageValue = Integer.valueOf(FIRST_STAGE).intValue();
		secondStageValue = Integer.valueOf(SECOND_STAGE).intValue();
		thirdStageValeu = Integer.valueOf(THIRD_STAGE).intValue();
		fourthStageValue = Integer.valueOf(FOURTH_STAGE).intValue();
		fifthStageValue = Integer.valueOf(FIFTH_STAGE).intValue();

		createBitmaps();

		locationIncreaseStep = (float) (axis_background.getWidth() - node_background.getWidth()) / 4;

		paintRuler = new Paint();
		paintPointer = new Paint();
	}

	private void createBitmaps()
	{
		axis_background = decodeBitmapFromResource(R.drawable.axis_background);
		axis_foreground = decodeBitmapFromResource(R.drawable.axis_foreground);
		bg_pointer = decodeBitmapFromResource(R.drawable.bg_pointer);
		node_background = decodeBitmapFromResource(R.drawable.node_background);
		node_foreground = decodeBitmapFromResource(R.drawable.node_foreground);
		pan = decodeBitmapFromResource(R.drawable.pan);
		dialog_background = decodeBitmapFromResource(R.drawable.dialog_background);

		mNinePatch = new NinePatch(dialog_background, dialog_background.getNinePatchChunk(), null);
	}

	/**
	 * 通过id创建Bitmap图片
	 * @param id
	 * @return
	 */
	private Bitmap decodeBitmapFromResource(int id)
	{
		Bitmap bitmap;
		bitmap = BitmapFactory.decodeResource(getResources(), id);
		return bitmap;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int bodyWidth = axis_background.getWidth() + pan.getWidth() + pLeft + pRight;
		int bodyHeight = bg_pointer.getHeight() + pan.getHeight() + pTop + pBottom;
		Log.d(tag, "bodyWidth=" + bodyWidth + " bodyHeight=" + bodyHeight);

		int widthSize = resolveAdjustedSize(bodyWidth, widthMeasureSpec, 0);
		int heightSize = resolveAdjustedSize(bodyHeight, heightMeasureSpec, 0);
		Log.d(tag, "widthSize=" + widthSize + " heightSize=" + heightSize);

		scaleRate = (widthSize > heightSize) ? (float) widthSize / (float) bodyWidth : (float) heightSize / (float) bodyHeight;
		Log.d(tag, "scaleRate=" + scaleRate);

		//坐标尺画笔设置
		paintRuler.setTextSize(RULER_TEXT_SIZE / scaleRate);
		paintRuler.setColor(Color.BLACK);
		paintRuler.setAntiAlias(true);
		paintRuler.setDither(true);
		//指针数值文本画笔设置
		paintPointer.setTextSize(POINTER_TEXT_SIZE / scaleRate);
		paintPointer.setColor(Color.WHITE);
		paintPointer.setAntiAlias(true);
		paintPointer.setDither(true);

		//增加文字框高度后，重新测量控件高度
		bodyHeight += BOTTOM_DUMMY_SIZE / scaleRate + (paintRuler.descent() - paintRuler.ascent()) + 2 * (RULER_TEXT_PADDING / scaleRate);
		heightSize = resolveAdjustedSize(bodyHeight, heightMeasureSpec, 0);

		setMeasuredDimension(widthSize, heightSize);
	}

	private int resolveAdjustedSize(int desireSize, int measureSpec, int minValue)
	{
		int result;

		int specSize = MeasureSpec.getSize(measureSpec);
		int specMode = MeasureSpec.getMode(measureSpec);
		Log.d(tag, "specMode=" + specMode + " specSize=" + specSize);
		switch (specMode)
		{
		case MeasureSpec.UNSPECIFIED:
			result = Math.max(desireSize, minValue);
			break;
		case MeasureSpec.AT_MOST:
			result = Math.max(Math.min(desireSize, specSize), minValue);
			break;
		case MeasureSpec.EXACTLY:
			result = specSize;
			break;

		default:
			result = desireSize; //默认尺寸
			break;
		}

		return result;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		switch (event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			int x = (int) (event.getX()/scaleRate);
			int y = (int) (event.getY()/scaleRate);
			Log.d(tag, "down x ="+x+" down y ="+y);
			//判断触摸点在哪个大饼上
			if( (x>=leftPanPoint.x && (x<=leftPanPoint.x+pan.getWidth())) && (y>=leftPanPoint.y && y<(leftPanPoint.y+pan.getHeight())) )
			{
				isLeftTouched = true;
				isRightTouched = false;
			}
			if( (x>=rightPanPoint.x && (x<=rightPanPoint.x+pan.getWidth())) && (y>=rightPanPoint.y && y<(rightPanPoint.y+pan.getHeight())) )
			{
				isLeftTouched = false;
				isRightTouched = true;
			}
			
			break;
		case MotionEvent.ACTION_MOVE:
			if(isLeftTouched)
			{
				valueLeft = getValueByXLocation((int) (event.getX()/scaleRate) - pan.getWidth()/2);
				if(valueLeft > valueRight)
				{
					valueLeft = valueRight;
					//如果两个大饼重合在最左边，需要更改为右边大饼可滑动
					if(firstStageValue == valueLeft)
					{
						isLeftTouched = false;
						isRightTouched = true;
					}
				}
				Log.d(tag, "valueLeft = "+valueLeft);
			}
			if(isRightTouched)
			{
				valueRight = getValueByXLocation((int) (event.getX()/scaleRate) - pan.getWidth()/2);
				if(valueRight < valueLeft)
				{
					valueRight = valueLeft;
					//如果两个大饼重合在最右边，需要更改为左边大饼可滑动
					if(fifthStageValue == valueRight)
					{
						isLeftTouched = true;
						isRightTouched = false;
					}
				}
				Log.d(tag, "valueRight = "+valueRight);
			}
			this.invalidate();
			break;
		case MotionEvent.ACTION_UP:
			isLeftTouched = false;
			isRightTouched = false;
			break;

		default:
			break;
		}
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		canvas.save();
		canvas.scale(scaleRate, scaleRate);

		//1. 绘制中轴控件主体部分
		int axisLeft = (pan.getWidth() - node_background.getWidth()) / 2 + pLeft;
		int canvasHeight = (int) (this.getHeight() / scaleRate);
		int bodyHeight = axis_background.getHeight() + bg_pointer.getHeight() + pTop + pBottom;
		int axisTop = (int) (canvasHeight - bodyHeight) / 2;
		canvas.drawBitmap(axis_background, axisLeft, axisTop, null);
		//2. 绘制上方的标尺
		drawRulerLine(canvas, axisLeft, axisTop);
		leftPanPoint = drawPan(canvas, valueLeft, axisLeft, axisTop);
		rightPanPoint = drawPan(canvas, valueRight, axisLeft, axisTop);
		Log.d(tag, "leftPanPoint:" + leftPanPoint);
		Log.d(tag, "rightPanPoint:" + rightPanPoint);
		//4. 绘制指针块
		Point leftPointerPoint = drawPointer(canvas, leftPanPoint);
		Point rightPointerPoint = drawPointer(canvas, rightPanPoint);
		Log.d(tag, "leftPointerPoint:" + leftPointerPoint);
		Log.d(tag, "rightPointerPoint:" + rightPointerPoint);

		//5. 绘制指针块中间的文字
		//		int leftValue = getValueByXLocation(leftPanPoint);
		//		int rightValue = getValueByXLocation(rightPanPoint);
		Point leftTextPoint = drawText(canvas, leftPointerPoint, valueLeft, paintPointer);
		Point rightTextPoint = drawText(canvas, rightPointerPoint, valueRight, paintPointer);

		canvas.restore();
		super.onDraw(canvas);
	}

	/**
	 * 绘制指针图
	 * @param canvas
	 * @param panPoint
	 * @return 返回指针图当前位置左上角的Point
	 */
	private Point drawPointer(Canvas canvas, Point panPoint)
	{
		int pointerX = panPoint.x + pan.getWidth() / 2 - bg_pointer.getWidth() / 2;
		int pointerY = panPoint.y + pan.getHeight();
		int bottom = (int) ((this.getHeight() - BOTTOM_DUMMY_SIZE) / scaleRate) - pBottom;
		canvas.drawBitmap(bg_pointer, null, new Rect(pointerX, pointerY, pointerX + bg_pointer.getWidth(), bottom), null);
		Point point = new Point(pointerX, pointerY);
		return point;
	}

	/**
	 * 绘制指针图上面的刻度数值，显示对应当前大饼位置的数值
	 * @param canvas
	 * @param point
	 * @param value
	 * @param paint
	 * @return 返回文本baseline的Point
	 */
	private Point drawText(Canvas canvas, Point point, int value, Paint paint)
	{
		int bottom = (int) ((this.getHeight() - BOTTOM_DUMMY_SIZE) / scaleRate) - pBottom;
		int px = (int) (point.x + bg_pointer.getWidth() / 2 - paint.descent());
		int py = (int) (point.y + (bottom - point.y) * 1 / 2);
		canvas.rotate(90, px, py);
		canvas.drawText(String.valueOf(value), px, py, paint);
		canvas.rotate(-90, px, py);
		Point pointResult = new Point(px, py);
		return pointResult;
	}
	
	/**
	 * 绘制大饼
	 * @author ChrisLiu
	 * @param canvas
	 * @param value
	 * @param axisLeft
	 * @param axisTop
	 * @return 返回大饼图片当前位置的左上角Point
	 */
	private Point drawPan(Canvas canvas, int value, int axisLeft, int axisTop)
	{
		int panX = getXLocationgFromValue(value);
		int panY = axisTop + node_background.getHeight() / 2 - pan.getHeight() / 2;
		canvas.drawBitmap(pan, panX, panY, null);

		Point point = new Point(panX, panY);
		return point;
	}

	/**
	 * 绘制最上方的坐标刻度标尺
	 * @author ChrisLiu
	 * @param canvas
	 * @param axisLeft
	 * @param axisTop
	 */
	private void drawRulerLine(Canvas canvas, int axisLeft, int axisTop)
	{
		int textPadding = (int) (RULER_TEXT_PADDING / scaleRate);

		for (int i = 0; i < stage.length; i++)
		{
			//1. 计算出包过字符串的宽度
			float strWidth = paintRuler.measureText(stage[i]);
			Log.d(tag, "strWidth = " + strWidth);

			//Rect bounds = new Rect();
			//mPaint.getTextBounds(stage[i], 0, stage[i].length(), bounds);
			//Log.d(tag, "x="+x+" y="+y);
			//Log.d(tag, "bounds.left="+bounds.left+" bounds.top="+bounds.top+" bounds.right="+bounds.right+" bounds.bottom="+bounds.bottom);

			//2. 画出字符串背景图，留好四周余量
			int picLeft = (int) (axisLeft + node_background.getWidth() / 2 + i * locationIncreaseStep - strWidth / 2 - textPadding);
			int picTop = (int) (axisTop * 3 / 4 - (paintRuler.descent() - paintRuler.ascent()) - textPadding - BOTTOM_DUMMY_SIZE / scaleRate);
			int picRight = (int) (picLeft + textPadding + strWidth + textPadding);
			int picBottom = (int) (picTop + textPadding + (paintRuler.descent() - paintRuler.ascent()) + textPadding);
			Log.d(tag, "picLeft=" + picLeft + " picTop=" + picTop + " picRight=" + picRight + " picBottom=" + picBottom);

			mRect = new Rect(picLeft, picTop, picRight, picBottom);
			mNinePatch.draw(canvas, mRect);

			//3. 水平绘制文字字符串
			float x = picLeft + textPadding;
			float y = picTop - paintRuler.ascent() + textPadding;
			canvas.drawText(stage[i], x, y, paintRuler);
			Log.d(tag, "descent=" + paintRuler.descent() + " ascent=" + paintRuler.ascent());
		}
	}

	/**
	 * 通过数值判断当前对应中轴上面的x坐标，主要用于更新数值之后的大饼、指针位置重绘
	 * @author ChrisLiu
	 * @param value
	 * @return 返回int型的x坐标值
	 */
	private int getXLocationgFromValue(int value)
	{
		int x;
		int result;
		//		Log.i(tag, "firstStageValue=" + firstStageValue + " secondStageValue=" + secondStageValue + " thirdStageValeu=" + thirdStageValeu + " fourthStageValue=" + fourthStageValue + " fifthStageValue=" + fifthStageValue);
		//		Log.d(tag, "locationIncreaseStep="+locationIncreaseStep);
		if (value < firstStageValue)
		{
			x = 0 + pLeft;
		}
		if (value > fifthStageValue)
		{
			x = fifthStageValue;
		}

		if (value <= fifthStageValue && value > fourthStageValue)
		{
			x = (int) (((float) (value - fourthStageValue) / (float) (fifthStageValue - fourthStageValue)) * locationIncreaseStep + 3 * locationIncreaseStep + pan.getWidth() / 2 + pLeft);
		} else if (value <= fourthStageValue && value > thirdStageValeu)
		{
			x = (int) (((float) (value - thirdStageValeu) / (float) (fourthStageValue - thirdStageValeu)) * locationIncreaseStep + 2 * locationIncreaseStep + pan.getWidth() / 2 + pLeft);
		} else if (value <= thirdStageValeu && value > secondStageValue)
		{
			x = (int) (((float) (value - secondStageValue) / (float) (thirdStageValeu - secondStageValue)) * locationIncreaseStep + 1 * locationIncreaseStep + pan.getWidth() / 2 + pLeft);
		} else if (value <= secondStageValue && value > firstStageValue)
		{
			x = (int) (((float) (value - firstStageValue) / (float) (secondStageValue - firstStageValue)) * locationIncreaseStep + 0 * locationIncreaseStep + pan.getWidth() / 2 + pLeft);
		} else
		{
			x = 0 + pLeft;
		}

		result = (int) (x - pan.getWidth() / 2);
		Log.d(tag, "getXLocationgFromValue()-> x = " + x + " result=" + result);
		if (result < 0)
		{
			result = 0;
		}
		return result;
	}
	
	/**
	 * 通过x坐标获取当前的数字，主要用于触摸滑动大饼显示数字在指针上
	 * @author ChrisLiu
	 * @param xLocation
	 * @return 返回int型的刻度数值
	 */
	private int getValueByXLocation(int xLocation)
	{
		float value = 0;
		float x = xLocation + pan.getWidth() / 2;
		Log.i(tag, "x=" + x);
		float firstStageXLocation = pan.getWidth() / 2 + pLeft;
		float secondStageXLocation = firstStageXLocation + locationIncreaseStep;
		float thirdStageXLocation = secondStageXLocation + locationIncreaseStep;
		float fourthStageXLocation = thirdStageXLocation + locationIncreaseStep;
		float fifthStageXLocation = fourthStageXLocation + locationIncreaseStep;

		float amount1to2 = secondStageValue - firstStageValue;
		float amount2to3 = thirdStageValeu - secondStageValue;
		float amount3to4 = fourthStageValue - thirdStageValeu;
		float amount4to5 = fifthStageValue - fourthStageValue;

		Log.i(tag, "firstStageXLocation=" + firstStageXLocation + " secondStageXLocation=" + secondStageXLocation + " thirdStageXLocation=" + thirdStageXLocation + " fourthStageXLocation=" + fourthStageXLocation + " fifthStageXLocation=" + fifthStageXLocation);

		if (x < firstStageXLocation)
		{
			x = firstStageXLocation;
		} 
		if (x > fifthStageXLocation)
		{
			x = fifthStageXLocation;
		}

		if (x <= fifthStageXLocation && x > fourthStageXLocation)
		{
			value = (x - fourthStageXLocation) / locationIncreaseStep * amount4to5 + fourthStageValue;
			value -= value%MOL_STEP4;
		} else if (x <= fourthStageXLocation && x > thirdStageXLocation)
		{
			value = (x - thirdStageXLocation) / locationIncreaseStep * amount3to4 + thirdStageValeu;
			value -= value%MOL_STEP3;
		} else if (x <= thirdStageXLocation && x > secondStageXLocation)
		{
			value = (x - secondStageXLocation) / locationIncreaseStep * amount2to3 + secondStageValue;
			value -= value%MOL_STEP2;
		} else if (x <= secondStageXLocation && x > firstStageXLocation)
		{
			value = (x - firstStageXLocation) / locationIncreaseStep * amount1to2 + firstStageValue;
			value -= value%MOL_STEP1;
		} else
		{
			value = firstStageValue;
		}

		Log.d(tag, "getValueByXLocation()-> value =" + (int) value);
		return (int) value;
	}

}
