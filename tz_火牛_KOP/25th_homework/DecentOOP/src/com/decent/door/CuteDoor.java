package com.decent.door;

public class CuteDoor extends Door implements FingerprintIdentityInterface,BluetoothInterface {

	@Override
	public boolean open() {
		// TODO Auto-generated method stub
		System.out.println("CuteDoor open");
		return true;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		System.out.println("CuteDoor open");
		return true;
	}

	@Override
	public boolean bluetoothCommunication() {
		// TODO Auto-generated method stub
		System.out.println("CuteDoor bluetoothCommunication");
		return true;
	}

	@Override
	public boolean checkFingerPrint() {
		// TODO Auto-generated method stub
		System.out.println("CuteDoor checkFingerPrint");
		return false;
	}

}
