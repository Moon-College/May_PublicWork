package com.decent.web.bean;

public class SportRecord {
	private int _id;
	private int user_id;
	private int sport_type_id;
	private String name;
	private String start_time;
	private int duration;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getSport_type_id() {
		return sport_type_id;
	}

	public void setSport_type_id(int sport_type_id) {
		this.sport_type_id = sport_type_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public SportRecord(int _id, int user_id, int sport_type_id, String name,
			String start_time, int duration) {
		super();
		this._id = _id;
		this.user_id = user_id;
		this.sport_type_id = sport_type_id;
		this.name = name;
		this.start_time = start_time;
		this.duration = duration;
	}

	public SportRecord() {
		super();
	}
	
}
