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
	//用于替换的fragment的framelayout
	private FrameLayout fl;
	//自定义view
	private MyView myView;
	//显示错误信息
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
	 * 在activity创建后去设置监听事件
	 */
	@Override
	protected void onStart() {
		super.onStart();
		setOnTextChanged();
		
	}
	
	/**
	 * 用于设置自定义view的内容监听事件
	 * 当从第二个fragment返回到第一个fragment的话，
	 * 如果不执行myView = firstFragment.getTelnumer();
	 * 则不起作用，为什么？？？
	 */
	private void setOnTextChanged() {
		myView = firstFragment.getTelnumer();
		myView.setOnTextChangedListener(this);
	}

	@Override
	public void onClick(View v) {
		if(step == 0){
			//如果错误信息显示，就弹个toast
			if(tv.getVisibility() == View.VISIBLE){
				Toast.makeText(this, "号码有误,请重新输入!", Toast.LENGTH_SHORT).show();
			}else{
				replaceFragment();
			}
		}
		if(step == 1){
			StringBuilder sb = new StringBuilder();
			sb.append("账号:"+firstFragment.getTelnumer().getText().toString()+"\n");
			sb.append("密码:"+firstFragment.getPassword().getText().toString().trim()+"\n");
			sb.append("姓名:"+secondFragment.getName().getText().toString().trim()+"\n");
			sb.append("地址:"+secondFragment.getAddress().getText().toString().trim());
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
			button.setText("下一步");
			step = 0;
			//当从第二个fragment返回第一个时候，需要执行此方法，请看此方法注释，有疑问.
			setOnTextChanged();
			handler.post(run);
		}
		if(count == 1){
			button.setText("注册");
			step = 1;
		}
		
	}

	/**
	 * 内容监听的回调方法
	 * activity的textView不需要重复创建
	 */
	@Override
	public void OnTextChanged(View v) {
		if(tv == null){
			tv = new TextView(this);
			tv.setText("输入的号码有误");
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
