package com.tangzhi.cn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.nineoldandroids.view.ViewHelper;

public class MainActivity extends Activity implements OnClickListener {
	/**ˮƽ����ļ��ٶ�*/
	private float a_x=-2f;
	/**��ֱ����ļ��ٶ�*/
	private float a_y=9.8f;
	/**ˮƽ����ĳ��ٶ�*/
	private float v_x=50f;
	/**view������*/
	private float cur_x,cur_y;
	/**ȫ�ֱ���ʱ��t*/
	private int t;
    private ImageView img;
	private Button btn_start;
	private Button btn_jump;
	private Handler handler=new Handler();
	private Runnable runnable=new Runnable() {
		
		public void run() {
			//�߼�����
			logic();
			ViewHelper.setTranslationX(img, cur_x);
			ViewHelper.setTranslationY(img, cur_y);
			handler.removeCallbacks(runnable);
			handler.postDelayed(runnable, 500);
			t++;
		}

		
	};
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initUI();
    }

	private void initUI() {
		img=(ImageView)findViewById(R.id.img);
		btn_start=(Button)findViewById(R.id.btn_start);
		btn_jump=(Button)findViewById(R.id.btn_jump);
		btn_start.setOnClickListener(this);
		btn_jump.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start:
			Animation();
			break;
		case R.id.btn_jump:
			Intent intent = new  Intent(this,WenXiActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		
	}
	//ͼƬ����Բ���˶�����Ч��
	private void Animation() {
		handler.post(runnable);
		
	}
	private void logic() {
		cur_x=(float) (v_x*t+0.5*a_x*Math.pow(t, 2));
		cur_y=(float) (0.5*Math.pow(t, 2)*a_y);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeCallbacks(runnable);
	}
}