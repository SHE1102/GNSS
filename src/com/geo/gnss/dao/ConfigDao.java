package com.geo.gnss.dao;

public class ConfigDao {

	private String rawPath;
	private String hostEmail;
	private String hostEmailPassword;
	private String hostEmailProtocol;
	private double centerLat;
	private double centerLon;
	private int zoom;
	public String getRawPath() {
		return rawPath;
	}
	public void setRawPath(String rawPath) {
		this.rawPath = rawPath;
	}
	public String getHostEmail() {
		return hostEmail;
	}
	public void setHostEmail(String hostEmail) {
		this.hostEmail = hostEmail;
	}
	public String getHostEmailPassword() {
		return hostEmailPassword;
	}
	public void setHostEmailPassword(String hostEmailPassword) {
		this.hostEmailPassword = hostEmailPassword;
	}
	public String getHostEmailProtocol() {
		return hostEmailProtocol;
	}
	public void setHostEmailProtocol(String hostEmailProtocol) {
		this.hostEmailProtocol = hostEmailProtocol;
	}
	public double getCenterLat() {
		return centerLat;
	}
	public void setCenterLat(double centerLat) {
		this.centerLat = centerLat;
	}
	public double getCenterLon() {
		return centerLon;
	}
	public void setCenterLon(double centerLon) {
		this.centerLon = centerLon;
	}
	public int getZoom() {
		return zoom;
	}
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

}
