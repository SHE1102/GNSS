package com.geo.gnss.dao;

import java.util.Date;

public class RecordCountDao {

	private Date date;
	private int registerCount;
	private int rinexCount;
	private int virtualCount;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getRegisterCount() {
		return registerCount;
	}
	public void setRegisterCount(int registerCount) {
		this.registerCount = registerCount;
	}
	public int getRinexCount() {
		return rinexCount;
	}
	public void setRinexCount(int rinexCount) {
		this.rinexCount = rinexCount;
	}
	public int getVirtualCount() {
		return virtualCount;
	}
	public void setVirtualCount(int virtualCount) {
		this.virtualCount = virtualCount;
	}
	
}
