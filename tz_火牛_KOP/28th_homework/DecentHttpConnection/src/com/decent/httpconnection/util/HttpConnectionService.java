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
	 * get��ʽ�ύ����
	 */
	public static final String GET_METHOD = "GET";
	/**
	 * post��ʽ�ύ����
	 */
	private static final String POST_METHOD = "POST";
	/**
	 * �ύ��ʽ������
	 */
	public static final String[] REQUEST_METHODS = { GET_METHOD, POST_METHOD };
	private static final String TAG = "HttpConnectionService";
	/**
	 * �ص�����
	 */
	private HttpConnectionCallback mHttpConnectionCallback;
	/**
	 * ����·��
	 */
	private String url;
	/**
	 * �ύ����ķ�ʽ
	 */
	private String method;
	/**
	 * ����
	 */
	private Map<String, String> parameters;

	/**
	 * ���캯��
	 * 
	 * @param mHttpConnectionCallback
	 *            �ص�
	 * @param url
	 *            ����·��
	 * @param method
	 *            ���ʷ���
	 * @param parameters
	 *            �����б�
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
	 * ����http������,����������첽�ģ� ���������HttpConnectionCallback�ص��� ���ڳɹ�����ʧ�ܵ�ʱ��ѽ�����ظ��ص�
	 */
	public void doRequestHttpConnection() {
		AsyncHttpRequestTask ahTask = new AsyncHttpRequestTask();
		ahTask.execute(null);
	}

	/**
	 * ʹ��get��������http
	 */
	private HttpResult doRequestHttpConnectionByGetMethod() {
		HttpResult result = new HttpResult();
		// 1������parameters����������б�ƴ�ӵ�url����
		StringBuffer sb = new StringBuffer();
		sb.append("?");
		for (Map.Entry<String, String> para : parameters.entrySet()) {
			sb.append(para.getKey());
			sb.append("=");
			sb.append(para.getValue());
			sb.append("&");
		}
		// ɾ���������Ǹ��ַ�(�����Ƕ�����Ǹ�?����&)��λ���Ǵ�0��ʼ�����
		sb.deleteCharAt(sb.length() - 1);
		// 2������url��connection,��������
		URL url = null;
		try {
			url = new URL(this.url + sb.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setResultCode(HttpResult.URL_ERROR);
			result.setContent("URL����");
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

		// 3�����ؽ��
		return result;
	}

	private HttpResult doRequestHttpConnectionByPostMethod() {
		// TODO Auto-generated method stub
		HttpResult result = new HttpResult();
		// 1���ȹ��촫�������ַ���
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
		
		// 2������url����ȡconnection�����Ұ�contentBtyesд��content
		URL url = null;
		try {
			url = new URL(this.url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setResultCode(HttpResult.URL_ERROR);
			result.setContent("URL����");
		}
		HttpURLConnection urlConnection = null;
		try {
			if (url != null) {
				urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setConnectTimeout(10000);
				urlConnection.setRequestMethod(POST_METHOD);
				//�������д����
				urlConnection.setDoOutput(true);//���ÿ����������д����
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
		// 3�����ؽ��
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
