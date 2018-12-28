package com.geo.gnss.statistic;

import java.util.Date;

public class CorsDao {

	private int userType;
	private String userName;
	private Date logDay;
	private String addrs;
	private String position;
	private int onlineTime;
	
	private double latitude;
	private double longitude;
	private double altitude;
	
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getLogDay() {
		return logDay;
	}
	public void setLogDay(Date logDay) {
		this.logDay = logDay;
	}
	public String getAddrs() {
		return addrs;
	}
	public void setAddrs(String addrs) {
		this.addrs = addrs;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
		praseString();
	}
	public int getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(int onlineTime) {
		this.onlineTime = onlineTime;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public double getAltitude() {
		return altitude;
	}
	private void praseString(){
		if(position == null || position.equals("")){
			latitude = longitude = altitude = 0.0;
			return;
		}
		
		String[] strings = position.split("/");
		latitude = Double.parseDouble(strings[0]);
		longitude = Double.parseDouble(strings[1]);
		altitude = Double.parseDouble(strings[2]);
	}
	
}
