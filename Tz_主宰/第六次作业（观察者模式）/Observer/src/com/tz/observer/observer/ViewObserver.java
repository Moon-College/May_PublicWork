package com.tz.observer.observer;

import android.widget.TextView;

public class ViewObserver extends BaseObserver {
	private TextView mTextView;
	private String str;

	public ViewObserver(TextView mTextView, String str) {
		this.mTextView = mTextView;
		this.str = str;
	}

	public void setShowContent(String str) {
		this.str = str;
	}

	@Override
	public void onChange() {
		mTextView.setText(str);
	}
}
