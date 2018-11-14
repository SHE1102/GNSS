package com.geo.gnss.dao;

public class UserAuthority {

	private String name;
	private boolean downloadRinex = false;
	private boolean downloadVirtual = false;
	private boolean solution = false;
	private boolean additionalFeature = false;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDownloadRinex() {
		return downloadRinex;
	}
	public void setDownloadRinex(boolean downloadRinex) {
		this.downloadRinex = downloadRinex;
	}
	public boolean isDownloadVirtual() {
		return downloadVirtual;
	}
	public void setDownloadVirtual(boolean downloadVirtual) {
		this.downloadVirtual = downloadVirtual;
	}
	public boolean isSolution() {
		return solution;
	}
	public void setSolution(boolean solution) {
		this.solution = solution;
	}
	public boolean isAdditionalFeature() {
		return additionalFeature;
	}
	public void setAdditionalFeature(boolean additionalFeature) {
		this.additionalFeature = additionalFeature;
	}
	
	
}
