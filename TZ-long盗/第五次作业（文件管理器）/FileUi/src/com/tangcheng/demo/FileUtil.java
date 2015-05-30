package com.tangcheng.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	/**
	 * �����ļ�
	 * 
	 * @param filePath
	 *            ·��
	 * @param filename
	 *            ����
	 * @return �Ƿ���ڸ��ļ�
	 * @throws IOException
	 */
	public static boolean doCreatefile(File currentPath, String filename)
			throws IOException {
		File file = new File(currentPath, filename);

		if (file.exists()) {
			return false;
		} else {
			return file.createNewFile();
		}

	}

	/**
	 * �����ļ���
	 * 
	 * @param filePath
	 *            ·��
	 * @param filename
	 *            ����
	 * @return �Ƿ�����ļ���
	 * @throws IOException
	 */
	public static boolean doCreatefolder(File currentPath, String filename)
			throws IOException {
		File folder = new File(currentPath, filename);
		if (folder.exists()) {
			return false;
		} else {
			return folder.mkdirs();
		}

	}

	/**
	 * ������
	 * 
	 * @param oldfile
	 *            Ҫ�������ļ�
	 * @param filename
	 *            ���������
	 * @throws IOException
	 */
	public static void dorename(File oldfile, String filename)
			throws IOException {
		File file = new File(oldfile.getParentFile(), filename);

		oldfile.renameTo(file);

	}

	public static void docopy(File src, File dest) throws IOException {
		if (src.isFile()) {
			cpFile(src, new File(dest, src.getName()));
		}
		if (src.isDirectory()) {
			File nextdest = new File(dest, src.getName());
			nextdest.mkdir();
			File[] files = src.listFiles();
			for (File f : files) {
				if (f.isFile()) {
					cpFile(f, new File(nextdest, f.getName()));
				} else {
					docopy(f, nextdest);
				}
			}
		}
		// if(src.isFile()){
		// new File(dest,src.getName()).createNewFile();
		// }
	}

	private static void cpFile(File srcfile, File destfile) throws IOException {
		FileInputStream in = new FileInputStream(srcfile);
		FileOutputStream out = new FileOutputStream(destfile);
		byte[] buf = new byte[1024 * 16];
		int len;
		while (-1 != (len = in.read(buf))) {
			out.write(buf, 0, len);
		}
		out.flush();
		in.close();
		out.close();

	}

}
