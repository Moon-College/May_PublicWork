package com.zjm.filesearch.adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.List;

import com.zjm.filesearch.R;
import com.zjm.filesearch.bean.MyFile;
import com.zjm.filesearch.util.ImgUtil;

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
		//Log.i(TAG, "position:"+position);
		if(myFile.isPic()){
			//说明软引用中的强引用对象被回收
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
	
	
	private class MyTask extends AsyncTask<String, Void, SoftReference<Bitmap>>{
		

		@Override
		protected SoftReference<Bitmap> doInBackground(String... params) {
			String path = params[0];
			int position = Integer.parseInt(params[1]);
			SoftReference<Bitmap> softBitmap = null;
//			data.get(position).setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeFile(path, options)));
			try {
				softBitmap = new SoftReference<Bitmap>(scaleBitmap(path,100,200));
				data.get(position).setBitmap(softBitmap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return softBitmap;
		}
		
		public Bitmap scaleBitmap(String path,int limitWidth,int limitHeight){
			Bitmap bitmap = null;
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;//加载图形的尺寸而不是图像本身
			BitmapFactory.decodeFile(path, options);
			bitmap = BitmapFactory.decodeFile(path,options);
			int width = options.outWidth;
			int height = options.outHeight;
			if(width > limitWidth&&height > limitHeight){
				if(width>height){
					options.inSampleSize = (width/limitHeight +height/limitHeight)/2;
				}else{
					options.inSampleSize = (width/limitWidth +height/limitWidth)/2;
				}
			}
			options.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeFile(path, options);
			Log.d(TAG, "width:"+bitmap.getWidth()+"height:"+bitmap.getHeight());
			return bitmap;
		}
		
		public Bitmap scaleBitmap(String path) throws FileNotFoundException{
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;//加载图形的尺寸而不是图像本身
			BitmapFactory.decodeFile(path, options);
			options.inSampleSize = ImgUtil.computeSampleSize(options, -1, 45*45);  //动态计算缩放比例
			options.inPreferredConfig = Bitmap.Config.RGB_565; 
			
			/** 如果 inPurgeable 设为True的话表示使用BitmapFactory创建的Bitmap 
		     * 用于存储Pixel的内存空间在系统内存不足时可以被回收， 
		     * 在应用需要再次访问Bitmap的Pixel时（如绘制Bitmap或是调用getPixel）， 
		     * 系统会再次调用BitmapFactory decoder重新生成Bitmap的Pixel数组。  
		     * 为了能够重新解码图像，bitmap要能够访问存储Bitmap的原始数据。 
		     *  
		     * 在inPurgeable为false时表示创建的Bitmap的Pixel内存空间不能被回收， 
		     * 这样BitmapFactory在不停decodeByteArray创建新的Bitmap对象， 
		     * 不同设备的内存不同，因此能够同时创建的Bitmap个数可能有所不同， 
		     * 200个bitmap足以使大部分的设备重新OutOfMemory错误。 
		     * 当isPurgable设为true时，系统中内存不足时， 
		     * 可以回收部分Bitmap占据的内存空间，这时一般不会出现OutOfMemory 错误。*/
			options.inPurgeable = true;
			options.inInputShareable = true;//设置是否深拷贝，与inPurgeable结合使用，inPurgeable为false时，该参数无意义。 
			/*通过设置Options.inPreferredConfig值来降低内存消耗： 
		               默认为ARGB_8888: 每个像素4字节. 共32位。 
		     Alpha_8: 只保存透明度，共8位，1字节。 
		     ARGB_4444: 共16位，2字节。 
		     RGB_565:共16位，2字节。 
		                如果不需要透明度，可把默认值ARGB_8888改为RGB_565,节约一半内存*/
			options.inPreferredConfig = Bitmap.Config.RGB_565;
			File file = new File(path);
			/*尽量不要使用setImageBitmap或setImageResource或BitmapFactory.decodeResource来设置一张大图，
			因为这些函数在完成decode后，最终都是通过java层的createBitmap来完成的，需要消耗更多内存。 
			因此，改用先通过BitmapFactory.decodeStream方法，创建出一个bitmap，
			再将其设为ImageView的source，decodeStream最大的秘密在于其直接调用JNI>>nativeDecodeAsset()来完成decode，
			无需再使用java层的createBitmap，从而节省了java层的空间。*/
			FileInputStream  is = new FileInputStream(file);
			options.inJustDecodeBounds = false;
			return BitmapFactory.decodeStream(is, null, options);
		}
		
	
		

		@Override
		protected void onPostExecute(SoftReference<Bitmap> result) {
			notifyDataSetChanged();
			super.onPostExecute(result);
		}

		
		
	}
	

}
