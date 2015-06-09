package com.qfx.filebrowser.adapter;

import java.lang.ref.SoftReference;
import java.util.List;

import com.qfx.filebrowser.R;
import com.qfx.filebrowser.bean.SdFile;
import com.qfx.filebrowser.util.BitmapCache;

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

public class FileListAdapter extends BaseAdapter {

	private Context context;
	private List<SdFile> data;
	private LayoutInflater inflater;
	private BitmapCache mCache;
	
	public FileListAdapter(Context context, List<SdFile> data) {
		this.context = context;
		this.data = data;
		this.inflater = LayoutInflater.from(context);
		mCache = BitmapCache.getInstance();
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public SdFile getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	private class ViewHolder {
		ImageView iv;
		TextView tv;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			Log.i("holder", "convertView create");
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_item, null);
			holder.iv = (ImageView) convertView.findViewById(R.id.iv_list_item);
			holder.tv = (TextView) convertView.findViewById(R.id.tv_list_item);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		SdFile sdFile = getItem(position);
		holder.tv.setText(sdFile.getFileName());
		if (sdFile.isDirectory()) { //是目录
			holder.iv.setImageResource(R.drawable.dirs);
		} else { //是文件：普通文件和图片文件
			if (sdFile.isPicture()) { //图片文件
				Bitmap bm = mCache.getBitmapFromCache(sdFile.getFilePath());
				if (bm == null) {
					holder.iv.setImageResource(R.drawable.ic_default_image);
					ImageTask task = new ImageTask();
					task.execute(sdFile.getFilePath());
				} else {
					holder.iv.setImageBitmap(bm);
				}
			} else { //普通文件
				holder.iv.setImageResource(R.drawable.file);
			}
			
		}
		return convertView;
	}
	
	private class ImageTask extends AsyncTask<String, Void, Bitmap> {
		

		@Override
		protected Bitmap doInBackground(String... params) {
			String path = params[0];
			
//			BitmapFactory.Options options = new BitmapFactory.Options();
//			options.inSampleSize = 2;//长宽各缩小1/2，  相当于总个图片缩小到1/4
//			Bitmap bitmap = BitmapFactory.decodeFile(path, options);
			//读取图片到内存前缩放图片
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			Bitmap bitmap = BitmapFactory.decodeFile(path, options);
			options.inSampleSize = calculateInSampleSize(options, 200, 400);
			options.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeFile(path, options);
			Log.i("bmp_width", "afterDecode:" + bitmap.getWidth());
			Log.i("bmp_height", "afterDecode:" + bitmap.getHeight());
			mCache.putBitmapToCache(path, bitmap);
			return bitmap;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			if (result != null) {
				notifyDataSetChanged();
			}
		}
		
		/**
		 * 计算缩放倍数
		 * @param options
		 * @param reqWidth
		 * @param reqHeight
		 * @return
		 */
		private int calculateInSampleSize(BitmapFactory.Options options,
				int reqWidth, int reqHeight) {
			final int height = options.outHeight;
			final int width = options.outWidth;
			int inSampleSize = 1;

			if (height > reqHeight || width > reqWidth) {

				final int heightRatio = Math.round((float) height
						/ (float) reqHeight);
				final int widthRatio = Math.round((float) width / (float) reqWidth);

				inSampleSize = heightRatio < widthRatio ? widthRatio : heightRatio;
			}
			return inSampleSize;
		}
		
	}

}
