package com.xigua.filebrowser;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileAdapter extends BaseAdapter{
	final int LIMIT_WIDTH = 120;
	private Context context;
	private List<cFile> data;
	private LayoutInflater inflater;
	public FileAdapter(Context context,List<cFile> data){
		this.context = context;
		this.data = data;
		this.inflater = LayoutInflater.from(context);
	}
	
	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return data.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.list_item, null);
			ImageView file_img = (ImageView) convertView.findViewById(R.id.file_img);
			TextView file_name = (TextView) convertView.findViewById(R.id.file_name);
			holder = new ViewHolder();
			holder.file_img = file_img;
			holder.file_name = file_name;
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		cFile cFile = data.get(position);
		if(cFile.isPic()){
		if(cFile.getSoftBitmap().get() == null&&!cFile.getName().equals("·µ»Ø")){
			holder.file_img.setImageResource(android.R.color.darker_gray);
			MyTask task = new MyTask();
			task.execute(cFile.getFilePath(),String.valueOf(position));
		}else{
			holder.file_img.setImageBitmap(cFile.getSoftBitmap().get());
		}
		}
		else{
			holder.file_img.setImageBitmap(cFile.getBitmap());
		}
		holder.file_name.setText(cFile.getName());
		return convertView;
	}
	
	private class ViewHolder{
		ImageView file_img;
		TextView file_name;
	}
	
	private class MyTask extends AsyncTask<String, Void, SoftReference<Bitmap>>{
		
		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			String path = params[0];
			int position = Integer.valueOf(params[1]);
//			BitmapFactory.Options options = new Options();
//			options.inSampleSize = 2;
//			Bitmap bitmap = BitmapFactory.decodeFile(path,options);
			SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(scaleBitmap(path));
			data.get(position).setSoftBitmap(softBitmap);
			return softBitmap;
		}
		
		@Override
		protected void onPostExecute(SoftReference<Bitmap> result) {
			FileAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
		
		/**
		 * ¸ù¾ÝÍ¼Æ¬³ß´çÀ´Ëõ·ÅÍ¼Æ¬
		 * @param path
		 * @return
		 */
		public Bitmap scaleBitmap(String path){
			Bitmap bitmap = null;
			BitmapFactory.Options options = new Options();
			options.inJustDecodeBounds = true;//
//			options.inSampleSize = 2;
			bitmap = BitmapFactory.decodeFile(path,options);
			int width = options.outWidth;
			int height = options.outHeight;
			Log.i("zzzzzzzzzzzzzzzzzyf", width+"");
			Log.i("zzzzzzzzzzzzzzzzzyf", height+"");
			if(width>LIMIT_WIDTH){
					options.inSampleSize = (width/LIMIT_WIDTH + height/(height/(width/LIMIT_WIDTH)))/2;
			}
			else{
				options.inSampleSize = 1;
			}
			Log.i("zzzzzzzzzzzzzzzzzyf", options.inSampleSize+"");
			options.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeFile(path,options);
			return bitmap;
		}
	}
}
