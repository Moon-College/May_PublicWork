package com.junwen.music.model;

public class Music {

	private long id; // 文件ID
	private String fileName; // 文件名字
	private String path; // 文件路径
	private long duration; // 文件时长
	private int size; // 文件大小

	public Music() {
	}

	public Music(long id, String fileName, String path, long duration) {
		this.id = id;
		this.fileName = fileName;
		this.path = path;
		this.duration = duration;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getDuration() {
		return duration;
	}
	
	public void setDuration(long duration) {
		this.duration = duration;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
