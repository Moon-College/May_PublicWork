package com.junwen.refresh.model;

import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class TextModel {
	private String content;
	private String imgUrl;
	private SoftReference<Bitmap> img;
	
	public SoftReference<Bitmap> getImg() {
		return img;
	}

	public void setImg(SoftReference<Bitmap> img) {
		this.img = img;
	}

	public TextModel() {
		super();
	}

	public TextModel(String content, String imgUrl) {
		this.content = content;
		this.imgUrl = imgUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
