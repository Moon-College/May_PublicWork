package com.tz.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity implements View.OnClickListener {
    private LinearLayout mContentView;

    private EditText mEditText;

    private TextView mTextView;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(generateContentView());

        mContentView.addView(generateEditText());
        mContentView.addView(generateTextView());

    }

    private View generateContentView() {
        mContentView = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContentView.setLayoutParams(params);
        mContentView.setOrientation(LinearLayout.HORIZONTAL);
        mContentView.setBackgroundColor(Color.WHITE);
        return mContentView;
    }

    private View generateEditText() {
        mEditText = new EditText(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1f;
        mEditText.setLayoutParams(params);
        mEditText.setPadding(10,10,10,10);
        mEditText.setTextColor(Color.BLACK);
        mEditText.setSingleLine();
        mEditText.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
        mEditText.setBackgroundResource(android.R.drawable.edit_text);
        return mEditText;
    }

    private View generateTextView() {
        mTextView = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTextView.setLayoutParams(params);
        mTextView.setText("搜索");
        mTextView.setId(android.R.id.text1);
        mTextView.setPadding(10, 10, 10, 10);
        mTextView.setTextColor(Color.BLACK);
        mTextView.setOnClickListener(this);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setBackgroundResource(android.R.drawable.btn_default);
        return mTextView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case android.R.id.text1:
                Toast.makeText(this, "搜索"+mEditText.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
