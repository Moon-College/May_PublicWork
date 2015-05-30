package com.junwen.adapter;

import java.lang.ref.SoftReference;
import java.util.List;

import com.example.androidfile.R;
import com.junwen.bean.FileItem;

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

public class MyAdapter extends BaseAdapter {

	private Context conctext;
	private List<FileItem> data;
	private LayoutInflater inflater;
	private final int WIDTH = 75;
	private final int HEIGHT =75;

	public MyAdapter(Context conctext, List<FileItem> data) {
		this.conctext = conctext;
		this.data = data;
		inflater = (LayoutInflater) conctext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_layout, null);
			holder = new ViewHolder();
			holder.img = (ImageView) convertView.findViewById(R.id.img);
			holder.name = (TextView) convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		FileItem item = data.get(position);
		if(item.isIspic())
		{
			//�����ͼƬ
			if(item.getSoftBitmap().get() ==null)
			{
				//����������������û��bitmap���������ݲ����ˣ���������ͼƬ�������ˣ����������Ҫ���¼���ͼƬ��,�����첽����,��������֮ǰ�������һ����ͼ
				holder.img.setImageResource(android.R.color.white);
				//�����첽����ͼƬ
				MyAsynTask task = new MyAsynTask();
				//���ļ���·���͵�ǰҪ���ص��ļ��������Ž�ȥ���Ա����첽������ɺ󣬽���һһ��Ӧ�����ͼƬ
				task.execute(item.getFile_path(),String.valueOf(position));
			}else
			{
				//������������������ֵ�ˣ�˵���Ѿ��첽��������ˣ�ˢ�����������ˣ�����Ҫ����ͼƬ��
				holder.img.setImageBitmap(item.getSoftBitmap().get());
			}
			
		}else
		{
			//�������ͼƬ
			holder.img.setImageBitmap(item.getBitmap());
		}
		holder.name.setText(item.getFile_Name());
		return convertView;
	}

	class ViewHolder {
		ImageView img;
		TextView name;
	}
	/**
	 * �첽�̣߳��첽����ÿһ��ͼƬ
	 * @author admi
	 *
	 */
	class MyAsynTask extends AsyncTask<String, Void, SoftReference<Bitmap>>
	{

		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			//��ȡ�ļ���·���Լ�����
			String path = params[0];
			int index =Integer.valueOf(params[1]) ;
			//����·����ȡ��һ��bitmap
			Bitmap bitmap = readScale(path);
			//�ѻ�ȡ����bitmap����ӵ���������ȥ���ٰ�����������ָ���������ļ������ͼƬ������
			SoftReference<Bitmap> sfbitmap = new SoftReference<Bitmap>(bitmap);
			//����������ͼƬ
			data.get(index).setSoftBitmap(sfbitmap);
			return sfbitmap;
		}
		@Override
		protected void onPostExecute(SoftReference<Bitmap> result) {
			//����������,֪ͨˢ��ListView
			MyAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}
	/**
	 * ��ȡͼƬ������ͼ
	 * @param path
	 * @return
	 */
	public Bitmap readScale(String path) {
		Bitmap bitmap = null;
		//����options
		BitmapFactory.Options options = new  Options();
		//����Ϊtrue�󣬾Ͳ��������Ĵ������󣬶��ǻ�ȡͼƬ�Ŀ��
		options.inJustDecodeBounds = true;
		//��options�����ȥ����ȡ���ͼƬ�Ŀ�ߣ���û����������bitmap
		bitmap = BitmapFactory.decodeFile(path,options);
		int width = options.outWidth;
		int height = options.outHeight;
		//�������ű���
		options.inSampleSize = width/WIDTH;
		//������Ϊfalse�������ͻ������Ĵ���������
		options.inJustDecodeBounds=false;
		bitmap = BitmapFactory.decodeFile(path, options);
		return bitmap;
	}
}
