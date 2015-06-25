package com.example.tz_knight_ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


public class UiActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = initUI(this);
        setContentView(view);
    }
    private View initUI(Context context) {
		LinearLayout llLayout = new LinearLayout(context);
		llLayout.setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		llLayout.setLayoutParams(lp);
		EditText et = new EditText(context);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1.0f);
		et.setLayoutParams(llp);
		Button btn = new Button(context);
		LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		btn.setLayoutParams(btnParams);
		et.setTextSize(14.0f);
    	btn.setText("测试");
		llLayout.addView(et, 0);
		llLayout.addView(btn, 1);
    	return llLayout;
	}
}
