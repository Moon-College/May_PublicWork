package com.tz.customadapter.consts;

public enum Sex {
	MAN("MAN", "ÄÐ"), WOMEN("WOMEN", "Å®");
	private String code;
	private String name;

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private Sex(String code, String name) {
		this.code = code;
		this.name = name;
	}
}
