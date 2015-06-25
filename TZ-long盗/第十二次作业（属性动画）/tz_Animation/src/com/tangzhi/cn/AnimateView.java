package com.tangzhi.cn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by qinhan on 15/6/13.
 */
public class AnimateView extends View {
    private int mWidth;
    private int mHeight;

    private float mCircleX = 0;
    private float mCircleY = 0;

    private Paint mPaint;

    private ParabolicAnimation animation;

    public AnimateView(Context context) {
        super(context);
    }

    public AnimateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (animation!=null)animation.draw(canvas,mPaint);
    }



    public void start() {
        if (animation != null) {
            animation.cancel();
        }
        animation = new ParabolicAnimation();
        animation.start();
    }

    private class ParabolicAnimation implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
        private ValueAnimator engine;

        public ParabolicAnimation() {
            engine = ValueAnimator.ofFloat(0f, 3000f).setDuration(5000);
            engine.setInterpolator(new AccelerateDecelerateInterpolator());
            engine.addListener(this);
            engine.addUpdateListener(this);
        }

        @Override
        public void onAnimationStart(Animator animator) {
            mCircleX = 0;
            mCircleY = 0;
        }

        @Override
        public void onAnimationEnd(Animator animator) {

        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }

        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            mCircleX =  Float.parseFloat(valueAnimator.getAnimatedValue().toString());
            mCircleY = mCircleX * mCircleX * 0.001f;
            invalidate();
        }

        public void draw(Canvas canvas, Paint paint) {
            canvas.drawCircle(mCircleX,mCircleY,20,mPaint);
        }

        public void start() {
            engine.start();
        }

        public void cancel() {
            engine.cancel();
        }

    }
}
