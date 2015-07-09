package com.ssk.dividing.view;


import com.ssk.dividing.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 自定义滑竿控件
 * @author QQ565204031 杀死凯
 *
 */
public class DividingView extends View{

	//绿色背景
	private Bitmap bg_on;
    //灰色背景
	private Bitmap bg_off;
	//圆形按钮
	private Bitmap btn;
	//显示选中价格
	private Bitmap btn_number;
	//背景高度
	private int bg_height;
	//背景宽度
	private int bg_width;
	//画笔
	private Paint paint;
	//画布缩放
	private float zoom;
	//按钮X坐标
	private float btnx;
	//上面按钮Y坐标
	private float btn_up_y;
	//下面按钮Y坐标
	private float btn_down_y;
	//价格上限
	private int price_up;
	//价格下限
	private int price_down;
	//区域高度
	private int area_height;
	
	private boolean isup;
	private boolean isdown;
	//每个区最高价格
	private int firstprice;
	private int secondprice;
	private int thirdprice;
	private int fourthprice;
	private int fifthprice;
	//每个区前进刻度 默认20
	private int firstscale;
	private int secondscale;
	private int thirdscale;
	private int fourthscale;
	
	public DividingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//通过TypedArray解析xml里的属性（用自定义的属性文件）
		TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.DividingView);
		//默认 500,200
		price_up=a.getInt(R.styleable.DividingView_upprice,500);
		price_down=a.getInt(R.styleable.DividingView_downprice,200);
		//默认0,200,500,1000,10000
		firstprice=a.getInt(R.styleable.DividingView_firstprice,0);
		secondprice=a.getInt(R.styleable.DividingView_secondprice,200);
		thirdprice=a.getInt(R.styleable.DividingView_thirdprice,500);
		fourthprice=a.getInt(R.styleable.DividingView_fourthprice,1000);
		fifthprice=a.getInt(R.styleable.DividingView_fifthprice,10000);
		//刻度默认 20,20,20,1000
		firstscale=a.getInt(R.styleable.DividingView_firstscale,20);
		secondscale=a.getInt(R.styleable.DividingView_secondscale,20);
		thirdscale=a.getInt(R.styleable.DividingView_thirdscale,20);
		fourthscale=a.getInt(R.styleable.DividingView_fourthscale,1000);
		
		paint = new Paint();
		initBitmap();
	}

	/**
	 * 初始化图片
	 */
	private void initBitmap() {
		bg_on=BitmapFactory.decodeResource(getResources(), R.drawable.axis_after);
		bg_off=BitmapFactory.decodeResource(getResources(), R.drawable.axis_before);
		btn=BitmapFactory.decodeResource(getResources(), R.drawable.btn);
		btn_number=BitmapFactory.decodeResource(getResources(), R.drawable.bg_number);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//转换画布坐标
			float btn_x=event.getX()/zoom;
			float btn_y=event.getY()/zoom;
			if(btn_x>=btnx&&btn_x<=btnx+btn.getWidth()){
				//X坐标正确
				if(btn_y>=(btn_up_y-btn.getWidth()/2)&&btn_y<=(btn_up_y+btn.getWidth()/2)){
					//上面按钮选中
					isup=true;
					isdown=false;
				}
				if(btn_y>=(btn_down_y-btn.getWidth()/2)&&btn_y<=(btn_down_y+btn.getWidth()/2)){
					//下面按钮选中
					isdown=true;
					isup=false;
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			float y=event.getY()/zoom;
			if(isup){
				//上面按中
				if(btn_down_y-btn.getHeight()>y){
					price_up=getprice(y);
					if(price_up<=price_down){
						price_up = price_down;
					}
					this.invalidate();
				}
				Log.i("INFO", "上面按中"+price_up);
			}
			if(isdown){
				//下面按中
				if(y>(btn_up_y+btn.getHeight())){
					//两个按钮不能重叠
					price_down=getprice(y);
					if(price_down>=price_up){
						price_down = price_up;
					}
					this.invalidate();
				}
				Log.i("INFO", "下面按中"+price_down);
			}
			break;
		case MotionEvent.ACTION_UP:
			isdown=false;
			isup=false;
			break;
		default:
			break;
		}
		return true;
	}
	

	/**
	 * 测量宽高
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//获得控件宽高
		int sizeheight=MeasureSpec.getSize(heightMeasureSpec);
		int sizewidth=MeasureSpec.getSize(widthMeasureSpec);
		//获得控件宽高模式
		int modelheight=MeasureSpec.getMode(heightMeasureSpec);
		int modelwidth=MeasureSpec.getMode(widthMeasureSpec);
		
		int measuredHeight;//测量后高度
		int measuredWidth;//测量后宽度
		
		bg_height = bg_on.getHeight();//滑竿高度
		bg_width = bg_on.getWidth();//滑竿宽度
		
		if(MeasureSpec.EXACTLY==modelheight){
			//dp,fill_parent模式
			measuredHeight=sizeheight>bg_height?bg_height:sizeheight;//设置的高度不能超过图片的高度
		}else
		{
			//wrap_content模式
			measuredHeight=bg_height;//图片的宽度
		}
		measuredWidth=2*measuredHeight/3;//宽度是高度的3分之2
		zoom=(float)measuredHeight/bg_height;
		//区间高度
		area_height=(bg_height-bg_width)/4;
		setMeasuredDimension(measuredWidth,measuredHeight);
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	/**
	 * 绘制页面
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();//保存状态
		//缩放,宽度和高度是成比例的,所以缩放都一样。
		canvas.scale(zoom, zoom);
		//画背景图片
		int bg_left=(int) ((this.getWidth()/zoom-bg_width)/2);//控件宽度要转成画布宽度
		canvas.drawBitmap(bg_off, bg_left, 0, paint);
	
		//画上面按钮
		btn_up_y=getbtnY(price_up);
		btnx=(int)((this.getWidth()/zoom-btn.getWidth())/2);
		Log.i("INFO","btn_up_y="+btn_up_y);
		canvas.drawBitmap(btn, btnx,btn_up_y-btn.getWidth()/2, paint);
		
		//画下面按钮
		btn_down_y=getbtnY(price_down);
		canvas.drawBitmap(btn, btnx, btn_down_y-btn.getWidth()/2, paint);
		
		//画绿色的背景
		//绘画该图片需显示多少
		Rect src=new Rect(0, (int) btn_up_y+btn.getHeight()/2, bg_width, (int)btn_down_y-btn.getHeight()/2);
		//画布位置
		Rect dst=new Rect(bg_left, (int)btn_up_y+btn.getHeight()/2,(int)(bg_left+bg_width), (int)btn_down_y-btn.getHeight()/2);
		canvas.drawBitmap(bg_on, src, dst, paint);
		
		//画左边上面刻度值
		Rect topdst=new Rect(0, (int)btn_up_y-btn_number.getHeight()/2, (int)bg_left-bg_width/2,(int) btn_up_y+btn_number.getHeight()/2);
		
		canvas.drawBitmap(btn_number, null, topdst, paint);
		//画左边下面刻度值
		Rect downdst=new Rect(0, (int)btn_down_y-btn_number.getHeight()/2, (int)bg_left-bg_width/2,(int) btn_down_y+btn_number.getHeight()/2);
		
		canvas.drawBitmap(btn_number, null, downdst, paint);
		
		paint.setTextSize(20/zoom);//设置文本的画笔尺寸
		paint.setColor(getResources().getColor(R.color.white));
		
		//画左边上面价格
		float up_textheight=paint.descent()-paint.ascent();
		//测量文字宽度
		float up_textleft=(3*btn_number.getWidth()/4- paint.measureText(getprice(btn_up_y)+""))/2;
		
		canvas.drawText(getprice(btn_up_y)+"", up_textleft,btn_up_y+up_textheight/2-paint.descent(), paint);
		//画左边下面价格
		float down_textleft=(3*btn_number.getWidth()/4- paint.measureText(getprice(btn_up_y)+""))/2;
		float down_textheight=paint.descent()-paint.ascent();
		canvas.drawText(getprice(btn_down_y)+"", down_textleft,btn_down_y+down_textheight/2-paint.descent(), paint);
		
		
		//画价格刻度
		String[] items=new String[]{
			fifthprice+"",fourthprice+"",thirdprice+"",secondprice+"",firstprice+""
		};
		paint.setColor(getResources().getColor(R.color.black));
		for(int i=0;i<items.length;i++){
			float textheight=paint.descent()-paint.ascent();
			canvas.drawText(items[i],5*bg_left/4,i*area_height+bg_width/2+textheight/2-paint.descent(), paint);
		}
		//重置刚刚保存的状态
		canvas.restore();
		super.onDraw(canvas);
	}

	/**
	 * 根据价格算出按钮Y坐标
	 * @param price
	 * @return
	 */
	private float getbtnY(int price) {
		float y,percent;
		//大于最打，小于最小处理
		price=price>fifthprice?fifthprice:(price<firstprice?firstprice:price);
		if(price<=fifthprice&&price>fourthprice){
			//第一区域
			percent=area_height*(fifthprice-price)/(fifthprice-fourthprice);//价格在区域的百分比
			y=percent+bg_width/2;//转换成坐标
		}else if(price<=fourthprice&&price>thirdprice){
			//第二区域
			percent=area_height*(fourthprice-price)/(fourthprice-thirdprice);//价格在区域的百分比
			y=percent+bg_width/2+area_height;//转换成坐标
		}else if(price<=thirdprice&&price>secondprice){
			//第三区域
			percent=area_height*(thirdprice-price)/(thirdprice-secondprice);//价格在区域的百分比
			y=percent+area_height*2+bg_width/2;//转换成坐标
		}else if(price<=secondprice&&price>firstprice){
			//第四区域
			percent=area_height*(secondprice-price)/(secondprice-firstprice);//价格在区域的百分比
			y=percent+area_height*3+bg_width/2;//转换成坐标
		}else{
			//0
			y = 4*area_height+bg_width/2;
			percent=1;
		}
		Log.i("INFO","percent="+percent+"|price="+price);
		return y;
	}
	/**
	 * 根据Y坐标价格
	 * @param y
	 */
	private int getprice(float y) {
		int price,totalvalue;
		float percent;
		//超出范围处理
		y=y<bg_width/2?bg_width/2:(y>bg_width/2+4*area_height?bg_width/2+4*area_height:y);
		if(y>=bg_width/2&&y<bg_width/2+area_height*1){
			//第一区间
			
			//区间总价格
			totalvalue=fifthprice-fourthprice;//刻度
			//Y占区间的百分比
			percent=(y-bg_width/2)/area_height;
			//百分比转换成对应价格,百分比越小应该价格越高,所以要减
			price=fifthprice-(int) (totalvalue*percent);
		    
		}else if(y>=area_height*1&&y<bg_width/2+area_height*2){
			//第二区间
			
			//区间总价格
			totalvalue=fourthprice-thirdprice;//刻度
			//Y占区间的百分比
			percent=(y-bg_width/2-area_height*1)/area_height;
			//百分比转换成对应价格,百分比越小应该价格越高,所以要减
			price=fourthprice-(int) (totalvalue*percent);
		}else if(y>=area_height*2&&y<bg_width/2+area_height*3){
			//第三区间
			
			//区间总价格
			totalvalue=thirdprice-secondprice;//刻度
			//Y占区间的百分比
			percent=(y-bg_width/2-area_height*2)/area_height;
			//百分比转换成对应价格,百分比越小应该价格越高,所以要减
			price=thirdprice-(int) (totalvalue*percent);
		}else if(y>=area_height*3&&y<bg_width/2+area_height*4){
			//第四区间
			
			//区间总价格
			totalvalue=secondprice-firstprice;//刻度
			//Y占区间的百分比
			percent=(y-bg_width/2-area_height*3)/area_height;
			//百分比转换成对应价格,百分比越小应该价格越高,所以要减
			price=secondprice-(int) (totalvalue*percent);
		}else{
			price=0;
		}
		//根据不同区间,刻度不一样
		int mol;
		if(price<fifthprice&&price>=fourthprice){
			//第一区间
			mol = price%fourthscale;
			if(mol>=fourthscale/2){
				price = price-mol+fourthscale;
			}else{
				price = price - mol;
			}
		}else if(price<fourthprice&&price>=thirdprice){
			//第二区间
			mol = price%thirdscale;
			if(mol>=thirdscale/2){
				price = price-mol+thirdscale;
			}else{
				price = price - mol;
			}
		}else if(price<thirdprice&&price>=secondprice){
			//第三区间
			mol = price%secondscale;
			if(mol>=secondscale/2){
				price = price-mol+secondscale;
			}else{
				price = price - mol;
			}
		}else if(price<secondprice&&price>=firstprice){
			//第四区间
			mol = price%firstscale;
			if(mol>=firstscale/2){
				price = price-mol+firstscale;
			}else{
				price = price - mol;
			}
		}
		return price;
	}
}
