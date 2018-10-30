package com.geo.gnss.dao;

public class VirtualRinexDao {
    private String latitude;
    private String longitude;
    private String altitude;
    private int coordinateFormat;
    private String date;
    private int zone;
    private String startTime;
    private String endTime;
    
    private int rinexVersion;
    private boolean mixture;
    private int frenquencyPoint;
    private int timeInterval;
    
    private boolean satelliteSystem_GPS;
    private boolean satelliteSystem_GLO; 
    private boolean satelliteSystem_BeiDou;
    private boolean satelliteSystem_Galileo;
    private boolean satelliteSystem_QZSS;
    private boolean satelliteSystem_SBAS;
    
	public VirtualRinexDao() {
		mixture = false;
		satelliteSystem_GPS = satelliteSystem_GLO = satelliteSystem_BeiDou = false;
	    satelliteSystem_Galileo = satelliteSystem_QZSS = satelliteSystem_SBAS = false;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public int getCoordinateFormat() {
		return coordinateFormat;
	}

	public void setCoordinateFormat(int coordinateFormat) {
		this.coordinateFormat = coordinateFormat;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		this.zone = zone;
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

	public int getRinexVersion() {
		return rinexVersion;
	}

	public void setRinexVersion(int rinexVersion) {
		this.rinexVersion = rinexVersion;
	}

	public boolean isMixture() {
		return mixture;
	}

	public void setMixture(boolean mixture) {
		this.mixture = mixture;
	}

	public int getFrenquencyPoint() {
		return frenquencyPoint;
	}

	public void setFrenquencyPoint(int frenquencyPoint) {
		this.frenquencyPoint = frenquencyPoint;
	}

	public int getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(int timeInterval) {
		this.timeInterval = timeInterval;
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
	
	

}
