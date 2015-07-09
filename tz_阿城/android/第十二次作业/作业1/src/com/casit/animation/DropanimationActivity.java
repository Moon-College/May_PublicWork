package com.casit.animation;

import com.nineoldandroids.view.ViewHelper;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DropanimationActivity extends Activity implements OnTouchListener {
	
    private RelativeLayout mchat;
	private float downY;
	private float fingerRoll;
	private float viewRoll;
	private ImageView ivEye;
	private float mChatOriginY;
	private int[] locations;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mchat = (RelativeLayout) findViewById(R.id.rl2);
        mchat.setOnTouchListener(this);
        ivEye = (ImageView) findViewById(R.id.iv_eye);

    }

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		//����������޶���Χ
		int limit =  ivEye.getHeight()+100;
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			downY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			fingerRoll = event.getY() - downY;
			if(fingerRoll<=0){
				break;
			}
			viewRoll = fingerRoll * 0.6f;
			ViewHelper.setTranslationY(mchat,  viewRoll);
			break;
		case MotionEvent.ACTION_UP:
			fingerRoll =event.getY()- downY;
			viewRoll = fingerRoll * 0.6f;
			mChatOriginY = mchat.getY();
			locations = new int[2]; 
			mchat.getLocationOnScreen(locations);
			if(viewRoll<limit){
				//����500����ִ����һ������������Ҫȡ����ô��
				ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(500);  
				valueAnimator.start();
				valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					
					public void onAnimationUpdate(ValueAnimator animation) {
						//500������UPDATE�����ᱻ�����ĵ���
						// TODO Auto-generated method stub
						float progress = Float.parseFloat(animation.getAnimatedValue().toString());
					    float y = mChatOriginY - progress*viewRoll;
					    mchat.setY(y);
					}
				});
			}else{
				ValueAnimator valueAnimator2 =ValueAnimator.ofFloat(0,1).setDuration(500);
				valueAnimator2.start();
				valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					
					public void onAnimationUpdate(ValueAnimator animation) {
						// TODO Auto-generated method stub
					    DisplayMetrics dm = new DisplayMetrics(); 
					    getWindowManager().getDefaultDisplay().getMetrics(dm); 
					    int height = dm.heightPixels;  //�õ��߶� 

						float progress = Float.parseFloat(animation.getAnimatedValue().toString());
						float y = mChatOriginY + progress*(height-locations[1]);
						mchat.setY(y);
					}
				});
			}
			//��Ϣ�б����ľ���С���޶�ֵ������ȥ
			//����ֱ�ӻ������ײ�
		default:
			break;
		}
		return true;
	}
    
}