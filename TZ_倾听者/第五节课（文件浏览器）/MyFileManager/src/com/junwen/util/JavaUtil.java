package com.junwen.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

public class JavaUtil {
	/**
	 * 根据文件，返回文件的大小
	 * 
	 * @param fileSize
	 * @return
	 */
	public static String getFileSize(File file) {
		int fileSize = 0;
		try {
			FileInputStream is = new FileInputStream(file);
			try {
				fileSize = is.available();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileSize < 1024) {
			fileSizeString = df.format((double) fileSize) + "B";
		} else if (fileSize < 1048576) {
			fileSizeString = df.format((double) fileSize / 1024) + "KB";
		} else if (fileSize < 1073741824) {
			fileSizeString = df.format((double) fileSize / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileSize / 1073741824) + "G";
		}
		return fileSizeString;
	}
}
