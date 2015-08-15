package com.hq.test;
/**
 * 智能门
 * @author hequn
 *
 */
public class IntelligentDoor extends Door implements Bluetooth{

	@Override
	public void openDoor() {
		System.out.println("开门");
	}

	@Override
	public void closeDoor() {
		System.out.println("关门");
	}

	@Override
	public void openCommunication() {
		System.out.println("智能开门");
	}

	@Override
	public void closeCommunication() {
		System.out.println("智能开门");
	}
	
}
