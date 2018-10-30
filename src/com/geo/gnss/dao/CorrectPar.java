package com.geo.gnss.dao;

public class CorrectPar {
	protected boolean use;
	protected double dx;
	protected double dy;
	protected double dh;
	
	public boolean isUse() {
		return use;
	}
	public void setUse(boolean use) {
		this.use = use;
	}
	public double getDx() {
		return dx;
	}
	public void setDx(double dx) {
		this.dx = dx;
	}
	public double getDy() {
		return dy;
	}
	public void setDy(double dy) {
		this.dy = dy;
	}
	public double getDh() {
		return dh;
	}
	public void setDh(double dh) {
		this.dh = dh;
	}
	
}
