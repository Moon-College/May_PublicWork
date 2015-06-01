package com.cn.test.adapter;

import java.util.List;

import com.cn.test.R;
import com.cn.test.been.SdFile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created on 2015-5-28 ����9:33:06 MyAdapter.java<br/>
 * Author ZhuHequn 1363790125@qq.com <br/>
 * version 1.0 TODO
 */
public class MyAdapter extends BaseAdapter {
	private Context context;
	private List<SdFile> data;
	LayoutInflater inflater;

	public MyAdapter(Context context, List<SdFile> data) {
		this.context = context;
		this.data = data;
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			// û�л������´������������Ժ���뵽����
			convertView = inflater.inflate(R.layout.list, null);
			holder = new ViewHolder();
			holder.file_img = (ImageView) convertView
					.findViewById(R.id.file_img);
			holder.file_name = (TextView) convertView
					.findViewById(R.id.file_name);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();

		}

		SdFile msdfile = data.get(position);
		if (msdfile.getBitmap() == null) {
			// ͼƬ��Ϊ���أ�����һ���첽����ȥ����ͼƬ
			// ����Ĭ��ͼƬ
			holder.file_img.setImageResource(android.R.color.white);
			MyTask task = new MyTask();
			task.execute(msdfile.getFilepath().toString(),
					String.valueOf(position), "txt");
		}

		return convertView;
	}

	public class ViewHolder {
		ImageView file_img;
		TextView file_name;

	}

	private class MyTask extends AsyncTask<String, Void, Bitmap> {
		// �����߳��ں�̨����
		@Override
		protected Bitmap doInBackground(String... params) {
			// ����ͼƬ·��
			String path = params[0];
			// �����ļ��±�
			int position = Integer.valueOf(params[1]);
			BitmapFactory.Options options = new Options();
			
			// ͼƬ��С��1/4
			options.inSampleSize = 2;
			// ͨ��·�����ص�ͼƬ
			Bitmap bitmap = BitmapFactory.decodeFile(path, options);
			// ����ͼƬ
			data.get(position).setBitmap(bitmap);
			return bitmap; 
		}
		// ������Ϻ��ṩˢ��UI
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			MyAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}
}