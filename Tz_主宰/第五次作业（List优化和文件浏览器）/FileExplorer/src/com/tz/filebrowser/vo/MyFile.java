package com.tz.filebrowser.vo;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.Date;

import android.graphics.Bitmap;

import com.tz.filebrowser.consts.FileType;
import com.tz.filebrowser.manager.DataProvider;

public class MyFile {
	private String name;
	private String path;
	private File file;

	private FileType fileType;
	private long size;
	private Date createTime;
	private int childDirCount;
	private int childFileCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getChildDirCount() {
		return childDirCount;
	}

	public void setChildDirCount(int childDirCount) {
		this.childDirCount = childDirCount;
	}

	public int getChildFileCount() {
		return childFileCount;
	}

	public void setChildFileCount(int childFileCount) {
		this.childFileCount = childFileCount;
	}

	/**
	 * ��ʼ��MyFile��Ϣ
	 * 
	 * @param file
	 */
	public void initFile(File file) {
		this.file = file;
		DataProvider dataProvider = DataProvider.getInstance();

		// ��ȡ�������ļ�����
		FileType fileType = checkFileType(file);
		setFileType(fileType);

		// ���ö�Ӧ������
		if (file.isDirectory()) {
			// ������ļ������ͣ����ʼ���������ļ��������ļ��и���
			File[] files = file.listFiles();
			int fileCount = 0;
			int dirCount = 0;
			if (files != null) {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						dirCount++;
					} else {
						fileCount++;
					}
				}
			}
			setChildDirCount(dirCount);
			setChildFileCount(fileCount);
		} else {
			// ������ļ����ͣ����ʼ���ļ���С�ͻ�ȡ�ļ����һ���޸ĵ�ʱ��
			setSize(file.length());
			setCreateTime(new Date(file.lastModified()));
		}
	}

	/**
	 * ����ļ�����
	 * 
	 * @param file
	 * @return
	 */
	public FileType checkFileType(File file) {
		String fileName = file.getName().toLowerCase();
		if (file.isDirectory()) {
			return FileType.DIR;
		} else if (fileName.endsWith("png") || fileName.endsWith("jpeg")
				|| fileName.endsWith("jpg") || fileName.endsWith("bmp")
				|| fileName.endsWith("gif")) {
			return FileType.IMAGE;
		} else {
			return FileType.FILE;
		}
	}
}
