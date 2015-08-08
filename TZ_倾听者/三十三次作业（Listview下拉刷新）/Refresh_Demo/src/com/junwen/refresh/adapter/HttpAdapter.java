package com.junwen.refresh.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.refresh_demo.R;
import com.junwen.refresh.model.TextModel;
import com.junwen.refresh.util.HttpUtil;

public class HttpAdapter extends BaseAdapter{

	private Context context; //������
	private List<TextModel> data;
	private LayoutInflater inflater;
	
	public HttpAdapter(Context context, List<TextModel> data
			) {
		this.context = context;
		this.data = data;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_layout, null);
			holder.img = (ImageView) convertView.findViewById(R.id.img);
			holder.tv = (TextView) convertView.findViewById(R.id.text);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		TextModel textModel = data.get(position);
		holder.tv.setText(textModel.getContent());
		//���Ϊ�գ������첽���м���ͼƬ,�����ͼƬ�ˣ���ֱ����ʾ
		if(textModel.getImg()==null){
			ImageTask task = new ImageTask();
			task.execute(textModel.getImgUrl(),String.valueOf(position));
		}else if(textModel.getImg().get()!=null){
			holder.img.setImageBitmap(textModel.getImg().get());
		}
		return convertView;
	}
	
	public class ViewHolder{
		TextView tv;
		ImageView img;
	}
	
	/**
	 * �첽���첽����ͼƬ�����´��뵽���ݼ��Ȼ�����ˢ��Adapter
	 * @author Junwen
	 *
	 */
	public class ImageTask extends AsyncTask<String, Void, Bitmap>{
		
		private int position;
		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			URI path = null;
			HttpResponse response = null;
			int position = Integer.valueOf(params[1]);
			this.position = position;
			try {
				path = new URI(url);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			HttpGet httpget = new HttpGet(path);
			HttpClient client = new DefaultHttpClient();
			try {
				response = client.execute(httpget);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				try {
					InputStream content = response.getEntity().getContent();
				return BitmapFactory.decodeStream(content);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			TextModel textModel = data.get(position);
			//��ͼƬ����һ��������Ȼ�󷵻�
			Bitmap bitmap = HttpUtil.getBitmap(result);
			//����ͼƬ
			textModel.setImg(new SoftReference<Bitmap>(bitmap));
			//ˢ��Adapter
			HttpAdapter.this.notifyDataSetChanged();
		}
		
	}

}
