package com.junwen.bean;

import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class FileItem {
	// 文件名字
	private String fileName;
	// 文件路径
	private String filePath;
	// 文件描述
	private String fileDesc;
	// 文件图片
	private Bitmap fileImg;
	// 文件目录
	private boolean isDirectory =false;
	// 是否是文件
	private boolean isFile = false;
	// 是否是Pdf文件
	private boolean isPdf = false;
	// 是否是文本文件
	private boolean isTxt = false;
	//文件的大小
	private String fileSize;
	private boolean isimg = false;
	
	public boolean isIsimg() {
		return isimg;
	}
	public void setIsimg(boolean isimg) {
		this.isimg = isimg;
	}
	public Bitmap getFileImg() {
		return fileImg;
	}
	public void setFileImg(Bitmap fileImg) {
		this.fileImg = fileImg;
	}
	/**
	 * 获取文件的大小
	 * @return
	 */
	public String getFileSize() {
		return fileSize;
	}
	/**
	 * 设置文件的大小
	 * @param fileSize
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * 获取文件名字
	 * 
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 设置文件名字
	 * 
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 获取文件路径
	 * 
	 * @return
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * 设置文件路径
	 * 
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 获取文件描述
	 * 
	 * @return
	 */
	public String getFileDesc() {
		return fileDesc;
	}

	/**
	 * 设置文件描述
	 * 
	 * @param fileDesc
	 */
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	

	// 获取是否是文件夹
	public boolean isDirectory() {
		return isDirectory;
	}

	// 设置是否是文件夹
	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	// 获取是否是文件
	public boolean isFile() {
		return isFile;
	}

	/**
	 * 设置是否是文件
	 * 
	 * @param isFile
	 */
	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}

	/**
	 * 获取是否是pdf
	 * 
	 * @return
	 */
	public boolean isPdf() {
		return isPdf;
	}

	/**
	 * 设置是否是pdf文件
	 * 
	 * @param isPdf
	 */
	public void setPdf(boolean isPdf) {
		this.isPdf = isPdf;
	}

	/**
	 * 获取是否是文本文件
	 * 
	 * @return
	 */
	public boolean isTxt() {
		return isTxt;
	}

	/**
	 * 设置是否是文本文件
	 * 
	 * @param isTxt
	 */
	public void setTxt(boolean isTxt) {
		this.isTxt = isTxt;
	}

}
