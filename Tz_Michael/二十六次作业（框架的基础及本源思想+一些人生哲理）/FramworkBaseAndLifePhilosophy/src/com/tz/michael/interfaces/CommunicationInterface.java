package com.tz.michael.interfaces;
/**
 * 规定一些额外功能的接口(通话)
 * @author michael
 *
 */
public interface CommunicationInterface {

	/**
	 * 根据输入的号码，与对应的房间通话
	 * @param roomNum
	 */
	void communicate(int roomNum);
	
}
