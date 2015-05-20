package com.tz.linearlayout;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		//通过代码加载布局
		initViewByCode();
		
		/**
		//最外层的容器
		LinearLayout ll = new LinearLayout(this);
		ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		ll.setOrientation(LinearLayout.HORIZONTAL);
		
		//编辑框
		EditText et = new EditText(this);
		et.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));  //et的weight相对于线型父容器ll
		et.setHint("请输入搜索内容");
		ll.addView(et);
		
		//按钮
		Button btn = new Button(this);
		btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btn.setText("搜索");
		ll.addView(btn);
		
		setContentView(ll);
		*/
	}
	
	/**
	 * 通过代码加载布局
	 */
	private void initViewByCode() {
		//最外部容器
		LinearLayout ll_all = new LinearLayout(this);  //初始化控件
		ll_all.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)); //设置宽高
		ll_all.setOrientation(LinearLayout.VERTICAL);  //线型容器排列方式，默认水平方式
		
		//上部容器
		LinearLayout ll_top = new LinearLayout(this);
		ll_top.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		
		EditText et = new EditText(this);
		//et的权重【weight】是相对于线型父容器ll_top的属性，所有weight属性设置给线型父容器ll_top
		et.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));  
		et.setHint("请输入搜索内容");  //编辑框提示语
		ll_top.addView(et, 0);   //将et放到线型容器ll_top中，第一个位置
		
		Button btn = new Button(this);
		btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btn.setText("搜索"); //设置文本
		ll_top.addView(btn, 1); //将btn放到线型容器ll_top中，第二个位置
		
		//下部容器
		LinearLayout ll_buttom = new LinearLayout(this);
		//ll_buttom的权重【weight】是相对于线型父容器ll_all的属性
		ll_buttom.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1));
		//注意此处width属性设置为LayoutParams.MATCH_PARENT，图片框应充满父容器，图片才有居中显示 
		LinearLayout.LayoutParams ll_buttom_lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ll_buttom_lp.gravity = Gravity.CENTER;  //img相对于线型父容器ll_buttom的属性,所以此属性要设置给ll_buttom容器
		
		ImageView img = new ImageView(this);
		img.setImageResource(R.drawable.mm);  //设置图片
		img.setLayoutParams(ll_buttom_lp);  
		ll_buttom.addView(img, 0);
		
		ll_all.addView(ll_top, 0);
		ll_all.addView(ll_buttom, 1);
		setContentView(ll_all);  //加载布局
	}
}
