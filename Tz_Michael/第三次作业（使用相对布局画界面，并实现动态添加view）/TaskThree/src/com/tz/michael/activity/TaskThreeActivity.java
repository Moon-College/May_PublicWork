package com.tz.michael.activity;

import com.tz.michael.R;
import com.tz.michael.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
/**
 * 代码画布局二
 * @author szm
 *
 */
public class TaskThreeActivity extends Activity implements OnClickListener {
	/**最外层容器*/
    private RelativeLayout lay_out;
    /**上面的水平容器*/
	private LinearLayout lay_top;
	private EditText et;
	private Button btn;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lay_out = new RelativeLayout(this);
        lay_out.setBackgroundColor(0x55635214);
        //这里是RelativeLayout.LayoutParams
        LayoutParams params_re=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        //为最外层容器设置属性
        lay_out.setLayoutParams(params_re);
        
        //实例化上面的容器
        lay_top = new LinearLayout(this);
        lay_top.setBackgroundColor(0x44ff0000);
        //这里是RelativeLayout.LayoutParams
        LayoutParams params_top=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        //为params_top添加约束
        params_top.addRule(RelativeLayout.ALIGN_TOP);
        lay_top.setOrientation(LinearLayout.HORIZONTAL);
        lay_top.setLayoutParams(params_top);
        
        et = new EditText(this);
        LinearLayout.LayoutParams params_et=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1);
        et.setLayoutParams(params_et);
        btn = new Button(this);
        LinearLayout.LayoutParams params_btn=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        btn.setLayoutParams(params_btn);
        btn.setText("提交");
        //将et和btn都添加到lay_top
        lay_top.addView(et, 0);
        lay_top.addView(btn, 1);
        
        ImageView imageView=new ImageView(this);
        LayoutParams params_img=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params_img.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setImageResource(R.drawable.ic_launcher);
        imageView.setLayoutParams(params_img);
        
        //将上面容器加到最外层容器
        lay_out.addView(lay_top, 0);
        //imageView加到最外层容器
        lay_out.addView(imageView, 1);
        //设置布局
        setContentView(lay_out);
        btn.setOnClickListener(this);
    }

	public void onClick(View v) {
		startActivity(new Intent(this,DynamicAddViewActivity.class));
	}
}