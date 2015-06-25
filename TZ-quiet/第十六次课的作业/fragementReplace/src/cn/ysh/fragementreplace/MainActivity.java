package cn.ysh.fragementreplace;

import cn.ysh.callback.myview.MyView;
import cn.ysh.fragment.RegisterFirstFragment;
import cn.ysh.fragment.RegisterSecondFragment;
import cn.ysh.listener.OnTextChangedListener;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener, OnBackStackChangedListener , OnTextChangedListener {
	private FragmentManager fm;
	private RegisterFirstFragment firstFragment;
	private Button button;
	private int step;
	private RegisterSecondFragment secondFragment;
	//�����滻��fragment��framelayout
	private FrameLayout fl;
	//�Զ���view
	private MyView myView;
	//��ʾ������Ϣ
	private TextView tv;
	private Handler handler = new Handler();
	private Runnable run = new Runnable(){

		@Override
		public void run() {
			String str =  myView.getText().toString();
			if(str.matches("9+.*")){
				tv.setVisibility(View.VISIBLE);
			}else{
				tv.setVisibility(View.INVISIBLE);
			}
			handler.removeCallbacks(run);
			handler.postDelayed(run, 200);
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		fl = (FrameLayout) findViewById(R.id.framelayout);
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
		fm = getSupportFragmentManager();
		fm.addOnBackStackChangedListener(this);
		firstFragment = new RegisterFirstFragment();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.framelayout, firstFragment);
		transaction.commit();
	}
	/**
	 * ��activity������ȥ���ü����¼�
	 */
	@Override
	protected void onStart() {
		super.onStart();
		setOnTextChanged();
		
	}
	
	/**
	 * ���������Զ���view�����ݼ����¼�
	 * ���ӵڶ���fragment���ص���һ��fragment�Ļ���
	 * �����ִ��myView = firstFragment.getTelnumer();
	 * �������ã�Ϊʲô������
	 */
	private void setOnTextChanged() {
		myView = firstFragment.getTelnumer();
		myView.setOnTextChangedListener(this);
	}

	@Override
	public void onClick(View v) {
		if(step == 0){
			//���������Ϣ��ʾ���͵���toast
			if(tv.getVisibility() == View.VISIBLE){
				Toast.makeText(this, "��������,����������!", Toast.LENGTH_SHORT).show();
			}else{
				replaceFragment();
			}
		}
		if(step == 1){
			StringBuilder sb = new StringBuilder();
			sb.append("�˺�:"+firstFragment.getTelnumer().getText().toString()+"\n");
			sb.append("����:"+firstFragment.getPassword().getText().toString().trim()+"\n");
			sb.append("����:"+secondFragment.getName().getText().toString().trim()+"\n");
			sb.append("��ַ:"+secondFragment.getAddress().getText().toString().trim());
			Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
		}
	}

	private void replaceFragment() {
		secondFragment = new RegisterSecondFragment();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.framelayout, secondFragment);
		transaction.addToBackStack("second");
		transaction.commit();
		
	}

	@Override
	public void onBackStackChanged() {
		int count = fm.getBackStackEntryCount();
		if(count == 0){
			button.setText("��һ��");
			step = 0;
			//���ӵڶ���fragment���ص�һ��ʱ����Ҫִ�д˷������뿴�˷���ע�ͣ�������.
			setOnTextChanged();
			handler.post(run);
		}
		if(count == 1){
			button.setText("ע��");
			step = 1;
		}
		
	}

	/**
	 * ���ݼ����Ļص�����
	 * activity��textView����Ҫ�ظ�����
	 */
	@Override
	public void OnTextChanged(View v) {
		if(tv == null){
			tv = new TextView(this);
			tv.setText("����ĺ�������");
			tv.setTextColor(Color.RED);
			tv.setTextSize(20);
			tv.setGravity(Gravity.CENTER_VERTICAL);
			fl.addView(tv);
		}
		handler.post(run);
	}

	@Override
	protected void onDestroy() {
		handler.removeCallbacks(run);
		super.onDestroy();
	}
}
