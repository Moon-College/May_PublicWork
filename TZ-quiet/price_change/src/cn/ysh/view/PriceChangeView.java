package cn.ysh.view;

import cn.ysh.main.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PriceChangeView extends View{
	
	private Bitmap axis_before;//灰色滑竿
	private Bitmap axis_after;//绿色滑竿
	private Bitmap big_ball;//需要滑动的大球
	private Bitmap price_rect;//价格矩形
	private int parentWidth;//控件的宽
	private int parentHeight;//控件的高
	private int little_ball_width;//滑竿上小球宽度
	private int per_size;//每段区域的距离
	//价格分级
	private final int FIRST_LEVEL = 0;
	private final int SECOND_LEVEL = 200;
	private final int THIRD_LEVEL = 500;
	private final int FOURTH_LEVEL = 1000;
	private final int FIFTH_LEVEL = 10000;
	//画笔
	private Paint paint;
	//高度缩放的比例
	private float scale_height;
	private float big_ball_x;
	private final int PADDING = 15;
	private float big_ball_up_y;
	private float big_ball_down_y;
	private boolean isUpPress;
	private boolean isDownPress;
	private int price_up = 1000;
	private int price_down = 200;
	

	public PriceChangeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		axis_before = BitmapFactory.decodeResource(getResources(), R.drawable.axis_before);
		axis_after = BitmapFactory.decodeResource(getResources(), R.drawable.axis_after);
		big_ball = BitmapFactory.decodeResource(getResources(), R.drawable.btn);
		price_rect = BitmapFactory.decodeResource(getResources(), R.drawable.bg_number);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);//父容器指定的宽
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);//父容器指定的高
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		little_ball_width= axis_before.getWidth();
		per_size = (axis_before.getHeight() - little_ball_width) / 4;
		//父控件的高度：如果是指定的，就用测量出来的；如果是wrap_content，就用滑竿的高度
		parentHeight = ((heightMode == MeasureSpec.EXACTLY) ? heightSize : axis_before.getHeight());
		parentHeight = Math.min(parentHeight, heightSize);
		parentWidth = 2 * parentHeight / 3;
		//为了让控件能全部显示出来，需要对画布进行缩放
		scale_height = (float)parentHeight / axis_before.getHeight();
		setMeasuredDimension(parentWidth, parentHeight);
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		//画之前先保存状态，然后缩放
		canvas.save();
		canvas.scale(scale_height, scale_height);
		//滑竿左边的x坐标(居中)
		int bg_x = (parentWidth-little_ball_width)/2;
		//绘制滑竿
		canvas.drawBitmap(axis_before, bg_x, 0, null);
		//绘制滑竿上小球旁的文本，先给出个string数组记录这5个文本
		String[] texts = new String[]{
			"无限",
			String.valueOf(FOURTH_LEVEL),
			String.valueOf(THIRD_LEVEL),
			String.valueOf(SECOND_LEVEL),
			String.valueOf(FIRST_LEVEL)
		};
		//文本的x坐标
		paint.setTextSize(20);
		paint.setColor(Color.BLACK);
		int text_x = bg_x*5/4;
		for(int i = 0; i < texts.length; i ++){
			//计算文本的y坐标需要计算每段区域的距离per_size
			//paint.descent() - paint.ascent()) / 2 - paint.descent()  这是文本基线的坐标
			int text_y = little_ball_width / 2 + (int)((paint.descent() - paint.ascent()) / 2 - paint.descent()) + i * per_size;
			canvas.drawText(texts[i], text_x, text_y, paint);
		}
		big_ball_x = (this.getWidth() / scale_height - big_ball.getWidth()) / 2;
		big_ball_up_y = getYByPrice(price_up);
		canvas.drawBitmap(big_ball, big_ball_x, big_ball_up_y - big_ball.getHeight() / 2, null);
		big_ball_down_y = getYByPrice(price_down);
		canvas.drawBitmap(big_ball, big_ball_x, big_ball_down_y - big_ball.getHeight() / 2, null);
		//绘制随大球跑动的矩形价格（文字和图片）
		Rect rect_up = getRectByY(big_ball_up_y);
		//第一个矩形是否需要裁剪，不需要就为null，第二个是需要画在哪个矩形里面
		canvas.drawBitmap(price_rect, null, rect_up, null);
		Rect rect_down = getRectByY(big_ball_down_y);
		canvas.drawBitmap(price_rect, null, rect_down, null);
		//绘制绿色滑竿的部分
		//裁剪部分的矩形
		Rect src = new Rect(0, (int)(big_ball_up_y + big_ball.getHeight() / 2), little_ball_width, (int)big_ball_down_y - big_ball.getHeight() / 2);
		//绘制到画布坐标系的矩形
		Rect dst = new Rect(bg_x, (int)(big_ball_up_y + big_ball.getHeight() / 2), (bg_x + little_ball_width), (int)(big_ball_down_y - big_ball.getHeight() / 2));
		canvas.drawBitmap(axis_after, src, dst, paint);
		//绘制矩形里面的价格文字
		float rect_text_up_x = (rect_up.width() * 3 / 4 - paint.measureText(String.valueOf(price_up))) / 2;
		float text_up_y = big_ball_up_y +(paint.descent() - paint.ascent()) / 2 - paint.descent();
		canvas.drawText(String.valueOf(price_up), rect_text_up_x, text_up_y, paint);
		float rect_text_down_x = (rect_up.width() * 3 / 4 - paint.measureText(String.valueOf(price_down))) / 2;
		float text_down_y = big_ball_down_y + (paint.descent() - paint.ascent()) / 2 - paint.descent();
		canvas.drawText(String.valueOf(price_down), rect_text_down_x, text_down_y, paint);
		//画完之后重置画布
		canvas.restore();
		super.onDraw(canvas);
	}

	/**
	 * 根据y坐标获取矩形
	 * @param y
	 * @return
	 */
	private Rect getRectByY(float y) {
		Rect rect = new Rect();
		rect.left = 0;
		rect.right = (int) big_ball_x;
		float text_h = paint.descent() - paint.ascent();
		rect.top = (int) (y - text_h / 2 - PADDING);
		rect.bottom = (int) (y + text_h / 2 + PADDING);
		return rect;
	}

	/**
	 * 根据价格算出可以拖动的大球的Y坐标
	 * @param price
	 * @return
	 */
	private float getYByPrice(int price) {
		float y;
		if(price > 10000){
			price = 10000;
		}
		if(price < 0){
			price = 0;
		}
		if(price <= FIFTH_LEVEL && price > FOURTH_LEVEL){
			y = per_size * (FIFTH_LEVEL - price) / (FIFTH_LEVEL - FOURTH_LEVEL) + little_ball_width / 2;
		}else if(price <= FOURTH_LEVEL && price > THIRD_LEVEL){
			y = per_size * (FOURTH_LEVEL - price) / (FOURTH_LEVEL - THIRD_LEVEL) + little_ball_width / 2 + per_size;
		}else if(price <= THIRD_LEVEL && price > SECOND_LEVEL){
			y = per_size * (THIRD_LEVEL - price) / (THIRD_LEVEL - SECOND_LEVEL) + little_ball_width / 2 + 2 * per_size;
		}else if(price <= SECOND_LEVEL && price > FIRST_LEVEL){
			y = per_size * (SECOND_LEVEL - price) / (SECOND_LEVEL - FIRST_LEVEL) + little_ball_width / 2 + 3 * per_size;
		}else{
			y = little_ball_width / 2 + 4 * per_size;
		}
		return y;
	}
	
	/**
	 * 根据有坐标获取价格
	 * @param y（坐标系y坐标，需要除以缩放的值，才是相对于画布的坐标）
	 * @return
	 */
	public int getPriceByY(float y){
		int price;
		if(y < little_ball_width / 2){
			y = little_ball_width / 2;
		}
		if(y >= little_ball_width / 2 + per_size * 4){
			y = little_ball_width / 2 + per_size * 4;
		}
		if(y >= little_ball_width /2 && y < little_ball_width /2 + per_size){
			price =  (int) (FIFTH_LEVEL - ((y - little_ball_width / 2) / per_size * (FIFTH_LEVEL - FOURTH_LEVEL)));
		}else if(y >=little_ball_width /2 + per_size && y< little_ball_width /2 + per_size * 2){
			price =  (int) (FOURTH_LEVEL - ((y - little_ball_width / 2 - per_size) / per_size * (FOURTH_LEVEL - THIRD_LEVEL)));
		}else if(y >= little_ball_width /2 + per_size * 2 && y < little_ball_width /2 + per_size * 3){
			price =  (int) (THIRD_LEVEL - ((y - little_ball_width / 2 - per_size * 2) / per_size * (THIRD_LEVEL - SECOND_LEVEL)));
		}else if(y >= little_ball_width /2 + per_size * 3 && y < little_ball_width /2 + per_size * 4){
			price =  (int) (SECOND_LEVEL - ((y - little_ball_width / 2 - per_size * 3) / per_size * (SECOND_LEVEL - FIRST_LEVEL)));
		}else{
			price = 0;
		}
		//对于价格进行刻度限制
		if(price < 1000){
			int mol = price % 20;
			if(mol >= 10){
				price += 20 - mol;
			}else{
				price -=mol; 
			}
		}else{
			int mol = price % 1000;
			if(mol >= 500){
				price += 1000 - mol;
			}else{
				price -=mol;
			}
		}
		return price;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			float press_x = event.getX() / scale_height;
			float press_y = event.getY() / scale_height;
			if(press_x >= big_ball_x && press_x <= big_ball_x + big_ball.getWidth()){
				if(price_up == 0 && price_down == 0){
					isUpPress = true;
					isDownPress = false;
				}else{
					if(press_y >= big_ball_up_y - big_ball.getHeight() / 2 && press_y <= big_ball_up_y + big_ball.getHeight() / 2){
						isUpPress = true;
						isDownPress = false;
					}
					if(press_y >= big_ball_down_y - big_ball.getHeight() / 2 && press_y <= big_ball_down_y + big_ball.getHeight() / 2){
						isUpPress = false;
						isDownPress = true;				
					}
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			float move_y = event.getY() / scale_height;
			if(isUpPress){
				price_up = getPriceByY(move_y);
				if(price_up <= price_down){
					price_up = price_down;
				}
			}
			if(isDownPress){
				price_down = getPriceByY(move_y);
				if(price_down >= price_up){
					price_down = price_up;
				}
			}
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			isUpPress = false;
			isDownPress = false;
			break;
			
		default:
			
			break;
		}
		return true;
	}

}
