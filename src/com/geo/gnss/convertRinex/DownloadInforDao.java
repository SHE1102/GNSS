package com.geo.gnss.convertRinex;

import java.util.Date;

public class DownloadInforDao {
    private int type = 0;
    private String user = "";
    private String dataDate = "";
    private String startTime = "";
    private String endTime = "";
    private String stationName = "";
    private String rinexVersion = "";
    
    private Date currentDate = new Date();
    
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDataDate() {
		return dataDate;
	}
	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getRinexVersion() {
		return rinexVersion;
	}
	public void setRinexVersion(String rinexVersion) {
		this.rinexVersion = "0".equals(rinexVersion) ? "2.10" : "3.02";
	}
	public Date getCurrentDate() {
		return currentDate;
	}
    
}
