package com.decent.door;
/**
 * �����ż̳���door�����Ҵ���ָ��ʶ��Ĺ���
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
