package com.myandroid.fileSearch.adapter;

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

import com.myandroid.fileSearch.R;
import com.myandroid.fileSearch.bean.SdFile;

public class FileBaseAdapter extends BaseAdapter {

	final int WIDTH = 72;
	private Context context = null;
	private List<SdFile> mList = null;
	private LayoutInflater inflater = null;
	private SdFile sdFile = null;

	public FileBaseAdapter(Context context, List<SdFile> list) {
		this.context = context;
		this.mList = list;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return mList.size();
	}

	public Object getItem(int position) {
		return mList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.file_list_item, null);
			viewHolder = new ViewHolder();
			viewHolder.image = (ImageView) convertView
					.findViewById(R.id.imageFile);
			viewHolder.tvName = (TextView) convertView
					.findViewById(R.id.fileName);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		sdFile = mList.get(position);
		viewHolder.tvName.setText(sdFile.getName());
		if (sdFile.isPic()) {	
			if (sdFile.getSoftBitmap().get()==null) {
				//手机内存过高，回收了图片，通过异步加载软引用图片
				viewHolder.image.setImageResource(android.R.color.white);
				Task task=new Task();
				task.execute(sdFile.getFile_path(),String.valueOf(position));
			}else{
				//内存还不够高，通过软引用显示强引用图片
				viewHolder.image.setImageBitmap(sdFile.getSoftBitmap().get());
			}
		}else{
			//说明不是图片
			viewHolder.image.setImageBitmap(sdFile.getBitmap());
		}
		return convertView;
	}

	private class ViewHolder {
		private ImageView image;
		private TextView tvName;
	}

	/**
	 * 通过路径得到图片，按比例压缩图片
	 * 
	 * @param path
	 * @return
	 */
	public Bitmap scaleBitmap(String path) {
		Bitmap bitmap = null;
		BitmapFactory.Options options = new Options();   //图片压缩的参数
		//true的状态只加载图片的信息，不加载图片的内存
		options.inJustDecodeBounds = true;
		//通过路径获取图片
		bitmap = BitmapFactory.decodeFile(path, options);
		//原始图片的宽度
		int width = options.outWidth;
		if (width > WIDTH) {
			//压缩图片比例
			options.inSampleSize = width / WIDTH;
		}
		//false状态，加载图片内存
		options.inJustDecodeBounds=false;
		bitmap=BitmapFactory.decodeFile(path, options);		
		Log.i("INFO", "width:"+bitmap.getWidth()+","+bitmap.getHeight());
		return bitmap;
	}

	private class Task extends AsyncTask<String, Void, SoftReference<Bitmap>> {

		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			String path = params[0];
			int position = Integer.valueOf(params[1]);
//			BitmapFactory.Options options = new BitmapFactory.Options();
			SoftReference<Bitmap> softBitmap=new SoftReference<Bitmap>(scaleBitmap(path));
			mList.get(position).setSoftBitmap(softBitmap);// 设置图片
			return softBitmap;
		}

		@Override
		protected void onPostExecute(SoftReference<Bitmap> result) {
			FileBaseAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}

	}

}
