package com.xigua.layoutdesign2;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	private ImageView img;
	private EditText edit;
	private Button btn;
	private LinearLayout lllay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
		//设置最外层的相对布局和头部的线性布局
		RelativeLayout rlay = new RelativeLayout(this);
		rlay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		LinearLayout llay = new LinearLayout(this);
		llay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		llay.setId(11111);
        
		edit = new EditText(this);
        btn = new Button(this);
        img = new ImageView(this);
        //用来动态添加TextView的线性布局
		lllay = new LinearLayout(this);

		//设置EditText，Button，ImageView的Params，以及LinearLayout的params
        LinearLayout.LayoutParams eparams = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1);
        LinearLayout.LayoutParams bparams = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,0);
        RelativeLayout.LayoutParams iparams = new RelativeLayout.LayoutParams(
        		RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        iparams.addRule(RelativeLayout.CENTER_IN_PARENT);
		RelativeLayout.LayoutParams rrparams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		rrparams.addRule(RelativeLayout.BELOW,llay.getId());
		lllay.setLayoutParams(rrparams);
        
		//设置控件的基本属性
        edit.setLayoutParams(eparams);
        edit.setHint("请输入添加TextView的个数");
        edit.setInputType(InputType.TYPE_CLASS_NUMBER);
        btn.setLayoutParams(bparams);
        btn.setText("添加");
        img.setLayoutParams(iparams);
        img.setImageResource(R.drawable.ic_launcher);
        lllay.setOrientation(LinearLayout.VERTICAL);
        
        //添加编辑框和按钮到顶部的线性布局
        llay.addView(edit,0);
        llay.addView(btn,1);
        //添加顶部的线性布局，ImageView和另一个线性布局
        rlay.addView(llay);
        rlay.addView(img);
        rlay.addView(lllay);
        
        setContentView(rlay);
        
        //设备按钮的点击事件，隐藏图片，根据编辑框输入的数字进行添加
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				img.setVisibility(View.GONE);
				if(!edit.getText().toString().equals("")){
				for(int i=0;i<Integer.valueOf(edit.getText().toString());i++){
					TextView text = new TextView(MainActivity.this);
					text.setText("这是第"+i+"个TextView");
					text.setGravity(Gravity.CENTER);
                    LinearLayout.LayoutParams linear = new LinearLayout.LayoutParams(
                    		LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    text.setLayoutParams(linear);
                    text.setTextColor(Color.BLACK);
					lllay.addView(text);
				}
				}else{
					Toast.makeText(MainActivity.this, "输入框内容不能为空", Toast.LENGTH_SHORT).show();
				}
			}
		});
        
	}
}
