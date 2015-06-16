package com.junwen.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.junwen.bean.App;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;

public class InstallApk {

	/**
	 * ��װAPK
	 * @param url Ҫ��װ�����URL
	 * @param context ������
	 */
	public static void installApplication(String url,Context context ) {
		
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(url)), "application/vnd.android.package-archive");
		context.startActivity(intent);
		
	}
	/**
	 * ж��Ӧ��
	 */
	public static  void uninstall(String path,Context context) {
		
			Uri uri  = Uri.parse("package:"+path);
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_DELETE);
			intent.setData(uri);
			if(intent.resolveActivity( context.getPackageManager()) != null) {
				context.startActivity(intent);
			}
	}//*
	/**
	 * ��ȡ����Ӧ�õ���Ϣ
	 * @param context
	 * @return
	 */
	public static List<App> selectApplication(Context context) {
		
		List<App> data = new ArrayList<App>();
		PackageManager packageManager = context.getPackageManager();
		List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
		for (PackageInfo packageInfo : installedPackages) {
			//�����ǵ�����Ӧ��
			 if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {  
				App app = new App();
				app.setName(packageInfo.applicationInfo.loadLabel(packageManager).toString()); //���Ӧ������
				app.setPackageName(packageInfo.packageName); //��Ӱ���
				Drawable loadIcon = packageInfo.applicationInfo.loadIcon(packageManager);
				app.setIcon(loadIcon);
				data.add(app);
			}
		}
		return data;
		
	}
}
