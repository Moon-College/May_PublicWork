package com.tz.michael;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class CodeLayoutActivity extends Activity {
	/**最外层容器*/
    private LinearLayout lay_out;
    /**上面的水平容器*/
	private LinearLayout lay_top;
	private EditText et;
	/**下面的容器*/
	private LinearLayout lay_bottom;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lay_out = new LinearLayout(this);
        lay_out.setOrientation(LinearLayout.VERTICAL);
        
        lay_top = new LinearLayout(this);
        lay_top.setOrientation(LinearLayout.HORIZONTAL);
        
        et = new EditText(this);
        LayoutParams params_et=new LayoutParams(0,LayoutParams.WRAP_CONTENT,1);
        et.setLayoutParams(params_et);
        Button btn=new Button(this);
        LayoutParams params_btn=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        btn.setLayoutParams(params_btn);
        btn.setText("提交");
        //将et和btn都添加到lay_top
        lay_top.addView(et, 0);
        lay_top.addView(btn, 1);
        
        lay_bottom = new LinearLayout(this);
        lay_bottom.setBackgroundColor(0x55ff00ff);
        LayoutParams params_lay_bot=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        lay_bottom.setGravity(Gravity.CENTER);
        lay_bottom.setLayoutParams(params_lay_bot);
        TextView tv=new TextView(this);
        tv.setText("Danny 你好");
        lay_bottom.addView(tv);
        //将上下容器都加到最外层容器
        lay_out.addView(lay_top, 0);
        lay_out.addView(lay_bottom,1);
        //设置布局
        setContentView(lay_out);
//        setContentView(R.layout.main);
    }
}