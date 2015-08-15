package com.hq.test;

public class DoorControl {

	/**
	 * 关门
	 * @param door
	 */
	public static void openDoor(Door door){
		if(door!=null){
			door.openDoor();
		}
	}
	/**
	 * 关门
	 * @param door
	 */
	public static void closeDoor(Door door){
		if(door!=null){
			door.closeDoor();
		}
	}
}
