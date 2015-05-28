package com.tz.michael.bean;

import java.io.File;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;
/**
 * 文件模板类
 * @author admin
 *
 */
public class FileBean {
	/**文件名字*/
	private String fileName;
	/**包含的文件*/
	private File file;
	/**文件路径*/
	private String filePath;
	/**是否是图片*/
	private boolean isPic;
	private SoftReference<Bitmap> bitmap;
	
	public SoftReference<Bitmap> getBitmap() {
		return bitmap;
	}
	public void setBitmap(SoftReference<Bitmap> bitmap) {
		this.bitmap = bitmap;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public boolean isPic() {
		return isPic;
	}
	public void setPic(boolean isPic) {
		this.isPic = isPic;
	}
	public FileBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "FileBean [fileName=" + fileName + ", file=" + file
				+ ", filePath=" + filePath + ", isPic=" + isPic + ", bitmap="
				+ bitmap + "]";
	}
	
}
