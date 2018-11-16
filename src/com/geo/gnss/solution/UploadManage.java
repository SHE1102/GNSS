package com.geo.gnss.solution;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.geo.gnss.dao.AppConfig;
import com.geo.gnss.jna.DllInterface.ToRinexDLL64;
import com.geo.gnss.station.Station;
import com.geo.gnss.station.StationManage;
import com.geo.gnss.util.StationSort;

public class UploadManage {
	
	private double b, l;
	private AppConfig appConfig;
	private List<Station> stationList = null;
	private SolutionParameter solutionParameter;

	public UploadManage(SolutionParameter solutionParameter, AppConfig appConfig) {
		this.solutionParameter = solutionParameter;
		this.appConfig = appConfig;
		
		getStations();
	}

	private void getStations(){
		StationManage stationManage = new StationManage(appConfig.getRawPath(), appConfig.getAppPath());
		try {
		    stationManage.read(); 
		    stationList = stationManage.getWorkStationList(); 
		} catch (Exception e) {
		  //e.printStackTrace(); 
	    }
	}

	public SolutionParameter getSolutionParameter() {
		return solutionParameter;
	}

	public void parseDatFile(String filePath) {
		File uploadFile = new File(filePath);
		ToRinexDLL64.instance.ParseDataToRinexFormat_HTML(filePath, uploadFile.getParent(), 16782887, null);
		
		File[] files = uploadFile.getParentFile().listFiles();
		for(File fileTem : files){
			if(fileTem.getName().endsWith("O")){
				filePath = fileTem.getAbsolutePath();
				break;
			}
		}
		
		parseOFile(filePath);
	}
	
	public void parseOFile(String filePath) {
		double coorX = 0.0, coorY = 0.0, coorZ = 0.0;
		try {
			LineNumberReader lr = new LineNumberReader(new FileReader(filePath));

			String line = "";
			while ((line = lr.readLine()) != null) {
				if (line.contains(" APPROX POSITION XYZ")) {
					line = line.substring(0, line.indexOf(" APPROX POSITION XYZ"));
					line = line.trim();
					String[] coorArray = line.split("  ");
					coorX = Double.parseDouble(coorArray[0]);
					coorY = Double.parseDouble(coorArray[1]);
					coorZ = Double.parseDouble(coorArray[2]);
					//System.out.println("Dynamic:x=" + coorX + " y=" + coorY + " z=" + coorZ);
				} else if (line.contains("TIME OF FIRST OBS")) {
					line = line.replaceAll("\\s+", " ");
					line = line.substring(0, line.indexOf("GPS TIME OF FIRST OBS"));
					line = line.trim();
					String[] startTimeArray = line.split(" ");
					String startTime = String.format("%s-%s-%s %s:%s", startTimeArray[0], startTimeArray[1],
							startTimeArray[2], startTimeArray[3], startTimeArray[4]);
					//System.out.println("startTime:" + startTime);
					String[] timeArray = startTime.split(" ");
					solutionParameter.setDate(timeArray[0]);
					solutionParameter.setStartTime(timeArray[1]);
				} else if (line.contains("TIME OF LAST OBS")) {
					line = line.replaceAll("\\s+", " ");
					line = line.substring(0, line.indexOf("GPS TIME OF LAST OBS"));
					line = line.trim();
					String[] endTimeArray = line.split(" ");
					String endTime = String.format("%s-%s-%s %s:%s", endTimeArray[0], endTimeArray[1],
							endTimeArray[2], endTimeArray[3], endTimeArray[4]);
					//System.out.println("endTime:" + endTime);
					String[] timeArray = endTime.split(" ");
					solutionParameter.setEndTime(timeArray[1]);
					break;
				}
			}
			lr.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}
		
		XYZToBLH(coorX, coorY, coorZ);
		//distance
		for (Station station : stationList) {
			station.calDistanceByBLH(b, l);
		}

		getList();
	}

	/**
	 * 将所有站点按距离排序;
	 * 去掉上传文件本身的站点
	 * 静态解算取最近的4个站点
	 * 动态解算取最近的一个站点作为移动站
	 */
	private void getList() {
		List<Station> stationListTemp = new ArrayList<>();
		if(stationList == null || stationList.size() == 0){
			return;
		}
		for(Station station : stationList){
			String path = getStationPath(station.getId());
			if(!new File(path).exists()){
				continue;
			}
			stationListTemp.add(station);
		}
		stationList.clear();
		stationList.addAll(stationListTemp);
		
		Collections.sort(stationList, new StationSort());
		
		//去掉上传文件本身的站点
		if(stationList.get(0).getTargetDistance() <= 1E-4){
			stationList.remove(0);
		}

		if(stationList == null || stationList.size() == 0){
			return;
		}
		
		if (solutionParameter.getSolutionType() == 0) {
			List<String> baseStationList = new ArrayList<>();
			int size = stationList.size();
			size = size > 4 ? 4 : size;

			for (int i = 0; i < size; i++) {
				baseStationList.add(stationList.get(i).getId());
			}
			solutionParameter.setBaseStationList(baseStationList);
		} else {
			List<String> roverStationList = new ArrayList<>();
			roverStationList.add(stationList.get(0).getId());
			solutionParameter.setRoverStationList(roverStationList);
		}
	}
	
	private String getStationPath(String stationName){
    	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Calendar calendar = Calendar.getInstance();
    	try {
			calendar.setTime(sDateFormat.parse(solutionParameter.getDate()));
		} catch (ParseException e) {
			//e.printStackTrace();
		}
    	
    	int year = calendar.get(Calendar.YEAR);
    	int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(appConfig.getRawPath());
    	sb.append(File.separator);
    	sb.append(year);
    	sb.append(File.separator);
    	sb.append(dayOfYear);
    	sb.append(File.separator);
    	sb.append(stationName);
    	
    	return sb.toString();
    }

	private void XYZToBLH(double SourceX, double SourceY, double SourceZ){
		double destinationB=0,destinationL=0;//,destinationH=0;
		double da = 6378137.0;
		double df = 298.257223563;

		double e2;
		double A,F;
		double N;
		F=1.0/df;
		A=da;
		e2 = 2 * F - F * F;
		double dl=Math.atan2(SourceY, SourceX);
		destinationL=dl;

		double b1,b2,h1,h2;
		h1=Math.sqrt(Math.pow(SourceX,2)+Math.pow(SourceY,2)+Math.pow(SourceZ,2))-A;
		b1=Math.atan2((SourceZ/Math.sqrt(SourceX*SourceX+SourceY*SourceY)),(1.-e2*A/(A+h1)));
		
		if (Math.abs(SourceZ) < 1E-4)
		{
			destinationB = 0;
			destinationL = 0;
			//destinationH = 0;
			return;
		}

		do
		{
			N=A/(Math.sqrt(1.-e2*Math.sin(b1)*Math.sin(b1)));
			h2=h1;b2=b1;
			h1=SourceZ/Math.sin(b1)-N*(1-e2);
			b1=Math.atan2((SourceZ/Math.sqrt(SourceX*SourceX+SourceY*SourceY)),(1.-e2*N/(N+h1)));

		}while(Math.abs(b2-b1)>Math.pow(10.0,-11)||Math.abs(h2-h1)>Math.pow(10.0,-5));

		destinationB=b1;
		N=A/(Math.sqrt(1.-e2*Math.sin(b1)*Math.sin(b1)));
		//destinationH=SourceZ/Math.sin(b1)-N*(1-e2);

		destinationB = destinationB*180.0/Math.PI;
		destinationL = destinationL*180.0/Math.PI;
		
		b = destinationB;
		l = destinationL;
	}

}
