package com.junwen.test;

public class Test {
	
	/**
	 *  
	 * 
	 * 
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//控制智能门
		DoorControl.openDoor(new IntelligentDoor());
		IntelligentDoor door = new IntelligentDoor();
		//打开智能门的蓝牙
		door.openCommunication();
		//关闭智能门的蓝牙
		door.closeCommunication();
		
	}
}
