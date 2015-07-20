package com.xiaowei.double_slider_view.custom;
/**
 * 双向滑竿
 */
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.xiaowei.double_slider_view.R;

public class DoubleSliderView extends View {
	private Bitmap grayBg;//灰色滑竿背景
	private Bitmap greenBg;//绿色滑竿背景
	private Bitmap leftPriceBg;//左边的价格背景
	private Bitmap priceBg;//价格饼背景
	private int priceArea[] = new int[]{0,200,500,1000,10000};//右侧的价格区间段
	private int areaHeight;//每个价格区段的高度
	private Paint paint;//画笔
	private int bgWidth;//灰色滑竿宽度
	private int bgHeight;//灰色滑竿高度
	private float scale_h;//缩放比例(高度来缩放,宽度是由高度来决定的)
	private int upPrice;//上限价格
	private int downPrice;//下限价格(需要自定义属性来赋值)
	private float priceX;//大饼的X坐标
	private final int TEXT_PANDDING = 30;
	public DoubleSliderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//获取自定义属性
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DoubleSliderView);
		upPrice = a.getInt(R.styleable.DoubleSliderView_upPrice,1000);
		downPrice = a.getInt(R.styleable.DoubleSliderView_downPrice, 200);
		grayBg = getBitmapFromResource(R.drawable.axis_before);
		greenBg = getBitmapFromResource(R.drawable.axis_after);
		leftPriceBg = getBitmapFromResource(R.drawable.bg_number);
		priceBg = getBitmapFromResource(R.drawable.btn);
		paint = new Paint();//画笔
		paint.setColor(Color.RED);//画笔颜色为灰色
	}
	/**
	 * 从drawable资源中获取bitmap
	 * @param id
	 */
	private Bitmap getBitmapFromResource(int id) {
		return BitmapFactory.decodeResource(getResources(),id);
	}
	/**
	 * 自定义布局的测量
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);//父容器指定的宽
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);//父容器指定的高
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		bgWidth = grayBg.getWidth();//灰色滑竿宽度
		bgHeight = grayBg.getHeight();//灰色滑竿高度
		areaHeight = (bgHeight - bgWidth)/4;//每个价格区间段高度
		//先考虑高度的大小取值
		//父元素决定子控件的大小,子元素控件的大小受其父元素的控制
		int measureHeight = (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : bgHeight;
		measureHeight = Math.min(measureHeight, sizeHeight);//取最小值给子控件的高,以防万一越屏
		int measureWidth = measureHeight / 3;//取高度的1/3赋值给宽度
		scale_h = (float)measureHeight / bgHeight;
		setMeasuredDimension(measureWidth, measureHeight);
	}
	/**
	 * 绘制里面的内容
	 */
	@Override
	protected void onDraw(Canvas canvas) { 
		canvas.save();//保存当前画布状态,存放在私有的栈里面
		//缩放画布(对宽和高进行缩放)
		canvas.scale(scale_h, scale_h);
		//开始绘画
		//计算灰色滑竿的x坐标
		int bg_x = (int) ((this.getWidth()/scale_h-bgWidth)/2);
		//绘制灰色滑竿
		canvas.drawBitmap(grayBg, bg_x, 0, paint);
		//绘制灰色滑竿右侧的文本
		paint.setTextSize(30 / scale_h);//画笔笔触大小
		for(int i = 0 ; i < priceArea.length ; i ++){
			float text_x = (float) (bg_x * 1.6);//x坐标
			float text_y = bgWidth / 2 + areaHeight * i + (paint.descent() -paint.ascent())/2 - paint.descent();//Y轴坐标
			//开始绘制文本
			canvas.drawText(String.valueOf(priceArea[priceArea.length - 1 -i]),text_x,text_y,paint);
		}
		priceX = ( this.getWidth()/scale_h - priceBg.getWidth() )/ 2;
		float upPriceY = getPriceYbyPrice(upPrice);//根据价格来获取Y坐标(上限)
		canvas.drawBitmap(priceBg, priceX, upPriceY - priceBg.getWidth() / 2, paint);
		float downPriceY = getPriceYbyPrice(downPrice);//根据价格来获取Y坐标(下限)
		canvas.drawBitmap(priceBg, priceX, downPriceY - priceBg.getWidth() / 2 ,paint);
		//绘制绿色滑竿
		Rect upPriceRect = getRectByY(upPriceY);
		canvas.drawBitmap(leftPriceBg,null, upPriceRect, paint);//绘制上限价格的矩形,第二个参数表示是否需要裁剪,传null就可以了
		Rect downPriceRect = getRectByY(downPriceY);
		canvas.drawBitmap(leftPriceBg,null,downPriceRect, paint);//绘制下限价格的矩形
		//绘制绿色滑竿上的文字
		float text_up_x = (3*upPriceRect.width()/4 - paint.measureText(String.valueOf(upPrice))) / 2;
		float text_up_y = upPriceY + (paint.descent() - paint.ascent()) / 2 - paint.descent();//基线的坐标计算
		canvas.drawText(String.valueOf(upPrice),text_up_x,text_up_y, paint);
		float text_down_x = (3*downPriceRect.width()/4 - paint.measureText(String.valueOf(downPrice)))/2;
		float text_down_y = downPriceY + (paint.descent() - paint.ascent()) / 2 - paint.descent();//基线的坐标计算
		canvas.drawText(String.valueOf(downPrice),text_down_x,text_down_y, paint);
		//完成绘制之后重绘
		canvas.restore();//还原画布的缩放前的状态
		super.onDraw(canvas);
	}
	/**
	 * 根据Y坐标获取一个矩形
	 * @param upPriceY
	 */
	private Rect getRectByY(float y) {
		Rect rect = new Rect();
		rect.left = 0;//左
		rect.right = (int) priceX;//右
		float textHeight = paint.descent() - paint.ascent();
		rect.top =(int) (y - textHeight /2 - TEXT_PANDDING);
		rect.bottom = (int)(y + textHeight / 2 + TEXT_PANDDING);
		return rect;
	}
	/**
	 * 根据价格来算出价格所在的Y坐标
	 * @param price
	 * @return
	 */
	private float getPriceYbyPrice(int price) {
		//分区间来计算
		float y =0;//默认的y坐标
		//处理越界业务逻辑
		if (price > 10000) {
			price = 10000;
		}
		if (price < 0) {
			price = 0 ;
		}
		if (price<=10000 && price > 1000) {
			y= areaHeight * (10000-price) / (10000 - 1000) + bgWidth /2 ;
		}else if(price <= 1000 && price > 500){
			y= areaHeight * (1000-price) / (1000 - 500) + bgWidth /2 + areaHeight * 1 ;
		}else if(price <= 500 && price > 200){
			y = areaHeight * (500 - price) / (500 - 200) + bgWidth / 2  + areaHeight * 2;
		}else if(price <= 200 && price > 0){
			y = areaHeight * (200 - price) / (200 - 0) + bgWidth / 2 +areaHeight * 3 ;
		}else{
			y = areaHeight * 4 + bgWidth / 2;
		}
		return y;
	}
}
