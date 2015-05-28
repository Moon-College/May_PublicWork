package com.tz.filebrowser.adapter;

import java.lang.ref.SoftReference;
import java.util.List;

import com.tz.filebrowser.R;
import com.tz.filebrowser.bean.MyFile;

import android.annotation.SuppressLint;
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
 * �ļ�������
 */
public class FileAdapter extends BaseAdapter {

	private List<MyFile> data;
	private Context context;
	private LayoutInflater inflater;

	public FileAdapter(Context context, List<MyFile> data) {
		this.context = context;
		this.data = data;
		this.inflater = LayoutInflater.from(context);
	}

	// �ö��ٸ���Ŀ
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

	// ÿ����Ŀ���������
	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			// ˵��û�л��棬����xml������ͼ
			convertView = inflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.file_img = (ImageView) convertView.findViewById(R.id.file_img);
			holder.file_name = (TextView) convertView.findViewById(R.id.file_name);
			convertView.setTag(holder); // ���鲼��holder�ŵ�convertView��һ�𻺴�
		} else {
			holder = (ViewHolder) convertView.getTag(); // ˵���л��棬��convertViewȡ�����ݷŵ�holderֱ����
		}
		MyFile myFile = data.get(position); // ��ȡ��Ӧ�±���ļ�����

		//�ж��Ƿ���ͼƬ
		if(myFile.isPic()){
			// �����ݰ󶨸��ؼ�
			// file.getBitmap()��������         	file.getBitmap().get()�Ƿ���ͼƬ����ʵͼƬ�� --> ǿ����
			if (myFile.getBitmap().get() == null) {
				// ˵����ͼƬ��ͼƬ��û�м��أ�����Ĭ��ͼƬΪ��ɫ����
				holder.file_img.setImageResource(android.R.color.white);
				// ����һ���첽����ȥ����ͼƬ
				MyTask task = new MyTask();
				task.execute(myFile.getFilePath(), String.valueOf(position));
			} else {
				// ��ͼƬ��ֱ���ó����ã��󶨸�ͼƬ�ؼ�
				// holder.file_img.setImageBitmap(myFile.getBitmap());
				holder.file_img.setImageBitmap(myFile.getBitmap().get());
			}
		}else if(myFile.isDir()){
			// �ж��Ƿ���Ŀ¼			 
			holder.file_img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.dirs));
		}else{
			// ��ͨ�ļ�
			holder.file_img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.file));
		}
		
		holder.file_name.setText(myFile.getFileName());

		return convertView;
	}

	private class MyTask extends AsyncTask<String, Void, Bitmap> {

		// ��̨����
		@Override
		protected Bitmap doInBackground(String... params) {
			String path = params[0]; // ͼƬ·��
			int positon = Integer.valueOf(params[1]); // ��Ӧ�ļ��±�
			// ����sd������ͼƬ
			BitmapFactory.Options opts = new Options();
			opts.inSampleSize = 2; // ͼƬ������С��ԭ����1/2��  ��СͼƬ��ԭ����1/4��ע��Ҫ��2�ı�����
			Bitmap bitmap = BitmapFactory.decodeFile(path, opts);

			// data.get(positon).setBitmap(bitmap); //����ͼƬ
			// return bitmap;

			data.get(positon).setBitmap(new SoftReference<Bitmap>(bitmap));  
			return null;
		}

		// �������˾ͻ�ִ�и÷������������߳���,ˢ��UI
		@Override
		protected void onPostExecute(Bitmap result) {
			// ˢ��������������UI
			FileAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);               
		}
	}

	private class ViewHolder {
		ImageView file_img;
		TextView file_name;
	}

}
