package com.tz.michael.activity;

import com.tz.michael.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;
/**
 * 动态添加view
 * @author szm
 *
 */
public class DynamicAddViewActivity extends Activity implements OnClickListener {

	private static final int TOP = 0;
	private static final int BTN = 1;
	private static final int ET = 2;
	/**最外层容器*/
    private RelativeLayout lay_out;
    /**上面的水平容器*/
	private LinearLayout lay_top;
	/**文本编辑框*/
	private EditText et;
	/**底部的容器*/
	private LinearLayout lay_bottom;
	/**提交按钮*/
	private Button btn;
	
	private static int index;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lay_out = new RelativeLayout(this);
        lay_out.setBackgroundColor(0x55635214);
        //这里是RelativeLayout.LayoutParams
        LayoutParams params_re=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        //为最外层容器设置属性
        lay_out.setLayoutParams(params_re);
        
        //实例化上面的容器
        lay_top = new LinearLayout(this);
        lay_top.setId(TOP);
        lay_top.setBackgroundColor(0x44ff0000);
        //这里是RelativeLayout.LayoutParams
        LayoutParams params_top=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        //为params_top添加约束
        params_top.addRule(RelativeLayout.ALIGN_TOP);
        lay_top.setOrientation(LinearLayout.HORIZONTAL);
        lay_top.setLayoutParams(params_top);
        
        et = new EditText(this);
        et.setId(ET);
        LinearLayout.LayoutParams params_et=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1);
        et.setLayoutParams(params_et);
        btn = new Button(this);
        btn.setId(BTN);
        LinearLayout.LayoutParams params_btn=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        btn.setLayoutParams(params_btn);
        btn.setText("提交");
        //将et和btn都添加到lay_top
        lay_top.addView(et, 0);
        lay_top.addView(btn, 1);
        
        lay_bottom = new LinearLayout(this);
        lay_bottom.setOrientation(LinearLayout.VERTICAL);
        LayoutParams params_img=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        params_img.addRule(RelativeLayout.BELOW,TOP);
        lay_bottom.setLayoutParams(params_img);
        
        //将上面容器加到最外层容器
        lay_out.addView(lay_top, 0);
        //imageView加到最外层容器
        lay_out.addView(lay_bottom, 1);
        //设置布局
        setContentView(lay_out);
        btn.setOnClickListener(this);
	}

	public void onClick(View v) {
		//保证文本框中有内容
		if(et.getText().toString().trim().length()!=0){
			try{
				int sum=Integer.valueOf(et.getText().toString().trim());
				for(int i=0;i<sum;i++,index++){
					TextView tv=new TextView(this);
					tv.setBackgroundColor(0xeeffffff);
					tv.setText(index+1+"");
					lay_bottom.addView(tv);
				}
			}catch (Exception e) {
			}
		}
	}
	
}
