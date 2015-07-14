package com.lin.mytzwork;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;
import android.widget.Spinner;

/**
 * 精灵类  游戏中所有的角色 都是精灵
 */
public class Spirit implements Cloneable {
    private float x;
    private float y;
    private float xSpeed;
    private float ySpeed;
    private int wdith;
    private int height;

    private Bitmap bmp;
    private Bitmap[] bmps;

    private long lastTime;
    private int index;


    public Spirit(Bitmap bmp) {
        this.bmp = bmp;
        this.wdith = bmp.getWidth();
        this.height = bmp.getHeight();
    }

    public Spirit(Bitmap[] bmps) {
        this.bmps = bmps;
        this.bmp = bmps[0];
        this.wdith = bmp.getWidth();
        this.height = bmp.getHeight();
    }

    public Bitmap[] getBmps() {
        return bmps;
    }

    public void setBmps(Bitmap[] bmps) {
        this.bmps = bmps;

        if (bmps == null || bmps.length == 0) {
            return;
        }
        this.bmp = bmps[0];
        this.wdith = bmp.getWidth();
        this.height = bmp.getHeight();
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
        this.wdith = bmp.getWidth();
        this.height = bmp.getHeight();
        Log.d("debug", "height" + height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWdith() {
        return wdith;
    }

    public void setWdith(int wdith) {
        this.wdith = wdith;
    }

    public float getY() {
        return y;
    }


    public void setXY(float x, float y) {
        this.y = y;
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    /**
     * 拿到下一张图片
     *
     * @param span
     */
    public void nextBitmap(long span) {
        if (bmps == null || bmps.length == 0) {
            return;
        }

        if (System.currentTimeMillis() - lastTime >= span) {
            index++;
            lastTime = System.currentTimeMillis();
            bmp = bmps[index % bmps.length];
        }
    }

    /**
     * 绘制当前的精灵
     *
     * @param canvas
     */
    public void drawSelf(Canvas canvas) {
        nextBitmap(50);
        canvas.drawBitmap(bmp, x, y, null);
    }


    public Spinner getClongMe() {
        try {
            return (Spinner) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取该精灵的矩形
     */
    public RectF getSpiritRect() {
        return new RectF(this.getX(), this.getY(), this.getX() + this.getWdith(), this.getY() + this.getHeight());
    }


}
