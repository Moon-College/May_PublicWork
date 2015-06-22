package com.tz.gesture;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnTouchListener {
    public static final int SINGLE_POINTER = 1;
    private ImageView imageView;
    private Matrix matrix;
    float startX = 0f;
    float startY = 0f;
    float mDistance;
    float degree = 0f;
    private Point mCenterPoint;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        imageView = (ImageView) findViewById(R.id.iv);
        imageView.setImageResource(R.drawable.ani);
        matrix = new Matrix();
        imageView.setImageMatrix(matrix);
        imageView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        if (pointerCount == SINGLE_POINTER) {

            moveImage(motionEvent);
        } else {
            scaleImage(motionEvent);
            rotateImage(motionEvent);
        }
        return true;
    }

    private void scaleImage(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case MotionEvent.ACTION_POINTER_DOWN:
                mDistance = computeDistance(motionEvent, true);
                break;
            case MotionEvent.ACTION_MOVE:
                float tempDistance = computeDistance(motionEvent, false);
                float scaleRatio = tempDistance / mDistance;
                matrix.postScale(scaleRatio, scaleRatio, mCenterPoint.x, mCenterPoint.y);
                imageView.setImageMatrix(matrix);
                mDistance = tempDistance;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                startX = 0f;
                startY = 0f;
                break;
        }
    }

    private void rotateImage(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case MotionEvent.ACTION_POINTER_DOWN:
                float xOne = motionEvent.getX();
                float yOne = motionEvent.getY();
                float xTwo = motionEvent.getX(1);
                float yTwo = motionEvent.getY(1);
                mDistance = computeDistance(motionEvent, true);
                degree = (float)(Math.atan2((yTwo - yOne) , (xTwo-xOne))/2d*Math.PI)*360f ;
                break;
            case MotionEvent.ACTION_MOVE:
                float yMoveOne = motionEvent.getY();
                float yMoveTwo = motionEvent.getY(1);
                float xMoveOne = motionEvent.getX();
                float xMoveTwo = motionEvent.getX(1);
                float tempDegree = (float) (Math.atan2((yMoveTwo - yMoveOne), (xMoveTwo - xMoveOne))/2d*Math.PI)*360f;
                matrix.postRotate(-0.2f*(degree - tempDegree), mCenterPoint.x, mCenterPoint.y);
                imageView.setImageMatrix(matrix);
                degree = tempDegree;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                startX = 0f;
                startY = 0f;
                break;
        }
    }

    private float computeDistance(MotionEvent motionEvent, boolean isSetCenter) {
        float xOne = motionEvent.getX();
        float yOne = motionEvent.getY();
        float xTwo = motionEvent.getX(1);
        float yTwo = motionEvent.getY(1);
        if (isSetCenter) {
            float centerX = (xOne + xTwo) / 2;
            float centerY = (yOne + yTwo) / 2;
            mCenterPoint = new Point((int) centerX, (int) centerY);
        }
        float xDistance = xTwo - xOne;
        float yDistance = yTwo - yOne;
        return (float) Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    private void moveImage(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = motionEvent.getX();
                startY = motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (startX != 0f) {
                    float xDistance = startX - motionEvent.getX();
                    float yDistance = startY - motionEvent.getY();
                    matrix.postTranslate(-xDistance, -yDistance);
                    startX = motionEvent.getX();
                    startY = motionEvent.getY();
                    imageView.setImageMatrix(matrix);
                }
                break;
            case MotionEvent.ACTION_UP:
                startY = motionEvent.getY();
                startX = motionEvent.getX();
                break;
        }
    }
}
