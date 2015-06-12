package com.tz.michael.utils;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class ConntrolUtils {

	/**
	 * 安装apk方法
	 * @param context
	 * @param f
	 */
	public static void install(Context context,File f){
		if(f!=null){
			if(f.getName().endsWith(".apk")){
				Intent intent = new Intent(Intent.ACTION_VIEW); 
				intent.setDataAndType(Uri.fromFile(f), "application/vnd.android.package-archive"); 
				context.startActivity(intent);
			}else{
				Toast.makeText(context, "非apk类型，不支持安装", 0).show();
			}
		}else{
			Toast.makeText(context, "文件不存在", 0).show();
		}
	}
	
	/**
	 * apk的卸载
	 * @param context
	 * @param packagePath 程序完整的路径 (包名+程序名).例子com.demo.CanavaCancel
	 */
	public static void uninstall(Context context,String packagePath){
			Uri packageURI = Uri.parse("package:"+packagePath);   
			Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);   
			context.startActivity(uninstallIntent);
	}
	
	/**
	 * 打开一个应用
	 * @param context
	 * @param file 
	 */
	private void openApp(Context context,File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                        "application/vnd.android.package-archive");
        context.startActivity(intent);
}
	
}
