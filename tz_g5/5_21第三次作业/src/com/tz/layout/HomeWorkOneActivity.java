package com.tz.layout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created by qinhan on 15/5/23.
 */
public class HomeWorkOneActivity extends Activity implements View.OnClickListener {

    private LinearLayout mHeader;

    private EditText mEditText;

    private TextView mTextView;

    private RelativeLayout mContent;

    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(generateContent());
        mContent.addView(generateHeader());
        mContent.addView(generateImageView());
        mHeader.addView(generateEditText());
        mHeader.addView(generateTextView());
    }

    private View generateContent() {
        mContent = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContent.setLayoutParams(params);
        return mContent;
    }


    private View generateHeader() {
        mHeader = new LinearLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
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

    private View generateImageView() {
        mImageView = new ImageView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        mImageView.setLayoutParams(params);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView.setImageResource(R.drawable.movie);
        return mImageView;
    }

    @Override
    public void onClick(View v) {

    }
}
