package com.decent.door.test;

import com.decent.door.BluetoothInterface;
import com.decent.door.Door;
import com.decent.door.FingerprintIdentityInterface;

/**
 * house����һ����,�Լ���ʹ���ŵĸ��ֹ���(�ӿ�)
 * @author yanqiang
 *
 */
public class House{
	/**
	 * door�ǳ����࣬��Ҫ�����ʵ��
	 */
	private Door door;
	/**
	 * ָ��ʶ��ӿڣ�����û������˻��ڿ��Ź��ŵ�ʱ����õ�
	 */
	private FingerprintIdentityInterface mFingerIdentity;

	private BluetoothInterface mBluetoothInterface;
	/**
	 * ��ȡ��ǰ���õ�ָ��ʶ��ӿ�
	 * @return
	 */
	public FingerprintIdentityInterface getFingerIdentity() {
		return mFingerIdentity;
	}

	/**
	 * ����ָ��ʶ��ӿڣ�����ӿ���������˻��ڿ����ŵ�ʱ�򱻵��õ�
	 * @param mFingerIdentity
	 */
	public void setFingerIdentity(FingerprintIdentityInterface mFingerIdentity) {
		this.mFingerIdentity = mFingerIdentity;
	}

	public Door getDoor() {
		return door;
	}

	public void setDoor(Door door) {
		this.door = door;
	}

	/**
	 * ��ȡ���õ������ӿ�
	 * @return �����ӿ�
	 */
	public BluetoothInterface getBluetoothInterface() {
		return mBluetoothInterface;
	}

	/**
	 * ���������ӿڣ�����Ե�ʱ��ص���
	 * @param mBluetoothInterface
	 */
	public void setBluetoothInterface(BluetoothInterface mBluetoothInterface) {
		this.mBluetoothInterface = mBluetoothInterface;
	}

	/**
	 * ����
	 * @return �����Ƿ�ɹ�
	 */
	public boolean openTheHouseDoor() {
		boolean result = false;
		if(mFingerIdentity!=null){
			result = mFingerIdentity.checkFingerPrint();
			if(result){
				result = door.open();
			}
		}else{
			result = door.open();
		}
		
		return result;
	}

	/**
	 * ����
	 * @return �����Ƿ�ɹ�
	 */
	public boolean closeTheHouseDoor() {
		boolean result = false;
		if(mFingerIdentity!=null){
			result = mFingerIdentity.checkFingerPrint();
			if(result){
				result = door.close();
			}
		}else{
			result = door.close();
		}
		
		return result;
	}
	/**
	 * ���������������ԣ���ҪsetBluetoothInterface֮�������ԣ�����ֱ�ӷ���false
	 * @return ����Ƿ�ɹ�
	 */
	public boolean pairBlueToothWithTheHouse(){
		if(mBluetoothInterface!=null){
			return mBluetoothInterface.bluetoothCommunication();
		}
		System.out.println("this house has no Bluetooth");
		return false;
	}
}
