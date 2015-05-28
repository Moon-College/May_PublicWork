package com.tz.michael.adapter;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.tz.michael.activity.R;
import com.tz.michael.bean.FileBean;
import com.tz.michael.utils.ViewHolder;

public class MyFileListAdapter extends MyCustomAdapter<FileBean>{

	public MyFileListAdapter(Context mContext, List<FileBean> list, int layoutId) {
		super(mContext, list, layoutId);
	}

	@Override
	public void convert(ViewHolder holder, FileBean t) {
		ImageView img=holder.getView(R.id.img);
		if(t.getBitmap()==null){
			img.setImageResource(R.color.white);
			Task task=new Task();
			task.execute(t.getFilePath(),holder.position+"");
		}else{
			//普通的加载图片
			img.setImageBitmap(t.getBitmap().get());
		}
		holder.setText(R.id.tv_name, t.getFileName());
	}

	public class Task extends AsyncTask<String, Void, Bitmap>{

		/**
		 * 在后台线程
		 */
		@Override
		protected Bitmap doInBackground(String... params) {
//			BitmapFactory.Options options=new Options();
//			//长宽各缩小1/2
//			options.inSampleSize=2;
//			Bitmap bitmap=BitmapFactory.decodeFile(params[0],options);
			Bitmap bitmap=fitSizeImg(params[0]);
			int position=Integer.valueOf(params[1]);
			MyFileListAdapter.this.list.get(position).setBitmap(new SoftReference<Bitmap>(bitmap));
			return bitmap;
		}
		
		/**
		 * 此方法是在主线程中
		 */
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			MyFileListAdapter.this.notifyDataSetChanged();
		}

	}
	
	/**
	 * 返回适合尺寸的图片
	 * @param path 文件路径
	 * @return
	 */
	private  Bitmap fitSizeImg(String path) {
		if(path == null || path.length()<1 ) return null;
		File file = new File(path);
		Bitmap resizeBmp = null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		// 数字越大读出的图片占用的heap越小 不然总是溢出
		if (file.length() < 20480) { // 0-20k
		opts.inSampleSize = 1;
		} else if (file.length() < 51200) { // 20-50k
		opts.inSampleSize = 2;
		} else if (file.length() < 307200) { // 50-300k
		opts.inSampleSize = 4;
		} else if (file.length() < 819200) { // 300-800k
		opts.inSampleSize = 6;
		} else if (file.length() < 1048576) { // 800-1024k
		opts.inSampleSize = 8;
		} else {
		opts.inSampleSize = 10;
		}
		resizeBmp = BitmapFactory.decodeFile(file.getPath(), opts);
		return resizeBmp;
		}
	
}
