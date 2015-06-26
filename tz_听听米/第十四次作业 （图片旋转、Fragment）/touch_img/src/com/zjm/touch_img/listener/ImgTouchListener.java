package com.zjm.touch_img.listener;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

/**
 * 图片的触摸监听
 * 
 * @author 邹继明
 * @date 2015-6-17下午11:13:12
 */
@SuppressLint("ClickableViewAccessibility")
public class ImgTouchListener implements OnTouchListener {
	/**
	 * 记录图片的操作模式
	 */
	private int mode = 0;// 初始状态

	private final static int MODE_DRAG = 1;// 拖动
	private final static int MODE_ZOOM = 2;// 缩放
	private final static int MODE_ROTA = 3;// 旋转

	private PointF startPoint;

	/** 用于记录改变后的矩阵状态 */
	private Matrix matrix = new Matrix();
	/** 用于记录图片当前的矩阵状态 */
	private Matrix currentMatrix = new Matrix();

	private float startDis;// 两根手指开始的距离

	private ImageView iv;

	private PointF midPoint;


	private float newDegree;

	private float startDegree;
	
	//图片中心点坐标
	private int x;
	private int y;

	public ImgTouchListener() {

	}

	public ImgTouchListener(ImageView iv) {
		this.iv = iv;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		x= iv.getWidth()/2;
		y= iv.getHeight()/2;
		int pointers = event.getPointerCount();// 手指的数量
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			// mode = MODE_DRAG;
			// startPoint = new PointF(event.getX(), event.getY());
			// // 记录ImageView当前的移动位置
//			currentMatrix.set(iv.getImageMatrix());
			mode = MODE_ROTA;
			startDegree = rotation(event);//开始角度
			break;
		case MotionEvent.ACTION_MOVE:
			switch (mode) {
			case MODE_DRAG:
				// 在原有的位置上（没有移动之前的位置上）进行移动
				matrix.set(currentMatrix);
				// Log.i("INFO",
				// "startPoint.x:"+startPoint.x+",startPoint.y:"+startPoint.y);
				matrix.postTranslate(event.getX() - startPoint.x, event.getY()
						- startPoint.y);
				break;
			case MODE_ZOOM:
				if (pointers != 2) {
					break;
				}
				float endDis = distance(event);
				float scale = endDis / startDis;// 缩放倍数
				// 记录原有的矩阵状态
				matrix.set(currentMatrix);
				matrix.postScale(scale, scale, midPoint.x, midPoint.y);
				break;
			case MODE_ROTA:
//				matrix.set(currentMatrix);
				newDegree = rotation(event);
				matrix.postRotate(startDegree - newDegree, x,
						y);
				startDegree = newDegree;
				break;
			default:
				break;
			}
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			mode = MODE_ZOOM;
			midPoint = getMidPoint(event);
			startDis = distance(event);
			// 计算当前ImageView的缩放倍数
			currentMatrix.set(iv.getImageMatrix());
			break;
		case MotionEvent.ACTION_UP:
			mode = 0;
			break;
		case MotionEvent.ACTION_POINTER_UP:
			mode = 0;
			break;

		default:
			break;
		}
		// 通过新的矩阵摆放位置
		iv.setImageMatrix(matrix);
		return true;
	}

	/**
	 * 取旋转角度
	 * 
	 * @author 邹继明
	 * @date 2015-6-18上午1:04:35
	 * @param event
	 * @return
	 * @return float
	 */
	private float rotation(MotionEvent event) {
		double radians = Math.atan2(y-event.getY(), event.getX()-x);// 反正切获取弧度
		Log.i("INFO", "radians:"+radians);
//		float degrees = (float) Math.toDegrees(radians);// 角度
		float degrees = (float) (radians*180/Math.PI);
		Log.i("INFO", "degrees:"+degrees);
		return degrees;
	}

	/**
	 * 计算两根手指的中间点
	 * 
	 * @author 邹继明
	 * @date 2015-6-18上午12:22:38
	 * @param event
	 * @return
	 * @return PointF
	 */
	private PointF getMidPoint(MotionEvent event) {
		float midX = (event.getX(0) + event.getX(1)) / 2;
		float midY = (event.getY(0) + event.getY(1)) / 2;
		return new PointF(midX, midY);
	}

	/**
	 * 计算两个手指的距离
	 * 
	 * @author 邹继明
	 * @date 2015-6-18上午12:10:22
	 * @param event
	 * @return
	 * @return float
	 */
	@SuppressWarnings("deprecation")
	private int distance(MotionEvent event) {
		// Log.i("INFO","x0:"+event.getX(0));
		// Log.i("INFO","x1:"+event.getX(1));
		float dx = event.getX(1) - event.getX(0);
		float dy = event.getY(1) - event.getY(0);
		return (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

}
