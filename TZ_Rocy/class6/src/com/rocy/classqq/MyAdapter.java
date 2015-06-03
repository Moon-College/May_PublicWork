package com.rocy.classqq;

import java.lang.ref.SoftReference;
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
	private static int limit_with=50;
	private static int limit_height=50;

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
			   if(card.getSoftBitmap()==null){
				  Log.i("info", "永永远远在训话");
					// 是图片
					holder.face.setBackground(new BitmapDrawable(
							context.getResources(), card.getBitmap()));
					if(!card.isDownLoad()){
					MyTask task = new MyTask();
					task.execute(card.getFile().getAbsolutePath(), position + "");
					}
			   }else{
				   holder.face.setBackground(new BitmapDrawable(
							context.getResources(), card.getSoftBitmap().get()));
			   }
				
		} else {
			// 不是图片
			holder.face.setBackground(new BitmapDrawable(
					context.getResources(), card.getBitmap()));
		}

		return convertView;
	}

	class MyTask extends AsyncTask<String, Void, Void> {

		@SuppressLint("NewApi")
		@Override
		protected Void doInBackground(String... params) {
			// 第一次获得原始图片数据
			BitmapFactory.Options options=new Options();
			Log.i("info", "width--"+options.outWidth+",height--"+options.outWidth);
			options.inJustDecodeBounds=true;
			BitmapFactory.decodeFile(params[0],options);
			//第二次获得需要处理的数据；
			//获取缩放比例
			int width=options.outWidth;
			int height=options.outHeight;
			Log.i("info", "width"+options.outWidth+",height"+options.outWidth);
			BitmapFactory.Options options2=new Options();
			options2.inJustDecodeBounds=false;
			if(width>limit_with &&height>limit_height){
				options2.inSampleSize=height/limit_with;
				
			}else{
				options2.inSampleSize=1;
			}
			
			Bitmap scaleBitmap = BitmapFactory.decodeFile(params[0],options2);
			Log.i("info", "width:"+options2.outWidth+",height:"+options2.outHeight);
			SDCard sdCard = list.get(Integer.valueOf(params[1]));
			//添加到软引用
			SoftReference<Bitmap> softBitmap =new SoftReference<Bitmap>(scaleBitmap);
			sdCard.setSoftBitmap(softBitmap);
//			sdCard.setBitmap(scaleBitmap);
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


