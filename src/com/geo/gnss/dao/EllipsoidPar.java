package com.geo.gnss.dao;

public class EllipsoidPar {

	protected String name;
	protected double axis;
	protected double flatRate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAxis() {
		return axis;
	}
	public void setAxis(double axis) {
		this.axis = axis;
	}
	public double getFlatRate() {
		return flatRate;
	}
	public void setFlatRate(double flatRate) {
		this.flatRate = flatRate;
	}

	
}
