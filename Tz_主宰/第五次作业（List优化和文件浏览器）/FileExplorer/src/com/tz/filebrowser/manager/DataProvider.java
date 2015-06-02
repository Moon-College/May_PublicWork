package com.tz.filebrowser.manager;

import java.io.File;
import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;

import com.tz.filebrowser.task.LoadBitmapTask;
import com.tz.filebrowser.vo.MyFile;
import com.tz.fileexplorer.R;

public class DataProvider {
	// android 设备的文件根目录
	public static final String ROOT_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath();

	// 用于保存文件打开类型的常量
	public static final Map<String, String> fileOpenUriType;

	// 一些默认图片id
	public static final int DIR_ICON_RESID = R.drawable.icon_folder;
	public static final int BACK_DIR_ICON_RESID = R.drawable.icon_return;
	public static final int FILE_ICON_RESID = R.drawable.icon_file;

	private final Map<String, SoftReference<Bitmap>> bitmapCache = new HashMap<String, SoftReference<Bitmap>>();

	private static DataProvider instance;
	private Context context;

	static {
		fileOpenUriType = new HashMap<String, String>();
		// fileOpenUriType.put(后缀名，MIME类型}
		fileOpenUriType.put(".3gp", "video/3gpp");
		fileOpenUriType.put(".apk", "application/vnd.android.package-archive");
		fileOpenUriType.put(".asf", "video/x-ms-asf");
		fileOpenUriType.put(".avi", "video/x-msvideo");
		fileOpenUriType.put(".bin", "application/octet-stream");
		fileOpenUriType.put(".bmp", "image/bmp");
		fileOpenUriType.put(".c", "text/plain");
		fileOpenUriType.put(".class", "application/octet-stream");
		fileOpenUriType.put(".conf", "text/plain");
		fileOpenUriType.put(".cpp", "text/plain");
		fileOpenUriType.put(".doc", "application/msword");
		fileOpenUriType
				.put(".docx",
						"application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		fileOpenUriType.put(".xls", "application/vnd.ms-excel");
		fileOpenUriType
				.put(".xlsx",
						"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		fileOpenUriType.put(".exe", "application/octet-stream");
		fileOpenUriType.put(".gif", "image/gif");
		fileOpenUriType.put(".gtar", "application/x-gtar");
		fileOpenUriType.put(".gz", "application/x-gzip");
		fileOpenUriType.put(".h", "text/plain");
		fileOpenUriType.put(".htm", "text/html");
		fileOpenUriType.put(".html", "text/html");
		fileOpenUriType.put(".jar", "application/java-archive");
		fileOpenUriType.put(".java", "text/plain");
		fileOpenUriType.put(".jpeg", "image/jpeg");
		fileOpenUriType.put(".jpg", "image/jpeg");
		fileOpenUriType.put(".js", "application/x-javascript");
		fileOpenUriType.put(".log", "text/plain");
		fileOpenUriType.put(".m3u", "audio/x-mpegurl");
		fileOpenUriType.put(".m4a", "audio/mp4a-latm");
		fileOpenUriType.put(".m4b", "audio/mp4a-latm");
		fileOpenUriType.put(".m4p", "audio/mp4a-latm");
		fileOpenUriType.put(".m4u", "video/vnd.mpegurl");
		fileOpenUriType.put(".m4v", "video/x-m4v");
		fileOpenUriType.put(".mov", "video/quicktime");
		fileOpenUriType.put(".mp2", "audio/x-mpeg");
		fileOpenUriType.put(".mp3", "audio/x-mpeg");
		fileOpenUriType.put(".mp4", "video/mp4");
		fileOpenUriType.put(".mpc", "application/vnd.mpohun.certificate");
		fileOpenUriType.put(".mpe", "video/mpeg");
		fileOpenUriType.put(".mpeg", "video/mpeg");
		fileOpenUriType.put(".mpg", "video/mpeg");
		fileOpenUriType.put(".mpg4", "video/mp4");
		fileOpenUriType.put(".mpga", "audio/mpeg");
		fileOpenUriType.put(".msg", "application/vnd.ms-outlook");
		fileOpenUriType.put(".ogg", "audio/ogg");
		fileOpenUriType.put(".pdf", "application/pdf");
		fileOpenUriType.put(".png", "image/png");
		fileOpenUriType.put(".pps", "application/vnd.ms-powerpoint");
		fileOpenUriType.put(".ppt", "application/vnd.ms-powerpoint");
		fileOpenUriType
				.put(".pptx",
						"application/vnd.openxmlformats-officedocument.presentationml.presentation");
		fileOpenUriType.put(".prop", "text/plain");
		fileOpenUriType.put(".rc", "text/plain");
		fileOpenUriType.put(".rmvb", "audio/x-pn-realaudio");
		fileOpenUriType.put(".rtf", "application/rtf");
		fileOpenUriType.put(".sh", "text/plain");
		fileOpenUriType.put(".tar", "application/x-tar");
		fileOpenUriType.put(".tgz", "application/x-compressed");
		fileOpenUriType.put(".txt", "text/plain");
		fileOpenUriType.put(".wav", "audio/x-wav");
		fileOpenUriType.put(".wma", "audio/x-ms-wma");
		fileOpenUriType.put(".wmv", "audio/x-ms-wmv");
		fileOpenUriType.put(".wps", "application/vnd.ms-works");
		fileOpenUriType.put(".xml", "text/plain");
		fileOpenUriType.put(".z", "application/x-compress");
		fileOpenUriType.put(".zip", "application/x-zip-compressed");
		fileOpenUriType.put("", "*/*");
	}

	private DataProvider(Context context) {
		this.context = context;
	}

	public static DataProvider getInstance() {
		if (instance == null) {
			synchronized (DataProvider.class) {
				if (instance == null) {
					instance = new DataProvider(null);
				}
			}
		}
		return instance;
	}

	public static DataProvider getInstance(Context context) {
		if (instance == null) {
			synchronized (DataProvider.class) {
				if (instance == null) {
					instance = new DataProvider(context);
				}
			}
		}
		return instance;
	}

	/**
	 * 通过文件路径获取该文件下的所有子文件的子目录
	 * 
	 * @param path
	 *            目录
	 * @return
	 */
	public List<MyFile> getFilesFromPath(String path) {
		List<MyFile> data = new ArrayList<MyFile>();

		// 第一个为返回上一级目录
		MyFile backFile = new MyFile();
		String backPath = path.substring(0, path.lastIndexOf("/"));
		backFile.setPath(backPath);
		backFile.setName("返回");
		if (ROOT_PATH.equals(path)) {
			backFile.setPath(path);
		} else {
			backFile.setPath(backPath);
		}
		backFile.initFile(new File(backFile.getPath()));
		data.add(backFile);

		// 初始化文件目录下的子目录和文件
		File currFile = new File(path);
		File[] files = currFile.listFiles();
		if (files != null) {
			for (File file : files) {
				MyFile myFile = new MyFile();
				myFile.setFile(file);
				myFile.setPath(file.getAbsolutePath());
				myFile.setName(file.getName());
				myFile.initFile(file);
				data.add(myFile);
			}
		}
		return data;
	}

	/**
	 * 通过文件的resourceID读取Bitmap
	 * 
	 * @param resId
	 * @return
	 */
	public Bitmap getResDrawable(int resId) {
		return BitmapFactory.decodeResource(context.getResources(), resId);
	}

	public void displayBitmap(MyFile myFile, final ImageView imageView) {
		final String path = myFile.getPath();
		SoftReference<Bitmap> bitmap = bitmapCache.get(path);
		if (bitmap != null && bitmap.get() != null) {
			imageView.setImageBitmap(bitmap.get());
		} else {
			LoadBitmapTask task = (LoadBitmapTask) imageView.getTag();
			if (task != null && !task.isCancelled()) {
				task.cancel(true);
			}
			task = null;
			Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					Bitmap tempBitmap = (Bitmap) msg.obj;
					bitmapCache
							.put(path, new SoftReference<Bitmap>(tempBitmap));
					imageView.setImageBitmap(tempBitmap);
					imageView.setTag(null);
				}
			};
			imageView.setImageResource(Color.WHITE);
			task = new LoadBitmapTask(myFile.getPath(), handler,
					imageView.getWidth(), imageView.getHeight());
			task.execute();
			imageView.setTag(task);
		}
	}

	/**
	 * 将时间Date格式转换成对应的字符串格式
	 * 
	 * @param date
	 *            要转换的时间
	 * @param formatStr
	 *            转化的格式 如：yyyy-MM-dd
	 * @return 返回时间字符串：2015-01-01
	 */
	public static String date2String(Date date, String formatStr) {
		if (date == null) {
			return "";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.format(date);
	}

	public void openFile(File file) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// 设置intent的Action属性
		intent.setAction(Intent.ACTION_VIEW);
		// 获取文件file的MIME类型
		String type = getMIMEType(file);
		// 设置intent的data和Type属性。
		intent.setDataAndType(Uri.fromFile(file), type);
		// 跳转
		context.startActivity(intent);
	}

	/**
	 * 通过文件得到其打开的类型
	 * 
	 * @param file
	 * @return
	 */
	public String getMIMEType(File file) {
		// 获取文件后缀名
		String fileName = file.getName();
		String key = "";
		int startIndex = fileName.lastIndexOf(".");
		if (startIndex >= 0) {
			key = fileName.substring(startIndex).toLowerCase();
		}

		// 通过文件后缀名获取文件打开的类型
		String suffix = fileOpenUriType.get(key);
		if (suffix == null) {
			suffix = fileOpenUriType.get("");
		}
		return suffix;
	}
}
