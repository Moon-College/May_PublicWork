package com.decent.door.test;

import com.decent.door.CuteDoor;
import com.decent.door.SecurityDoor;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean result;
		House house = new House();
		SecurityDoor defenseDoor = new SecurityDoor();
		house.setDoor(defenseDoor);
		house.setFingerIdentity(defenseDoor);
		house.openTheHouseDoor();
	    house.closeTheHouseDoor();
	    result = house.pairBlueToothWithTheHouse();
	    System.out.println("house with defenseDoor pairBlueToothWithTheHouse return = "+result);
		
		House cuteHuse = new House();
		CuteDoor cuteDoor = new CuteDoor();
		cuteHuse.setDoor(cuteDoor);
		cuteHuse.setFingerIdentity(cuteDoor);
		cuteHuse.setBluetoothInterface(cuteDoor);
		cuteHuse.openTheHouseDoor();
		cuteHuse.closeTheHouseDoor();
		result = cuteHuse.pairBlueToothWithTheHouse();
		System.out.println("house with defenseDoor pairBlueToothWithTheHouse return = "+result);
        
		/*
			SecurityDoor checkFingerPrint
			open the SecurityDoor
			SecurityDoor checkFingerPrint
			close the SecurityDoor
			this house has no Bluetooth
			house with defenseDoor pairBlueToothWithTheHouse return = false
			CuteDoor checkFingerPrint
			CuteDoor checkFingerPrint
			CuteDoor bluetoothCommunication
			house with defenseDoor pairBlueToothWithTheHouse return = true
		 */
	}

}
