package com.riders.browser.adapter;

import java.lang.ref.SoftReference;
import java.util.List;
import com.riders.browser.R;
import com.riders.browser.entity.SdFile;
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

/**
 * 
 * @ClassName: RidersListAdapter
 * @Description: TODO
 * @author: Riders
 * @date: 2015-5-27 下午5:51:00 自定义的文件浏览 适配器
 */
public class RidersListAdapter extends BaseAdapter {
	private Context context;
	private List<SdFile> dataList;
	private LayoutInflater inflater;

	public RidersListAdapter(Context context, List<SdFile> sdFiles) {
		this.context = context;
		this.dataList = sdFiles;
		this.inflater = LayoutInflater.from(context);
	}

	/**
	 * 条目数量
	 */
	@Override
	public int getCount() {
		return dataList.size();
	}

	/**
	 * 获取条目
	 */
	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	/**
	 * item的下标(索引)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * getVeiw 视图显示
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		// 把视图加入到 convertView 缓存
		if (convertView == null) {
			// convertView为空的情况
			convertView = inflater.inflate(R.layout.browse_item, null);
			ImageView imgFile = (ImageView) convertView
					.findViewById(R.id.img_file);
			TextView fileName = (TextView) convertView
					.findViewById(R.id.file_name);
			// 绑定组件给 viewHolder
			viewHolder = new ViewHolder();
			viewHolder.img = imgFile;
			viewHolder.name = fileName;
			// 设置缓存
			convertView.setTag(viewHolder);
		} else {
			// convertView 不为空
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// 拿到对应 position的 SDfile对象
		SdFile sdFile = dataList.get(position);
		//是图片
		if (sdFile.isPic()) {
			if (sdFile.getSoftReference().get() == null) {
				//软引用为空，代表这个软引用的强引用被回收了 内存过高
				//需要调用异步线程加载软引用图片
				//设置默认显示图片
				viewHolder.img.setImageResource(R.drawable.photo_loading);
				ImageTask task = new ImageTask();
				task.execute(sdFile.getFilePath(),String.valueOf(position));
			}else {
				//内存没有过高 直接用软引用的强应用赋值给ImageView
				viewHolder.img.setImageBitmap(sdFile.getSoftReference().get());
			}
		}else {
			//不是图片
			viewHolder.img.setImageBitmap(sdFile.getBitmap());
		}
		/*if (sdFile.getBitmap() == null) {
			// 图片为空 开异步线程加载
			// 设置默认
			viewHolder.img.setImageResource(R.drawable.photo_loading);
			ImageTask task = new ImageTask();
			task.execute(sdFile.getFilePath(),String.valueOf(position));
		}else{
			//从缓存视图获取
			viewHolder.img.setImageBitmap(sdFile.getBitmap());
		}*/
		viewHolder.name.setText(sdFile.getFileName());
		return convertView;
	}

	/**
	 * 
	 * @ClassName: ViewHolder
	 * @Description: TODO
	 * @author: Riders
	 * @date: 2015-5-27 下午5:51:38 
	 * 定义viewholder类封装 组件
	 */
	private class ViewHolder {
		ImageView img;
		TextView name;
	}
	/**
	 * 
	 * @ClassName: ImageTask
	 * @Description: TODO
	 * @author: Riders
	 * @date: 2015-5-27 下午7:28:10
	 * AsyncTask<Params, Progress, Result> 第一个参数是路径，
	 * 第二个参数是加载时的进度条，第三个参数是加载后要返回的结果
	 * String... params 可变参数
	 */
	private static final int MAX_WIDTH = 100;
	private class ImageTask extends AsyncTask<String, Void, SoftReference<Bitmap>>{
		//异步线程启动 后台加载
		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			String key = params[0];//拿到传进来的图片路径
			int position = Integer.parseInt(params[1]);//图片所对应的下标
			//对图片进行缩放避免出现OOM 加入软引用
			SoftReference<Bitmap> reference = new SoftReference<Bitmap>(scaleBitmap(key));
			dataList.get(position).setSoftReference(reference);
//			BitmapFactory.Options options = new Options();
//			options.inJustDecodeBounds = true;
//			options.inSampleSize = 4; //图片缩小1/16
//			Bitmap bitmap = BitmapFactory.decodeFile(key, options);//把缩小后的图片放到bitmap中
//			int width = options.outWidth;
//			int height = options.outHeight;
//			
//			if (width >MAX_WIDTH) {
//				options.inSampleSize = (width/MAX_WIDTH + height / MAX_WIDTH)/2;
//			}
//			options.inJustDecodeBounds = false;
//			bitmap = BitmapFactory.decodeFile(key,options);
//			dataList.get(position).setBitmap(bitmap);//设置图片显示
			return reference;
		}
		
		private Bitmap scaleBitmap(String key) {
			Bitmap bitmap = null;
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;//图片不被输出
			bitmap = BitmapFactory.decodeFile(key, options);
			//拿到输出的图片大小
			int width = options.outWidth;
			int height = options.outHeight;
			if (width > MAX_WIDTH) {
				options.inSampleSize = width/MAX_WIDTH;
			}
			options.inJustDecodeBounds = false;//图片可输出
			bitmap = BitmapFactory.decodeFile(key, options);
			return bitmap;
		}

		//加载完 刷新主线程UI
		@Override
		protected void onPostExecute(SoftReference<Bitmap> result) {
			RidersListAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
		
	}
}
