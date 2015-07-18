package com.tz.michael.interfaces;
/**
 * 人脸识别接口
 * @author michael
 *
 */
public interface FaceIdentifyInterface {

	/**
	 * 判断人脸是否合法
	 * @param b
	 * @return boolean
	 */
	boolean isFaceLegal(byte[] b);
	
}
