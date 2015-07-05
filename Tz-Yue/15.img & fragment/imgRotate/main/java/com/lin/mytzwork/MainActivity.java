package com.lin.mytzwork;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * 以图片的中心点为中心，跟着手指移动旋转
 * <p/>
 * 原理:设置图片的矩阵
 * 监听onTouch事件。
 * 以图片中心为为坐标系原点，得到手指按下的x y 位置 得到正切得到角度
 * <p/>
 * 得到移动时手指的角度，然后设置到矩阵中
 */
public class MainActivity extends Activity implements View.OnTouchListener {
    private ImageView imageView;
    private Matrix matrix;

    private float startX;
    private float startY;
    private double fromDegree;//手指所在的角度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) this.findViewById(R.id.iv);
        matrix = new Matrix();
        imageView.setImageMatrix(matrix);
        imageView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                //获取该点与中心点的dy和dx
                float dx = startX - width / 2;
                float dy = height / 2 - startY;
                float atan = (float) Math.atan2(dy, dx); //得到正切值
                fromDegree = atan * 180 / Math.PI; //正切转换为角度
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                //计算当前moving过程中的角度
                float moving_dx = x - width / 2;
                float moving_dy = height / 2 - y;
                float moving_atan = (float) Math.atan2(moving_dy, moving_dx);
                double toDegree = moving_atan * 180 / Math.PI; //当前手指的角度

                /*旋转的角度，中心点*/
                matrix.postRotate((float) (fromDegree - toDegree), (float) width / 2, (float) height / 2);
                fromDegree = toDegree; //交换角度
                imageView.setImageMatrix(matrix);
                break;
        }
        return true;
    }
}
