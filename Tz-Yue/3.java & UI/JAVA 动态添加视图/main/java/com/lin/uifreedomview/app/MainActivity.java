package com.lin.uifreedomview.app;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;


public class MainActivity extends Activity implements View.OnClickListener {

    private LinearLayout layout;
    private Button button;
    private LinearLayout linearLayout;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        button = new Button(this);
        button.setText("添加");
        button.setOnClickListener(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        editText = new EditText(this);
        editText.setSingleLine(true);
        editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));


        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(Color.RED);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        layout.addView(button, 0);
        layout.addView(editText, 1);
        layout.addView(linearLayout, 2);

        setContentView(layout);
    }


    @Override
    public void onClick(View v) {

        if (linearLayout == null) {
            return;
        } else {
            linearLayout.removeAllViews();
        }

        String trim = editText.getText().toString().trim();
        trim = TextUtils.isEmpty(trim) ? "5" : trim;
        int x = Integer.parseInt(trim);

        for (int i = 0; i < x; i++) {
            TextView textView = new TextView(this);
            textView.setText("我是文本" + i);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(5, 5, 5, 5);
            textView.setBackgroundColor(Color.WHITE);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(textView);
        }

        Toast.makeText(this, "添加成功", Toast.LENGTH_LONG).show();
    }
}
