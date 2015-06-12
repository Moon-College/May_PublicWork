package com.chris.simpledownloadsdemo;

import com.chris.utils.CLog;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SimpleDownloadsActivity extends Activity implements OnClickListener
{
	private static final String TAG = "SimpleDownloadsActivity";
	private final String PIC_ADDR = "http://e.hiphotos.baidu.com/image/pic/item/8435e5dde71190ef723123f4cc1b9d16fcfa60d6.jpg";
	private final String APK_ADDR = "http://gdown.baidu.com/data/wisegame/bf53f2c39e440d43/anzhuoshichang_91.apk";
	private final String ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

	private Button btnDownloadPic, btnDownloadAPK;
	private ProgressBar progressBar;
	private TextView progressText;
	private ImageView image;
	private Bitmap onSaveBm;

	private int progressLength;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_downloads);

		initViews(savedInstanceState);
	}

	private void initViews(Bundle savedInstanceState)
	{
		btnDownloadPic = (Button) findViewById(R.id.btnDownloadPic);
		btnDownloadAPK = (Button) findViewById(R.id.btnDownloadAPK);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		progressText = (TextView) findViewById(R.id.progressText);
		image = (ImageView) findViewById(R.id.image);

		btnDownloadPic.setOnClickListener(this);
		btnDownloadAPK.setOnClickListener(this);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		if (savedInstanceState != null)
		{
			progressBar.setMax(savedInstanceState.getInt("progressMax"));
			progressBar.setProgress(savedInstanceState.getInt("progressCur"));
			progressText.setText(savedInstanceState.getCharSequence("progressText"));
		}
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		outState.putInt("progressMax", progressLength);
		outState.putInt("progressCur", progressBar.getProgress());
		outState.putCharSequence("progressText", progressText.getText());
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.btnDownloadPic:
			//下载图片并显示
			String picAddr = PIC_ADDR;
			PicDownloadTask picDownloads = new PicDownloadTask();
			picDownloads.execute(picAddr);
			break;
		case R.id.btnDownloadAPK:
			//下载APK并安装
			image.setImageBitmap(null);
			String apkAddr = APK_ADDR;
			ApkDownloadTask apkDownloads = new ApkDownloadTask();
			apkDownloads.execute(apkAddr);
			break;

		default:
			break;
		}
	}

	private class ApkDownloadTask extends AsyncTask<String, Integer, File>
	{

		@Override
		protected File doInBackground(String... params)
		{
			File apkFile = null;
			String apkAddr = params[0];
			try
			{
				apkFile = realDownloadApkFile(apkAddr);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return apkFile;
		}

		/**
		 * 下载APK
		 * 
		 * @param apkAddr
		 * @return file
		 * @throws IOException
		 */
		public File realDownloadApkFile(String apkAddr) throws IOException
		{
			File file = null;
			URL apkUrl = new URL(apkAddr);
			byte[] bufByte = new byte[512];
			HttpURLConnection connection = (HttpURLConnection) apkUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			if (200 == connection.getResponseCode())
			{
				CLog.i(TAG, "APK connetion OK.");
				progressLength = connection.getContentLength();
				InputStream is = connection.getInputStream(); //获取下载文件的输入流
				CLog.i(TAG, "APK max progressLength = " + progressLength);
				progressBar.setMax(progressLength);
				progressBar.setProgress(0);
				String filename = apkAddr.substring(apkAddr.lastIndexOf("/"));
				//用BufferedOutputStream写入的apk文件，必须flush或者close，否则无法解析安装，原因不明
				BufferedOutputStream bufout = new BufferedOutputStream(new FileOutputStream(ROOT_PATH + "/" + filename));
				//				FileOutputStream fo = new FileOutputStream(ROOT_PATH+"/"+filename);
				int length = is.read(bufByte);
				while (length != -1)
				{
					bufout.write(bufByte, 0, length);
					//					fo.write(bufByte, 0, length);
					this.publishProgress(length);
					length = is.read(bufByte);
					CLog.i(TAG, "read len = " + length);
				}
				file = new File(ROOT_PATH + "/" + filename);
				is.close();
				bufout.flush();
				bufout.close();
				return file;
			}
			return null;
		}

		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(File result)
		{
			if (result != null)
			{
				CLog.i(TAG, "file name = " + result.getName());
				installApkFile(result);
			}
			super.onPostExecute(result);
		}

		/**
		 * 安装apk文件
		 * 
		 * @param result
		 */
		private void installApkFile(File result)
		{
			if (result.exists())
			{
				//安装apk
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive");
				SimpleDownloadsActivity.this.startActivity(intent);
			}
		}

		@Override
		protected void onProgressUpdate(Integer... values)
		{
			int percent = values[0].intValue();
			CLog.i(TAG, "percent = " + percent);
			int curPercent = progressBar.getProgress();
			progressBar.setProgress(curPercent + percent);
			progressText.setText("APK下载进度： " + (100 * (curPercent + percent)) / progressLength + "%");
			super.onProgressUpdate(values);
		}

		@Override
		protected void onCancelled(File result)
		{
			// TODO Auto-generated method stub
			super.onCancelled(result);
		}
	}

	private class PicDownloadTask extends AsyncTask<String, Integer, Bitmap>
	{
		@Override
		protected Bitmap doInBackground(String... params)
		{
			Bitmap bm = null;
			try
			{
				bm = picRealDownload(params[0]);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bm;
		}

		private Bitmap picRealDownload(String url) throws IOException
		{
			Bitmap bm = null;
			byte[] blockBuf = new byte[512]; //下载缓存块大小
			URL picURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) picURL.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			if (200 == connection.getResponseCode())
			{
				CLog.i(TAG, "pic connetion OK.");
				progressLength = connection.getContentLength();
				//谁知进度条的最大值
				progressBar.setMax(progressLength);
				progressBar.setProgress(0);
				CLog.i(TAG, "pic max progressLength = " + progressLength);

				String fileName = url.substring(url.lastIndexOf("/"));
				InputStream picInputStream = connection.getInputStream();
				//BufferedReader bufReader = new BufferedReader(new InputStreamReader(picInputStream));
				BufferedOutputStream bufOutStream = new BufferedOutputStream(new FileOutputStream(ROOT_PATH + "/" + fileName));
				int len = picInputStream.read(blockBuf);
				while (-1 != len)
				{
					//写入Sd卡文件
					bufOutStream.write(blockBuf, 0, len);
					//更新进度条
					this.publishProgress(len);
					//读入下一个block数据
					len = picInputStream.read(blockBuf);
				}
				bufOutStream.flush();
				bufOutStream.close();
				picInputStream.close();

				bm = scalPicture(ROOT_PATH + "/" + fileName);

				return bm;
			}
			CLog.i(TAG, "connetion failed.");
			return null;
		}

		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Bitmap result)
		{
			if (result != null)
			{
				image.setImageBitmap(result);
				CLog.i(TAG, "bitmap width = " + result.getWidth());
				CLog.i(TAG, "bitmap height = " + result.getHeight());
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values)
		{
			int value = values[0].intValue();
			int progress = progressBar.getProgress();
			CLog.i(TAG, "current progress = " + progress);
			progressBar.setProgress(progress + value);
			progressText.setText("图片下载进度: " + (100 * (progress + value) / progressLength) + "%");
			super.onProgressUpdate(values);
		}

		@Override
		protected void onCancelled(Bitmap result)
		{
			// TODO Auto-generated method stub
			super.onCancelled(result);
		}

		public Bitmap scalPicture(String pathName)
		{
			Bitmap bm = null;
			CLog.i(TAG, "pathName = " + pathName);
			//获取控件ImageView的空间尺寸
			int imageViewWidth = image.getWidth();
			int imageViewHeight = image.getHeight();
			CLog.i(TAG, "ImageView width = " + imageViewWidth);
			CLog.i(TAG, "ImageView height = " + imageViewHeight);
			//获取下载图片的尺寸大小
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inJustDecodeBounds = true;
			bm = BitmapFactory.decodeFile(pathName, opts);
			int picWidth = opts.outWidth;
			int picHeight = opts.outHeight;
			CLog.i(TAG, "Download pic width = " + picWidth);
			CLog.i(TAG, "Download pic height = " + picHeight);

			//控件长宽比大于图片长宽比，说明相对来说，控件是横屏，图片是竖屏
			if (imageViewWidth * picHeight > picWidth * imageViewHeight)
			{
				//只对图片的高度做判断是否需要缩放
				if (picHeight > imageViewHeight)
				{
					opts.inSampleSize = picHeight / imageViewHeight;
					CLog.i(TAG, "1. opts.inSampleSize = " + opts.inSampleSize);
				}
			}
			//控件长宽比小于图片长宽比，说明相对来说，控件是竖屏，图片是横屏
			else if (imageViewWidth * picHeight <= picWidth * imageViewHeight)
			{
				//只对图片的宽度做判断是否需要缩放
				if (picWidth > imageViewWidth)
				{
					opts.inSampleSize = picWidth / imageViewWidth;
					CLog.i(TAG, "2. opts.inSampleSize = " + opts.inSampleSize);
				}
			}
			opts.inJustDecodeBounds = false;

			bm = BitmapFactory.decodeFile(pathName, opts);

			return bm;
		}
	}

}
