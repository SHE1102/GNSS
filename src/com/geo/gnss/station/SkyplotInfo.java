package com.geo.gnss.station;

public class SkyplotInfo {
	private int type;
	private String id;
	private String azimuth;
	private String elevation;
	private String snr0;
	private String snr1;
	private String snr2;
	
    public int getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public String getAzimuth() {
		return azimuth;
	}

	public String getElevation() {
		return elevation;
	}

    public String getSnr0() {
		return snr0;
	}

	public String getSnr1() {
		return snr1;
	}

	public String getSnr2() {
		return snr2;
	}

	public String parseSkyplot(String content) {
    	String[] item = content.split(",");
    	id = item[0];
    	azimuth = item[1];
    	elevation = item[2];
    	snr0 = item[3];
    	snr1 = item[4];
    	snr2 = item[5];
    	
    	char s = id.charAt(0);
    	
    	switch (s) {
		case 'G':
			type = 0;//GPS
			break;
		case 'R':
			type = 1;//GLONASS
			break;
		case 'C':
			type = 2;//BEIDOU
			break;
		case 'E':
			type = 3;//GALILEO
			break;
		default:
			type = 4;
			break;
		}
    	
    	return String.valueOf(s);
    }
}
