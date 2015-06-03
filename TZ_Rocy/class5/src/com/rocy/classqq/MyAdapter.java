package com.rocy.classqq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rocy.classqq.bean.SDCard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends BaseAdapter {
	private List<SDCard> list;
	private Context context;
	private LayoutInflater inflater;

	public MyAdapter(Context context, List<SDCard> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.lv_style, null);
			holder.face = (ImageView) convertView.findViewById(R.id.iv_face);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		SDCard card = list.get(position);
		holder.name.setText(card.getName());
		if (card.isMap()) {
			
				Log.i("info", "永永远远在训话");
				// 是图片
				holder.face.setBackground(new BitmapDrawable(
						context.getResources(), card.getBitmap()));
				if(!card.isDownLoad()){
				MyTask task = new MyTask();
				task.execute(card.getFile().getAbsolutePath(), position + "");
				}
		} else {
			// 不是图片
			holder.face.setBackground(new BitmapDrawable(
					context.getResources(), card.getBitmap()));
		}

		return convertView;
	}

	class MyTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			// 异步处理图片
			Bitmap bitmap = BitmapFactory.decodeFile(params[0]);
			BitmapFactory.Options options=new Options();
			options.inSampleSize=4;
			Log.i("info", "我们的路径" + params[0] + "我们的位置:" + params[1]);
			SDCard sdCard = list.get(Integer.valueOf(params[1]));
			sdCard.setBitmap(bitmap);
			sdCard.setDownLoad(true);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			MyAdapter.this.notifyDataSetChanged();
		}
	}
}


class ViewHolder {
	ImageView face;
	TextView name;
}


