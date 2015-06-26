package com.tangzhi.cn;

import java.io.File;

import com.tangzhi.cn.util.BaseAsyncTaskTry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
    
    private Button loadimg;
	private Button loadFileApk;
	private TextView tvimg;
	private TextView tvfile;
	private ProgressBar barimg;
	private ProgressBar barfile;
	private ImageView showimg;
	/**图片路径*/
	private String path="http://news.shangdu.com/gaoqing/20110329/P_260797_1__853934915.jpg";
    /**文件eg ：apk的路径*/
	private String file_path="http://www.pumasoft.cn/Qixin_pumasoft.apk";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initUI();
    }

	private void initUI() {
		loadimg=(Button)findViewById(R.id.btn_loadImg);
		loadFileApk=(Button)findViewById(R.id.btn_loadFileApk);
		tvimg=(TextView)findViewById(R.id.tv1);
		tvfile=(TextView)findViewById(R.id.tv2);
		barimg=(ProgressBar)findViewById(R.id.bar1);
		barfile=(ProgressBar)findViewById(R.id.bar2);
		showimg=(ImageView)findViewById(R.id.imgview);
		loadimg.setOnClickListener(this);
		loadFileApk.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_loadImg://下载图片
			LoadImgAsyncTask imgTask = new LoadImgAsyncTask(this,barimg,tvimg);
			imgTask.execute(path);
			break;
		case R.id.btn_loadFileApk://下载文件
			LoadFileAsynTask fileTask = new LoadFileAsynTask(this,barfile,tvfile);
			fileTask.execute(file_path);
			break;
		default:
			break;
		}
		
	}
	
	class LoadImgAsyncTask extends BaseAsyncTaskTry {

		public LoadImgAsyncTask(Context context, ProgressBar progressBar,
				TextView tv) {
			super(context, progressBar, tv);
			
		}
		@Override
		protected void onPostExecute(File result) {
			Bitmap bitmap=BitmapFactory.decodeFile(result.getAbsolutePath());
			showimg.setImageBitmap(bitmap);
			super.onPostExecute(result);
		}
		
	}
	
	class LoadFileAsynTask extends  BaseAsyncTaskTry{

		public LoadFileAsynTask(Context context, ProgressBar progressBar,
				TextView tv) {
			super(context, progressBar, tv);
			
		}
		@Override
		protected void onPostExecute(File result) {
			String filePath=result.getAbsolutePath();
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.parse("file://" + filePath),"application/vnd.android.package-archive"); 
			startActivity(intent);
			super.onPostExecute(result);
		}
		
	}
	
	
	
	
}