package com.geo.gnss.dao;

public class VerticalPar {
	protected boolean use;
	protected double orgx;
	protected double orgy;
	protected double northSlope;
	protected double eastSlope;
	public boolean isUse() {
		return use;
	}
	public void setUse(boolean use) {
		this.use = use;
	}
	public double getOrgx() {
		return orgx;
	}
	public void setOrgx(double orgx) {
		this.orgx = orgx;
	}
	public double getOrgy() {
		return orgy;
	}
	public void setOrgy(double orgy) {
		this.orgy = orgy;
	}
	public double getNorthSlope() {
		return northSlope;
	}
	public void setNorthSlope(double northSlope) {
		this.northSlope = northSlope;
	}
	public double getEastSlope() {
		return eastSlope;
	}
	public void setEastSlope(double eastSlope) {
		this.eastSlope = eastSlope;
	}
	
}
