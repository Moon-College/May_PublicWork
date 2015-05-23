package com.tz.layout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created by qinhan on 15/5/23.
 */
public class HomeWorkTwoActivity extends Activity implements View.OnClickListener {
    private LinearLayout mHeader;

    private LinearLayout mContent;

    private LinearLayout mList;

    private TextView mTextView;

    private EditText mEditText;

    private ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(generateContent());
        mContent.addView(generateHeader());
        mContent.addView(generateScrollView());

        mHeader.addView(generateEditText());
        mHeader.addView(generateTextView());

        mScrollView.addView(generateList());

    }

    private View generateContent() {
        mContent = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContent.setLayoutParams(params);
        mContent.setOrientation(LinearLayout.VERTICAL);
        mContent.setBackgroundColor(Color.WHITE);
        return mContent;
    }

    private View generateHeader() {
        mHeader = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mHeader.setLayoutParams(params);
        mHeader.setOrientation(LinearLayout.HORIZONTAL);
        mHeader.setBackgroundColor(Color.WHITE);
        return mHeader;
    }

    private View generateEditText() {
        mEditText = new EditText(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1f;
        mEditText.setLayoutParams(params);
        mEditText.setPadding(10,10,10,10);
        mEditText.setTextColor(Color.BLACK);
        mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
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

    private View generateScrollView() {
        mScrollView = new ScrollView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        params.weight = 1;
        mScrollView.setLayoutParams(params);
        mScrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        return mScrollView;
    }

    private View generateList() {
        mList = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mList.setLayoutParams(params);
        mList.setOrientation(LinearLayout.VERTICAL);
        mList.setGravity(Gravity.TOP);
        return mList;
    }

    private void generateListItem(int size) {
        mList.removeAllViews();

        for (int i = 0; i < size; i++) {
            TextView mTemp = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mTemp.setLayoutParams(params);
            mTemp.setPadding(10, 10, 10, 10);
            mTemp.setTextColor(Color.BLACK);
            mTemp.setBackgroundColor(Color.WHITE);
            mTemp.setGravity(Gravity.CENTER_VERTICAL);
            mTemp.setText("" + (i+1));
            mList.addView(mTemp);
        }
    }

    private int parseInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case android.R.id.text1:
                int size = parseInt(mEditText.getText().toString());
                generateListItem(size);
                break;
        }
    }
}
