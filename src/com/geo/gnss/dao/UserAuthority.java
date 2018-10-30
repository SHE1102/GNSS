package com.geo.gnss.dao;

public class UserAuthority {

	private boolean downloadRinex = false;
	private boolean downloadVirtual = false;
	private boolean solutionStatic = false;
	private boolean solutionDynamic = false;
	private boolean coordinateConvert = false;
	
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
	public boolean isSolutionStatic() {
		return solutionStatic;
	}
	public void setSolutionStatic(boolean solutionStatic) {
		this.solutionStatic = solutionStatic;
	}
	public boolean isSolutionDynamic() {
		return solutionDynamic;
	}
	public void setSolutionDynamic(boolean solutionDynamic) {
		this.solutionDynamic = solutionDynamic;
	}
	public boolean isCoordinateConvert() {
		return coordinateConvert;
	}
	public void setCoordinateConvert(boolean coordinateConvert) {
		this.coordinateConvert = coordinateConvert;
	}
	
	
}
