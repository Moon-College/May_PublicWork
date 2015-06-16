package com.tz.refl.activity;

import com.tz.refl.R;
import com.tz.refl.R.layout;
import com.tz.refl.custom.MyButton;
import com.tz.refl.custom.MyTextView;
import com.tz.refl.utils.ReflectionUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReflictionTryActivity extends Activity {
	
	private MyTextView tv_one,tv_two,tv_three,tv_four;
	private MyButton btn_one;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ReflectionUtils.findViews(this);
    }
    
    /**
     * 按钮的点击事件
     * @param v
     */
    public void myOnclick(View v){
    	Toast.makeText(this, tv_one.getText(), 0).show();
    }
    
}