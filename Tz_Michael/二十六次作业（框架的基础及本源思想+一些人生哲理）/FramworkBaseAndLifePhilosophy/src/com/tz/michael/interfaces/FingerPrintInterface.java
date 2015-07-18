package com.tz.michael.interfaces;
/**
 * 指纹识别的功能接口
 * @author michael
 *
 */
public interface FingerPrintInterface {

	/**
	 * 判断指纹是否正确（或者在数据指纹库中）
	 * @param b（这里应该是一个指纹，这里猜测是把指纹转化成字节数组，故这样设计）
	 * @return 返回一个boolean
	 */
	boolean isFingerPrintRight(byte[] b);
	
}
