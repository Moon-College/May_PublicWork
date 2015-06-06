package com.lzq.file_brower_listview;

import java.lang.ref.SoftReference;
import java.util.List;

import android.R.integer;
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

public class FileAdapter extends BaseAdapter {
	private Context context;
	private List<FileBean> data;
	private LayoutInflater mInflater;

	private final int LIMISIZE = 100;

	public FileAdapter(Context context, List<FileBean> data) {
		this.context = context;
		this.data = data;
		mInflater = LayoutInflater.from(context);
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
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.list_item, null);
			holder.iv = (ImageView) convertView.findViewById(R.id.iv);
			holder.tv = (TextView) convertView.findViewById(R.id.tv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		FileBean file = data.get(position);

		if (file.isPic) {// 如果是图片
			if (file.softBitmap.get() == null) {// 如果软引用中的强引用对象为空
				holder.iv.setImageResource(android.R.color.black);

				MyTasck myTasck = new MyTasck();
				myTasck.execute(file.file_path, String.valueOf(position));
			} else {
				holder.iv.setImageBitmap(file.softBitmap.get());
			}
		} else {// 如果不是图片
			holder.iv.setImageBitmap(file.bitmap);
		}

		// if (file.bitmap == null) {
		// holder.iv.setImageResource(android.R.color.black);// 设置默认背景
		// new MyTasck().execute(file.file_path, String.valueOf(position));//
		// 开线程加载图片
		// } else {
		// holder.iv.setImageBitmap(file.bitmap);
		// }

		holder.tv.setText(file.name);

		return convertView;
	}

	public class ViewHolder {
		ImageView iv;
		TextView tv;
	}

	class MyTasck extends AsyncTask<String, Void, SoftReference<Bitmap>> {

		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			String file_path = params[0];// 文件的路径
			int position = Integer.valueOf(params[1]);// positon
			// BitmapFactory.Options options = new Options();
			// options.inSampleSize = 4;// 长和宽缩放为 原来的1/4 就是 缩小了 1/16
			// Bitmap bitmap = BitmapFactory.decodeFile(file_path, options);
			SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(scaleBitmap(file_path));
//			data.get(Integer.valueOf(position)).bitmap = bitmap;
			
			data.get(position).softBitmap = softBitmap;
			return softBitmap;
		}

		public Bitmap scaleBitmap(String file_path) {
			
			Bitmap bitmap = null;
			BitmapFactory.Options options = new Options();
			options.inJustDecodeBounds = true;
			bitmap = BitmapFactory.decodeFile(file_path, options);
			int width = options.outWidth;
			int height = options.outHeight;

			if (width > LIMISIZE && height > LIMISIZE) {
				options.inSampleSize = width / LIMISIZE;
			}

			options.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeFile(file_path, options);
			
//			SoftReference<Bitmap> softReference = new SoftReference<Bitmap>(bitmap);
			return bitmap;
		}

		@Override
		protected void onPostExecute(SoftReference<Bitmap> result) {
			FileAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}

	}

}
