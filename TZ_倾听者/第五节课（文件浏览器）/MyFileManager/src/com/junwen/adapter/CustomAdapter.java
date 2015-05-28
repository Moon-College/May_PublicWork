package com.junwen.adapter;

import java.lang.ref.SoftReference;
import java.util.List;

import android.content.Context;
import android.content.ClipData.Item;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfilemanager.R;
import com.junwen.bean.FileItem;

public class CustomAdapter extends BaseAdapter{
	
private List<FileItem> data ;
private Context context;
private LayoutInflater inflater;

	public CustomAdapter(List<FileItem> data, Context context) {
	this.data = data;
	this.context = context;
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
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null)
		{
			convertView = inflater.inflate(R.layout.item_layout,null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.size = (TextView) convertView.findViewById(R.id.tv_size);
			holder.img = (ImageView) convertView.findViewById(R.id.img);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		//执行赋值
		//获取当前的文件对象
		FileItem fileItem = data.get(position);
		//设置文件的名字
		holder.name.setText(fileItem.getFileName());
		//如果有文件大小，就设置文件大小
		if(fileItem.getFileSize()!=null)
		{
			holder.size.setText(fileItem.getFileSize());
		}else
		{
			holder.size.setVisibility(View.GONE);
		}
		
		//如果是图片
		if(fileItem.isIsimg() ==true)
		{
		//如果图片的为空
		if(fileItem.getFileImg()==null)
		{	
				//则设置一个空的图片进去
				holder.img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.zelo));
				//启动异步线程来加载图片
				MyAsynTask task = new MyAsynTask();
				task.execute(fileItem.getFilePath(),String.valueOf(position));
		}else
		{
			//如果有图片，就直接添加进去
			holder.img.setImageBitmap(fileItem.getFileImg());
		}
		//如果是文件夹
		}else if(fileItem.isDirectory()){
			holder.img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.folder));
			//如果是文本文件
		}else if(fileItem.isTxt()){
			holder.img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.txt));
		}
		//如果是pdf文件
		else if(fileItem.isPdf()){
			holder.img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.iconfont_pdf));
		}
		return convertView;
	}
	class ViewHolder 
	{
		ImageView img;
		TextView name;
		TextView size;
	}
	class MyAsynTask extends AsyncTask<String, Void, Bitmap>
	{
		@Override
		protected Bitmap doInBackground(String... params) {
			//根据文件的路径获取图片
			//文件的路径
			String path = params[0];
			//要操作的索引
			int index= Integer.valueOf(params[1]);
			BitmapFactory.Options  option = new BitmapFactory.Options();
			option.inSampleSize = 4;
			Bitmap bitmap =BitmapFactory.decodeFile(path,option);
			//设置图片
			FileItem fileItem = data.get(index);
			//重新给这个设置图片
			fileItem.setFileImg(bitmap);
			return bitmap;
		}
		@Override
		protected void onPostExecute(Bitmap result) {
			//更新数据
		CustomAdapter.this.notifyDataSetChanged();
		}
	}
}
