package com.tz.ssk.javalayoutsecond;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener{

	private Context mContext;
	private EditText et;
	private Button bt;
	private LinearLayout ll_middle;
	private static final int BT_ID=10086,LL_TOP_ID=10087;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        mContext=this;
        initView();
    }
	private void initView() {
		// TODO Auto-generated method stub
		 //RelativeLayout 初始化
        RelativeLayout rl=new RelativeLayout(mContext);
        android.widget.RelativeLayout.LayoutParams rl_lp=new android.widget.RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT); 
        rl.setLayoutParams(rl_lp);
        
      //LinearLayout 初始化
        LinearLayout ll_top=new LinearLayout(mContext);
        ll_top.setOrientation(LinearLayout.HORIZONTAL);
        ll_top.setId(LL_TOP_ID);
        android.widget.LinearLayout.LayoutParams ll_toplp=new android.widget.LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        ll_top.setLayoutParams(ll_toplp);
        
        //Button 初始化
        bt=new Button(mContext);
        android.widget.LinearLayout.LayoutParams bt_lp=new android.widget.LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        bt.setLayoutParams(bt_lp);
        bt.setText("搜索");
        bt.setId(BT_ID);
        bt.setOnClickListener(MainActivity.this);
        
        //EditText 初始化
        et=new EditText(mContext);
        android.widget.LinearLayout.LayoutParams et_lp=new android.widget.LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1);
        et.setLayoutParams(et_lp);
        
        //LinearLayout 初始化
        ll_middle=new LinearLayout(mContext);
        android.widget.RelativeLayout.LayoutParams ll_middlelp=new android.widget.RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        ll_middlelp.addRule(RelativeLayout.BELOW,LL_TOP_ID);
        ll_middle.setLayoutParams(ll_middlelp);
        ll_middle.setBackgroundResource(R.color.white);
        ll_middle.setOrientation(LinearLayout.VERTICAL);
        
        
        ll_top.addView(et, 0);
        ll_top.addView(bt, 1);
        rl.addView(ll_top,0);
        rl.addView(ll_middle, 1);
        
        setContentView(rl);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case BT_ID:
			String strnumber=et.getText().toString().trim();
			if(strnumber.matches("\\d+"))
			{
				int i=Integer.parseInt(strnumber);
				if(i<500)
				{
					//生成TextView
					initTextView(i);
				}else
				{
					Toast.makeText(mContext, "大哥,这个搞会爆炸的。。。。", Toast.LENGTH_LONG).show();
				}
				
			}else
			{
				Toast.makeText(mContext, "要填写数字啊，大哥。。。。", Toast.LENGTH_LONG).show();
			}
			break;
		default:
			break;
		}
	}
	private void initTextView(int parseInt) {
		//删除之前的
		ll_middle.removeAllViews();
		android.widget.LinearLayout.LayoutParams tv_lp=new android.widget.LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		for(int i=0;i<parseInt;i++)
		{
			TextView tv=new TextView(mContext);
			tv.setLayoutParams(tv_lp);
			tv.setText("TextView"+1);
			tv.setGravity(Gravity.CENTER);
			ll_middle.addView(tv);
		}
	}
}
