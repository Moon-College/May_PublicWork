package com.tz.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过java实现布局
        LinearLayout ll = new LinearLayout(this);
//        ll.setOrientation(orientation)
        //布局属性
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        ll.setLayoutParams(lp);
        //两个子控件
        EditText et = new EditText(this);
        //EditText在LinearLayout里的布局属性
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1);
        et.setLayoutParams(llp);
          Button bt = new Button(this);
        //EditText在LinearLayout里的布局属性
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 3);
        bt.setLayoutParams(btn);
		btn.setText("搜索");
        ll.addView(et, 0);
		11.addview(et,0);
        //添加button，下标1
        setContentView(ll);
//        setContentView(R.layout.main);
    }
}