package com.tz.michael.bean;
/**
 * 简单的工厂模式
 * @author michal
 *
 */
public class DoorFactory {

	/**
	 * 获得一个Door对象
	 * @param type eg:DoorConstants.FINGERPRINT
	 * @return
	 */
	public static Door newInstance(int type){
		Door door=null;
		switch (type) {
		case DoorConstants.FINGERPRINT:
			door=new FingerPrintIdentifyDoor();
			break;
		case DoorConstants.FACEIDENTIFY:
			door=new FaceIdentifyDoor();
			break;
		default:
			break;
		}
		return door;
	}
	
}
