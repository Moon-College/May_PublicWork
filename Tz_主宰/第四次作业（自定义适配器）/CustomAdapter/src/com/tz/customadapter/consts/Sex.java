package com.tz.customadapter.consts;

public enum Sex {
	MAN("MAN", "��"), WOMEN("WOMEN", "Ů");
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
