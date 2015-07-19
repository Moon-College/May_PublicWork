/**
 * Project Name:lsn6_listview
 * File Name:FileAdapter.java
 * Package Name:com.zht.listview.adapter
 * Date:2015-6-9上午11:15:16
 * Copyright (c) 2015, shixi_hongtao@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.listview.adapter;

import java.lang.ref.SoftReference;
import java.util.List;

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
import com.zht.listview.R;
import com.zht.listview.bean.SdcardFile;

/**
 * ClassName:FileAdapter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-6-9 上午11:15:16 <br/>
 * 
 * @author acer
 * @version
 * @since JDK 1.6
 * @see
 */
public class FileAdapter extends BaseAdapter {
	private List<SdcardFile> mList;
	private Context mContext;
	private final int LIMIT_WIDTH = 100;
	private final int LIMIT_HEIGHT = 200;

	public FileAdapter(List<SdcardFile> mList, Context mContext) {
		this.mList = mList;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FileHolder holder;
		if (convertView == null) {
			holder = new FileHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_list, null);
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.text = (TextView) convertView.findViewById(R.id.text);
			convertView.setTag(holder);
		} else {
			holder = (FileHolder) convertView.getTag();
		}
		// 赋值
		SdcardFile mFile = mList.get(position);
//		if (mFile.getBitmap() == null) {
//			holder.icon.setImageResource(android.R.color.white);
//			MyTask task = new MyTask();
//			task.execute(mFile.getFilePath(), String.valueOf(position));
//		} else {
//			holder.icon.setImageBitmap(mFile.getBitmap());
//		}
		
		//使用软引用
		if(mFile.isBitmap()){
			if(mFile.getSoftBitmap().get()==null){
				holder.icon.setImageResource(android.R.color.white);
				MyTask2 task = new MyTask2();
				task.execute(mFile.getFilePath(),String.valueOf(position));
			}else{
				//说明内存还能接收，直接将强应用对象拿出来显示在imageview里
				holder.icon.setImageBitmap(mFile.getSoftBitmap().get());
			}
		}else{
			holder.icon.setImageBitmap(mFile.getBitmap());
		}

		holder.text.setText(mFile.getName());
		return convertView;
	}

	private class FileHolder {
		public ImageView icon;
		public TextView text;
	}

//	private class MyTask extends AsyncTask<String, Void, Bitmap> {
//
//		@Override
//		protected Bitmap doInBackground(String... params) {
//			String path = params[0];
//			int position = Integer.valueOf(params[1]);
//			BitmapFactory.Options options = new Options();
//			options.inSampleSize = 2;
//			//通过路径加载图片
//			Bitmap bitmap = BitmapFactory.decodeFile(path, options);
//			mList.get(position).setBitmap(bitmap);
//			return bitmap;
//		}
//
//		@Override
//		protected void onPostExecute(Bitmap result) {
//			//通知刷新列表
//			FileAdapter.this.notifyDataSetChanged();
//			super.onPostExecute(result);
//		}
//
//	}
	
	private class MyTask2 extends AsyncTask<String, Void, SoftReference<Bitmap>>{

		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			//获取路径和文件下标
			String path = params[0];
			int position = Integer.parseInt(params[1]);
			//缩放图片使用软引用
			SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(scaleBitmap(path));
			mList.get(position).setSoftBitmap(softBitmap);//设置图片
			return softBitmap;
		}

		@Override
		protected void onPostExecute(SoftReference<Bitmap> result) {
			FileAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
		
		
		
	}
	
	//根据图片的尺寸来进行图片缩放
	public Bitmap scaleBitmap(String path){
		Bitmap bitmap = null;
		BitmapFactory.Options options = new Options();
		options.inJustDecodeBounds = true;//不把图片加载到内存而获取图片的宽高
		bitmap = BitmapFactory.decodeFile(path, options);
		int outWidth = options.outWidth;
		int outHeight = options.outHeight;
		if(outWidth > LIMIT_WIDTH && outHeight > LIMIT_HEIGHT){
			if(outWidth > outHeight){
				options.inSampleSize = (outWidth/LIMIT_HEIGHT + outHeight/LIMIT_WIDTH)/2;
			}else{
				options.inSampleSize = (outWidth/LIMIT_WIDTH + outHeight/LIMIT_HEIGHT)/2;//计算缩放的倍数
			}
		}
		options.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(path,options);
		return bitmap;
	}
}
