package com.junwen.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class JavaUtil {
	/**
	 * 获取屏幕宽度
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context)
	{
		DisplayMetrics metric = new DisplayMetrics();
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		manager.getDefaultDisplay().getMetrics(metric);
	     int width = metric.widthPixels; // 屏幕宽度（像素）
	     return width;
	}
}
