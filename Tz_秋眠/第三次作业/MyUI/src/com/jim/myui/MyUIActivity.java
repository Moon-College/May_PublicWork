package com.jim.myui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MyUIActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	private final static int SEARCH_LL = 10000;
	private final static int IMAGEVIEW = 10001;
	private final static int TEXTVIEW_LL = 10002;
	private final static int BUTTON = 10003;
	private final static int LLVIEW = 10004;
	Button button;
	EditText editText;
	LinearLayout contentLinearLayout;
	ImageView mImageView;
	LinearLayout layout;
	ScrollView scrollView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initViews();

	}

	private void initViews() {
		// ����RelativeLayout
		RelativeLayout mRelativeLayout = new RelativeLayout(this);
		RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		mRelativeLayout.setLayoutParams(mLayoutParams);
		// װ������밴ť��LinearLayout
		LinearLayout ll = new LinearLayout(this);
		ll.setId(SEARCH_LL);
		LinearLayout.LayoutParams llLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setLayoutParams(llLayoutParams);
		// �����
		editText = new EditText(this);
		LinearLayout.LayoutParams etLayoutParams = new LinearLayout.LayoutParams(
				0, LinearLayout.LayoutParams.WRAP_CONTENT, 2);
		editText.setHint("����1~100����");
		editText.setSingleLine();
		editText.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
		editText.setLayoutParams(etLayoutParams);
		ll.addView(editText, 0);
		// ��ť
		button = new Button(this);
		LinearLayout.LayoutParams butLayoutParams = new LinearLayout.LayoutParams(
				0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		button.setText("ȷ��");
		button.setId(BUTTON);
		button.setLayoutParams(butLayoutParams);
		ll.addView(button, 1);

		mRelativeLayout.addView(ll, 0);
		// װ�·��������ݵ�linearlayout
		contentLinearLayout = new LinearLayout(this);
		contentLinearLayout.setId(TEXTVIEW_LL);
		contentLinearLayout.setBackgroundColor(0x3355ff55);
		contentLinearLayout.setOrientation(LinearLayout.VERTICAL);
		RelativeLayout.LayoutParams contentLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		contentLayoutParams.addRule(RelativeLayout.BELOW, SEARCH_LL);
		contentLinearLayout.setLayoutParams(contentLayoutParams);
		// ͼƬ
		mImageView = new ImageView(this);
		mImageView.setId(IMAGEVIEW);
		mImageView.setImageResource(R.drawable.icon);
		LinearLayout.LayoutParams ivLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		ivLayoutParams.gravity = Gravity.CENTER;
		mImageView.setLayoutParams(ivLayoutParams);
		contentLinearLayout.addView(mImageView);
		// Ϊ�ı��Ӹ�������
		scrollView = new ScrollView(this);
		LinearLayout.LayoutParams slp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		scrollView.setVerticalScrollBarEnabled(true);
		scrollView.setLayoutParams(slp);
		scrollView.setVisibility(View.GONE);
		contentLinearLayout.addView(scrollView);
		// �����ı���LinearLayout
		layout = new LinearLayout(MyUIActivity.this);
		layout.setId(LLVIEW);
		layout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(lp);
		scrollView.addView(layout);

		mRelativeLayout.addView(contentLinearLayout, 1);
		setContentView(mRelativeLayout);
		button.setOnClickListener(this);
	}

	/**
	 * �����ť����������е�����Ϊ�յ�ʱ�򣬷���ͼƬ��������Ϊ1~100����ʾ�ı������������ֲ���ʾ
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case BUTTON:
			String s = editText.getText().toString();

			if (!"".equals(s) && s != null) {
				if (s.matches("\\d+")) {
					int i = Integer.valueOf(s);
					if (i > 100 || i < 1) {
						Toast.makeText(MyUIActivity.this, "������1~100֮�������",
								Toast.LENGTH_SHORT).show();
					} else {
						mImageView.setVisibility(View.GONE);
						scrollView.setVisibility(View.VISIBLE);
						initTextView(i);
					}
				} else {
					Toast.makeText(MyUIActivity.this, "����������",
							Toast.LENGTH_SHORT).show();
				}
			} else if ("".equals(s) || s == null) {
				Log.i("INFO", "s=\"\"");
				mImageView.setVisibility(View.VISIBLE);
				scrollView.setVisibility(View.GONE);
			}
			break;

		default:
			break;
		}

	}

	/**
	 * �������ּ�����Ӧ�����ı�
	 * 
	 * @param i
	 */
	private void initTextView(int i) {
		layout.removeAllViews();
		LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		for (int x = 1; x <= i; x++) {
			Log.i("x==", x + "");
			TextView textView = new TextView(MyUIActivity.this);
			textView.setText("�ı�" + x);
			textView.setId(10010 + x);
			textView.setLayoutParams(lParams);
			layout.addView(textView);
		}
	}
}