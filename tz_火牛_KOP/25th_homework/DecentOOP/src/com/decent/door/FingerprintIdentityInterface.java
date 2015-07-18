package com.decent.door;

/**
 * 扩展用的指纹识别的接口
 * @author yanqiang
 *
 */
public interface FingerprintIdentityInterface {
	/**
	 * 指纹识别函数
	 * @return 指纹识别是否成功
	 */
	boolean checkFingerPrint();
}
