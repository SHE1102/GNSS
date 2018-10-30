package com.geo.gnss.dao;

public class FileAttrDao {
	private String name;
	private int type;//0:dir 1:File  2:uplevel dir
	private String size;
	private String lastModifyTime;
	private String parentPath;
	private String relativePath;
	
	private String startDateTime;
	private String endDateTime;
	private String antennaHeight;
	private String date;

	public  void setName(String name) {
		this.name = name;
	}

	public  String getName() {
		return name;
	}
	
	public  void setType(int type) {
		this.type = type;
	}
	
	public  int getType() {
		return type;
	}
	
	public  void setSize(String size) {
		this.size = size;
	}
	
	public  String getSize() {
		return size;
	}
	
	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
	public String getLastModifyTime() {
		return lastModifyTime;
	}
	
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getRelativePath() {
		return relativePath;
	}
	
	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}
	
	public String getParentPath() {
		return parentPath;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getAntennaHeight() {
		return antennaHeight;
	}

	public void setAntennaHeight(String antennaHeight) {
		this.antennaHeight = antennaHeight;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
		
}
