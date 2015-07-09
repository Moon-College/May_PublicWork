package com.casit.hc;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

public class BallMoveActivity extends Activity {
    /** Called when the activity is first created. */
	ImageView imageView;
	public final static float velocity = 20;//ˮƽ�׳��ٶ�
	public final static float acceleration  = 3;//��ֱ���ٶ�
	private int height;
	private int width;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        imageView = (ImageView) findViewById(R.id.iv);
        
    }
    
    public void start(View view){

	    DisplayMetrics dm = new DisplayMetrics(); 
	    getWindowManager().getDefaultDisplay().getMetrics(dm); 
	    height = dm.heightPixels;
		width = dm.widthPixels;
		int time = (int) (1000*width/velocity);//���ˮƽ������Ҫ��ʱ��
		ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(time);  
		valueAnimator.start();		
		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {			
			public void onAnimationUpdate(ValueAnimator animation) {
				//500������UPDATE�����ᱻ�����ĵ���
				// TODO Auto-generated method stub
				float nowx;
				float nowy;
				float progress = Float.parseFloat(animation.getAnimatedValue().toString());
				nowx = (progress* width);
				nowy =(float) ((acceleration *nowx* nowx/ velocity)*0.5);		
				imageView.setX(nowx);
				imageView.setY(nowy);
			}
		});
    }
}