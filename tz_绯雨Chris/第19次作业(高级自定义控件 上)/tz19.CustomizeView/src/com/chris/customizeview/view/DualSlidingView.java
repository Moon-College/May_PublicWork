package com.chris.customizeview.view;

import com.chris.customizeview.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.TextView;

public class DualSlidingView extends View
{
	private static final String tag = "DualSlidingView";
	private static final String FIRST_STAGE = " 0 ";
	private static final String SECOND_STAGE = "100";
	private static final String THIRD_STAGE = "500";
	private static final String FOURTH_STAGE = "1000";
	private static final String FIFTH_STAGE = "5000";
	private static final float STAGE_TEXT_PADDING = 6;
	private static final float STAGE_TEXT_SIZE = 25;
	private static final float DUMMY_BUF_SIZE = 50;

	private Bitmap axis_background, axis_foreground, bg_pointer, node_background, node_foreground, pan, dialog_background;
	private Paint mPaint;
	private float scaleRate;
	private NinePatch mNinePatch;
	private Rect mRect;

	int pLeft = this.getPaddingLeft();
	int pRight = this.getPaddingRight();
	int pTop = this.getPaddingTop();
	int pBottom = this.getPaddingBottom();

	String[] stage = new String[]
	{ FIRST_STAGE, SECOND_STAGE, THIRD_STAGE, FOURTH_STAGE, FIFTH_STAGE };

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
		createBitmaps();
		mNinePatch = new NinePatch(dialog_background, dialog_background.getNinePatchChunk(), null);
		mPaint = new Paint();
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
	}

	private Bitmap decodeBitmapFromResource(int id)
	{
		Bitmap bitmap;
		bitmap = BitmapFactory.decodeResource(getResources(), id);
		return bitmap;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int bodyWidth = axis_background.getWidth() + (pan.getWidth() - node_background.getWidth()) + pLeft + pRight;
		int bodyHeight = bg_pointer.getHeight() + pan.getHeight() + pTop + pBottom;
		Log.d(tag, "bodyWidth=" + bodyWidth + " bodyHeight=" + bodyHeight);

		int widthSize = resolveAdjustedSize(bodyWidth, widthMeasureSpec, 0);
		int heightSize = resolveAdjustedSize(bodyHeight, heightMeasureSpec, 0);
		Log.d(tag, "widthSize=" + widthSize + " heightSize=" + heightSize);

		scaleRate = (widthSize > heightSize) ? (float) widthSize / (float) bodyWidth : (float) heightSize / (float) bodyHeight;
		Log.d(tag, "scaleRate=" + scaleRate);

		mPaint.setTextSize(STAGE_TEXT_SIZE / scaleRate);
		mPaint.setColor(Color.BLACK);
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		
		//增加文字框高度后，重新测量控件高度
		bodyHeight += DUMMY_BUF_SIZE/scaleRate + (mPaint.descent() - mPaint.ascent()) +  2*(STAGE_TEXT_PADDING / scaleRate);
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
	protected void onDraw(Canvas canvas)
	{
		canvas.save();
		canvas.scale(scaleRate, scaleRate);

		//绘制主体控件
		int axisLeft = (pan.getWidth() - node_background.getWidth()) / 2 + pLeft;
		int canvasHeight = (int) (this.getHeight() / scaleRate);
		int bodyHeight = pan.getHeight() + pTop + pBottom;
		int axisTop = (int) (canvasHeight - bodyHeight)/2;
		canvas.drawBitmap(axis_background, axisLeft, axisTop, null);

		//绘制字符串背景图
		float xIncreaseStep = (float) (axis_background.getWidth() - node_background.getWidth()) / 4;
		int textPadding = (int) (STAGE_TEXT_PADDING / scaleRate);
		
		for (int i = 0; i < stage.length; i++)
		{
			//1. 计算出包过字符串的宽度
			float strWidth = mPaint.measureText(stage[i]);
			Log.d(tag, "strWidth = " + strWidth);

			//			Rect bounds = new Rect();
			//			mPaint.getTextBounds(stage[i], 0, stage[i].length(), bounds);
			//			Log.d(tag, "x="+x+" y="+y);
			//			Log.d(tag, "bounds.left="+bounds.left+" bounds.top="+bounds.top+" bounds.right="+bounds.right+" bounds.bottom="+bounds.bottom);

			//2. 画出字符串背景图，留好四周余量
			int picLeft = (int) (axisLeft + node_background.getWidth() / 2 + i * xIncreaseStep  - strWidth/2 - textPadding);
			int picTop = (int) (axisTop*3/4 - (mPaint.descent() - mPaint.ascent()) - textPadding - DUMMY_BUF_SIZE/scaleRate);
			int picRight = (int) (picLeft + textPadding + strWidth + textPadding);
			int picBottom = (int) (picTop + textPadding + (mPaint.descent() - mPaint.ascent()) + textPadding);
			Log.d(tag, "picLeft=" + picLeft + " picTop=" + picTop + " picRight=" + picRight + " picBottom=" + picBottom);

			mRect = new Rect(picLeft, picTop, picRight, picBottom);
			mNinePatch.draw(canvas, mRect);

			//3. 绘制文字字符串
			float x = picLeft + textPadding;
			float y = picTop - mPaint.ascent() + textPadding;
			Log.d(tag, "descent=" + mPaint.descent() + " ascent=" + mPaint.ascent());

			canvas.drawText(stage[i], x, y, mPaint);
		}

		canvas.restore();
		super.onDraw(canvas);
	}

}
