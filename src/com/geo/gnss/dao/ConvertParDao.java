package com.geo.gnss.dao;

public class ConvertParDao {
    private int rinexVersion;
    private int timeInterval;
    private boolean mixTure;
    private boolean outPutPar1; 
    private boolean outPutPar2; 
    private boolean outPutPar3; 
    private boolean outPutPar4; 
    private boolean satelliteSystem_GPS;
    private boolean satelliteSystem_GLO; 
    private boolean satelliteSystem_BeiDou;
    private boolean satelliteSystem_Galileo;
    private boolean satelliteSystem_QZSS;
    private boolean satelliteSystem_SBAS;
    private int frequencyPoint;
    private String date;
    private String stationName;
    private String startTime;
    private String endTime;
    private int zone;
    
	public int getRinexVersion() {
		return rinexVersion;
	}
	public void setRinexVersion(int rinexVersion) {
		this.rinexVersion = rinexVersion;
	}
	public int getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(int timeInterval) {
		this.timeInterval = timeInterval;
	}
	public boolean isMixTure() {
		return mixTure;
	}
	public void setMixTure(boolean mixTure) {
		this.mixTure = mixTure;
	}
	public boolean isOutPutPar1() {
		return outPutPar1;
	}
	public void setOutPutPar1(boolean outPutPar1) {
		this.outPutPar1 = outPutPar1;
	}
	public boolean isOutPutPar2() {
		return outPutPar2;
	}
	public void setOutPutPar2(boolean outPutPar2) {
		this.outPutPar2 = outPutPar2;
	}
	public boolean isOutPutPar3() {
		return outPutPar3;
	}
	public void setOutPutPar3(boolean outPutPar3) {
		this.outPutPar3 = outPutPar3;
	}
	public boolean isOutPutPar4() {
		return outPutPar4;
	}
	public void setOutPutPar4(boolean outPutPar4) {
		this.outPutPar4 = outPutPar4;
	}
	public boolean isSatelliteSystem_GPS() {
		return satelliteSystem_GPS;
	}
	public void setSatelliteSystem_GPS(boolean satelliteSystem_GPS) {
		this.satelliteSystem_GPS = satelliteSystem_GPS;
	}
	public boolean isSatelliteSystem_GLO() {
		return satelliteSystem_GLO;
	}
	public void setSatelliteSystem_GLO(boolean satelliteSystem_GLO) {
		this.satelliteSystem_GLO = satelliteSystem_GLO;
	}
	public boolean isSatelliteSystem_BeiDou() {
		return satelliteSystem_BeiDou;
	}
	public void setSatelliteSystem_BeiDou(boolean satelliteSystem_BeiDou) {
		this.satelliteSystem_BeiDou = satelliteSystem_BeiDou;
	}
	public boolean isSatelliteSystem_Galileo() {
		return satelliteSystem_Galileo;
	}
	public void setSatelliteSystem_Galileo(boolean satelliteSystem_Galileo) {
		this.satelliteSystem_Galileo = satelliteSystem_Galileo;
	}
	public boolean isSatelliteSystem_QZSS() {
		return satelliteSystem_QZSS;
	}
	public void setSatelliteSystem_QZSS(boolean satelliteSystem_QZSS) {
		this.satelliteSystem_QZSS = satelliteSystem_QZSS;
	}
	public boolean isSatelliteSystem_SBAS() {
		return satelliteSystem_SBAS;
	}
	public void setSatelliteSystem_SBAS(boolean satelliteSystem_SBAS) {
		this.satelliteSystem_SBAS = satelliteSystem_SBAS;
	}
	public int getFrequencyPoint() {
		return frequencyPoint;
	}
	public void setFrequencyPoint(int frequencyPoint) {
		this.frequencyPoint = frequencyPoint;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
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
	public int getZone() {
		return zone;
	}
	public void setZone(int zone) {
		this.zone = zone;
	}
     
}
