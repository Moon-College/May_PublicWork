package com.lin.mytzwork.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lin.mytzwork.R;
import com.lin.mytzwork.util.L;

/**
 * 介绍：两个点  可以滑动两点来设置价格 也可以通过价格来设置 两点的距离
 * <p/>
 * 原理： 通过draw绘制背景 文字  还有 移动的点  通过监听onTouch事件来移动两个点
 * <p/>
 * 难点：画布坐标系  屏幕坐标系的 转换：
 * 背景图比较大,已经完全超出了屏幕所显示，并且因为手机的分辨率不同所以屏幕坐标系 跟 画布坐标系 有区别
 * 解决方案：明白显示 用户操作 都是用的是屏幕坐标  一旦 要在画布上操作都要转到画布坐标系
 * 先得到 屏幕高 与 画布上的高 得到 比例  每次从屏幕获得坐标就/比例得到在画布上实际的长度
 * <p/>
 * 文字绘制到指定地方：
 * 文字绘制的时候不是以左上角的原点为y轴的原点 而是 在文字中有一个 叫做 基线的位置 绘制的时候 就会以基线作为Y轴的位置
 * 以基线为原点坐标，基线以上到文字顶部 的宽度为-Ascent 基线一下到文字底部  Descent
 * <p/>
 * 如果要想要文字 居中  就需要 得到文字高度/2-Descent 再加上原来的位置就把文字居中了
 */
public class PriceView extends View {
    private Bitmap gray_bg;//灰色的背景滑竿
    private Bitmap green_bg;//绿色的背景滑竿
    private Bitmap num_price;//左边价格绿色背景
    private Bitmap btn; //滑动的点

    private Paint paint;

    private final int FIRST_STAGE = 0;
    private final int SECOND_STAGE = 200;
    private final int THIRD_STAGE = 500;
    private final int FOURTH_STAGE = 1000;
    private final int FIFTH_STAGE = 10000;

    //默认两个移动点的位置
    private int price_up = 1000;
    private int price_down = 100;

    private boolean isUpTouched = false;
    private boolean isDownTouched = false;

    private int bg_height;//滑竿的高度
    private int bg_width;//滑竿的高度，球的高度
    private int span_state;//每一个等级的区域高度
    private float scale_h; //显示的长度与背景的长度的比例
    private float bg_x; //滑竿的x坐标
    private float y_up; //up点对应的中心Y坐标
    private float y_down; //down对应中心Y坐标


    public PriceView(Context context) {
        super(context);
        price_up = 1000;
        price_down = 20;
        init();
    }


    public PriceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PriceView);
        price_up = array.getInteger(R.styleable.PriceView_maxMoney, 600);
        price_down = array.getInteger(R.styleable.PriceView_minMoney, 200);

        L.i("info", price_down + " " + price_up);
        array.recycle();
    }

    private void init() {
        green_bg = BmpForRes(R.mipmap.axis_after);
        gray_bg = BmpForRes(R.mipmap.axis_before);
        btn = BmpForRes(R.mipmap.btn);
        num_price = BmpForRes(R.mipmap.bg_number);

        paint = new Paint();
        paint.setColor(Color.GRAY);
    }

    private Bitmap BmpForRes(int id) {
        return BitmapFactory.decodeResource(getResources(), id);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);//父容器指定的宽
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);//父容器指定的高

        bg_height = green_bg.getHeight();//背景的高度
        bg_width = green_bg.getWidth();//背景的宽度
        span_state = (int) (((float) bg_height - bg_width) / 4);//区间的宽度 因为背景图的小球的直径就是背景的宽度 所以-宽度 /4
        int measuredHeight = (heightMode == MeasureSpec.EXACTLY) ? heightSize : bg_height; //具体大小？就用控件大小：背景
        measuredHeight = Math.min(measuredHeight, heightSize);//测量高度不应该大于父容器高度
        int measuredWidth = 2 * measuredHeight / 3;
        scale_h = (float) measuredHeight / bg_height;
        setMeasuredDimension(measuredWidth, measuredHeight);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        /*缩放画布*/
        canvas.save();//保存画布状态
        canvas.scale(scale_h, scale_h);//进行缩放


        //////////////////开始绘制内容/////////////////
        //计算滑竿的x坐标
        bg_x = (getWidth() / scale_h - bg_width) / 2;
        canvas.drawBitmap(gray_bg, bg_x, 0, paint);


        //绘制右边的5个文本
        String[] numbers = new String[]{
                String.valueOf(FIFTH_STAGE),    //10000
                String.valueOf(FOURTH_STAGE),   //1000
                String.valueOf(THIRD_STAGE),    //500
                String.valueOf(SECOND_STAGE),   //200
                String.valueOf(FIRST_STAGE)     //100
        };
        paint.setTextSize(20 / scale_h);//设置文本的画笔尺寸


        for (int i = 0; i < numbers.length; i++) {
            int text_x = (int) (5 * bg_x / 4);
            /**
             * i是第几个区间
             * span_state区间高度
             * bg_width是小球的直径（因为有半个后才开始的区间所以-半个小球）
             * (paint.descent() - paint.ascent()) / 2 - paint.descent()
             * Y轴本来在基线(BaseLine)位置，为了让文字上下居中，所以要把Y轴向下挪文字的一半-一个descent长度
             */
            int text_y = (int) (i * span_state + bg_width / 2 + (paint.descent() - paint.ascent()) / 2 - paint.descent());
            canvas.drawText(numbers[i], text_x, text_y, paint);
        }


        //画两个移动点
        y_up = getBtnYByPrice(price_up);

        float x_up = (getWidth() / scale_h - btn.getWidth()) / 2;
        canvas.drawBitmap(btn, x_up, y_up - btn.getHeight() / 2, paint);


        y_down = getBtnYByPrice(price_down);
        canvas.drawBitmap(btn, x_up, y_down - btn.getHeight() / 2, paint);

        //画左边的价格
        canvas.drawBitmap(num_price, x_up - num_price.getWidth(), y_up - num_price.getHeight() / 2, paint);
        canvas.drawBitmap(num_price, x_up - num_price.getWidth(), y_down - num_price.getHeight() / 2, paint);

        //绘制左边的价格
        float num_y = y_up - num_price.getHeight() / 2 + (num_price.getHeight() - paint.descent() - paint.ascent()) / 2;
        float num_x = x_up - num_price.getWidth() + (num_price.getWidth() * 3 / 4 - paint.measureText(String.valueOf(price_up))) / 2;
        canvas.drawText(String.valueOf(price_up), num_x, num_y, paint);

        num_y = y_down - num_price.getHeight() / 2 + (num_price.getHeight() - paint.descent() - paint.ascent()) / 2;
        num_x = x_up - num_price.getWidth() + (num_price.getWidth() * 3 / 4 - paint.measureText(String.valueOf(price_down))) / 2;
        canvas.drawText(String.valueOf(price_down), num_x, num_y, paint);


        //画绿色区域的拉杆
        /*如果价格重叠，说明两点重合了，所以就就不需要显示绿色部分*/
        if (price_down != price_up) {
            /**
             * 原理：绿色滑竿 和 白色滑竿 其实是一样的。
             * 之所以可以显示绿色的滑竿，是因为我们可以绘制画中画
             * 显示滑竿的一部分。
             *
             *子视图是以原图作为坐标系的原点的
             *
             */
            Rect src = new Rect(0, (int) (y_up + btn.getHeight() / 2), bg_width, (int) (y_down - btn.getHeight() / 2));
            Rect dst = new Rect((int) bg_x, (int) (y_up + btn.getHeight() / 2), (int) (bg_x + bg_width), (int) (y_down - btn.getHeight() / 2));
            canvas.drawBitmap(green_bg, src, dst, paint);
        }


        /*完成绘制以后 重置到 保存的状态*/
        canvas.restore();//重置
        super.onDraw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x1 = event.getX() / scale_h;
                float y1 = event.getY() / scale_h;
                //按下的X点 >= 移动点图形的左边 && 按下的X点 <= 移动图形的最右边
                if (x1 >= (getWidth() / scale_h - btn.getWidth()) / 2 && x1 <= (getWidth() / scale_h + btn.getWidth()) / 2) {
                    if (y1 >= y_up - btn.getHeight() / 2 && y1 <= y_up + green_bg.getHeight() / 2) {
                        isUpTouched = true;
                        isDownTouched = false;
                    }


                    if (y1 >= y_down - btn.getHeight() / 2 && y1 <= y_down + green_bg.getHeight() / 2) {
                        isUpTouched = false;
                        isDownTouched = true;
                    }


                    /**
                     * 两个点重合  不知道下一次点击 到底是 移动谁 所以全部放开
                     */
                    if (price_down == price_up) {
                        isUpTouched = true;
                        isDownTouched = true;
                    }


                }
                break;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY() / scale_h;
                int priceByY = getPriceByY(y);

                /**
                 * 两个移动都打开，说明现在两点重叠
                 * 所以根据移动的方向来判断到底是移动 up  还是 down
                 */
                if (isDownTouched && isUpTouched) {
                    if (priceByY > price_down) {
                        isUpTouched = true;
                        isDownTouched = false;
                    }
                    if (priceByY < price_down) {
                        isUpTouched = false;
                        isDownTouched = true;
                    }
                    break;
                }


                if (isDownTouched) {
                    /**
                     * 当前下面的按钮 的价格 如果>=上面的按钮的价格 就 让上到下的价格一致
                     */
                    price_down = priceByY;
                    if (price_down >= price_up) {
                        price_down = price_up;
                    }
                }

                if (isUpTouched) {
                    price_up = priceByY;
                    if (price_up <= price_down) {
                        price_up = price_down;
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                isUpTouched = false;
                isDownTouched = false;
                break;
        }

        invalidate();
        return true;
    }

    /**
     * 根据价格来计算移动点的Y坐标
     *
     * @param price
     * @return
     */
    private float getBtnYByPrice(int price) {
        float y;
        if (price < 0) {
            price = 0;
        }
        if (price > 10000) {
            price = 10000;
        }
        if (price <= FIFTH_STAGE && price > FOURTH_STAGE) {
            y = span_state * (FIFTH_STAGE - price) / (FIFTH_STAGE - FOURTH_STAGE) + bg_width / 2;
        } else if (price <= FOURTH_STAGE && price > THIRD_STAGE) {
            y = span_state * (FOURTH_STAGE - price) / (FOURTH_STAGE - THIRD_STAGE) + bg_width / 2 + span_state;
        } else if (price <= THIRD_STAGE && price > SECOND_STAGE) {
            y = span_state * (THIRD_STAGE - price) / (THIRD_STAGE - SECOND_STAGE) + bg_width / 2 + 2 * span_state;
        } else if (price <= SECOND_STAGE && price > FIRST_STAGE) {
            y = span_state * (SECOND_STAGE - price) / (SECOND_STAGE - FIRST_STAGE) + bg_width / 2 + 3 * span_state;
        } else {
            y = 4 * span_state + bg_width / 2;
        }
        return y;
    }

    /**
     * 根据y坐标来获取价格*Y坐标指的画布的Y坐标
     */
    public int getPriceByY(float y) {
        int price;
        if (y < bg_width / 2) {
            y = bg_width / 2;
        }
        if (y > (bg_width / 2 + 4 * span_state)) {
            y = bg_width / 2 + 4 * span_state;
        }
        if (y >= bg_width / 2 && y < bg_width / 2 + span_state) {
            //1000-10000
            price = (int) (FIFTH_STAGE - (FIFTH_STAGE - FOURTH_STAGE) * (y - bg_width / 2) / span_state);
        } else if (y >= bg_width / 2 + span_state && y < bg_width / 2 + 2 * span_state) {
            price = (int) (FOURTH_STAGE - (FOURTH_STAGE - THIRD_STAGE) * (y - bg_width / 2 - span_state) / span_state);
        } else if (y >= bg_width / 2 + 2 * span_state && y < bg_width / 2 + 3 * span_state) {
            price = (int) (THIRD_STAGE - (THIRD_STAGE - SECOND_STAGE) * (y - bg_width / 2 - 2 * span_state) / span_state);
        } else if (y >= bg_width / 2 + 3 * span_state && y < bg_width / 2 + 4 * span_state) {
            price = (int) (SECOND_STAGE - (SECOND_STAGE - FIRST_STAGE) * (y - bg_width / 2 - 3 * span_state) / span_state);
        } else {
            price = 0;
        }

        //对于价格需要进行刻度的最小限制
        if (price <= 1000) {
            int mol = price % 20;
            if (mol >= 10) {
                price = price - mol + 20;
            } else {
                price = price - mol;
            }
        }
        if (price > 1000) {
            int mol = price % 1000;
            if (mol >= 500) {
                price = price - mol + 1000;
            } else {
                price = price - mol;
            }
        }
        return price;
    }
}
