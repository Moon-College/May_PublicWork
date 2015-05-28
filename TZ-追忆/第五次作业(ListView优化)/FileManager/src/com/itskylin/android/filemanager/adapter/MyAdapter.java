package com.itskylin.android.filemanager.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itskylin.android.filemanager.R;
import com.itskylin.android.filemanager.entity.SdFile;
import com.itskylin.android.filemanager.utils.ImageUtils;

/**
 * ClassName: MyAdapter
 * @Description: TODO
 * @author BlueSky QQ：345066543
 * @date 2015年5月27日
 */
public class MyAdapter extends BaseAdapter {

	private ArrayList<SdFile> data;
	private Context context;

	public MyAdapter(Context context, ArrayList<SdFile> data) {
		this.context = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		ViewHoper viewHoper = null;
		if (convertView == null) {
			view = View.inflate(context, R.layout.items, null);
			viewHoper = new ViewHoper();
			viewHoper.fileImg = (ImageView) view.findViewById(R.id.file_img);
			viewHoper.fileName = (TextView) view.findViewById(R.id.file_name);
			view.setTag(viewHoper);
		} else {
			view = convertView;
			viewHoper = (ViewHoper) view.getTag();
		}

		viewHoper.fileName.setText(data.get(position).getName());
		if (data.get(position).getBitmap() == null) {

			viewHoper.fileImg.setImageBitmap(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.loading));

			// 加载旋转动画
			Animation animation = AnimationUtils.loadAnimation(context,
					R.anim.img_rotate);

			viewHoper.fileImg.startAnimation(animation);

			// 异步加载图片
			ImageLoaderAsyncTask task = new ImageLoaderAsyncTask();
			task.execute(data.get(position).getFile().getAbsoluteFile()
					.toString(), String.valueOf(position));
		} else {
			viewHoper.fileImg.setImageBitmap(data.get(position).getBitmap());
		}

		return view;
	}

	@SuppressWarnings("all")
	private class ViewHoper {
		ImageView fileImg;
		TextView fileName;
		TextView fileCreateTime;
		TextView fileUpdateTime;
	}

	private class ImageLoaderAsyncTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			String filePath = params[0];
			int position = Integer.parseInt(params[1]);
			Bitmap bitmap = ImageUtils.getThumbnail(context, filePath);
			data.get(position).setBitmap(bitmap);
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			MyAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}
}
