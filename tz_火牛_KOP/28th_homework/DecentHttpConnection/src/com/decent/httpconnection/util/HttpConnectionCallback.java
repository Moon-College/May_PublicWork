package com.decent.httpconnection.util;

import com.decent.httpconnection.bean.HttpResult;

public interface HttpConnectionCallback {
	void afterRequest(HttpResult result);
}
