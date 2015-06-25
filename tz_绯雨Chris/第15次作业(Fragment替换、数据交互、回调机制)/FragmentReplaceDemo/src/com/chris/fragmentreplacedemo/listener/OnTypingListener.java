package com.chris.fragmentreplacedemo.listener;

import android.view.View;

public interface OnTypingListener
{
	void onTyping(String str, CharSequence s, int start, int before, int count);
}
