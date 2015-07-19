/**
 * Project Name:lsn25_sqlite
 * File Name:Word.java
 * Package Name:com.zht.sqlite.bean
 * Date:2015-7-19下午8:54:03
 * Copyright (c) 2015, hongtao8@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.zht.sqlite.bean;

/**
 * ClassName:Word <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-7-19 下午8:54:03 <br/>
 * 
 * @author hongtao
 * @version
 * @since JDK 1.6
 * @see
 */
public class Word {
	private int id;
	private String content;

	public Word() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Word(String content) {
		super();
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
