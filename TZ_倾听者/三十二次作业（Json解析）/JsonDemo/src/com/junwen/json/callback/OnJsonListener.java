package com.junwen.json.callback;

import com.junwen.json.model.User;

public interface OnJsonListener {

	void onSuccess(User user);
	void onFail(String result);
}
