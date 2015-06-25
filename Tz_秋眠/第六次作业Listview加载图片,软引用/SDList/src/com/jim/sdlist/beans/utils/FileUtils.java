package com.jim.sdlist.beans.utils;

import java.io.File;

public class FileUtils {
	/**
	 * 获取某个路径下文件的数量，包括其子路径下的文件数量
	 * 
	 * @param dir
	 * @return 文件夹中文件的总个数
	 */
	public static long getFileCount(File dir) {
		long count = 0;
		File[] files = dir.listFiles();
		count = files.length;
		for (File f : files) {
			if (f.isDirectory()) {
				count += getFileCount(f);
				count--;
			}
		}
		return count;
	}

	public static String getFileSize(File f) {
		long size = 0;
		String s = "";
		size = f.length();
		if (size < 1024) {
			s = "大小" + size + "B";
		} else if (size >= 1024 && size < 1024 * 1024) {
			s = "大小" + size + "KB";
		} else if (size >= 1024 * 1024 && size < 1024 * 1024 * 1024) {
			s = "大小" + size + "MB";
		} else {
			s = "大小" + size + "GB";
		}
		return s;
	}

	/**
	 * 判断文件是否为图片
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isPicFileName(String fileName) {
		if (fileName == null) {
			return false;
		}
		if (fileName.toLowerCase().endsWith("png")
				|| fileName.toLowerCase().endsWith("jpg")) {
			return true;
		}
		return false;
	}
}
