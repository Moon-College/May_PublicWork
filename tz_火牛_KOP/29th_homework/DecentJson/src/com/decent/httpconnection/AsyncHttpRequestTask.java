package com.decent.httpconnection;

import android.os.AsyncTask;

import com.decent.httpconnection.bean.HttpResult;

public class AsyncHttpRequestTask extends AsyncTask<Void, Void, HttpResult> {

	private HttpAccessService httpAccessService;

	public AsyncHttpRequestTask(HttpAccessService httpAccessService) {
		super();
		this.httpAccessService = httpAccessService;
	}

	@Override
	protected HttpResult doInBackground(Void... params) {
		HttpResult httpResult = httpAccessService.doRequestHttpAccess();
		return httpResult;
	}

	@Override
	protected void onPostExecute(HttpResult result) {
		// TODO Auto-generated method stub
		HttpAccessCallback haCallback = httpAccessService
				.getHttpAccessCallback();
		if (haCallback != null) {
			haCallback.afterRequest(result);
		}
	}

}
