package com.example.tz_animation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn_start, btn_end;
	
	private Button btn_alpha, btn_scale, btn_rotate, btn_translate;
	
	private ImageView image;
	
	private Animation anim2;
	
	private boolean flag = false;
	
	private Animation alphaAnim,rotateAnim,scaleAnim,translateAnim;
	
	private static final int STATUS_ALPHA_ANIMATION = 0;
	
	private static final int STATUS_ROTATE_ANIMATION = 1;
	
	private static final int STATUS_SCALE_ANIMATION = 2;
	
	private static final int STATUS_TRANSLATE_ANIMATION = 3;
	
	private int animStatus;
	
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			image.startAnimation(anim2);
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initView();
	}

	private void initView() {
		btn_start = (Button) findViewById(R.id.start);
		btn_end = (Button) findViewById(R.id.end);
		btn_alpha = (Button) findViewById(R.id.alpha);
		btn_rotate = (Button) findViewById(R.id.rotate);
		btn_scale = (Button) findViewById(R.id.scale);
		btn_translate = (Button) findViewById(R.id.translate);
		image = (ImageView) findViewById(R.id.imageview);
		btn_start.setOnClickListener(this);
		btn_end.setOnClickListener(this);
		btn_alpha.setOnClickListener(this);
		btn_rotate.setOnClickListener(this);
		btn_scale.setOnClickListener(this);
		btn_translate.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.start:

			break;
		case R.id.end:
			
			switch (animStatus) {
			case STATUS_ALPHA_ANIMATION:
				alphaAnim.cancel();
				break;
			case STATUS_ROTATE_ANIMATION:
				flag = true;
				anim2.cancel();
				break;
			case STATUS_SCALE_ANIMATION:
				scaleAnim.cancel();
				break;
			case STATUS_TRANSLATE_ANIMATION:
				break;

			default:
				break;
			}
			
			break;
		case R.id.alpha:
			alphaAnim = AnimationUtils.loadAnimation(this, R.anim.alpha);
			image.startAnimation(alphaAnim);
			animStatus = STATUS_ALPHA_ANIMATION;
			break;
		case R.id.rotate:
			animStatus = STATUS_ROTATE_ANIMATION;
			flag = false;
			anim2 = AnimationUtils.loadAnimation(this, R.anim.rotate);

			/*new Thread() {

				public void run() {
					while (!flag) {
						try {
							mHandler.obtainMessage().sendToTarget();
							sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};

			};*/
			
			//pivotXValue 坐标类型有相对和绝对的
			RotateAnimation animation = new RotateAnimation(0, 270, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
			animation.setDuration(1000);
			animation.setFillAfter(true);
			image.startAnimation(animation);
			break;
		case R.id.scale:
			animStatus = STATUS_SCALE_ANIMATION;
			scaleAnim = AnimationUtils.loadAnimation(this, R.anim.scale);
			image.startAnimation(scaleAnim);
			break;
		case R.id.translate:
			translateAnim = AnimationUtils.loadAnimation(this, R.anim.translate);
			image.startAnimation(translateAnim);
			break;

		default:
			break;
		}
	}

}
