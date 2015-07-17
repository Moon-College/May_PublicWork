package com.ssk.contacts;

import java.util.List;

public class Contacts_Raw {

	private long raw_contact_id;
	private List<Contacts_Data> date;
	public long getRaw_contact_id() {
		return raw_contact_id;
	}
	public void setRaw_contact_id(long raw_contact_id) {
		this.raw_contact_id = raw_contact_id;
	}
	public List<Contacts_Data> getDate() {
		return date;
	}
	public void setDate(List<Contacts_Data> date) {
		this.date = date;
	}
}
