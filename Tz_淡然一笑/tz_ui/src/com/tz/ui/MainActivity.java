package com.tz.ui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	/**
	 * 最外面的线型布局
	 */
	private LinearLayout ll;
	/**
	 * 上面水平的线型容器
	 */
	private LinearLayout ll_top;
	/**
	 * 下面垂直的线型布局
	 */
	private LinearLayout ll_below;
	/**
	 * 确定按钮
	 */
	private Button btn_confirm;
	/**
	 * 输入数字0-9
	 */
	private EditText et_inputNumber;
	/**
	 * 下面垂直的线型布局	  编辑框
	 */
	private TextView tv_below;
	/**
	 * 下面垂直的线型布局	 LayoutParams属性
	 */
	private LinearLayout.LayoutParams ll_below_lp;

	private int number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		// 代码加载布局 	  --> 作业1
//		 initViewByCode();

		// 代码加载布局，并且点击按钮生成TextView控件  	--> 作业2
		initViewAndOnClickByCode();

	}
	
	/**
	 * 代码加载布局
	 */
	private void initViewByCode() {
		// 最外面的相对布局
		RelativeLayout rl = new RelativeLayout(this);
		RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		// 设置图片imgx相对于父容器rl居中方式对齐
		rl_lp.addRule(RelativeLayout.CENTER_IN_PARENT); 

		// 上面的线型容器
		LinearLayout ll = new LinearLayout(this);
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		EditText et = new EditText(this);
		et.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));
		et.setHint("请输入搜索内容");
		ll.addView(et, 0);

		Button btn = new Button(this);
		btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btn.setText("搜索");
		ll.addView(btn, 1);

		// 下面的图片
		ImageView img = new ImageView(this);
		img.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		img.setImageResource(R.drawable.mm);
		// 要把图片img相对于父容器rl的属性，设置给img。不设置此属性，图片默认显示在左上角
		img.setLayoutParams(rl_lp);

		rl.addView(ll, 0);
		rl.addView(img, 1);
		setContentView(rl);
	}

	/**
	 * 代码加载布局，并且点击按钮生成TextView控件 
	 */
	private void initViewAndOnClickByCode() {
		// 最外面的线型布局
		ll = new LinearLayout(this);
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		ll.setOrientation(LinearLayout.VERTICAL);

		// 上面水平的线型容器
		ll_top = new LinearLayout(this);
		ll_top.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		ll_top.setOrientation(LinearLayout.HORIZONTAL);

		et_inputNumber = new EditText(this);
		et_inputNumber.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));
		et_inputNumber.setHint("请输入数字");
		// 编辑框设置只能输入数字0-9
		et_inputNumber.setInputType(InputType.TYPE_CLASS_NUMBER); 
		ll_top.addView(et_inputNumber);

		btn_confirm = new Button(this);
		btn_confirm.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btn_confirm.setText("确定");
		ll_top.addView(btn_confirm);

		// 将ll_top放到ll中
		ll.addView(ll_top); 

		// 下面垂直的线型布局
		ll_below = new LinearLayout(this);
	    ll_below_lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		// 设置外边距	left, top, right, bottom
		ll_below_lp.setMargins(10, 5, 10, 5);
		ll_below.setOrientation(LinearLayout.VERTICAL);

		// 将ll_below放到ll中
		ll.addView(ll_below); 
		// 第一次加载ll
		setContentView(ll);

		// 确定按钮点击事件
		btn_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 获取输入的数字
				number = Integer.parseInt(et_inputNumber.getText().toString());
				// 当输入的数字是0-9时，才去执行刷新ll_below视图的操作
				if(number>0 && number<10){
					// 点击确定按钮，刷新ll_below视图，动态生成TextView控件
					dynamicViewByCode(number);
				} else {
					Toast.makeText(MainActivity.this, "请输入0~9之间的正整数！", Toast.LENGTH_SHORT).show();
					// 编辑框内容置空
					et_inputNumber.setText("");
					// 获取编辑框焦点
					et_inputNumber.requestFocus();
				}
				
			}
		});

	}

	/**
	 * 点击确定按钮，刷新ll_below视图，动态生成TextView控件
	 * 
	 * @param number
	 */
	private void dynamicViewByCode(int number) {
		// 先清除tv_below，在重新加载tv_below
		ll_below.removeAllViews();
		for (int i = 0; i < number; i++) {
			tv_below = new TextView(this);
			tv_below.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			// 设置文字
			tv_below.setText("第" + i + "个TextView控件");
			// 设置字体
			tv_below.setTextSize(18);
			// 外边距
			tv_below.setLayoutParams(ll_below_lp);
			// 设置字体居中对齐方式
			tv_below.setGravity(Gravity.CENTER);
			// 设置边框
			tv_below.setBackgroundResource(R.drawable.textview_border);
			// 将tv_below放到ll_below
			ll_below.addView(tv_below); 
		}
		// 刷新ll_below视图，切记
		this.ll_below.invalidate();
		// 重新加载ll布局
		setContentView(ll);
	}

}
