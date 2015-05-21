package com.wrz.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // java实现界面布局
        // 创建线性布局
        LinearLayout ll = new LinearLayout(this);
        // 设置线性布局为 水平布局
        ll.setOrientation(LinearLayout.HORIZONTAL);
        // 设置线性布局 宽、高
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(lp);
        // 创建EditText
        EditText tv = new EditText(this);
        //tv.setText("这是一个输入框");
        // 设置EditText在布局中的位置属性
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT,1);
        tv.setLayoutParams(llp);
        
        // 创建Button
        Button btn = new Button(this);
        // 设置Button在布局中的位置属性
        llp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        // 设置Button text属性
        btn.setText("搜索");
        btn.setLayoutParams(llp);
        // 线性布局添加子控件
        ll.addView(tv, 0);// EditText控件
        ll.addView(btn, 1);// Button控件
        setContentView(ll);
    }
}