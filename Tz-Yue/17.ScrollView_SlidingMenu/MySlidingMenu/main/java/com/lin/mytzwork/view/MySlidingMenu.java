package com.lin.mytzwork.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * 侧滑菜单:
 * 思路：
 * onMeasure  测量子控件    根据屏幕宽度给菜单设置宽度  内容容器的宽度
 * onLayout 设置子控件的位置    指定第一次进来 显示内容容器
 * onScrollChanged  滑动监听
 * -----------------滑动HorizontalScrollView 回调该方法，拿到屏幕显示与控件原点的距离 scale
 * -----------------根据scale与菜单的宽度mMenuWidth的比例 设置 菜单容器、内容容器 的属性动画
 * onTouchEvent  滑动监听
 * --------------当滑动HorizontalScrollView抬起时：判断屏幕显示与控件原点的距离 是否大于 菜单宽度的一半，
 * --------------是：显示内容容器 隐藏菜单
 * --------------否：隐藏内容容器 显示惨淡
 */
public class MySlidingMenu extends HorizontalScrollView {
    private int widthPixls;//屏幕宽度
    private int mMenuWidth; //菜单宽度


    private ViewGroup mMenu;
    private ViewGroup mContent;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int x = (int) msg.obj;
            MySlidingMenu.this.smoothScrollTo(x, 0);
        }
    };

    public MySlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.setHorizontalScrollBarEnabled(false); //隐藏滚动条
        this.setHorizontalFadingEdgeEnabled(false);//侧边去阴影

        //计算屏幕宽
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        widthPixls = displayMetrics.widthPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LinearLayout ll = (LinearLayout) this.getChildAt(0);//最大的一个子控件
        mMenu = (ViewGroup) ll.getChildAt(0); //菜单容器
        mContent = (ViewGroup) ll.getChildAt(1); //内容容器

        /*设置菜单宽度*/
        mMenuWidth = (int) (0.8 * widthPixls);//最小值
        mMenu.getLayoutParams().width = (int) (widthPixls * 0.8); //设置菜单容器宽度
        mContent.getLayoutParams().width = widthPixls; //设置内容容器宽度
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (changed) {
            this.scrollTo(mMenuWidth, 0);//默认滑倒内容容器
        }
    }

    @Override
    protected void onScrollChanged(int left, int top, int oldLeft, int oldTop) {
        /**
         *  int left,  原点到屏幕原点的宽度距离
         *  int top,   原点到屏幕原点的高度距离
         *  int oldLeft,  改变前宽度
         *  int oldTop    改变钱的高度
         */
        float scale = (float) left / mMenuWidth; //相对于菜单的变换百分比 0 ~ 1


        /**
         * 菜单平移属性动画
         * setTranslationX(View view, float translationX)
         * view 指那个控件
         * translationX  指当前控件离控件原点的距离
         */
        ViewHelper.setTranslationX(mMenu, left * 0.7f);


        /**
         * 菜单缩放属性动画
         * leftScale变化1~0.7
         */
        float leftScale = 1 - scale * 0.3f;
        ViewHelper.setScaleX(mMenu, leftScale);
        ViewHelper.setScaleY(mMenu, leftScale);

        /**
         * 菜单透明度属性动画
         * 菜单透明度变化1~0.3
         */
        float leftAlpha = 1 - scale * 0.7f;
        ViewHelper.setAlpha(mMenu, leftAlpha);

        /**
         * mContent缩放属性动画
         * mContent变化 0.8~1.0
         */
        float rightScale = 0.8f + 0.2f * scale;
        ViewHelper.setScaleX(mContent, rightScale);
        ViewHelper.setScaleY(mContent, rightScale);


        super.onScrollChanged(left, top, oldLeft, oldTop);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_UP) {
            float scaleX = this.getScrollX(); //获得显示在屏幕的位置距离HoriontalScrolliew的原点的距离
            int scale = 0;
            if (scaleX > mMenuWidth / 2) { //超过菜单一半  就滑动到菜单的位置
                scale = mMenuWidth;
            }
            Message message = handler.obtainMessage();
            message.obj = scale;
            handler.sendMessage(message);
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 弹出菜单
     *
     * @param display
     */
    public void menuDisplay(boolean display) {
        Message message = handler.obtainMessage();
        message.obj = display ? 0 : mMenuWidth;
        handler.sendMessage(message);
    }

    /**
     * 当前是否显示菜单
     *
     * @return
     */
    public boolean menuIsDisplay() {
        boolean isDisplay = false;
        float scaleX = this.getScrollX();
        if (scaleX < mMenuWidth / 2) { //超过菜单一半  就滑动到菜单的位置
            isDisplay = true;
        }
        return isDisplay;
    }


}
