package com.geo.gnss.dao;

public class EmailDao {
    private String hostEmail;
    private String hostEmailPassword;
    private String hostEmailProtocol;
    private String userEmail;
    
    public EmailDao(String hostEmail, String hostEmailPassword, String hostEmailProtocol, String userEmail){
    	this.hostEmail = hostEmail;
    	this.hostEmailPassword = hostEmailPassword;
    	this.hostEmailProtocol = hostEmailProtocol;
    	this.userEmail = userEmail;
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
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
}
