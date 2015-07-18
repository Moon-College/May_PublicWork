package com.decent.door;
/**
 * 防盗门继承了door，并且带有指纹识别的功能
 * @author yanqiang
 *
 */
public class SecurityDoor extends Door implements FingerprintIdentityInterface {

	@Override
	public boolean open() {
		// TODO Auto-generated method stub
		System.out.println("open the SecurityDoor");
		return true;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		System.out.println("close the SecurityDoor");
		return true;
	}

	@Override
	public boolean checkFingerPrint() {
		// TODO Auto-generated method stub
		System.out.println("SecurityDoor checkFingerPrint");
		return true;
	}

}
