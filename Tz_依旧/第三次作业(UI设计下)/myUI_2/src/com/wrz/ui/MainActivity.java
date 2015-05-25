package com.wrz.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // java实现布局UI
        
        // 创建相对布局
        RelativeLayout rl = new RelativeLayout(this);
        // 设置相对布局 宽、高
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        rl.setLayoutParams(lp);
        // 创建线性布局
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        // 设置线性布局 宽、高
        LayoutParams xlp = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(xlp);
        // 线性布局中创建EditText
        EditText xet = new EditText(this);
        // 设置默认文本(有内容输入时，消失)
        xet.setHint("请输入网址");
        // 设置EditText 宽、高、权重
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        llp.gravity = Gravity.CENTER_VERTICAL;
        // 将layoutParams设置的属性添加到EditText上
        xet.setLayoutParams(llp);
        // 线性布局中创建Button
        Button sBtn = new Button(this);
        // 搜素按钮添加文本
        sBtn.setText("搜索");
        sBtn.setTextSize(30);
        sBtn.setPadding(20, 10, 20, 20);
        // 搜素按钮设置 宽、高
        llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llp.gravity = Gravity.CENTER_HORIZONTAL;
        sBtn.setLayoutParams(llp);
        // 线性布局添加控件
        // 添加EditText
        ll.addView(xet, 0);
        ll.addView(sBtn, 1);
        
        // 相对布局中天ImageView
        ImageView iv = new ImageView(this);
        // 设置图片src
        iv.setImageDrawable(getResources().getDrawable(R.drawable.movie));
        // 设置ImageView宽、高
        RelativeLayout.LayoutParams rll = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 设置图片相对于父级 居中显示
        rll.addRule(RelativeLayout.CENTER_IN_PARENT);
        iv.setLayoutParams(rll);
        // 相对布局中添加控件
        rl.addView(ll); // LinearLayout
        rl.addView(iv); // ImageView
        setContentView(rl);
    }
}