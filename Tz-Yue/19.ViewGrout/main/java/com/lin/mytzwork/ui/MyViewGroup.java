package com.lin.mytzwork.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.lin.mytzwork.R;

/**
 * Created by Administrator on 2015/7/4.
 */
public class MyViewGroup extends ViewGroup {

    ///////////////////容器的构造方法/////////////////////
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    ////////////////////////测量控件的大小///////////////////////////
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    ///////////////////确定子控件的位置////////////////////////////

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != GONE) {
                LayoutParams ly = (LayoutParams) childAt.getLayoutParams();

                int childLeft = (int) (ly.fromX * this.getMeasuredWidth() + this.getPaddingLeft() - childAt.getMeasuredWidth()/2);
                int childTop = (int) (ly.fromY * this.getMeasuredHeight() + this.getPaddingTop() - childAt.getMeasuredHeight()/2);
                int childRight = childLeft + childAt.getMeasuredWidth();
                int childBottom = childTop + childAt.getMeasuredHeight();

                childAt.layout(childLeft, childTop, childRight, childBottom);
            }
        }
    }

    /////////////////////////获得父容器LayoutPerams的generate方法/////////////////////////////////

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyViewGroup.LayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new MyViewGroup.LayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MyViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0);
    }
    //////////////////////本容器特有的LayoutPerams//////////////////////////////

    public static class LayoutParams extends ViewGroup.LayoutParams {

        /*本容器特有的参数，可以被设置到子控件*/
        public float fromX;
        public float fromY;

        private LayoutParams(int width, int height, float fromX, float fromY) {
            super(width, height);
            this.fromX = fromX;
            this.fromY = fromY;
        }


        /////////////////////////这里的三个LayoutPerams构造方法跟上面的generate想对应///////////////////////////

        /**
         * 这里是从自定义参数属性拿到 fromX fromY
         *
         * @param c
         * @param attrs
         */
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.MyViewGroup);
            this.fromX = array.getFloat(R.styleable.MyViewGroup_fromX, 0);
            this.fromY = array.getFloat(R.styleable.MyViewGroup_fromY, 0);
            array.recycle();
        }

        /**
         * 这里是 new 这个容器的构造
         *
         * @param width
         * @param height
         */
        public LayoutParams(int width, int height) {
            super(width, height);
        }

        /**
         * 这里是用使用设置任何参数 需要generateDefaultLayoutParams传入默认的LayoutPerams
         *
         * @param source
         */
        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}



