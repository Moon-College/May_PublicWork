package com.junwen.test;
/**
 * 智能门
 * @author admi
 *
 */
public class IntelligentDoor extends Door implements Bluetooth{

	@Override
	public void openDoor() {
		System.out.println("智能门开门");
	}

	@Override
	public void closeDoor() {
		System.out.println("智能门关门");
	}

	@Override
	public void openCommunication() {
		System.out.println("智能门打开蓝牙通信");
	}

	@Override
	public void closeCommunication() {
		System.out.println("智能门关闭蓝牙通信");
	}
	
}
