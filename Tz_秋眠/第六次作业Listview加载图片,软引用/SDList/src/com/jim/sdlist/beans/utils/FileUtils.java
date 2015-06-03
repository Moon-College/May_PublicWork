package com.jim.sdlist.beans.utils;

import java.io.File;

public class FileUtils {
	/**
	 * ��ȡĳ��·�����ļ�����������������·���µ��ļ�����
	 * 
	 * @param dir
	 * @return �ļ������ļ����ܸ���
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
			s = "��С" + size + "B";
		} else if (size >= 1024 && size < 1024 * 1024) {
			s = "��С" + size + "KB";
		} else if (size >= 1024 * 1024 && size < 1024 * 1024 * 1024) {
			s = "��С" + size + "MB";
		} else {
			s = "��С" + size + "GB";
		}
		return s;
	}

	/**
	 * �ж��ļ��Ƿ�ΪͼƬ
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
