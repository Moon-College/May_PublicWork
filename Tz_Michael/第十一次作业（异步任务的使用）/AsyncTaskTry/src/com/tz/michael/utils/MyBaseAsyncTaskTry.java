package com.tz.michael.utils;

import java.io.File;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * 此类只能封装了文件下载的操作
 * @author michael
 *
 */
public class MyBaseAsyncTaskTry extends BaseAsyncTask {

	private Context mContext;
	
	public MyBaseAsyncTaskTry(Context context,ProgressBar progressBar, TextView tv) {
		super(context,progressBar, tv);
		this.mContext=context;
	}

	@Override
	protected void onPostExecute(File result) {
		super.onPostExecute(result);
		ConntrolUtils.install(mContext, result);
	}
}
