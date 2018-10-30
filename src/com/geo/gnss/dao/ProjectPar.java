package com.geo.gnss.dao;

public class ProjectPar {

	protected int type;
	protected double centralMeridian;
	protected double tx;
	protected double ty;
	protected double tk;
	protected double projectionHeight;
	protected double referenceLatitude;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getCentralMeridian() {
		return centralMeridian;
	}
	public void setCentralMeridian(double centralMeridian) {
		this.centralMeridian = centralMeridian;
	}
	public double getTx() {
		return tx;
	}
	public void setTx(double tx) {
		this.tx = tx;
	}
	public double getTy() {
		return ty;
	}
	public void setTy(double ty) {
		this.ty = ty;
	}
	public double getTk() {
		return tk;
	}
	public void setTk(double tk) {
		this.tk = tk;
	}
	public double getProjectionHeight() {
		return projectionHeight;
	}
	public void setProjectionHeight(double projectionHeight) {
		this.projectionHeight = projectionHeight;
	}
	public double getReferenceLatitude() {
		return referenceLatitude;
	}
	public void setReferenceLatitude(double referenceLatitude) {
		this.referenceLatitude = referenceLatitude;
	}
	
	
}
