package com.lin.customvoiceview.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;

import com.lin.customvoiceview.R;

/**
 * Created by Administrator on 2015/6/1.
 */
public class SoundView extends View {
    private Bitmap bitmap_gray;
    private Bitmap bitmap_green;

    private AudioManager audioManager;
    private int maxVolume;
    private int streamVolume;
    private int width;
    private int height;
    private Context context;

    public SoundView(Context context) {
        super(context);
        init();

    }

    public SoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        bitmap_gray = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.gray);
        bitmap_green = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.green);
        width = bitmap_gray.getWidth();
        height = bitmap_gray.getHeight();
        audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //宽度的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        //系统得到的宽度
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        //高度的模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //高度的大小
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        //如果是wrap_content
        int measuredWidth = width + this.getPaddingLeft() + this.getPaddingRight();

        int measuredHeight = (2 * maxVolume - 1) * height + this.getPaddingTop() + this.getPaddingBottom();


        if (widthMode == MeasureSpec.EXACTLY) {
            measuredWidth = widthSize;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            measuredHeight = heightSize;
        }

        this.setMeasuredDimension(measuredWidth, measuredHeight);//手动设置宽高
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        for (int i = 0; i < maxVolume; i++) {
            if (i < maxVolume - streamVolume) {
                canvas.drawBitmap(bitmap_gray, 0 + getPaddingLeft(), (float) (i * 2 * height + getPaddingTop()), null);
            } else {
                canvas.drawBitmap(bitmap_green, 0 + getPaddingLeft(), (float) (i * 2 * height + getPaddingTop()), null);
            }
        }
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }
}
