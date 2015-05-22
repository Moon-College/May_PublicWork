package com.limz.javalayoutproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class JavaLayoutProjectActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        LinearLayout layout = new LinearLayout(this);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
        		LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);
        EditText editText = new EditText(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
        		LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
        editText.setLayoutParams(layoutParams);
        Button button = new Button(this);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(layoutParams.WRAP_CONTENT,
        		LayoutParams.WRAP_CONTENT);
        button.setText("ËÑË÷");
        button.setLayoutParams(layoutParams2);
        layout.addView(editText);
        layout.addView(button);
        setContentView(layout);
    }
}