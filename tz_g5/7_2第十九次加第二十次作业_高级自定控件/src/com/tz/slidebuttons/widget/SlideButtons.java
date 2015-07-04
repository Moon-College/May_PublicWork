package com.tz.slidebuttons.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.tz.slidebuttons.R;

/**
 * Created by qinhan on 15/7/3.
 */
public class SlideButtons extends View {


    private Paint mIndicatorTextPaint;
    private Paint mStageTextPaint;

    private float mUpperLimitPrice;
    private float mLowerLimitPrice;
    private final int FIRST_STAGE = 0;
    private final int SECOND_STAGE = 200;
    private final int THIRD_STAGE = 500;
    private final int FOURTH_STAGE = 1000;
    private final int TEXT_PADDING = 15;
    private final int FIFTH_STAGE = 10000;
    private float mSpan;
    private String mStagesText[] = {"无限", String.valueOf(FOURTH_STAGE), String.valueOf(THIRD_STAGE), String.valueOf(SECOND_STAGE), String.valueOf(FIRST_STAGE)};
    private int mStagesValues[] = {10000, 1000, 500, 200, 0};


    private float mScaleRatio;

    private Bitmap mSlideButtons;
    private Bitmap mSlideBar;
    private Bitmap mSlideBarBackground;
    private Bitmap mSlideIndicator;
    private int bgHeight;
    private int bgWidth;
    private boolean isUpTouched;
    private boolean isDownTouched;
    private int mButtonX;
    private int mUpperY;
    private int mLowerY;


    public SlideButtons(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.slidebuttons, 0, 0);
        mUpperLimitPrice = array.getFloat(R.styleable.slidebuttons_priceUp, 1000f);
        mLowerLimitPrice = array.getFloat(R.styleable.slidebuttons_priceDown, 200f);

        int indicatorTextColor = array.getColor(R.styleable.slidebuttons_indicatorTextColor, Color.WHITE);
        int stageTextColor = array.getColor(R.styleable.slidebuttons_stageTextColor, Color.WHITE);

        array.recycle();

        mIndicatorTextPaint = new Paint();
        mIndicatorTextPaint.setAntiAlias(true);
        mIndicatorTextPaint.setColor(indicatorTextColor);
        mIndicatorTextPaint.setTextSize(array.getDimension(R.styleable.slidebuttons_indicatorTextSize, 20));

        mStageTextPaint = new Paint();
        mStageTextPaint.setAntiAlias(true);
        mStageTextPaint.setColor(stageTextColor);
        mStageTextPaint.setTextSize(array.getDimension(R.styleable.slidebuttons_stageTextSize, 20));

        Resources resources = context.getResources();
        mSlideButtons = BitmapFactory.decodeResource(resources, R.drawable.btn);
        mSlideBar = BitmapFactory.decodeResource(resources, R.drawable.axis_before);
        mSlideBarBackground = BitmapFactory.decodeResource(resources, R.drawable.axis_after);
        mSlideIndicator = BitmapFactory.decodeResource(resources, R.drawable.bg_number);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWid = MeasureSpec.getSize(widthMeasureSpec);
        int measureWidMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHei = MeasureSpec.getSize(heightMeasureSpec);
        int measureHeiMode = MeasureSpec.getMode(heightMeasureSpec);

        bgHeight = mSlideBar.getHeight();
        bgWidth = mSlideBar.getWidth();

        int actualHei = (measureHeiMode == MeasureSpec.EXACTLY) ? measureHei : Math.min(bgHeight, measureHei);
        int actualWid = measureWidMode == MeasureSpec.EXACTLY ? measureWid : actualHei * 2 / 3;
        mScaleRatio = (float) actualHei / (float) bgHeight;
        mSpan = (bgHeight - bgWidth) / 4;
        setMeasuredDimension(actualWid, actualHei);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mScaleRatio, mScaleRatio);
        int centerX = (int) (getWidth() / mScaleRatio) / 2;
        canvas.drawBitmap(mSlideBar, centerX - bgWidth / 2, 0, null);
        float stageDescent = mStageTextPaint.descent();
        float stageAscent = mStageTextPaint.ascent();
        for (int i = 0; i < mStagesText.length; i++) {
            String text = mStagesText[i];
            int leftStage = centerX * 3 / 2;
            int topStage = (int) (bgWidth / 2 + i * mSpan + (stageDescent - stageAscent) / 2 - stageDescent);
            canvas.drawText(text, leftStage, topStage, mStageTextPaint);
        }

        int leftButton = centerX - mSlideButtons.getWidth() / 2;
        mButtonX = leftButton;
        mUpperY = (int) getYByPrice(mUpperLimitPrice);
        canvas.drawBitmap(mSlideButtons, leftButton, mUpperY, null);
        mLowerY = (int) getYByPrice(mLowerLimitPrice);
        canvas.drawBitmap(mSlideButtons, leftButton, mLowerY, null);

        Rect src = new Rect(0, (mUpperY+mSlideButtons.getHeight()/2), bgWidth, (mLowerY+mSlideButtons.getHeight()/2));
        Rect dst = new Rect(centerX - bgWidth / 2, (mUpperY+mSlideButtons.getHeight()/2), (centerX - bgWidth / 2+bgWidth), (mLowerY+mSlideButtons.getHeight()/2));
        canvas.drawBitmap(mSlideBarBackground, src, dst, null);
        Rect rect_up = getRectByY(mUpperY+mSlideButtons.getHeight()/2,leftButton);
        canvas.drawBitmap(mSlideIndicator, null, rect_up, null);
        Rect rect_down = getRectByY(mLowerY+mSlideButtons.getHeight()/2,leftButton);
        canvas.drawBitmap(mSlideIndicator, null, rect_down, null);

        float text_up_y = mUpperY+mSlideButtons.getHeight()/2 + (mIndicatorTextPaint.descent()-mIndicatorTextPaint.ascent())/2 -mIndicatorTextPaint.descent();
        float text_up_x = (3*rect_up.width()/4 - mIndicatorTextPaint.measureText(String.valueOf(mUpperLimitPrice)))/2;
        canvas.drawText(String.valueOf(mUpperLimitPrice), text_up_x, text_up_y, mIndicatorTextPaint);

        float text_down_y = mLowerY+mSlideButtons.getHeight()/2 +(mIndicatorTextPaint.descent()-mIndicatorTextPaint.ascent())/2-mIndicatorTextPaint.descent();
        float text_down_x = (3*rect_down.width()/4 - mIndicatorTextPaint.measureText(String.valueOf(mLowerLimitPrice)))/2;
        canvas.drawText(String.valueOf(mLowerLimitPrice),text_down_x,text_down_y, mIndicatorTextPaint);
        canvas.restore();
    }

    private Rect getRectByY(float y,float right) {
        // TODO Auto-generated method stub
        Rect rect = new Rect();
        rect.left = 0;
        rect.right = (int) right;
        float text_h = mIndicatorTextPaint.descent() - mIndicatorTextPaint.ascent();
        rect.top = (int) (y - text_h/2 - TEXT_PADDING);
        rect.bottom = (int) (y+text_h/2 + TEXT_PADDING);
        return rect;
    }
    private float getYByPrice(float price) {
        if (price < 0) price = 0f;
        if (price > 10000) price = 10000f;
        float y = 0;
        for (int i = 0; i < 4; i++) {
            if (price <= mStagesValues[i] && price > mStagesValues[i + 1]) {
                y = mSpan * (mStagesValues[i] - price) / (mStagesValues[i] - mStagesValues[i + 1]) + mSpan * i + bgWidth / 2-mSlideButtons.getWidth()/2;
                break;
            }
        }
        if (price == 0) y = mSpan * 4 + bgWidth / 2 - mSlideButtons.getWidth() / 2;
        return y;
    }

    public int getPriceByY(float y) {
        int price;
        if (y < bgWidth / 2) {
            y = bgWidth / 2;
        }
        if (y > (bgWidth / 2 + 4 * mSpan)) {
            y = bgWidth / 2 + 4 * mSpan;
        }
        if (y >= bgWidth / 2 && y < bgWidth / 2 + mSpan) {
            //1000-10000
            price = (int) (FIFTH_STAGE - (FIFTH_STAGE - FOURTH_STAGE) * (y - bgWidth / 2) / mSpan);
        } else if (y >= bgWidth / 2 + mSpan && y < bgWidth / 2 + 2 * mSpan) {
            price = (int) (FOURTH_STAGE - (FOURTH_STAGE - THIRD_STAGE) * (y - bgWidth / 2 - mSpan) / mSpan);
        } else if (y >= bgWidth / 2 + 2 * mSpan && y < bgWidth / 2 + 3 * mSpan) {
            price = (int) (THIRD_STAGE - (THIRD_STAGE - SECOND_STAGE) * (y - bgWidth / 2 - 2 * mSpan) / mSpan);
        } else if (y >= bgWidth / 2 + 3 * mSpan && y < bgWidth / 2 + 4 * mSpan) {
            price = (int) (SECOND_STAGE - (SECOND_STAGE - FIRST_STAGE) * (y - bgWidth / 2 - 3 * mSpan) / mSpan);
        } else {
            price = 0;
        }

        if (price <= 1000) {
            int mol = price % 20;
            if (mol >= 10) {
                price = price - mol + 20;
            } else {
                price = price - mol;
            }
        }
        if (price > 1000) {
            int mol = price % 1000;
            if (mol >= 500) {
                price = price - mol + 1000;
            } else {
                price = price - mol;
            }
        }
        return price;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX()/mScaleRatio;
                float y = event.getY()/mScaleRatio;
                if(x>=mButtonX&&x<=mButtonX+mSlideButtons.getWidth()){
                    if(y>=(mUpperY)&&y<=(mUpperY+mSlideButtons.getHeight())){
                        isUpTouched = true;
                        isDownTouched = false;
                    }
                    if(y>=(mLowerY )&&y<=(mLowerY +mSlideButtons.getHeight())){
                        isDownTouched = true;
                        isUpTouched = false;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float y2 = event.getY()/mScaleRatio;
                if(isUpTouched){
                    mUpperLimitPrice = getPriceByY(y2);
                    if(mUpperLimitPrice<=mLowerLimitPrice){
                        mUpperLimitPrice = mLowerLimitPrice;
                    }
                }

                if(isDownTouched){
                    mLowerLimitPrice = getPriceByY(y2);
                    if(mLowerLimitPrice>=mUpperLimitPrice){
                        mLowerLimitPrice = mUpperLimitPrice;
                    }
                }
                this.invalidate();
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
