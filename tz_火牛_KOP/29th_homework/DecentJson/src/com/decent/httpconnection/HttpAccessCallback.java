package com.decent.httpconnection;

import com.decent.httpconnection.bean.HttpResult;

public interface HttpAccessCallback {
	void afterRequest(HttpResult result);
}
