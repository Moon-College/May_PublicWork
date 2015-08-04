package com.junwen.refresh.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;

import com.alibaba.fastjson.JSON;
import com.junwen.refresh.constent.MyConstent;
import com.junwen.refresh.model.TextModel;
import com.tz.refresh.view.PullToRefreshLayout;

public class HttpUtil {
	
	private static int scale_value = 200; //����
	private static OnDownImageListener  onDownImageListener= null; //�ص��ӿ�
	private static PullToRefreshLayout pullToRefreshLayout = null; //����
	
	/**
	 * ����ָ������������ȡ��ָ����ֵ�ú�10����Ŀ
	 * @param number
	 * @return
	 */
	public static List<TextModel> getTextList(int number,OnDownImageListener onImageListener,
			PullToRefreshLayout pullToRefreshLayout){
		onDownImageListener = onImageListener;
		HttpUtil.pullToRefreshLayout = pullToRefreshLayout;
		//�����첽����ʼ��ȡ�µ���Ŀ��Ϣ
		MyTask task = new MyTask();
		task.execute(number);
		return null;
	}
	public static class MyTask extends AsyncTask<Integer, Void, String>{

		@Override
		protected String doInBackground(Integer... params) {
			int number = params[0];
			String url = MyConstent.SERVER_URL;
			URI url_http = null;
			try {
				url_http = new URI(url+"?number="+number);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			HttpGet httpGet = new HttpGet(url_http);
			HttpClient client = new DefaultHttpClient();
			try {
				HttpResponse response = client.execute(httpGet);
				if(response.getStatusLine().getStatusCode() == 200){
					return EntityUtils.toString(response.getEntity());
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			List<TextModel> list = JSON.parseArray(result,TextModel.class);
			if(list != null && list.size()>0){
					onDownImageListener.onSuccess(list,pullToRefreshLayout);
			}else{
				onDownImageListener.onFail("û������");
			}
		}
	}
	/**
	 * �ص��ӿ�,����ȡ������ɺ�ص�
	 * @author Junwen
	 *
	 */
	public interface OnDownImageListener{
		void onSuccess(List<TextModel> model,PullToRefreshLayout pullToRefreshLayout);
		void onFail(String result);
	}
	/**
	 * ��ͼƬ����һ��������
	 * @param bitmap
	 * @return
	 */
	public static Bitmap getBitmap(Bitmap bitmap){
		Bitmap bit = bitmap;
		int width = bit.getWidth();
		int height = bit.getHeight();
		float scale = (float)scale_value / width;
		Matrix matrix = new Matrix();
		matrix.postScale(scale, scale);
		Bitmap createBitmap = Bitmap.createBitmap(bit, 0, 0, width, height, matrix, true);
		return createBitmap;
	}

}
