package myvolume;

import com.example.myvolume.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;;

/**
 * 自定义的声音控件
 * @author Administrator
 *
 */
public class MyVolumeView extends View implements OnClickListener{

	public AudioManager mAudioManager;
	private int mMaxVolume=0;
	private int mCurrentVolume=0;
	private OnClickListener ocl;
	private Bitmap mDefaultBitmap;
	private Bitmap mCurrentBitmao;
	private View v;
	private int upY;
	private int height=0;
	private int count=0;
	public MyVolumeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mDefaultBitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.gray);
		mCurrentBitmao=BitmapFactory.decodeResource(context.getResources(),R.drawable.green);
		mAudioManager=(AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		mMaxVolume=mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		mCurrentVolume=mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		setOnClickListener(this);
		v=this;
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int sizeWidth=MeasureSpec.getSize(widthMeasureSpec);
		int sizeWidthModel=MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight=MeasureSpec.getSize(heightMeasureSpec);
		int sizeHeightModel=MeasureSpec.getMode(heightMeasureSpec);
		
		/**
		 * 如果是wrap_content就重写设置宽高
		 */
		int measureWidth=mDefaultBitmap.getWidth()+this.getPaddingLeft()+this.getPaddingRight();
		int measureHeight=(2*mMaxVolume-1)*mDefaultBitmap.getHeight()+this.getPaddingTop()+this.getPaddingBottom();
		
		if(sizeWidthModel==MeasureSpec.EXACTLY)
		{
			measureWidth=sizeWidth;
		}
		if(sizeHeightModel==MeasureSpec.EXACTLY)
		{
			measureHeight=sizeHeight;
		}
		this.setMeasuredDimension(measureWidth, measureHeight); 
	}
	@Override
	protected void onDraw(Canvas canvas) {
		drawBitmap(canvas);
		super.onDraw(canvas);
	}

	/**
	 *  绘制图片
	 * @param canvas
	 */
	private void drawBitmap(Canvas canvas) {
		int left=this.getPaddingLeft();
		int top=this.getPaddingTop();
		/**
		 * 灰色的图片
		 */
		for(int i=0;i<mMaxVolume-mCurrentVolume;i++)
		{
			top=this.getPaddingTop()+i*2*mDefaultBitmap.getHeight();
			canvas.drawBitmap(mDefaultBitmap, left,top,null);
		}

		/**
		 * 绿色的图片
		 */
		for(int i=mMaxVolume-mCurrentVolume;i<mMaxVolume;i++)
		{
			top=this.getPaddingTop()+i*2*mCurrentBitmao.getHeight();
			canvas.drawBitmap(mCurrentBitmao, left,top,null);
		}		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			doChange(event);
			break;
		case MotionEvent.ACTION_UP:
			doChange(event);
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	/**
	 * 改变UI的显示
	 * @param event
	 */
	private void doChange(MotionEvent event) {
		upY=(int) event.getY();
		height=upY-v.getPaddingTop();			
		if(height>0)
		{
			count=(height/mCurrentBitmao.getHeight())/2;
		}
		mCurrentVolume=mMaxVolume-count;
		if(mCurrentVolume<0)
		{
			mCurrentVolume=0;
		}
		invalidate();		
		/**
		 * 改变系统声音大小
		 */
		mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mCurrentVolume,  AudioManager.FLAG_PLAY_SOUND);
	}
	
	@Override
	public void onClick(View v) {
		if(ocl!=null)
		{
			ocl.onClick(v);
		}
	}
	
	
	public OnClickListener getOcl() {
		return ocl;
	}
	public void setOcl(OnClickListener ocl) {
		this.ocl = ocl;
	}
	/**
	 * 获取默认图片
	 * @return
	 */
	public Bitmap getmDefaultBitmap() {
		return mDefaultBitmap;
	}
	/**
	 * 设置默认图片
	 * @return
	 */
	public void setmDefaultBitmap(Bitmap mDefaultBitmap) {
		this.mDefaultBitmap = mDefaultBitmap;
	}

	/**
	 * 获取发生图片
	 * @return
	 */
	public Bitmap getmCurrentBitmao() {
		return mCurrentBitmao;
	}
	/**
	 * 设置发生图片
	 * @return
	 */
	public void setmCurrentBitmao(Bitmap mCurrentBitmao) {
		this.mCurrentBitmao = mCurrentBitmao;
	}
	/**
	 * 获取当前声音
	 * @return
	 */
	public int getmCurrentVolume() {
		return mCurrentVolume;
	}

	/**
	 * 设置当前声音
	 * @param mCurrentVolume
	 */
	public void setmCurrentVolume(int mCurrentVolume) {
		this.mCurrentVolume = mCurrentVolume;
	}

	
}
