package com.tz.michael.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;
/**
 * 自定义的音量控件
 * @author szm Michael
 *
 */
public class SoundView extends View {

	/**画图片的画笔*/
	private Paint paint;
	/**点亮颜色*/
	private int valueColor;
	/**默认未点亮颜色*/
	private int defaultColor;
	/**音量管理者*/
	public AudioManager am;
	/**最大音量值*/
	private int maxVol;
	/**当前音量*/
	private int curVol;
	/**音量块的宽*/
	private int rect_width;
	/**音量块的高*/
	private int rect_height;
	/**高度缩放比例*/
	private float hScaleValue;
	/**宽度缩放比例*/
	private float wScaleValue;
	
	/**
	 * 返回当前点亮值颜色
	 * @return
	 */
	public int getValueColor() {
		return valueColor;
	}

	/**
	 * 点亮值颜色
	 * @param valueColor
	 */
	public void setValueColor(int valueColor) {
		this.valueColor = valueColor;
		invalidate();
	}

	/**
	 * 返回当前未点亮值得颜色
	 * @return
	 */
	public int getDefaultColor() {
		return defaultColor;
	}

	/**
	 * 设置未点亮颜色
	 * @param defaultColor
	 */
	public void setDefaultColor(int defaultColor) {
		this.defaultColor = defaultColor;
		invalidate();
	}

	/**
	 * 获得当前音量值
	 * @return
	 */
	public int getCurVol() {
		return curVol;
	}

	/**
	 * 设置当前音量值
	 * @param curVol
	 */
	public void setCurVol(int curVol) {
		this.curVol = curVol;
		invalidate();
	}

	/**
	 * 获得音量块的宽度
	 * @return
	 */
	public int getRect_width() {
		return rect_width;
	}

	/**
	 * 设置音量块的宽度
	 * @param rect_width
	 */
	public void setRect_width(int rect_width) {
		this.rect_width = rect_width;
		invalidate();
		requestLayout();
	}

	/**
	 * 获得当前音量块的高度
	 * @return
	 */
	public int getRect_height() {
		return rect_height;
	}

	/**
	 * 设置音量块的高度
	 * @param rect_height
	 */
	public void setRect_height(int rect_height) {
		this.rect_height = rect_height;
		invalidate();
		requestLayout();
	}

	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint=new Paint();
		valueColor=Color.GREEN;
		defaultColor=Color.GRAY;
		rect_width=100;
		rect_height=20;
		am=(AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		maxVol=am.getStreamMaxVolume(AudioManager.STREAM_RING);
		curVol=am.getStreamVolume(AudioManager.STREAM_RING);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int top=this.getPaddingTop();
		int left=this.getPaddingLeft();
		canvas.save();
		canvas.scale(wScaleValue, hScaleValue);
		for(int i=0;i<maxVol;i++){
			if(i<maxVol-curVol){
				//画未点亮的图片
				paint.setColor(defaultColor);
				canvas.drawRect(left, top+2*i*rect_height, left+rect_width, top+2*i*rect_height+rect_height, paint);
			}else{
				//画点亮的图片
				paint.setColor(valueColor);
				canvas.drawRect(left, top+2*i*rect_height, left+rect_width, top+2*i*rect_height+rect_height, paint);
			}
		}
		canvas.restore();
		super.onDraw(canvas);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width=MeasureSpec.getSize(widthMeasureSpec);
		int widthMode=MeasureSpec.getMode(widthMeasureSpec);
		int height=MeasureSpec.getSize(heightMeasureSpec);
		int heightMode=MeasureSpec.getMode(heightMeasureSpec);
		int wrapWidth=rect_width+this.getPaddingLeft()+this.getPaddingRight();
		int measuredWidth=widthMode==MeasureSpec.EXACTLY?width:wrapWidth;
		wScaleValue=(float)(measuredWidth)/wrapWidth;
		int wrapHeight=(2*maxVol-1)*rect_height+this.getPaddingBottom()+this.getPaddingTop();
		int measuredHeight=heightMode==MeasureSpec.EXACTLY?height:wrapHeight;
		hScaleValue=(float)(measuredHeight)/wrapHeight;
		setMeasuredDimension(measuredWidth, measuredHeight);
	}
	
}
