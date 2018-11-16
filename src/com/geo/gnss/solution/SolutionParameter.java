package com.geo.gnss.solution;

import java.util.Arrays;
import java.util.List;

public class SolutionParameter {
	private int solutionType;
    private String date;
    private String startTime;
    private String endTime;
    private String folderName;
    private String uploadFilePath = "";
    private List<String> baseStationList = null;
	private List<String> roverStationList = null;
    
	public int getSolutionType() {
		return solutionType;
	}
	public void setSolutionType(int solutionType) {
		this.solutionType = solutionType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public void setStationString(String stationString) {
		parseStation(stationString);
	}
	public List<String> getBaseStationList() {
		return baseStationList;
	}
	public void setBaseStationList(List<String> baseStationList) {
		this.baseStationList = baseStationList;
	}
	public List<String> getRoverStationList() {
		return roverStationList;
	}
	public void setRoverStationList(List<String> roverStationList) {
		this.roverStationList = roverStationList;
	}
	public String getUploadFilePath() {
		return uploadFilePath;
	}
	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}
	//1000:22KM:?55KM:5000:
	public void parseStation(String stationList) {
		String baseString= "", roverString = "";

		if(stationList.indexOf("?") == -1){
			baseString = stationList;
		} else {
			baseString = stationList.substring(0,stationList.indexOf("?"));
			roverString = stationList.substring(stationList.indexOf("?") + 1);
		}
		
		if(!baseString.isEmpty()){
			baseStationList = Arrays.asList(baseString.split(":"));
		}
		
		if(!roverString.isEmpty()){
			roverStationList = Arrays.asList(roverString.split(":"));
		}
	}
    
    
}
