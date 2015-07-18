package com.decent.door.test;

import com.decent.door.BluetoothInterface;
import com.decent.door.Door;
import com.decent.door.FingerprintIdentityInterface;

/**
 * house会有一个门,以及会使用门的各种功能(接口)
 * @author yanqiang
 *
 */
public class House{
	/**
	 * door是抽象类，需要具体的实现
	 */
	private Door door;
	/**
	 * 指纹识别接口，如果用户设置了会在开门关门的时候调用到
	 */
	private FingerprintIdentityInterface mFingerIdentity;

	private BluetoothInterface mBluetoothInterface;
	/**
	 * 获取当前设置的指纹识别接口
	 * @return
	 */
	public FingerprintIdentityInterface getFingerIdentity() {
		return mFingerIdentity;
	}

	/**
	 * 设置指纹识别接口，这个接口如果设置了会在开关门的时候被调用到
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
	 * 获取设置的蓝牙接口
	 * @return 蓝牙接口
	 */
	public BluetoothInterface getBluetoothInterface() {
		return mBluetoothInterface;
	}

	/**
	 * 设置蓝牙接口，在配对的时候回调用
	 * @param mBluetoothInterface
	 */
	public void setBluetoothInterface(BluetoothInterface mBluetoothInterface) {
		this.mBluetoothInterface = mBluetoothInterface;
	}

	/**
	 * 开门
	 * @return 开门是否成功
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
	 * 关门
	 * @return 关门是否成功
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
	 * 和这个房间蓝牙配对，需要setBluetoothInterface之后才能配对，否则直接返回false
	 * @return 配对是否成功
	 */
	public boolean pairBlueToothWithTheHouse(){
		if(mBluetoothInterface!=null){
			return mBluetoothInterface.bluetoothCommunication();
		}
		System.out.println("this house has no Bluetooth");
		return false;
	}
}
