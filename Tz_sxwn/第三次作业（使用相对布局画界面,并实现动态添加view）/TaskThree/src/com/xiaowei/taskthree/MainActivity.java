package com.xiaowei.taskthree;
/**
 * 代码画布局
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity {
	/**最外层容器**/
	private RelativeLayout layout_out;
	/**上面的水平容器**/
	private LinearLayout layout_top;
	private EditText et;
	private Button submitBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout_out  = new RelativeLayout(this);
		layout_out.setBackgroundColor(0x55635214);//设置最外层的背景色
		//设置宽,高参数
		LayoutParams params  = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		//设置属性
		layout_out.setLayoutParams(params);
		
		
		//实例化最上层的容器
		layout_top = new LinearLayout(this);
		layout_top.setBackgroundColor(0x44ff);
		//设置params
		LayoutParams params_top = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		//为layout_top添加约束
		params_top.addRule(RelativeLayout.ALIGN_TOP);
		layout_top.setOrientation(LinearLayout.HORIZONTAL);
		layout_top.setLayoutParams(params_top);
		
		
		et = new EditText(this);
        LinearLayout.LayoutParams params_et=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1);
        et.setLayoutParams(params_et);
        submitBtn = new Button(this);
        LinearLayout.LayoutParams params_btn=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        submitBtn.setLayoutParams(params_btn);
        submitBtn.setText("提交");
        //将et和btn都添加到lay_top
        layout_top.addView(et, 0);
        layout_top.addView(submitBtn, 1);
        
        
        /***动态设置一张imageview***/
        ImageView imageView=new ImageView(this);
        LayoutParams params_img=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params_img.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setImageResource(R.drawable.ic_launcher);
        imageView.setLayoutParams(params_img);
		setContentView(layout_out);
		
		
		/****给外部容器添加布局*****/
		layout_out.addView(layout_top);
		layout_out.addView(imageView);
		
		/***设置布局***/
		setContentView(layout_out);
		
	    submitBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
					Intent intent = new Intent(MainActivity.this, DynamicAddViewActivity.class);
					startActivity(intent);
			}
		});
	}

}
