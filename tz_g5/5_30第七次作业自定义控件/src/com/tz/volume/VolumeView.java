package com.tz.volume;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.tz.volmue.R;

/**
 * Created by qinhan on 15/6/1.
 */
public class VolumeView extends View {

    private int WID;//控件宽度

    private int HEI;//控件高度

    private AudioManager mManager;

    private int maxVol;//当前音乐的最大音量

    private int currentVol;//当前音乐音量

    private Bitmap mGreenBitmap;

    private Bitmap mGrayBitmap;

    private GestureDetector mDetector;

    public VolumeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        mManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        maxVol = mManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currentVol = mManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mGrayBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
        mGreenBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.green);
        mDetector = new GestureDetector(getContext(),new VolumeGestureDetector());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int measuredWidth = mGrayBitmap.getWidth() + this.getPaddingLeft() + this.getPaddingRight();

        int measuredHeight = (2 * maxVol - 1) * mGrayBitmap.getHeight() + this.getPaddingTop() + this.getPaddingBottom();

        if (modeWidth == MeasureSpec.EXACTLY) {
            measuredWidth = sizeWidth;
        }
        if (modeHeight == MeasureSpec.EXACTLY) {
            measuredHeight = sizeHeight;
        }
        this.setMeasuredDimension(measuredWidth, measuredHeight);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        WID = h;
        HEI = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int left = this.getPaddingLeft();//水平起始坐标
        int top = this.getPaddingTop();//垂直起始坐标
        for (int i = 0; i < maxVol; i++) {
            if (i < maxVol - currentVol) {
                top = i * 2 * mGrayBitmap.getHeight() + this.getPaddingTop();
                canvas.drawBitmap(mGrayBitmap, left, top, null);
            } else {
                top = i * 2 * mGreenBitmap.getHeight() + this.getPaddingTop();
                canvas.drawBitmap(mGreenBitmap, left, top, null);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return true;
    }

    /**
     * 改变音量
     */
    public boolean changeVolume(int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                mManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
                break;
            case KeyEvent.KEYCODE_VOLUME_UP:
                mManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
                break;
            default:
                return false;
        }
        currentVol = mManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        invalidate();
        return true;
    }


    class VolumeGestureDetector extends GestureDetector.SimpleOnGestureListener {


        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            float mOldX = e1.getX(), mOldY = e1.getY();
            int y = (int) e2.getY();
            float scrollY = mOldY - y;
            Log.v("scroll", scrollY + "");
            onVolumeSlide(scrollY / HEI);
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

    /**
     * 调节音量
     *
     * @param percent
     */
    public void onVolumeSlide(float percent) {

        float index = (percent * maxVol) + currentVol;
        if (index >= maxVol) {
            index = maxVol;
        } else if (index <= 0f) {
            index = 0f;
        }
        Log.v("index", index + "");
        // 变更声音
        mManager.setStreamVolume(AudioManager.STREAM_MUSIC, (int) index, AudioManager.FLAG_PLAY_SOUND);
        currentVol = mManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        invalidate();
    }

}
