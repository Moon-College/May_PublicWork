package com.hq.test;

public class Test {
	
	/**
	 *  
	 * ������
	 * 
	 * @author hequn
	 * @param args
	 */
	public static void main(String[] args) {
		
		//控制智能�?
		DoorControl.openDoor(new IntelligentDoor());
		IntelligentDoor door = new IntelligentDoor();
		//打开智能门的蓝牙
		door.openCommunication();
		//关闭智能门的蓝牙
		door.closeCommunication();
		
	}
}
