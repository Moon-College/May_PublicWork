package com.zht.killprocess;

import java.text.DecimalFormat;

public class TextFormat {

	//23.87MB  00.87MB/KB   byte
	public static String formatByte(long data){
		DecimalFormat format = new DecimalFormat("##.##");
		if(data < 1024){
			return data + "bytes";
		}else if(data < 1024*1024){
			return format.format(data/1024f) + "KB";
		}else if(data < 1024*1024*1024){
			return format.format(data/1024f/1024f) + "MB";
		}else if(data < 1024*1024*1024*1024){
			return format.format(data/1024f/1024f/1024f) + "GB";
		}else{
			return "����ͳ�Ʒ���";
		}
	}
}
