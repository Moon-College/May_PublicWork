package com.example.wenjianliulanqi.adapter;

import java.util.List;

import com.example.wenjianliulanqi.R;
import com.example.wenjianliulanqi.bean.SDFile;

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
    private List<SDFile>data;
    private LayoutInflater inflater;
    
    public FileAdapter(Context context,List<SDFile>data){
    	this.context=context;
    	this.data=data;
    	this.inflater=LayoutInflater.from(context);
    			
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if(convertView==null){
		convertView=inflater.inflate(R.layout.list_item, null);
		holder=new ViewHolder();
		holder.file_img=(ImageView)convertView.findViewById(R.id.file_img);
		holder.file_name=(TextView)convertView.findViewById(R.id.file_name);
		convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		SDFile sdFile=data.get(position);
		
		if(sdFile.getBitmap()==null){
			//图片还在加载，启动一个异步任务去加载图片
			//设置默认图片
			holder.file_img.setImageResource(R.drawable.ic_launcher);
			MyTask task=new MyTask();
			task.execute(sdFile.getFilePath(),String.valueOf(position),"hello");
		}else{
		holder.file_img.setImageBitmap(sdFile.getBitmap());
		}
		holder.file_name.setText(sdFile.getName());
		return convertView;
	}
	class ViewHolder{
		ImageView file_img;
		TextView file_name;
	}
	private class MyTask extends AsyncTask<String, Void, Bitmap>{

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			String path=params[0];
			int position=Integer.valueOf(params[1]);//文件下标
			BitmapFactory.Options options=new Options();
			options.inSampleSize=2; //图片缩小到1/4
			Bitmap bitmap=BitmapFactory.decodeFile(path, options);
			data.get(position).setBitmap(bitmap);
			return bitmap;
		}
		//加载完毕后提供刷新UI
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			FileAdapter.this.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	}

}
