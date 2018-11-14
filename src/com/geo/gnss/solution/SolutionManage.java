package com.geo.gnss.solution;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.geo.gnss.dao.AppConfig;
import com.geo.gnss.jna.DllInterface.MergeDLL64;
import com.geo.gnss.jna.DllInterface.SplitDLL64;

public class SolutionManage {
    private List<String> baseStationList = null;
    private List<String> roverStationList = null;
    
    private List<String> baseFileList = null;
    private List<String> roverFileList = null;
    
    
	public SolutionManage(String rawPath, String appPath, String sessionId) {
	}
	
	private AppConfig appConfig;
	private SolutionParameter solutionParameter;
	public SolutionManage(AppConfig appConfig, SolutionParameter solutionParameter){
		this.appConfig = appConfig;
		this.solutionParameter = solutionParameter;
		
		parseStation(solutionParameter.getStationString());
	}

	public boolean parseStatic(){
		String folderPath = appConfig.getAppPath() + File.separator + "SolutionStatic" + File.separator + solutionParameter.getFolderName();
		File folderFile = new File(folderPath);
		folderFile.mkdirs();
		
		if(baseStationList == null || baseStationList.size() == 0){
			return false;
		}
		
		baseFileList = parse(baseStationList, folderPath);
		return true;
	}
	
	public boolean parseDynamic(){
		String folderPath = appConfig.getAppPath() + File.separator + "SolutionDynamic" + File.separator + solutionParameter.getFolderName();
		File folderFile = new File(folderPath);
		folderFile.mkdirs();
		
		if(baseStationList == null || baseStationList.size() == 0 
				||roverStationList == null || roverStationList.size() == 0){
			return false;
		}
		
		baseFileList = parse(baseStationList, folderPath);
		roverFileList = parse(roverStationList, folderPath);
		
		return true;
	}
	
	public void parseStation(String stationList) {
		String baseString= "", roverString = "";

		int n = stationList.indexOf("rover");
		if(n != -1){
			baseString = stationList.substring(0, n);
			roverString = stationList.substring(n+1);
			
			baseString = baseString.substring(baseString.indexOf(":")+1);
			roverString = roverString.substring(roverString.indexOf(":")+1);
			
			baseStationList = Arrays.asList(baseString.split(":"));
			roverStationList = Arrays.asList(roverString.split(":"));
		}  else {
			baseString = stationList;
			baseString = baseString.substring(baseString.indexOf(":")+1);
			
			baseStationList = Arrays.asList(baseString.split(":"));
		}
	}
	
	public List<String> parse(List<String> stationList, String folderPath){
		List<String> fileListTemp = new ArrayList<>();
		for(String stationName : stationList){
			String stationPath = getStationPath(stationName);
			List<String> srcFileList = getTimeRangFiles(stationPath);
			
			if(srcFileList == null || srcFileList.size() <= 0){
    			continue;
    		}
			
			StringBuilder sb = new StringBuilder();
			for(String srcPath : srcFileList){
				File srcFile = new File(srcPath);
				String destFilePath = folderPath + File.separator + srcFile.getName();
				File destFile = new File(destFilePath);
				
				try {
					Files.copy(srcFile.toPath(), destFile.toPath());
				} catch (IOException e) {
					//e.printStackTrace();
				}
				
				sb.append(destFilePath);
	    		sb.append("?");
				//destFileList.add(destFilePath);
			}
	    	
	    	String mergeFilePath = folderPath + File.separator + stationName + "_merge.dat";//getSaveFilePath(station, true, destPath);
	    	boolean res = MergeDLL64.instance.MergeRawFile(sb.toString(), mergeFilePath);
			System.out.println("Merge finish!Result:" + res);
			
			String splitStartTime = getSplitTime(solutionParameter.getDate(), solutionParameter.getStartTime());
	    	String splitEndTime = getSplitTime(solutionParameter.getDate(), solutionParameter.getEndTime());
	    	
	    	String saveFilePath = getSaveFilePath(folderPath, stationName);
	    	res = SplitDLL64.instance.Split(mergeFilePath, saveFilePath, splitStartTime, splitEndTime);
			System.out.println("Split finish!Result:" + res);
			
			fileListTemp.add(saveFilePath);
		}
		return fileListTemp;
	}
	
    private String getSplitTime(String date, String time){
		StringBuilder sb = new StringBuilder();
		sb.append(date);
		sb.append(" ");
		sb.append(time);
		sb.append(":00.01");
		return sb.toString();
	}
    
    private List<String> getTimeRangFiles(String path) {
		File stationPathDir = new File(path);
		File[] fileArray = stationPathDir.listFiles();
		if(fileArray == null || fileArray.length == 0){
			return null;
		}
		
		List<String> list = new ArrayList<>();
		for(File file : fileArray){
		    if(file.isDirectory()){
		    	continue;
		    }
		    
		    String name = file.getName();
			String ext = name.substring(name.lastIndexOf(".")+1).toUpperCase();
			
		    if("DAT".equals(ext)){
		    	if(parseHead(file)){
		    		list.add(file.getAbsolutePath());
		    	}
		    }
		}
		
		return list;
	}
    
    private boolean parseHead(File file){
    	byte[] data = new byte[1000];
 		
 		try {
 			FileInputStream inputStream = new FileInputStream(file);
 			inputStream.read(data, 0, 0x200);
 			inputStream.close();
 		} catch (Exception e) {
 		}
 		
 		boolean res = false;
 		if(data[0]==0x4E && data[1]==0x47 && data[2]==0x53 && data[3]==0x2D
 				&& data[4]==0x47 && data[5]==0x50 && data[6]==0x53 && data[7]==0x20){
 			res = readHead(data);
 		}
 		
 		return res;
    }
    
    private boolean readHead(byte[] bt) {
        String ss = "";
		
		byte[] destTime = new byte[8];
		System.arraycopy(bt, 107, destTime, 0, 8);
		ss = new String(destTime);
		int startHour = Integer.parseInt(ss.substring(0,ss.indexOf(":")));
		
		System.arraycopy(bt, 115, destTime, 0, 8);
		ss = new String(destTime);
		if(ss.indexOf(":") == -1){
			return false;
		}
		int endHour = Integer.parseInt(ss.substring(0,ss.indexOf(":")));
		
		boolean res = false;
		int setStartHour=0,setEndHour=0;
		String setStartHourString = solutionParameter.getStartTime();
		String setEndHourString = solutionParameter.getEndTime();
		setStartHour = Integer.parseInt(setStartHourString.substring(0, setStartHourString.indexOf(":")));
		setEndHour = Integer.parseInt(setEndHourString.substring(0, setEndHourString.indexOf(":")));
		
		int setEndHourMin = Integer.parseInt(setEndHourString.substring(setEndHourString.indexOf(":")+1));
		
		if(setEndHourMin > 0 && setEndHourMin <= 59){
			setEndHour += 1;
		}
		
		if(setEndHour == 0 && startHour >= setStartHour){
			res = true;
		} else if(endHour == 0){
			if(startHour >= setStartHour && endHour <= setEndHour && startHour < endHour){
				res = true;
			}
		} else {
			if(startHour >= setStartHour && endHour <= setEndHour){
				res = true;
			}
		}
		
		return res;
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
    
	private String getSaveFilePath(String destPath, String stationName){
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Calendar calendar = Calendar.getInstance();
    	try {
			calendar.setTime(sDateFormat.parse(solutionParameter.getDate()));
		} catch (ParseException e) {
			//e.printStackTrace();
		}
    	
    	int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
    	
    	String startTime = solutionParameter.getStartTime();
    	String endTime = solutionParameter.getEndTime();
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(destPath);
    	sb.append(File.separator);
    	sb.append(stationName);
    	sb.append(dayOfYear);
    	sb.append(startTime.substring(0, startTime.indexOf(":")));
    	sb.append(endTime.substring(0, endTime.indexOf(":")));
    	sb.append(".dat");
    	
    	return sb.toString();
	}

	public List<String> getBaseFileList() {
		return baseFileList;
	}

	public List<String> getRoverFileList() {
		return roverFileList;
	}
	
    
}
