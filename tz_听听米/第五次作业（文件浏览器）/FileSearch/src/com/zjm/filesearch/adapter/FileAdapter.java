package com.zjm.filesearch.adapter;

import java.lang.ref.SoftReference;
import java.util.List;

import com.zjm.filesearch.R;
import com.zjm.filesearch.bean.MyFile;
import com.zjm.filesearch.util.ImgUtil;

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

/**
 * listview三层优化
 * */
public class FileAdapter extends BaseAdapter {
	
	private static final String TAG = FileAdapter.class.getSimpleName().toString();
	
	private List<MyFile> data;
	private Context context;
	private LayoutInflater inflater;
	private MyFile myFile;
	
	public FileAdapter(Context context,List<MyFile> data) {
		this.context = context;
		this.data = data;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		// TODO Auto-generated method stub
		return position;
	}
	
	private class ViewHolder{
		ImageView img;
		TextView tv;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(null == convertView){
			convertView = inflater.inflate(R.layout.list_file, null);
			holder = new ViewHolder();
			holder.img = (ImageView) convertView.findViewById(R.id.img);
			holder.tv = (TextView) convertView.findViewById(R.id.tv);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		myFile = data.get(position);
		Log.i(TAG, "position:"+position);
		if(myFile.isPic()){
			
			if(myFile.getBitmap().get() == null){
				holder.img.setImageResource(R.color.white);
				MyTask myTask = new MyTask();//异步线程
				myTask.execute(myFile.getPath(),String.valueOf(position));
			}else{
				holder.img.setImageBitmap(myFile.getBitmap().get());
			}
			
		}else{
			holder.img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.th_bookshelf));
		}
		
		holder.tv.setText(myFile.getName());
		return convertView;
	}
	
	
	private class MyTask extends AsyncTask<String, Void, Bitmap>{
		

		@Override
		protected Bitmap doInBackground(String... params) {
			String path = params[0];
			int position = Integer.parseInt(params[1]);
			
			BitmapFactory.Options options = new BitmapFactory.Options();
//			options.inSampleSize = 2;
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(path, options);
			
			options.inSampleSize = ImgUtil.computeSampleSize(options, -1, 128*128);  //动态计算缩放比例
			options.inPreferredConfig = Bitmap.Config.RGB_565; 
			options.inJustDecodeBounds = false;
			options.inPurgeable = true;
			options.inInputShareable = true;
			data.get(position).setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeFile(path, options)));
			
			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			FileAdapter.this.notifyDataSetChanged();
		}

		
		
	}
	

}
