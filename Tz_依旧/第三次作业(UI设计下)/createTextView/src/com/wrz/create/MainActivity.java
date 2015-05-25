package com.wrz.create;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private static final int EV_ID = 1000;
	private static final int BTN_ID = 1001;
	LinearLayout m_ll = null;
	EditText t_ev = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        // 初始化布局
        initLayout();
    } 
    
    /***
     * 初始化布局 
     */
    public void initLayout(){
    	// 创建LinearLayout布局
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        LayoutParams ll_lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        ll.setLayoutParams(ll_lp);
        
        // 创建顶部LinearLayout
        LinearLayout t_ll = new LinearLayout(this);
        t_ll.setOrientation(LinearLayout.HORIZONTAL);
        ll_lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        t_ll.setLayoutParams(ll_lp);
        
        // 创建顶部中EditView
        t_ev = new EditText(this);
        t_ev.setHint("请输入数字");
        t_ev.setId(EV_ID);
        LinearLayout.LayoutParams t_llp = new  LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        t_ev.setLayoutParams(t_llp);
        
        // 创建顶部中button
        Button t_btn = new Button(this);
        t_btn.setText("创建TextView");
        t_btn.setId(BTN_ID);
        t_llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        t_btn.setLayoutParams(t_llp);
        // button点击事件
        t_btn.setOnClickListener(MainActivity.this);
        
        // 将控件添加到头部布局中
        t_ll.addView(t_ev, 0);
        t_ll.addView(t_btn, 1);
        
        // 创建中部 LinearLayout布局
        m_ll = new LinearLayout(this);
        ll_lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        m_ll.setOrientation(LinearLayout.VERTICAL);
        m_ll.setLayoutParams(ll_lp);
        
        // 将头部布局添加到整个布局中
        ll.addView(t_ll);
        // 将中部布局添加到整个布局中
        ll.addView(m_ll);
        
        setContentView(ll);
    }

    /***
     * button点击事件
     * 
     */
	public void onClick(View v) {
		switch(v.getId()){
			case BTN_ID:
				String txt = t_ev.getText().toString().trim();
				if(txt.matches("\\d+")){
					int count = Integer.valueOf(txt);
					if(count < 15){
						// 初始化TextView
						initTextView(count);
					} else{
						Toast.makeText(this, "太多了，显示不下。。。",Toast.LENGTH_SHORT).show();
					}
				} else{
					Toast.makeText(this, "亲，要输入数字哦！！！",Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				break;
		}
	}

	/***
	 * 初始化TextView
	 */
	private void initTextView(int count) {
		// 删除之前的TextView
		m_ll.removeAllViews();
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		for (int i = 0; i < count; i++) {
			TextView tv = new TextView(this);
			tv.setText("textview"+(i+1));
			tv.setLayoutParams(llp);
			tv.setGravity(Gravity.CENTER);
			m_ll.addView(tv);
		}
	}
}