package com.decent.httpconnection.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import android.os.AsyncTask;
import android.util.Log;

import com.decent.httpconnection.bean.HttpResult;

public class HttpConnectionService {
	/**
	 * get方式提交请求
	 */
	public static final String GET_METHOD = "GET";
	/**
	 * post方式提交请求
	 */
	private static final String POST_METHOD = "POST";
	/**
	 * 提交方式的数组
	 */
	public static final String[] REQUEST_METHODS = { GET_METHOD, POST_METHOD };
	private static final String TAG = "HttpConnectionService";
	/**
	 * 回调函数
	 */
	private HttpConnectionCallback mHttpConnectionCallback;
	/**
	 * 访问路径
	 */
	private String url;
	/**
	 * 提交请求的方式
	 */
	private String method;
	/**
	 * 参数
	 */
	private Map<String, String> parameters;

	/**
	 * 构造函数
	 * 
	 * @param mHttpConnectionCallback
	 *            回调
	 * @param url
	 *            访问路径
	 * @param method
	 *            访问方法
	 * @param parameters
	 *            参数列表
	 */
	public HttpConnectionService(
			HttpConnectionCallback mHttpConnectionCallback, String url,
			String method, Map<String, String> parameters) {
		super();
		this.mHttpConnectionCallback = mHttpConnectionCallback;
		this.url = url;
		this.method = method;
		this.parameters = parameters;
	}

	/**
	 * 请求http的连接,这个函数是异步的， 如果设置了HttpConnectionCallback回调， 会在成功或者失败的时候把结果返回给回调
	 */
	public void doRequestHttpConnection() {
		AsyncHttpRequestTask ahTask = new AsyncHttpRequestTask();
		ahTask.execute(null);
	}

	/**
	 * 使用get方法访问http
	 */
	private HttpResult doRequestHttpConnectionByGetMethod() {
		HttpResult result = new HttpResult();
		// 1、遍历parameters，构造参数列表，拼接到url后面
		StringBuffer sb = new StringBuffer();
		sb.append("?");
		for (Map.Entry<String, String> para : parameters.entrySet()) {
			sb.append(para.getKey());
			sb.append("=");
			sb.append(para.getValue());
			sb.append("&");
		}
		// 删除掉最后的那个字符(可能是多余的那个?或者&)，位置是从0开始计算的
		sb.deleteCharAt(sb.length() - 1);
		// 2、构造url和connection,访问网络
		URL url = null;
		try {
			url = new URL(this.url + sb.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setResultCode(HttpResult.URL_ERROR);
			result.setContent("URL错误");
		}

		HttpURLConnection urlConnection = null;
		try {
			if (url != null) {
				urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setConnectTimeout(10000);
				urlConnection.setRequestMethod(GET_METHOD);
				int resultCode = urlConnection.getResponseCode();
				result.setResultCode(resultCode);
				StringBuffer contentBuffer = new StringBuffer();
				String buffer;
				if (HttpResult.HTTP_OK == resultCode) {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(
									urlConnection.getInputStream()));
					while ((buffer = reader.readLine()) != null) {
						contentBuffer.append(buffer);
					}
					result.setContent(contentBuffer.toString());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}

		// 3、返回结果
		return result;
	}

	private HttpResult doRequestHttpConnectionByPostMethod() {
		// TODO Auto-generated method stub
		HttpResult result = new HttpResult();
		// 1、先构造传参数的字符串
		StringBuffer sb = new StringBuffer();
		if (parameters != null) {
			for (Map.Entry<String, String> para : parameters.entrySet()) {
				sb.append(para.getKey());
				sb.append("=");
				sb.append(para.getValue());
				sb.append("&");
			}
		}
		if (parameters != null && parameters.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		byte[] contentBtyes = sb.toString().getBytes();
		
		// 2、构造url，获取connection，并且把contentBtyes写进content
		URL url = null;
		try {
			url = new URL(this.url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setResultCode(HttpResult.URL_ERROR);
			result.setContent("URL错误");
		}
		HttpURLConnection urlConnection = null;
		try {
			if (url != null) {
				urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setConnectTimeout(10000);
				urlConnection.setRequestMethod(POST_METHOD);
				//向服务器写数据
				urlConnection.setDoOutput(true);//设置可以向服务器写数据
				urlConnection.setRequestProperty("Content-Length", String.valueOf(contentBtyes.length));				
				OutputStream outputStream = urlConnection.getOutputStream();
				outputStream.write(contentBtyes);
				int resultCode = urlConnection.getResponseCode();
				result.setResultCode(resultCode);
				StringBuffer contentBuffer = new StringBuffer();
				String buffer;
				if (HttpResult.HTTP_OK == resultCode) {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(
									urlConnection.getInputStream()));
					while ((buffer = reader.readLine()) != null) {
						contentBuffer.append(buffer);
					}
					result.setContent(contentBuffer.toString());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
		// 3、返回结果
		return result;
	}

	private class AsyncHttpRequestTask extends
			AsyncTask<Void, Void, HttpResult> {
		@Override
		protected HttpResult doInBackground(Void... params) {
			HttpResult httpResult = null;
			if (GET_METHOD.equals(method)) {
				httpResult = doRequestHttpConnectionByGetMethod();
			} else if (POST_METHOD.equals(method)) {
				httpResult = doRequestHttpConnectionByPostMethod();
			} else {
				Log.e(TAG, "This method is not supported at present");
			}
			return httpResult;
		}

		@Override
		protected void onPostExecute(HttpResult result) {
			// TODO Auto-generated method stub
			if (mHttpConnectionCallback != null) {
				mHttpConnectionCallback.afterRequest(result);
			}
		}
	}

	public HttpConnectionCallback getmHttpConnectionCallback() {
		return mHttpConnectionCallback;
	}

	public void setmHttpConnectionCallback(
			HttpConnectionCallback mHttpConnectionCallback) {
		this.mHttpConnectionCallback = mHttpConnectionCallback;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

}
