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
			//如果是图片
			if(item.getSoftBitmap().get() ==null)
			{
				//如果这个软引用里面没有bitmap，或者内容不足了，这个里面的图片被回收了，在这里就需要重新加载图片了,启动异步加载,但是在这之前，先添加一个空图
				holder.img.setImageResource(android.R.color.white);
				//启动异步加载图片
				MyAsynTask task = new MyAsynTask();
				//把文件的路径和当前要加载的文件的索引放进去，以便于异步加载完成后，进行一一对应的添加图片
				task.execute(item.getFile_path(),String.valueOf(position));
			}else
			{
				//如果这个软引用里面有值了，说明已经异步加载完成了，刷新是适配器了，现在要加载图片了
				holder.img.setImageBitmap(item.getSoftBitmap().get());
			}
			
		}else
		{
			//如果不是图片
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
	 * 异步线程，异步加载每一张图片
	 * @author admi
	 *
	 */
	class MyAsynTask extends AsyncTask<String, Void, SoftReference<Bitmap>>
	{

		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			//获取文件的路径以及索引
			String path = params[0];
			int index =Integer.valueOf(params[1]) ;
			//根据路径获取到一个bitmap
			Bitmap bitmap = readScale(path);
			//把获取到的bitmap，添加到软引用里去，再把这个对象存入指定索引的文件对象的图片属性里
			SoftReference<Bitmap> sfbitmap = new SoftReference<Bitmap>(bitmap);
			//给对象设置图片
			data.get(index).setSoftBitmap(sfbitmap);
			return sfbitmap;
		}
		@Override
		protected void onPostExecute(SoftReference<Bitmap> result) {
			//更新适配器,通知刷新ListView
			MyAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}
	/**
	 * 获取图片的缩略图
	 * @param path
	 * @return
	 */
	public Bitmap readScale(String path) {
		Bitmap bitmap = null;
		//创建options
		BitmapFactory.Options options = new  Options();
		//设置为true后，就不会真正的创建对象，而是获取图片的宽高
		options.inJustDecodeBounds = true;
		//把options放入进去，获取这个图片的宽高，而没有真正创建bitmap
		bitmap = BitmapFactory.decodeFile(path,options);
		int width = options.outWidth;
		int height = options.outHeight;
		//设置缩放比例
		options.inSampleSize = width/WIDTH;
		//再设置为false，这样就会真正的创建对象了
		options.inJustDecodeBounds=false;
		bitmap = BitmapFactory.decodeFile(path, options);
		return bitmap;
	}
}
