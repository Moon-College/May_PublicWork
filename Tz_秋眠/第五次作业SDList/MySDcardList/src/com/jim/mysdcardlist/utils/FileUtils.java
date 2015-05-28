package com.jim.mysdcardlist.utils;

import java.io.File;

public class FileUtils {
	/**
	 * 获取目录下文件的个数，
	 * @param dir
	 * @return
	 */
	public static long getFileCount(File dir) {
		long file_count = 0;
		File[] files = dir.listFiles();
		file_count = files.length;
		for (File f : files) {
			if (f.isDirectory()) {
				file_count = file_count + getFileCount(f);
				file_count--;
			}
		}
		return file_count;
	}
	
}
