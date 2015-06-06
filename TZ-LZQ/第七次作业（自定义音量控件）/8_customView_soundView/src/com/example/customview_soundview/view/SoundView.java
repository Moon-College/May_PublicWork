package com.example.customview_soundview.view;

import com.example.customview_soundview.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 自定义音量控件
 * 
 * @author L_ZQ
 * 
 */
public class SoundView extends View {

	private Bitmap bitmap_gray;
	private Bitmap bitmap_green;
	public AudioManager am;
	private int maxVolume;
	private int currentVolume;
	private int down_y;

	public int getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}

	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		bitmap_gray = BitmapFactory.decodeResource(getResources(),
				R.drawable.gray);
		bitmap_green = BitmapFactory.decodeResource(getResources(),
				R.drawable.green);
		// 初始化AudioManager
		am = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
		// 最大音量 媒体音量
		maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		// 当前音量
		currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);

		// Button
	}

	// 测量宽高
	// widthMeasureSpec，heightMeasureSpec尺寸和模式组合的一个值 ，底层是用到位运算
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 首先获取父控件指定的宽高和模式 ，模式有3中： fillParent 指定dp warp_content ,
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);// 都是指的px
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

		// 如果是wrap_content 的时候，它并不知道自己要多高多款，所以就会和父控件一样，所以我们要自己设置
		int measuredWidth = bitmap_gray.getWidth() + this.getPaddingLeft()
				+ this.getPaddingRight();

		int measuredHeight = (2 * maxVolume - 1) * bitmap_gray.getHeight()
				+ this.getPaddingTop() + this.getPaddingBottom();

		if (modeWidth == MeasureSpec.EXACTLY) { // 模式有3中，但是android 把 fill_Parent
												// 和 固定值即指定dp
												// 想象为一种.EXACTLY就是前面两种的归类
			measuredWidth = sizeWidth;
		}
		if (modeHeight == MeasureSpec.EXACTLY) { // 模式有3中，但是android 把
													// fill_Parent 和 固定值即指定dp
													// 想象为一种
			measuredHeight = sizeHeight;
		}

		this.setMeasuredDimension(measuredWidth, measuredHeight);

		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	// 要经常绘制
	@Override
	protected void onDraw(Canvas canvas) {
		int top = 0;
		int left = this.getPaddingLeft();
		// 绘制音量图片
		// 加载图片
		// 绘制灰色图片
		for (int i = 0; i < maxVolume - currentVolume; i++) {
			top = i * bitmap_gray.getHeight() * 2 + this.getPaddingTop();
			canvas.drawBitmap(bitmap_gray, left, top, null);
		}
		// 绘制绿色图片
		for (int i = maxVolume - currentVolume; i < maxVolume; i++) {
			top = i * bitmap_green.getHeight() * 2 + this.getPaddingTop();
			canvas.drawBitmap(bitmap_green, left, top, null);
		}

		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			down_y = (int) event.getY();

			break;
		case MotionEvent.ACTION_MOVE:
			int move_y = (int) event.getY();

			// 触摸时 y坐标 的偏移量
			int offset = Math.abs(down_y - move_y);

			// 这个是他到底 上调了几个音量
			int number = (int) (offset * 0.05 / (bitmap_gray.getHeight() * 2) + 0.5);

			if (number != 0) {
				if (down_y - move_y > 0) {

					// if (number <= (maxVolume - currentVolume)) {

					// 调高音量
					for (int i = 0; i < number; i++) {

						if (currentVolume != maxVolume) {
							currentVolume = currentVolume + 1;
							am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
									AudioManager.ADJUST_RAISE, // 调高还是调低
									AudioManager.FLAG_PLAY_SOUND);// 调节音量的时候
																	// 的一些模式，比如震动，显示音量画面
							invalidate();
							// }
						}
					}
				} else if (down_y - move_y < 0) {
					// if (number <= currentVolume) {
					// 调低音量
					for (int i = 0; i < number; i++) {
						if (currentVolume != 0) {
							// invalidate();
							currentVolume = currentVolume - 1;
							am.adjustStreamVolume(AudioManager.STREAM_MUSIC,
									AudioManager.ADJUST_LOWER, // 调高还是调低
									AudioManager.FLAG_PLAY_SOUND);// 调节音量的时候
																	// 的一些模式，比如震动，显示音量画面
							invalidate();
							// }
						}
					}
				}
			}
			// down_y = (int) event.getY();

			break;
		case MotionEvent.ACTION_UP:

			break;

		default:
			break;
		}

		return super.onTouchEvent(event);
	}
}
